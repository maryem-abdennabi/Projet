/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.jfoenix.controls.JFXButton;
import Entity.Fournisseur;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import Service.FournisseurService;

/**
 * FXML Controller class
 *
 * @author Ibrahim
 */
public class MainFournisseursController implements Initializable {

    public static Fournisseur fo;

    @FXML
    private TableView<Fournisseur> tableFour;

    @FXML
    private TableColumn<Fournisseur, Integer> four_id;

    @FXML
    private TableColumn<Fournisseur, String> four_first_name;

    @FXML
    private TableColumn<Fournisseur, String> four_last_name;

    @FXML
    private TableColumn<Fournisseur, String> four_email;

    @FXML
    private TableColumn col_download;

    @FXML
    private TextField filterField;

    @FXML
    private TableColumn col_edit;

    @FXML
    private TableColumn col_delete;

    @FXML
    private TableColumn col_details;

    @FXML
    private JFXButton ajouter;
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    public void handleToAdd(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddFournisseurFXML.fxml"));
            AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    @FXML
    public void handleToEdit(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("EditFournisseurFXML.fxml"));
            AnchorPane.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println("ghjk");
        }
    }

    private ObservableList<Fournisseur> list = FXCollections.observableArrayList();
    private FournisseurService fournisseurService;
    private int count = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        four_id.setCellValueFactory(new PropertyValueFactory<>("id_fournisseur"));
        four_first_name.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        four_last_name.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        four_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        fournisseurService = new FournisseurService();
        list.addAll(fournisseurService.displayAll());
        tableFour.setItems(list);
        //Image image = new Image(getClass().getResourceAsStream("/edit.png"));

        Callback<TableColumn<Fournisseur, String>, TableCell<Fournisseur, String>> cellFactory = (params) -> {
            final TableCell<Fournisseur, String> cell = new TableCell<Fournisseur, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Image image = new Image(getClass().getResourceAsStream("edit.png"));
                        final Button editButton = new Button("");

                        editButton.setGraphic(new ImageView(image));
                        editButton.setStyle("-fx-base: #0080ff");
                        editButton.setPrefSize(35, 35);
                        editButton.setOnAction(event -> {
                            Fournisseur f = tableFour.getItems().get(getIndex());
                            System.out.println(f);
                            /*  Alert a = new Alert(Alert.AlertType.INFORMATION);
                             a.setContentText("You click this Fournisseur " + f.getEmail() + " " + f.getPhoneNumber());
                             a.show();*/
                            fo = f;
                            System.out.println(fo);
                            handleToEdit(event);

                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        Callback<TableColumn<Fournisseur, String>, TableCell<Fournisseur, String>> cellFactory1 = (params) -> {
            final TableCell<Fournisseur, String> cell = new TableCell<Fournisseur, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Image image = new Image(getClass().getResourceAsStream("delete.png"));

                        final Button deleteButton = new Button("");
                        deleteButton.setGraphic(new ImageView(image));
                        deleteButton.setPrefSize(35, 35);
                        deleteButton.setStyle("-fx-base: #e60000");
                        deleteButton.setOnAction(event -> {
                            Fournisseur f = tableFour.getItems().get(getIndex());
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                            a.setContentText("Vous etes sur de supprimer " + f.getFirstname() + " " + f.getLastname() + " ?");
                            Optional<ButtonType> action = a.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                fournisseurService.delete(f.getId_fournisseur());

                                list.clear();
                                list.addAll(fournisseurService.displayAll());
                            }

                        });
                        setGraphic(deleteButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        Callback<TableColumn<Fournisseur, String>, TableCell<Fournisseur, String>> cellFactory2 = (params) -> {
            final TableCell<Fournisseur, String> cell = new TableCell<Fournisseur, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Image image = new Image(getClass().getResourceAsStream("info.png"));

                        final Button deleteButton = new Button("");
                        deleteButton.setStyle("-fx-base: #e67300");
                        deleteButton.setPrefSize(35, 35);
                        deleteButton.setGraphic(new ImageView(image));
                        deleteButton.setOnAction(event -> {
                            Fournisseur f = tableFour.getItems().get(getIndex());
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setContentText("this Fournisseur " + f.toString());

                            a.show();

                        });
                        setGraphic(deleteButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        Callback<TableColumn<Fournisseur, String>, TableCell<Fournisseur, String>> cellFactory3 = (params) -> {
            final TableCell<Fournisseur, String> cell = new TableCell<Fournisseur, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Image image = new Image(getClass().getResourceAsStream("download.png"));
                        final Button downloadButton = new Button("");
                        downloadButton.setGraphic(new ImageView(image));
                        downloadButton.setStyle("-fx-base: #00e6e6");
                        downloadButton.setPrefSize(35, 35);

                        downloadButton.setOnAction(event -> {
                            Fournisseur f = tableFour.getItems().get(getIndex());
                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            count = count + 1;
                            String file = "f" + f.getEmail() + count;
                            fournisseurService.download(f, file);
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setContentText("Fichier telechargé");

                            a.show();

                        });
                        setGraphic(downloadButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        FilteredList<Fournisseur> filteredData = new FilteredList<>(list, b -> true);
        // filterField.textProperty().addListener((observable,old));
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(f -> {
				// If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                int s = f.getFirstname().toLowerCase().indexOf(lowerCaseFilter);
                int s1 = f.getLastname().toLowerCase().indexOf(lowerCaseFilter);
                int s2 = f.getEmail().toLowerCase().indexOf(lowerCaseFilter);
				// Compare first name and last name of every person with filter text.

                if (s != -1) {
                    return true; // Filter matches first name.
                } else if (s1 != -1) {
                    return true; // Filter matches last name.
                } else if (s2 != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Fournisseur> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableFour.comparatorProperty());
        tableFour.setItems(sortedData);
        col_edit.setCellFactory(cellFactory);
        col_delete.setCellFactory(cellFactory1);
        col_details.setCellFactory(cellFactory2);
        col_download.setCellFactory(cellFactory3);
        filterField.setPromptText("Recherche Fournisseur");
        filterField.getParent().requestFocus();

    }

}
