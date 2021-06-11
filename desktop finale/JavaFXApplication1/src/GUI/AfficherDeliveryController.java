package GUI;

import Entity.Delivery;
import Service.DeliveryService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AfficherDeliveryController implements Initializable {

    @FXML
    private AnchorPane anchropane;
    private TableColumn<Delivery, String> tv_iddelivery;
    @FXML
    private TableColumn<Delivery, String> tv_referencedelivery;
    @FXML
    private TableColumn<Delivery, String> tv_clientnamedelivery;
    @FXML
    private TableColumn<Delivery, String> tv_drivernamedelivery;
    @FXML
    private TableColumn<Delivery, String> tv_addressdelivery;
    @FXML
    private TableColumn<Delivery, String> tv_statutdelivery;
    @FXML
    private TableView<Delivery> tableDelivery;
    @FXML
    private TableColumn<Delivery, String> tv_idvehiculedelivery;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_map;
    @FXML
    private TableColumn col_delete;
    @FXML
    private TableColumn col_edit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            DeliveryService ds=new DeliveryService();
            
                ObservableList<Delivery> observableList = null;
            observableList = FXCollections.observableArrayList(ds.displayAll());
            tableDelivery.setItems(observableList);
            
            tableDelivery.setItems(observableList);
            
            //tv_iddelivery.setCellValueFactory(new PropertyValueFactory<>("id_delivery"));
            tv_referencedelivery.setCellValueFactory(new PropertyValueFactory<>("reference"));
            tv_clientnamedelivery.setCellValueFactory(new PropertyValueFactory<>("client_name"));
            tv_drivernamedelivery.setCellValueFactory(new PropertyValueFactory<>("driver_name"));
            tv_addressdelivery.setCellValueFactory(new PropertyValueFactory<>("address"));
            tv_statutdelivery.setCellValueFactory(new PropertyValueFactory<>("statut"));
            tv_idvehiculedelivery.setCellValueFactory(new PropertyValueFactory<>("vehicule"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficherDeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /*Delete*/
        /*Delete*/
         Callback<TableColumn<Delivery, String>, TableCell<Delivery, String>> cellFactory1 = (params) -> {
            final TableCell<Delivery, String> cell = new TableCell<Delivery, String>() {
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
                            Delivery f = tableDelivery.getItems().get(getIndex());
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                            a.setContentText("Vous etes sur de supprimer " + f.getReference()+" ?");
                            Optional<ButtonType> action=a.showAndWait();
                            if(action.get()==ButtonType.OK){
                                  DeliveryService vs;
                        try {
                            vs = new DeliveryService();
                            vs.delete(f.getId_delivery());

                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                            tableDelivery.refresh();
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
         Callback<TableColumn<Delivery, String>, TableCell<Delivery, String>> cellFactory2= (params) -> {
            final TableCell<Delivery, String> cell = new TableCell<Delivery, String>() {
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
                            Delivery f = tableDelivery.getItems().get(getIndex());
                            System.out.println(f);
                            try {
                                /*  Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setContentText("You click this Fournisseur " + f.getEmail() + " " + f.getPhoneNumber());
                                a.show();*/
                                update(event);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
         
            return cell;
    }   ;
         
        col_delete.setCellFactory(cellFactory1);
                 
        col_edit.setCellFactory(cellFactory2);
                 }


    @FXML
    private void ajout(ActionEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("AjoutDelivery.fxml"));
         anchropane.getChildren().setAll(root);
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("UpdateDelivery.fxml"));
         anchropane.getChildren().setAll(root);
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("DeleteDelivery.fxml"));
         anchropane.getChildren().setAll(root);
    }


    @FXML
    private void map(ActionEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("Testmap.fxml"));
         anchropane.getChildren().setAll(root);
    }
    
}