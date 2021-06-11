/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import Entity.Achat;
import Entity.Fournisseur;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import Service.AchatService;
import Service.FournisseurService;

/**
 * FXML Controller class
 *
 * @author Ibrahim
 */
public class AddAchatController implements Initializable {
    
    @FXML
    private ChoiceBox<String> fours; 
    @FXML
    private JFXButton addTo;
    @FXML
    private AnchorPane AnchorPane;
    public static String f;
    public static Achat a;
    public static Achat achat;

    public static String ref;
    
    @FXML
    public void handleAdd(ActionEvent event){
    f=fours.getValue();
    createAchat();
    
    Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddAchat2.fxml"));
            AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    public void createAchat(){
        AchatService achatService=new AchatService();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(date));
        int l=achatService.displayAll().size()+1;
       /* Achat achat=new Achat();
        achat.setEtat(3);
        achat.setClient_name(f);*/
        
        ref="four"+l;
       /* achat.setClient_type(ref);
        achat.setClient_address("bra16");
        achat.setDate(formatter.format(date));
        achat.setQuantite(0);*/
        a=new Achat(f, ref,"bra16",3, 3,formatter.format(date) );

        achatService.insert(a);
        Achat a1=achatService.findByAddress(ref);
        achat=a1;
        System.out.println(achat);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FournisseurService fournisseurService=new FournisseurService();
        for(Fournisseur f: fournisseurService.displayAll()){
          fours.getItems().add(f.getFirstname()+" "+f.getLastname());

        }
    }    
    
}
