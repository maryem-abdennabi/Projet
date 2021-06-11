package GUI;

import Entity.Vehicule;
import Service.VehiculeService;
import com.sun.jndi.toolkit.url.Uri;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AfficherVehiculeController implements Initializable {

    @FXML
    private TableView<Vehicule> tableVehicule;
    //private TableColumn<Vehicule,String> tv_idvehicule;
    @FXML
    private TableColumn<Vehicule,String> tv_matriculevehicule;
    @FXML
    private TableColumn<Vehicule,String> tv_weightvehicule;
    @FXML
    private TableColumn<Vehicule,String> tv_etatvehicule;
    @FXML
    private TableColumn<Vehicule,String> tv_marquevehicule;
    @FXML
    private TableColumn<Vehicule,String> tv_descvehicule;
    @FXML
    private Button Ajout;
    @FXML
    private AnchorPane anchropane;
    @FXML
    private Button tri_Mat;
    @FXML
    private Button tri_mar;
    @FXML
    private Button btn_retour;
    @FXML
    private TableColumn col_delete;

    private ObservableList<Vehicule> observableList = null;
    @FXML
    private TableColumn col_edit;

    
    /**
     * Initializes the controller class.
     */
    public void affiche() throws SQLException{
        
           VehiculeService vs=new VehiculeService();

        observableList = FXCollections.observableArrayList(vs.displayAll());
        tableVehicule.setItems(observableList);
        
        tableVehicule.setItems(observableList);
        
        //tv_idvehicule.setCellValueFactory(new PropertyValueFactory<>("id_vehicule"));
        tv_matriculevehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tv_weightvehicule.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tv_etatvehicule.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tv_marquevehicule.setCellValueFactory(new PropertyValueFactory<>("marque"));
        tv_descvehicule.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 /*Delete*/
         Callback<TableColumn<Vehicule, String>, TableCell<Vehicule, String>> cellFactory1 = (params) -> {
            final TableCell<Vehicule, String> cell = new TableCell<Vehicule, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                 //       Image image = new Image(getClass().getResourceAsStream("delete.png"));

                        final Button deleteButton = new Button("Delete");
                       // deleteButton.setGraphic(new ImageView(image));
                        deleteButton.setPrefSize(75,15);
                        deleteButton.setStyle("-fx-base: #e60000");
                        deleteButton.setOnAction(event -> {
                            Vehicule f = tableVehicule.getItems().get(getIndex());
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                            a.setContentText("Vous etes sur de supprimer " + f.getMatricule()+" ?");
                            Optional<ButtonType> action=a.showAndWait();
                            if(action.get()==ButtonType.OK){
                                  VehiculeService vs;
                        try {
                            vs = new VehiculeService();
                            vs.delete(f.getId_vehicule());

                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                            tableVehicule.refresh();
                            }
                            

                        });
                        setGraphic(deleteButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
         /*Edit*/
         Callback<TableColumn<Vehicule, String>, TableCell<Vehicule, String>> cellFactory2= (params) -> {
            final TableCell<Vehicule, String> cell = new TableCell<Vehicule, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Image image = new Image(getClass().getResourceAsStream("edit.png"));
                        final Button editButton = new Button("Edit");
                        
                       //editButton.setGraphic(new ImageView(image));
                       editButton.setStyle("-fx-base: #0080ff");
                       editButton.setPrefSize(55,15);
                        editButton.setOnAction(event -> {
                            Vehicule f = tableVehicule.getItems().get(getIndex());
                            System.out.println(f);
                            try {
                                /*  Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setContentText("You click this Fournisseur " + f.getEmail() + " " + f.getPhoneNumber());
                                a.show();*/
                                updateVehicule(event);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
         
        col_delete.setCellFactory(cellFactory1);

        col_edit.setCellFactory(cellFactory2);
         
    }    

    private void updateVehicule(ActionEvent event) throws IOException, SQLException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("UpdateVehicule.fxml"));
         anchropane.getChildren().setAll(root);
         
          /*String map = "http://maps.google.co.in/maps?q=Tunis";
         Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        startActivity(i);*/
          
        /*Parent root;
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateVehicule.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        UpdateVehiculeController controller = loader.getController();
        controller.getData(f);
        controller.setV(f);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        } catch (IOException ex) {
        }*/
    }

    @FXML
    private void AjoutVehicule(ActionEvent event) throws IOException {
        
         AnchorPane root=FXMLLoader.load(getClass().getResource("AjoutVehicule.fxml"));
         anchropane.getChildren().setAll(root);
        /*Scene scene=new Scene(root);
        Stage s=new Stage();
        s.setTitle("Ajout Vehicule");
        s.setScene(scene);
        s.show();*/
    }

    private void DeleteVehicule(ActionEvent event)  {
        
         try {
           AnchorPane root = FXMLLoader.load(getClass().getResource("DeleteVehicule.fxml"));
           anchropane.getChildren().setAll(root);
            /*Scene scene=new Scene(root);
            Stage s=new Stage();
            s.setTitle("Delete Vehicule");
            s.setScene(scene);
            s.show();*/
        } catch (IOException ex) {
            Logger.getLogger(AfficherVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void TriMatricule(ActionEvent event) throws SQLException {
        
             VehiculeService vs=new VehiculeService();

        ObservableList<Vehicule> observableList = null;
        observableList = FXCollections.observableArrayList(vs.TrieMatricule());
        tableVehicule.setItems(observableList);
        
        tableVehicule.setItems(observableList);
        
        //tv_idvehicule.setCellValueFactory(new PropertyValueFactory<>("id_vehicule"));
        tv_matriculevehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tv_weightvehicule.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tv_etatvehicule.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tv_marquevehicule.setCellValueFactory(new PropertyValueFactory<>("marque"));
        tv_descvehicule.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        } 

    @FXML
    private void TriMarque(ActionEvent event) throws SQLException {
        
        VehiculeService vs=new VehiculeService();

        ObservableList<Vehicule> observableList = null;
        observableList = FXCollections.observableArrayList(vs.TrieMarque());
        tableVehicule.setItems(observableList);
        
        tableVehicule.setItems(observableList);
        
        //tv_idvehicule.setCellValueFactory(new PropertyValueFactory<>("id_vehicule"));
        tv_matriculevehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tv_weightvehicule.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tv_etatvehicule.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tv_marquevehicule.setCellValueFactory(new PropertyValueFactory<>("marque"));
        tv_descvehicule.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

}