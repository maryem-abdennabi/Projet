package GUI;

import Entity.Delivery;
import Entity.Vehicule;
import Service.DeliveryService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Connexion.DataSource;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AjoutDeliveryController implements Initializable {

    @FXML
    private AnchorPane anchropane;
    @FXML
    private TextField txt_reference;
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_clientname;
    @FXML
    private TextField txt_drivername;
    @FXML
    private TextField txt_statut;
    //private TextField txt_idvehicuel;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_ajout;
    @FXML
    private ComboBox<Vehicule> combo_vehicule;
     ResultSet rs;
    Statement st;
    PreparedStatement pst;
    private Connection cnx;
    Vehicule v;
    int id=0;
    
    final ObservableList<Vehicule> options= FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fillComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutDeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    public void fillComboBox() throws SQLException
    {
       
        try {
            List<Vehicule> list=new ArrayList<>();
            cnx=DataSource.getInstance().getCon();
            String query="select * from vehicule";
            
            pst=cnx.prepareStatement(query);
                  //  pst.setString(1, query);
            rs=pst.executeQuery();
            while(rs.next())
            {
//                int i=rs.getInt("id_vehicule");   
//                String st=Integer.toString(i);
//                System.out.println(st);
//                options.add(st);
                v=new Vehicule(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getString(5),rs.getString(6));
                list.add(v);

                
            }
            options.addAll(list);
             combo_vehicule.getItems().setAll(list);

            System.out.println("aaa");
            System.out.println(options);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query1="select id_vehicule from vehicule where marque=? and matricule=?";
            
            pst=cnx.prepareStatement(query1);
            pst.setString(1,v.getMarque());
            pst.setString(2,v.getMatricule());
            rs=pst.executeQuery();
             while(rs.next())
            {
                id=rs.getInt(1);
            }
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
         AnchorPane root=FXMLLoader.load(getClass().getResource("AfficherDelivery.fxml"));
         anchropane.getChildren().setAll(root);
    }

    @FXML
    private void ajout(ActionEvent event) throws SQLException {
        DeliveryService vs=new DeliveryService();
              Vehicule v=combo_vehicule.getItems().get(combo_vehicule.getSelectionModel().getSelectedIndex());

        //int idveh = Integer.parseInt(txt_idvehicuel.getText());
        vs.insert(new Delivery(txt_reference.getText(), txt_clientname.getText(),txt_drivername.getText(),txt_name.getText(),txt_statut.getText(),v.getId_vehicule()));
    }
    }