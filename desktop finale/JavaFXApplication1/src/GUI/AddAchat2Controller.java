/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Entity.Achat;
import Entity.Fournisseur;
import Entity.Product;
import Entity.ProductAchat;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import Service.AchatService;
import Service.FournisseurService;
import Service.ProductAchatService;
import Service.ProductService;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Ibrahim
 */
public class AddAchat2Controller implements Initializable {
    
    @FXML
    private JFXTextField qte;
    
    @FXML
    private JFXButton addTo;
    
    @FXML
    private ChoiceBox<String> products; 
    

    
    @FXML
    private AnchorPane AnchorPane;
    
    @FXML
    public String f=AddAchatController.f;
    
    @FXML
    public void handleAdd(ActionEvent event){
        String ref=AddAchatController.ref;
        Achat a=AddAchatController.achat;
        String p=products.getValue().toString();
        String[] arr=p.split(" ");
        String idstr=arr[0];
        System.out.println(idstr);
        String quantite=qte.getText().toString();
        ProductService productService=new ProductService();
        Product product=productService.findById(Integer.parseInt(idstr));
        ProductAchatService productAchatService=new ProductAchatService();
        productAchatService.insert(new ProductAchat(Integer.parseInt(quantite), product, a));
            Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddAchat3.fxml"));
            AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        
      //  tableProdAchats.refresh();

    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qte.setPromptText("Quantite de produit");
        qte.getParent().requestFocus();
        ProductService productService=new ProductService();
        for(Product p: productService.displayAll()){
            products.getItems().add(p.getId_product()+" "+p.getProduct_name());
        }
       
        }
        
         
      
        
        
       
    
}
