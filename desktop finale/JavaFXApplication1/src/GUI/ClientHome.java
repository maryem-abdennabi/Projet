/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Priority;
import Entity.Product;
import Entity.Reclamation;
import Service.ProductService;
import Service.ReclamationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * FXML Controller class
 *
 * @author Ahmed
 */

import Entity.Product;
import Entity.Reclamation;
import Service.ProductService;
import Service.ReclamationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ClientHome implements Initializable {

  String ACCOUNT_SID = "AC02f86af29ff3caf5c5b0fab5a5402c42";
String AUTH_TOKEN = "72bca602aa940340d414e252fca2cfdd";


    
    @FXML
    private Button btn;
    @FXML
    private TextArea ic_contenu;
    private Connection cnx;
        private PreparedStatement pst;
        private ResultSet rs;
        private Statement st;
    @FXML
    private ComboBox<Product> chx;
        ReclamationService recs ;
    @FXML
    private Label client_name;
    
    private  ObservableList<Product> lst=FXCollections.observableArrayList();
    private  ObservableList<String> lst1=FXCollections.observableArrayList();
    @FXML
    private Button btn1;
    @FXML
    private Label client;
    @FXML
    private Text bottback;
    @FXML
    private Pane pane;
    @FXML
    private Button modif;
    @FXML
    private Button delete;
    @FXML
    private TableView<Reclamation> tablee;
  @FXML
    private TableColumn<Reclamation, String> Userr;
  @FXML
    private TableColumn<Reclamation, String> produitt;
  @FXML
    private TableColumn<Reclamation, String> contenuu;
  @FXML
    private TableColumn<Reclamation, Date> datee;
  @FXML
    private TableColumn<Reclamation, String> Usermail;
  @FXML
    private TableColumn<Reclamation, String> Etat;
  @FXML
    private TableColumn<Reclamation, Integer> Priority;
    @FXML
    private TextField md;
    @FXML
    private Text bottback1;
    @FXML
    private Pane pane2;
private ObservableList<Reclamation> rec = FXCollections.observableArrayList();
         ReclamationService recla=new ReclamationService();
    @FXML
    private ComboBox<String> prior;
    
    
    
    

    /**
     * Initializes the controller class.
     */
   @Override
       public void initialize(URL url, ResourceBundle rb) {
         String s=FXMLIdentificationController.nom;
    pane2.setVisible(false);
             client_name.setText(s);
             int v= FXMLIdentificationController.id;
             client.setText(String.valueOf(v));
        ProductService p = new ProductService();
             lst.addAll(p.displayAll());
                            chx.getItems().addAll(lst);
        ReclamationService recl = new ReclamationService();
             lst1.addAll(recl.DisplayP());
             prior.getItems().addAll(lst1);                    
             pane.setVisible(true);
             pane2.setVisible(false);
        // TODO
        
   
        tablee.setVisible(false);
        
       }    

//    public void initialize(URL url, ResourceBundle rb) {
////        client_name.setText("ahmed");
//      ObservableList<Integer> lst=FXCollections.observableArrayList();
//        ProductService p = new ProductService();
//                ResultSet rs= p.displayid();
//                 try {
//            while(rs.next())
//            {
//              lst.add(rs.getInt(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(User_ManagementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//                            chx.setItems( lst);
//
//        // TODO
//    }    

    @FXML
    private void ajouter(ActionEvent event) throws UnsupportedEncodingException, MalformedURLException, IOException, SQLException {
                    pane2.setVisible(false);
                    pane.setVisible(true);
  
           Reclamation R = new Reclamation();
           send();
            ProductService p=new ProductService();
            SimpleDateFormat   d= new SimpleDateFormat("yy-MM-dd");
            LocalDate dd = LocalDate.now();
            Date date = java.sql.Date.valueOf(dd);
            R.setDate_rec(date);
            R.setId_user( FXMLIdentificationController.id);
       //R.setId_user(40);
             R.setContenu(ic_contenu.getText());
            
             Product product=chx.getItems().get(chx.getSelectionModel().getSelectedIndex());
             System.out.println("product "+product);
            // Integer idp=Integer.parseInt(chx.getValue().toString());
           // Priority prio=prior.getItems().get(prior.getSelectionModel().getSelectedIndex());
            ReclamationService rc=new ReclamationService();
            String cc=prior.getValue().toString();
             R.setId_product(product.getId_product());
             R.setEtat("NonTraiter");
             R.setPriority(rc.idP(cc));
             R.setUsername(FXMLIdentificationController.nom);
             R.setMail(FXMLIdentificationController.mail);
             System.out.println(R);
             ReclamationService recs=new ReclamationService();
             recs.ajouteReclamation(R);
    }

   public void send(){
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21690118795"),
                new com.twilio.type.PhoneNumber("+16208786068"),
                "vous avez une nouvelle reclamation de la part de "+FXMLIdentificationController.nom).create();

        System.out.println(message.getSid());
  
    
}


    @FXML
    private void affiche(ActionEvent event) throws IOException {
        pane2.setVisible(true);
        pane.setVisible(false);
        tablee.setVisible(true);
    Reclamation re=new Reclamation();
         List<Reclamation> listUser= new ArrayList<Reclamation>();
        ReclamationService daoU =  new ReclamationService();
             
        listUser = daoU.DisplayAlll();
        rec.clear();
        rec.addAll(listUser);
        tablee.setItems(rec);
       
        Userr.setCellValueFactory(
            new PropertyValueFactory<>("username")
        );
        produitt.setCellValueFactory(
            new PropertyValueFactory<>("id_product")
        );
         contenuu.setCellValueFactory(
            new PropertyValueFactory<>("Contenu")
        );
          Usermail.setCellValueFactory(
            new PropertyValueFactory<>("mail")
        );  
        Priority.setCellValueFactory(
            new PropertyValueFactory<>("priority")
        );  
         Etat.setCellValueFactory(
            new PropertyValueFactory<>("etat")
        );
          datee.setCellValueFactory(
            new PropertyValueFactory<>("Date_rec")
        );
    }

    

    @FXML
    private void back(MouseEvent event) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                           Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                           stage.show();
                           Stage stage1 = (Stage) bottback.getScene().getWindow(); 
                           stage1.close();
                           } catch (IOException ex) {
                            showAlertWithHeaderText("page not found");
                            }
    }
private void showAlertWithHeaderText(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign In");
        alert.setHeaderText("Results:");
        alert.setContentText(msg);
        alert.showAndWait();
    }


    @FXML
    private void modifier(ActionEvent event) {
    int i=tablee.getSelectionModel().getSelectedIndex();
                   String t=md.getText();
                   Reclamation recc=tablee.getSelectionModel().getSelectedItem();
                   recc.setContenu(t);
                   rec.set(i, recc);
                   tablee.setItems(rec);
                   ReclamationService test=new ReclamationService();
                   System.out.println(recc.getId_reclamation());
                   test.ModifierUser2(recc);
                   md.setText("");
    }

    @FXML
    private void delete(ActionEvent event) {
    Reclamation recc=(Reclamation) tablee.getSelectionModel().getSelectedItem();
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
        tablee.setItems(data);
    }
    @FXML
    private void back2(MouseEvent event) {
   pane2.setVisible(false);
   pane.setVisible(true);
   tablee.setVisible(false);
    }
}

