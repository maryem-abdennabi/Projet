/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import Entity.Fournisseur;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import Service.FournisseurService;


/**
 * FXML Controller class
 *
 * @author Ibrahim
 */
public class AddFournisseurFXMLController implements Initializable {

     @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtPhoneNumber;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXButton btnAjouter;
    
    @FXML
    public void handleAdd(ActionEvent event){
        FournisseurService fournisseurService=new FournisseurService();
        if((validatePhoneNumber(txtPhoneNumber.getText()))&&(txtFirstName.getText().length()>3)&&(txtLastName.getText().length()>3)&&(txtAddress.getText().length()>8)){
            Fournisseur f=new Fournisseur(txtFirstName.getText(),txtLastName.getText(),txtPhoneNumber.getText(),txtAddress.getText(),txtEmail.getText());
            fournisseurService.insert(f);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Fournisseur ajouter avec success");
            a.show();
        }
        else{
           Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Cordonnes invalid");
            a.show();  
        }
        
        
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

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtLastName.setPromptText("Nom");
        txtLastName.getParent().requestFocus();
        txtFirstName.setPromptText("Prenom");
        txtFirstName.getParent().requestFocus();
        txtAddress.setPromptText("Address");
        txtAddress.getParent().requestFocus();
        txtEmail.setPromptText("Email");
        txtEmail.getParent().requestFocus();
        txtPhoneNumber.setPromptText("Numero Telephone");
        txtPhoneNumber.getParent().requestFocus();
       
    }    
    
}
