/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Product;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Connexion.DataSource;
import Service.ProductService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddProduct_EntrepotFXMLController implements Initializable {

    @FXML
    private AnchorPane add_form;
    @FXML
    private TextField jname;
    @FXML
    private TextField jtype;
    @FXML
    private TextField jreference;
    @FXML
    private TextField jmarque;
    @FXML
    private TextField jpriceHT;
    @FXML
    private TextField jpriceTTC;
    @FXML
    private TextField jWeight;
    @FXML
    private TextField jTVA;
    @FXML
    private Button reset;
    
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    @FXML
    private Label id_label;
    private static String ch;
    @FXML
    private Button add_product;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              
    }

    @FXML
    private void Reset(MouseEvent event) {
    jname.setText("");
    jtype.setText("");
    jreference.setText("");
    jmarque.setText("");
    jpriceHT.setText("");
    jpriceTTC.setText("");
    jWeight.setText("");
    jTVA.setText("");
    
    }
    public void SetItems(String p) {
        
        ch=String.valueOf(p);
        System.out.println(ch);
        

        id_label.setText(String.valueOf(p));
        id_label.setVisible(true);
   
    }

    @FXML
    private void add_product(MouseEvent event) {
        
        String name = jname.getText();
        String type = jtype.getText();
        String reference = jreference.getText();
        String marque = jmarque.getText();
        String priceHT = jpriceHT.getText();
        String priceTTC = jpriceTTC.getText();
        String Weight = jWeight.getText();
        String TVA = jTVA.getText();
        
          if((name.isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Name field is empty ");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
       else if((type.isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Type field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
        else if((reference.isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Reference field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
         else if((marque.isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Marque field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
        
        else if((!priceHT.matches("[-+]?[0-9]*\\.?[0-9]+"))&&(priceHT.isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("PriceHT must contain only float or integer values");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
         
         else  if((!priceTTC.matches("[-+]?[0-9]*\\.?[0-9]+"))&&(priceTTC.isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("PriceTTC must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         else if((!Weight.matches("[-+]?[0-9]*\\.?[0-9]+"))&&(Weight.isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Weight must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         else if((!TVA.matches("[-+]?[0-9]*\\.?[0-9]+"))&&(TVA.isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("TVA must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         
         else{
    
       
         
        try {
            this.cnx = DataSource.getInstance().getCon();
            PreparedStatement stmt = cnx.
                    prepareStatement("INSERT INTO product (product_name,product_type,reference,marque,priceHT,priceTTC,TVA,weight)  "
                            + "OUTPUT INSERTED.ID VALUES ('" +name+"','"+type+"','"+reference+"','"+ marque+"','"+ priceHT+"','"+ priceTTC+"','"+ Weight+"','"+TVA+"')");
             
            

	


            stmt.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Product Added successfully");
            a.setHeaderText("Success");
            a.show();
            System.out.println(stmt);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        
        try{
        
        this.cnx = DataSource.getInstance().getCon();
        PreparedStatement stmt2 = cnx.
                    prepareStatement("INSERT INTO stocks (id_product,id_entrepot)VALUES ('88','"+ch+"')");
        
        
        stmt2.executeUpdate();
            
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Stock Added successfully");
            a.setHeaderText("Success");
            a.show();
        }
        catch (Exception ex) {
            ex.printStackTrace();

        }
        
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pi_desktop_app/ProductFXML.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException ex) {

        }
        
        
    }}
    
}
