/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Connexion.DataSource;
import Entity.Product;

import Service.ProductService;
import Entity.Stocks;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class Stock_ProductFXMLController implements Initializable {

    @FXML
    private TableView<Stocks> Table_Stock;
    private TableColumn<Stocks, Integer> id_Stock;
    @FXML
    private TableColumn<Stocks, Integer> Product_Name;
    @FXML
    private TableColumn<Stocks, Float> Quantity;
    @FXML
    private TableColumn<Stocks, String> Unity;
    private ObservableList<Stocks> oblist = FXCollections.observableArrayList();
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    @FXML
    private Label id_label;
 
    
    private static String ch;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id_label.setVisible(false);

    }    
 public void SetItems(String p) {
        

        id_label.setText(String.valueOf(p));
        ch=String.valueOf(p);
        id_label.setVisible(false);
//        id_Stock.setCellValueFactory(new PropertyValueFactory<>("id_stocks"));
        Product_Name.setCellValueFactory(new PropertyValueFactory<>("id_product"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Unity.setCellValueFactory(new PropertyValueFactory<>("unity"));
        Table_Stock.setItems(oblist);
  
        String nnn=id_label.getText();
     //   int n=Integer.parseInt(nnn);
        System.out.println(ch);
        try {
             this.cnx = DataSource.getInstance().getCon();
            String sql = "select * from stock WHERE entrepot='"+ch+"'";
            stmt = cnx.createStatement();
            res = stmt.executeQuery(sql);
            ProductService ps= new ProductService();
            while (res.next()) {
                
               System.out.println(res.toString());
                 Product prod=ps.findById(res.getInt(2));
                System.out.println(prod);
               Stocks s=new Stocks(res.getInt(1), res.getFloat(4),prod, res.getString(5));
                System.out.println(s);
                oblist.add(s);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Take(MouseEvent event) {
        
         Stocks person = Table_Stock.getSelectionModel().getSelectedItem();
        if (person == null) {
            JOptionPane.showMessageDialog(null, "No Selected Item", "message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Edit_Product_Entrepot.fxml"));
            Parent root = loader.load();
            Edit_Product_EntrepotController controller = (Edit_Product_EntrepotController) loader.getController();
            controller.SetItems(person);
           anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public void SetItems(Entity.Entrepot p) {

        id_label.setText(String.valueOf(p.getId_entrepot()));
        
    }

    private void add(MouseEvent event) {
      String person = id_label.getText();
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AddProduct_EntrepotFXML.fxml"));
            Parent root = loader.load();
            AddProduct_EntrepotFXMLController controller = (AddProduct_EntrepotFXMLController) loader.getController();
            controller.SetItems(person);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();      
        } catch (IOException ex) {

        }
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
         anchorpane.getChildren().setAll(root);
    }


    
}
