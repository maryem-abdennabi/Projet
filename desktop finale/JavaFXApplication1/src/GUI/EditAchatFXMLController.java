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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import Service.AchatService;
import Service.FournisseurService;
import Service.ProductAchatService;
import Service.ProductService;

/**
 * FXML Controller class
 *
 * @author Ibrahim
 */
public class EditAchatFXMLController implements Initializable {

    @FXML
    private JFXTextField txtClient;
    @FXML
    private JFXTextField txtClient_type;
    @FXML
    private JFXTextField txtClient_address;
    @FXML
    private ListView<Product> listView;
    @FXML
    private JFXButton delete;
    public Achat ac=MainAchatFXMLController.a;
 public void setA(Achat a) {
        this.ac = a;
    }
    public Achat getA() {
        return ac;
    } 
 @FXML
    public void handleEdit(ActionEvent e) {
        if ((txtClient.getText().length() > 3) && (txtClient_address.getText().length() > 3) && (txtClient_type.getText().length() > 3)) {
            AchatService achatService = new AchatService();
            Achat a1 = new Achat(ac.getId_achat(), txtClient.getText(), txtClient_type.getText(), txtClient_address.getText(), ac.getQuantite(), ac.getEtat(), ac.getDate());
            achatService.update(a1);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Achat effectue");

            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Achat invalid");

            a.show();
        }

    }
  private ObservableList<Product> products = FXCollections.observableArrayList();



 
    public void getData(Achat a) {
        txtClient.setText(a.getClient_name());
        txtClient_type.setText(a.getClient_type());
        txtClient_address.setText(a.getClient_address());
        ProductAchatService pas = new ProductAchatService();
        ProductService ps = new ProductService();
        System.out.println(a);
        List<ProductAchat> listProductAchat;
        listProductAchat = pas.displayProductAchat(a.getId_achat());
        for (ProductAchat pa : listProductAchat) {
            Product p = ps.findById(pa.getProduct().getId_product());
            products.add(p);
        }
        listView.getItems().addAll(products);
        System.out.println(products);
    }


    @FXML
    public void handleDelete(ActionEvent e) {
        System.out.println(ac);
        Product p = listView.getItems().get(listView.getSelectionModel().getSelectedIndex());
        System.out.println(p.getId_product());
        ProductAchatService pas = new ProductAchatService();
        ProductService ps = new ProductService();
       products.clear();
        pas.deleteByIdProduct(p.getId_product(), ac.getId_achat());
        List<ProductAchat> listProductAchat;
        listProductAchat = pas.displayProductAchat(ac.getId_achat());
        for (ProductAchat pa : listProductAchat) {
            Product p1 = ps.findById(pa.getProduct().getId_product());
            products.add(p1);
        }
        listView.setItems(products);
//        Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getResource("EditAchatFXML.fxml"));
//            Scene scene = new Scene(root);
//            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
//            window.setScene(scene);
//            window.show();
//        } catch (IOException ex) {
//
//        }
        
        //listView.getItems().addAll(products);
        
    }
    //private Achat a;

  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtClient.setPromptText("Nom Client");
        txtClient.setText(ac.getClient_name());
        txtClient.getParent().requestFocus();
        txtClient_type.setPromptText("Client type");
        txtClient_type.setText(ac.getClient_type());
        txtClient_type.getParent().requestFocus();
        txtClient_address.setPromptText("Client address");
        txtClient_address.setText(ac.getClient_address());
        txtClient_address.getParent().requestFocus();
        ProductAchatService pas = new ProductAchatService();
        ProductService ps = new ProductService();
        
        List<ProductAchat> listProductAchat;
        listProductAchat = pas.displayProductAchat(ac.getId_achat());
        for (ProductAchat pa : listProductAchat) {
            Product p = ps.findById(pa.getProduct().getId_product());
            products.add(p);
        }
        listView.getItems().addAll(products);
        // pas.displayProductAchat(a.getId_achat()).forEach(System.out::println);
    }

}
