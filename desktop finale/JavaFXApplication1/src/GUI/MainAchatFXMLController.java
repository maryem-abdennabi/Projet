/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Achat;
import Entity.Fournisseur;
import Entity.Product;
import Entity.ProductAchat;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Service.AchatService;
import Service.ProductAchatService;
import Service.ProductService;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ibrahim
 */
public class MainAchatFXMLController implements Initializable {

    @FXML
    private TableView<Achat> tableAchat;
    public static Achat a;
    @FXML
    private TableColumn<Achat, Integer> achat_id;

    @FXML
    private TableColumn<Achat, String> achat_client_type;

    @FXML
    private TableColumn<Achat, String> achat_client_address;
   
    @FXML
    private TableColumn col_valider;

    @FXML
    private TableColumn col_edit;

    @FXML
    private TableColumn col_delete;

    @FXML
    private TableColumn col_details;
    
    @FXML
    private BarChart<?,?> barChart;
    
    @FXML
    private CategoryAxis x;
    
    @FXML
    private JFXButton ajouter;
    
    @FXML
    
    private NumberAxis y;
    
  
    
    @FXML 
    private TextField filterField;
    @FXML
    private AnchorPane AnchorPane;
    
      @FXML
    public void handleToAdd(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddAchat.fxml"));
            AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }


    public void handleToEdit(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("EditAchatFXML.fxml"));
            AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println("ghjk");
        }
    }

    private ObservableList<Achat> list = FXCollections.observableArrayList();

    private AchatService achatService;
     
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        achat_id.setCellValueFactory(new PropertyValueFactory<>("id_achat"));
        achat_client_type.setCellValueFactory(new PropertyValueFactory<>("client_type"));
        achat_client_address.setCellValueFactory(new PropertyValueFactory<>("client_address"));
        achatService = new AchatService();
        list.addAll(achatService.displayAll());
       
        tableAchat.setItems(list);
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
                            Achat ac = tableAchat.getItems().get(getIndex());
                            System.out.println(ac);
                            /*  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                             a1.setContentText("this achat deleted");
                             a1.show();*/
                            a=ac;
                            System.out.println(a);
                            handleToEdit(event);

                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        Callback<TableColumn<Achat, String>, TableCell<Achat, String>> cellFactory1 = (params) -> {
            final TableCell<Achat, String> cell = new TableCell<Achat, String>() {
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
                            Achat a1 = tableAchat.getItems().get(getIndex());
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                            a.setContentText("tu es sur de supprimer l'achat ?");
                            Optional<ButtonType> action=a.showAndWait();
                            if(action.get()==ButtonType.OK){
                               achatService.delete(a1.getId_achat());

                            list.clear();
                            list.addAll(achatService.displayAll());   
                            }

                          
                            // fournisseurService.delete(f.getId_fournisseur());

                            //  list.clear();
                            //list.addAll(fournisseurService.displayAll());

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
                            Achat a1 = tableAchat.getItems().get(getIndex());
                            System.out.println(a1);
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setContentText(a1.toString());
                            // achatService.delete(a1.getId_achat());

                            a.show();
                          //  list.clear();
                            // list.addAll(achatService.displayAll());

                        });
                        setGraphic(deleteButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        Callback<TableColumn<Achat, String>, TableCell<Achat, String>> cellFactory3 = (params) -> {
            final TableCell<Achat, String> cell = new TableCell<Achat, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Image image = new Image(getClass().getResourceAsStream("tick.png"));
                        final Button validerButton = new Button("");
                        final Label valider = new Label("valider");
                        validerButton.setStyle("-fx-base: #228B22");
                        validerButton.setPrefSize(35, 35);
                        validerButton.setGraphic(new ImageView(image));
                        validerButton.setOnAction(event -> {
                            Achat a1 = tableAchat.getItems().get(getIndex());
                            Alert a = new Alert(Alert.AlertType.INFORMATION);

                            a.setContentText("Achat validé");
                            achatService.updateEtat(2, a1.getId_achat());
                            // fournisseurService.delete(f.getId_fournisseur());

                            a.show();
                            list.clear();
                            list.addAll(achatService.displayAll());
                            //  list.clear();
                            //list.addAll(fournisseurService.displayAll());

                        });
                        Achat a = tableAchat.getItems().get(getIndex());
                        
                        if (a.getEtat() == 2) {
                            setGraphic(valider);
                        } else {
                            setGraphic(validerButton);

                        }

                        setText(null);
                    }
                }
            };

            return cell;
        };
        col_edit.setCellFactory(cellFactory);
        col_delete.setCellFactory(cellFactory1);
        col_details.setCellFactory(cellFactory2);
        col_valider.setCellFactory(cellFactory3);
       
      
        
      /* NumberAxis xAxis = new NumberAxis("X-Axis", 0d, 8.0d, 1.0d);
        NumberAxis yAxis = new NumberAxis("Y-Axis", 0.0d, 5.0d, 1.0d);*/
      /*  
        series.getData().add(new XYChart.Data("0.2", 3.5));
        series.getData().add(new XYChart.Data("0.7", 4.6));
        series.getData().add(new XYChart.Data("1.7", 2.6));
        series.getData().add(new XYChart.Data("4.3", 4.2));
        series.getData().add(new XYChart.Data("3.7", 1.6));
        series.getData().add(new XYChart.Data("0.5", 4.6));

       */
     /*   XYChart.Series series=new XYChart.Series<>();
        series.getData().add(new XYChart.Data("james", 2000));
        series.getData().add(new XYChart.Data("david", 3000));
        series.getData().add(new XYChart.Data("youssef", 5000));*/

        
     //   scatterChart.getData().add(series);
        FilteredList<Achat> filteredData = new FilteredList<>(list, b -> true);
      // filterField.textProperty().addListener((observable,old));
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(f -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                               String lowerCaseFilter = newValue.toLowerCase();
                               int s=f.getClient_name().toLowerCase().indexOf(lowerCaseFilter);
			       int s1=f.getClient_address().toLowerCase().indexOf(lowerCaseFilter);
                               int s2=f.getClient_type().toLowerCase().indexOf(lowerCaseFilter);
				// Compare first name and last name of every person with filter text.
				
				if (s != -1 ) {
					return true; // Filter matches first name.
				} else if (s1 != -1) {
					return true; // Filter matches last name.
				}
				else if (s2!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
        SortedList<Achat> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableAchat.comparatorProperty());
                tableAchat.setItems(sortedData);
        getChart();
        filterField.setPromptText("Recherche Achat");
        filterField.getParent().requestFocus();
       ProductAchatService productAchatService=new ProductAchatService();
        ProductService productService=new ProductService();
        for(ProductAchat p: productAchatService.displayAll()){
            Product product=productService.findById(p.getProduct().getId_product());
            System.out.println(product);
            int q=Math.round(p.getQuantite());
            System.out.println(q);
           // String n=product.getProduct_name();
            //series.getData().add(new XYChart.Data(product,120));
        }

        // TODO
    }
    public void getChart(){
         XYChart.Series series=new XYChart.Series();
        ProductAchatService productAchatService=new ProductAchatService();
        ProductService productService=new ProductService();
        for(ProductAchat p: productAchatService.displayAll()){
            Product product=productService.findById(p.getProduct().getId_product());
            System.out.println(product);
            int q=Math.round(p.getQuantite());
            String n=product.getProduct_name();
            series.getData().add(new XYChart.Data(product.getProduct_name(),q));
        }
                barChart.getData().addAll(series);
                barChart.lookupAll(".default-color0.chart-bar")
            .forEach(n -> n.setStyle("-fx-bar-fill: blue;"));

    }

}
