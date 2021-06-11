/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Stocks;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Connexion.DataSource;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Edit_Product_EntrepotController implements Initializable {

    @FXML
    private TextField Quantity;
    @FXML
    private TextField Unity;
    @FXML
    private Button edit;
    @FXML
    private TextField id;
    @FXML
    private Button delete;
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    @FXML
    private AnchorPane Anchropane;
    @FXML
    private Label retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setVisible(false);
    }    

    @FXML
    private void Edit_Product(MouseEvent event) {
        
           String quantity = Quantity.getText();
           String unity = Unity.getText();
           
            if(quantity.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Quantity must contain only float or integer values");
            a.setHeaderText("Invalid Value");
            a.show();
        }
         
         else if (unity.isEmpty()){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Unity must contain only float or integer values");
            a.setHeaderText("Invalid Value");
            a.show();
        }
         else{
           try {
            this.cnx = DataSource.getInstance().getCon();
            PreparedStatement stmt = cnx.
                    prepareStatement("UPDATE stock  SET  Quantity='" + quantity + "', Unity='" +unity+ "' WHERE id='" + id.getText() + "'");

            stmt.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Stock Updated successfully");
            a.setHeaderText("Success");
            a.show();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
         try {
              AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
              Anchropane.getChildren().setAll(root);
            
        } catch (IOException ex) {

        }   
        }}
        
        
    

    @FXML
    private void Delete_Product(MouseEvent event) {
           String sql = "delete from stock where id= '"+ id.getText()+"'";

        try {
            this.cnx = DataSource.getInstance().getCon();
            pst = cnx.prepareStatement(sql);
            pst.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Stock deleted successfully");
            a.setHeaderText("Success");
            a.show();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
          
        }
        
         try {
          AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
              Anchropane.getChildren().setAll(root);
        } catch (IOException ex) {

        }
        
        
    }

    void SetItems(Stocks person) {
        Quantity.setText(String.valueOf(person.getQuantity()));
        Unity.setText(String.valueOf(person.getUnity()));
        id.setText(String.valueOf(person.getId_stocks()));
        id.setEditable(false);
        }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
         Anchropane.getChildren().setAll(root);
    }
    
}
