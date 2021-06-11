/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Connexion.Cryptage;
import Service.UserService;
import java.io.IOException;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLIdentificationController implements Initializable{
public static String usr_type;
public static String nom;
public static String prenom;
public static String mail;
public static int id;

    @FXML
    private TextField txt1;
    @FXML
    private PasswordField txt2;
    @FXML
    private Button bott;
    @FXML
    private Text lab;
    @FXML
    private Text lab1;
    @FXML
    private Text txt;
UserService us;
Cryptage crypt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
us=new UserService();
    }    

    @FXML
    private void identification(ActionEvent event) throws SQLException {
    String email=txt1.getText().toString();
    String pass=txt2.getText().toString();
         ResultSet rs=us.display(email);
	if(rs.next()){
           
                boolean ok = BCrypt.checkpw(pass, rs.getString("password"));
                if(ok){
                JOptionPane.showMessageDialog(null,"            Connexion Etablie","message" ,JOptionPane.PLAIN_MESSAGE );
               usr_type=rs.getString("roles");
               nom=rs.getString("name");
               prenom=rs.getString("surname");               
               id=rs.getInt(1);
               mail=email;
                  if(usr_type.equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"))
                  {
                       try {
                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientHome.fxml"));
                       Parent root1 = (Parent) fxmlLoader.load();
                       Stage stage = new Stage();
                       stage.initStyle(StageStyle.UNDECORATED);
                       stage.setScene(new Scene(root1));  
                       stage.show();
                       Stage stage1 = (Stage) bott.getScene().getWindow(); 
                       stage1.close();
                       } catch (IOException ex) {
                       showAlertWithHeaderText("page not found "+ex);  
                       }
                      
                  }
                  else{
                    try {
                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home2.fxml"));
                       Parent root1 = (Parent) fxmlLoader.load();
                       Stage stage = new Stage();
                       stage.initStyle(StageStyle.UNDECORATED);
                       stage.setScene(new Scene(root1));  
                       stage.show();
                       Stage stage1 = (Stage) bott.getScene().getWindow(); 
                       stage1.close();
                       } catch (IOException ex) {
                       //showAlertWithHeaderText("page not found");     
            Logger.getLogger(FXMLIdentificationController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                  }
                }
            
            else{
                 showAlertWithHeaderText("invalid password");
            }
    }
        else{
            showAlertWithHeaderText("sir you didn't have an account, you should create it");
        }
} 
    
    @FXML
    private void clicker(MouseEvent event) {
            try {
                           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLInscription.fxml"));
                           Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                           stage.show();
                           Stage stage1 = (Stage) lab.getScene().getWindow(); 
                           stage1.close();
                           } catch (IOException ex) {
                                showAlertWithHeaderText("page not found");     
                            }
    }
     private void showAlertWithHeaderText(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Log In");
        alert.setHeaderText("Results:");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void forgot(MouseEvent event) {
          try {
                           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRecuperPass.fxml"));
                           Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                           stage.show();
                           Stage stage1 = (Stage) lab1.getScene().getWindow(); 
                           stage1.close();
                           } 
          catch (IOException ex) { 
             showAlertWithHeaderText("page not found");     
          }
        
    }

    @FXML
    private void retour(MouseEvent event) {
          try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                           Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                           stage.show();
                           Stage stage1 = (Stage) txt.getScene().getWindow(); 
                           stage1.close();
                           } 
          catch (IOException ex) {
              showAlertWithHeaderText("page not found");        
          }
    }    
}