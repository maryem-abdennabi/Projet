/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author HP
 */

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class FXMLEnvoyerPassWithPhoneController implements Initializable {
 public static final String ACCOUNT_SID = "ACf51516e23e780017cb89656157edd25c";
    public static final String AUTH_TOKEN ="b29cc33df9f62ca7b1ea396a006a9362";

   @FXML
    private Button bot1;
    @FXML
    private TextField text;
    @FXML
    private Text txt;
    int nomb;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          text.setText("WELCOME "+FXMLRecuperPassController.Nom+" !");
          nomb=FXMLRecuperPassController.Number;
    }    

    @FXML
    private void send(ActionEvent event) throws Exception  {
        System.out.println(""+FXMLRecuperPassController.pass);
   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+216"+FXMLRecuperPassController.Number),
                new com.twilio.type.PhoneNumber("+12673135103"),
                "Sir Your code is "+FXMLRecuperPassController.code).create();

        System.out.println(message.getSid());
  FXMLRecuperPassController.Number=0;
  FXMLRecuperPassController.Nom=null;
  FXMLRecuperPassController.pass=null;
   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                           stage.show();
                           Stage stage1 = (Stage) bot1.getScene().getWindow(); 
                           stage1.close();
    }

    

    @FXML
    private void retour(MouseEvent event) {
  try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRecuperPass.fxml"));
                           Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                          stage.show();
                           Stage stage1 = (Stage) txt.getScene().getWindow(); 
                           stage1.close();
                           } catch (IOException ex) {
                              showAlertWithHeaderText("page not found");
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
