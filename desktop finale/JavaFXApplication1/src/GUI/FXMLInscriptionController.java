/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Connexion.Cryptage;
import Connexion.image;
import Entity.User;
import Service.UserService;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLInscriptionController implements Initializable {

public static String usr_type;
public static int id;
public static String nom;
public static String prenom;
    ObservableList<String> list=FXCollections.observableArrayList("ROLE_ADMIN","ROLE_AGENTFINANCIER","ROLE_AGENTTRANSPORT","ROLE_CLIENT","ROLE_GESTIONNAIRE");
    /**
     * Initializes the controller class.
     */
    @FXML
     private ChoiceBox Check;
    @FXML
    private Button BottonIn;
    @FXML
    private TextField text1;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private PasswordField text5;
    @FXML
    private TextField text2;
    @FXML
    private TextField text6;
    @FXML
    private Text txt;
    @FXML
    private TextField text51;
 
     
     
 @Override
    public void initialize(URL url, ResourceBundle rb) {
     Check.setItems(list);       
    }    


    @FXML
    private void Inscrire(ActionEvent event) throws SQLException, ParseException {
    String F_name=text1.getText().toString();
    String L_name=text2.getText().toString();
    String mail=text3.getText().toString();
    String ch_Num=text4.getText().toString();
    String type=Check.getValue().toString();
    String photo=text6.getText().toString();
    String pass=text5.getText().toString();
    String phone=text51.getText().toString();
        //
   if(F_name.length()!=0 && L_name.length()!=0 && mail.length()!=0 && ch_Num.length()!=0 && 
           type.length()!=0 && photo.length()!=0 && pass.length()!=0)
   {
        if(pass.length()>=8)
        {
            if(phone.length()==8 && phone.matches("^[0-9]+$"))
            {
                if(isEmailAdress(L_name))
                {
               usr_type=type;
               nom=ch_Num;
               prenom=mail;
               int number=Integer.parseInt(text51.getText()); 
               boolean cc=false;
String bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(13,pass.toCharArray());
                UserService usrs=new UserService();
                if(type.equals("ROLE_CLIENT"))
                {
                 cc= usrs.insert(new User(F_name,F_name,L_name,L_name,1,bcryptChars,"a:1:{i:0;s:11:\"ROLE_CLIENT\";}",mail,ch_Num,number,photo));
                }
                if(type.equals("ROLE_ADMIN"))
                {
                 cc= usrs.insert(new User(F_name,F_name,L_name,L_name,1,bcryptChars,"a:1:{i:0;s:10:\"ROLE_ADMIN\";}",mail,ch_Num,number,photo));
                }
                if(type.equals("ROLE_GESTIONNAIRE"))
                {
                 cc= usrs.insert(new User(F_name,F_name,L_name,L_name,1,bcryptChars,"a:1:{i:0;s:17:\"ROLE_GESTIONNAIRE\";}",mail,ch_Num,number,photo));
                }
                if(type.equals("ROLE_AGENTTRANSPORT"))
                {
                 cc= usrs.insert(new User(F_name,F_name,L_name,L_name,1,bcryptChars,"a:1:{i:0;s:19:\"ROLE_AGENTTRANSPORT\";}",mail,ch_Num,number,photo));
                }
                if(type.equals("ROLE_AGENTFINANCIER"))
                {
                 cc= usrs.insert(new User(F_name,F_name,L_name,L_name,1,bcryptChars,"a:1:{i:0;s:19:\"ROLE_AGENTFINANCIER\";}",mail,ch_Num,number,photo));
                }
               if(cc==true)
               {
                   if(type!="ROLE_CLIENT")
                   {
                   try {
                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home2.fxml"));
                       Parent root1 = (Parent) fxmlLoader.load();
                       Stage stage = new Stage();
                       stage.initStyle(StageStyle.UNDECORATED);
                       stage.setScene(new Scene(root1));  
                       stage.show();
                       Stage stage1 = (Stage) BottonIn.getScene().getWindow(); 
                       stage1.close();
                       } catch (IOException ex) {
                       showAlertWithHeaderText("page not found"+ex);
                      }
               }
               else{
                   try {
                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clientfxml.fxml"));
                       Parent root1 = (Parent) fxmlLoader.load();
                       Stage stage = new Stage();
                       stage.initStyle(StageStyle.UNDECORATED);
                       stage.setScene(new Scene(root1));  
                       stage.show();
                       Stage stage1 = (Stage) BottonIn.getScene().getWindow(); 
                       stage1.close();
                       } catch (IOException ex) {
                       showAlertWithHeaderText("page not found"+ex);
                      }
                   
            }
            }
               else{
                   showAlertWithHeaderText("Error");
               }
            }
            else{
                showAlertWithHeaderText("invalid mail address");
            }
            }
            else{
                showAlertWithHeaderText("invalid phone number");
            }
        }
          else{
            showAlertWithHeaderText("the size of password should be greater then 8 character");
               }
    }
    else
    {
           showAlertWithHeaderText("all fields should be not null");
    }
    }
    
     private void showAlertWithHeaderText(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sign In");
        alert.setHeaderText("Results:");
        alert.setContentText(msg);
        alert.showAndWait();
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
                           } catch (IOException ex) {
                            showAlertWithHeaderText("page not found");
                            }
    }
public static boolean isEmailAdress(String email){
Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
Matcher m = p.matcher(email.toUpperCase());
return m.matches();
}
        @FXML
    private void ajouterIm(ActionEvent event) {
      image img=new image();
      img.filen();
      String pth=img.getp();
      if(pth==null)
      {
       showAlertWithHeaderText("path is null");
      }
else
{
    text6.setText(pth);
    }
    }

    @FXML
    private void capture(MouseEvent event) throws IOException {
        String nom=text1.getText().toString();
        Webcam webcam = Webcam.getDefault();
webcam.open();
ImageIO.write(webcam.getImage(), "PNG", new File("C:\\Users\\Ibrahim\\Desktop\\img\\"+nom+".png"));
webcam.close();
    }
}
