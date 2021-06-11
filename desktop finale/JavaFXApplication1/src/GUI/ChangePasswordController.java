/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.UserService;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private TextField txt1;
    @FXML
    private Button bot;
    @FXML
    private PasswordField txt2;
UserService us;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        us=new UserService();
    }    

    @FXML
    private void changer(ActionEvent event) {
        String pass=txt2.getText().toString();
    int v=Integer.parseInt(txt1.getText().toString());
    if(v==FXMLRecuperPassController.code)
       {
         if(pass.length()>=8)
        {  
         String bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(13,pass.toCharArray());
          if(us.updatepass(FXMLRecuperPassController.email, bcryptChars)==true)
          {
              FXMLRecuperPassController.email=null;
              FXMLRecuperPassController.code=0;
              JOptionPane.showMessageDialog(null,"your password was changed","         Succes" ,JOptionPane.PLAIN_MESSAGE );
              try {
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLIdentification.fxml"));
              Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                          stage.show();
                           Stage stage1 = (Stage) txt1.getScene().getWindow(); 
                           stage1.close();
                           } catch (IOException ex) {
                              showAlertWithHeaderText("page not found");
                            }
          }
        }
         else{
            showAlertWithHeaderText("the size of password should be greater then 8 character");
               }
    }
    else{
            showAlertWithHeaderText("invalid code");
               }
    
}
    private void showAlertWithHeaderText(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account");
        alert.setHeaderText("Results:");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
