package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Connexion.DataSource;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Add_EntrepotController implements Initializable {

    @FXML
    private TextField jaddress;
    @FXML
    private TextField jnbr;
    @FXML
    private TextField jphone;
    @FXML
    private TextField jphone_bis;
    @FXML
    private Button Add_btn;
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    @FXML
    private AnchorPane add_form;
    Stage stage1 = null;
    @FXML
    private Button reset;
    @FXML
    private Label nbr_test;
    @FXML
    private Label address_test;
    @FXML
    private Label phone_test;
    @FXML
    private Label phone_bis_test;
    @FXML
    private TextField jlati;
    @FXML
    private TextField jlag;
    @FXML
    private Label lat_test;
    @FXML
    private Label long_test;
    @FXML
    private Label retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Add_Entrepot(MouseEvent event) {

        String address = jaddress.getText();
        String nbr = jnbr.getText();
        String phone = jphone.getText();
        String phone_bis = jphone_bis.getText();
        String lapt=jlati.getText();
        String longit=jlag.getText();
        if ((!validateRangs(nbr))||(nbr.isEmpty()))  {
           Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Nbr must contain only float or integer values");
            a.setHeaderText("Invalid Value");
            a.show();
 }

        else if ((!validatePhoneNumber(phone))||(phone.isEmpty()))  {
           Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Phone must contain 8 numbers");
            a.setHeaderText("Invalid Value");
            a.show();
        }

        else if ((!validatePhoneNumber(phone_bis))||(phone_bis.isEmpty()))  {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Phone must contain 8 numbers");
            a.setHeaderText("Invalid Value");
            a.show();
 }
        else if (address.isEmpty()) {
            address_test.setText("Empty Value please check it! ");
        }
       else {

            try {
                this.cnx = DataSource.getInstance().getCon();
                PreparedStatement stmt = cnx.
                        prepareStatement("INSERT INTO entrepot (address,nbrRangs,phone,phone_bis,latitude,longitude)VALUES ('" + address + "','" + nbr + "','" + phone + "','" + phone_bis + "','"+lapt+"','"+longit+"')");

                stmt.executeUpdate();
                 Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Entrepot Added successfully");
            a.setHeaderText("Success");
            a.show();
                try {
            AnchorPane root =FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
                add_form.getChildren().setAll(root);
                   
            
        } catch (IOException ex) {

        }

            } catch (Exception ex) {
                ex.printStackTrace();

            }

        }
        
         
    }

    @FXML
    private void Reset(MouseEvent event) {

        jaddress.setText("");
        jnbr.setText("");
        jphone.setText("");
        jphone_bis.setText("");
        phone_bis_test.setText("");
        nbr_test.setText("");
        phone_test.setText("");
        address_test.setText("");
        jlati.setText("");
        jlag.setText("");
        lat_test.setText("");
        long_test.setText("");
    }
        private static boolean validatePhoneNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{8}")) return true;
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		//return false if nothing matches the input
		else return false;
		
	}
                private static boolean validateRangs(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches(".*\\d.*")) return true;
		
		//return false if nothing matches the input
		else return false;
		
	}

    @FXML
    private void retour(MouseEvent event) throws IOException {
         AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
         add_form.getChildren().setAll(root);
    }

}