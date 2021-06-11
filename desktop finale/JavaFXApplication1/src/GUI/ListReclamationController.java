/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Reclamation;
import Service.ProductService;
import Service.ReclamationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.io.IOException;

import java.net.URL;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ListReclamationController implements Initializable {
   
    ObservableList list=FXCollections.observableArrayList();
      @FXML
    private TableView<Reclamation> table;
  @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private Button delete;
     @FXML
    private TableColumn<Reclamation, String> User;
      @FXML
    private TableColumn<Reclamation, Integer> produit;
       @FXML
    private TableColumn<Reclamation, String> contenu;
        @FXML
    private TableColumn<Reclamation, String> etat;
         @FXML
    private TableColumn<Reclamation, Date> date;
    
     private ObservableList<Reclamation> rec = FXCollections.observableArrayList();
         ReclamationService recla=new ReclamationService();
          @FXML
    private ChoiceBox<?> chb;
     @FXML
    private Label nombreR;
    @FXML
    private Button Details;
    @FXML
    private Text txt;
    @FXML
    private Button rech;
    @FXML
    private Button bntr;
    @FXML
    private AnchorPane AnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 String s=FXMLIdentificationController.nom;
//User.setText(s);
        LoadData();
        chb.getSelectionModel().selectLast();
        
         List<Reclamation> listUser= new ArrayList<Reclamation>();
        ReclamationService daoU =  new ReclamationService();
        listUser = daoU.DisplayAll();
        try {
         
nombreR.setText(String.valueOf(daoU.count()));
        } catch (SQLException ex) {
            Logger.getLogger(ListReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rec.clear();
        rec.addAll(listUser);
        table.setItems(rec);
        
        id.setCellValueFactory(
            new PropertyValueFactory<>("id_reclamation")
        );
        User.setCellValueFactory(
            new PropertyValueFactory<>("username")
        );
        produit.setCellValueFactory(
            new PropertyValueFactory<>("id_product")
        );
         contenu.setCellValueFactory(
            new PropertyValueFactory<>("Contenu")
        );
             etat.setCellValueFactory(
            new PropertyValueFactory<>("etat")
        );
               date.setCellValueFactory(
            new PropertyValueFactory<>("Date_rec")
        );
        
       
     
       
    }
    
  @FXML

    private void display(ActionEvent event) throws ClassNotFoundException {
   
        
       
            Reclamation recc=(Reclamation) table.getSelectionModel().getSelectedItem();
//


if(recc.equals("NonTraiter")){
}
else{
    if(recla.UpdateRec(recc)){
        resetTableData();
        
    }else{    
        
    }
   
    
}
      





//String ACCOUNT_SID = "AC02f86af29ff3caf5c5b0fab5a5402c42";
//String AUTH_TOKEN = "72bca602aa940340d414e252fca2cfdd";
//
//
//
//TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
//List<NameValuePair> params = new ArrayList<NameValuePair>();
//params.add(new BasicNameValuePair("To", "+21690118795"));
//params.add(new BasicNameValuePair("From", "+16208786068"));
//params.add(new BasicNameValuePair("Body", "Votre reclamation est en cours de traitement"));
//
//MessageFactory messageFactory = client.getAccount().getMessageFactory();
//Message message;
//
//message = messageFactory.create(params);
//System.out.println(message.getSid());
//       
//       
        

        
    
    }
    

         
    

    @FXML
    private void delete(ActionEvent event) {
        Reclamation recc=(Reclamation) table.getSelectionModel().getSelectedItem();
        if(recc==null){
            
        }
        else{
            if(recla.SupprimerReclamation(recc.getId_reclamation())){
                resetTableData();
                
            }else{
                
            }
        }
        
        
        
    }
    
         public void resetTableData()
    {
        List<Reclamation> listUser = new ArrayList<>();
        ReclamationService daoAg = new ReclamationService();
        listUser = daoAg.DisplayAll();
        ObservableList<Reclamation> data = FXCollections.observableArrayList(listUser);
        table.setItems(data);
    }

//    @FXML
//    private void search(KeyEvent event) {
//        ObservableList list=FXCollections.observableArrayList();
//        FilteredList filtre=new FilteredList(list,e->true);
//        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
//			filtre.setPredicate(predicate< super list>)(list l)->{
//            
//        };
//        
//        
//        
//        
//  
//  
 private void LoadData(){ 
        list.removeAll();
        String a = "Traiter";
        String b = "NonTraiter";
        
        list.addAll(a,b);
        chb.getItems().addAll(list);
        }  
 @FXML
    private void recherche(ActionEvent event) {
        rec.clear();
        resetTableData();
        ReclamationService rs=new ReclamationService();
        List<Reclamation> rt=rs.DisplayAll();
        List<Reclamation> resultat=new ArrayList<>();
        int t=rt.size();
        int i=0;
        while(i<t)
        {
       if(chb.getValue().equals(rt.get(i).getEtat()))
       {
        
        resultat.add(rt.get(i));
        
      
       }
     
       i++;
      }
        rec.addAll(resultat);
        table.setItems(rec);
       
    }

    @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        
                        AnchorPane root =FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
                       AnchorPane.getChildren().setAll(root);
    }
    
}
              
    

                                
