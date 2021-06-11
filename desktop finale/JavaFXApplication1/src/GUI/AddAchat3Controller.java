/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Achat;
import Entity.ProductAchat;
import Service.AchatService;
import Service.ProductAchatService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ibrahim
 */
public class AddAchat3Controller implements Initializable {
     @FXML
     JFXListView<ProductAchat> listView;
     
    
     
     @FXML
     JFXButton confirmation;
     
     @FXML
     JFXButton retour;
     
     @FXML
     public void handleToAchat(ActionEvent e){
                 Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddAchat2.fxml"));
            AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
     }
     
     @FXML
     AnchorPane AnchorPane;
     
     @FXML
     public void handleAdd(ActionEvent e){
         AchatService achatService=new AchatService();
         achatService.updateEtat(4,AddAchatController.achat.getId_achat());
         Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 String username = "kalboussihazem67@gmail.com";
 String password = "hazem2020";
Session session = Session.getInstance(props,new Authenticator() {
    @Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
 Message message = new MimeMessage(session);
    try {
        message.setFrom(new InternetAddress(username));
      //  message.setRecipient(Message.RecipientType.TO,new InternetAddress(FXMLRecuperPassController.email));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("brahimhm470@gmail.com"));
        message.setSubject("Achat confirmed");
        message.setText("l'achat est confirmer sous la reference "+AddAchatController.ref);
       Transport.send(message);
     
       // FXMLRecuperPassController.email=null;
       
//System.out.println("Message_envoye");  
    } catch (Exception ex) {
    }
                 Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("MainAchatFXML.fxml"));
            AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
         
     }
             Achat a=AddAchatController.achat;

             ProductAchatService productAchatService=new ProductAchatService();

    private List<ProductAchat> list=productAchatService.displayProductAchat(a.getId_achat());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        for(int i=0;i<list.size();i++){
            if(i%2 ==0){
                            listView.getItems().add(list.get(i));

            }
        }
         listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
            ProductAchatService productAchatService=new ProductAchatService();
             Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                            a.setContentText("Vous etes sur de supprimer " + listView.getSelectionModel().getSelectedItem().getProduct().getProduct_name() + " ?");
                            Optional<ButtonType> action = a.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                System.out.println(listView.getSelectionModel().getSelectedItem().getId_product_achat());
                                int i =listView.getSelectionModel().getSelectedIndex();
                                System.out.println(i);
                                list.remove((i*2)+1);
                                list.remove(listView.getSelectionModel().getSelectedItem());
                                productAchatService.deleteByIdProduct(listView.getSelectionModel().getSelectedItem().getProduct().getId_product(),AddAchatController.achat.getId_achat());
                                
                                
                                

Parent root;
                                  try {
            root = FXMLLoader.load(getClass().getResource("AddAchat2.fxml"));
            AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
                            }
            
        }
    });
        // TODO
    }    
    
}
