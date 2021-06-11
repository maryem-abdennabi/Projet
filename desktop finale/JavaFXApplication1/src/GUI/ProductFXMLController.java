package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import Entity.Fournisseur;
import Entity.Product1;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Connexion.DataSource;
import Service.FournisseurService;
import Service.ProductService;
import java.io.File;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author hp
 */
public class ProductFXMLController implements Initializable {
    @FXML
    private TextField search;
    @FXML
    private TableView<Product1> Table;
    @FXML
    private TableColumn<Product1, String> name;
    @FXML
    private TableColumn<Product1, String> type;
    @FXML
    private TableColumn<Product1, String> reference;
    @FXML
    private TableColumn<Product1, String> marque;
    @FXML
    private TableColumn<Product1, Float> priceht;
    @FXML
    private TableColumn<Product1, Float> pricettc;
    @FXML
    private TableColumn<Product1, Float> tva;
    @FXML
    private TableColumn<Product1, Float> weight;
    @FXML
    private Button add;
    private ObservableList<Product1> oblist = FXCollections.observableArrayList();
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    @FXML
    private Button pdf;
    @FXML
    private Button facebook;
    @FXML
    private Button twitter;
    private Image image;
    private FileInputStream fis;
    @FXML
    private AnchorPane AnchorPane;
    public static int id;
    @FXML
    private Label retour;
    @FXML
    private TableColumn<Product1, Image> photo;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        type.setCellValueFactory(new PropertyValueFactory<>("product_type"));
        reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        priceht.setCellValueFactory(new PropertyValueFactory<>("priceHT"));
        pricettc.setCellValueFactory(new PropertyValueFactory<>("priceTTC"));
        tva.setCellValueFactory(new PropertyValueFactory<>("TVA"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
       photo.setPrefWidth(80);
       photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        
   
       // photo.set
            
       
        //Table.setItems(oblist);
        
  


        try {
            this.cnx = DataSource.getInstance().getCon();
            String sql = "select * from product";
            stmt = cnx.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                Product1 cm = new Product1( );
            ImageView photo = new ImageView(new Image(new File("C:/wamp64/www/pidev/web/uploads/post/"+res.getString(10)).toURI().toString(),50,50,false,false));  
            oblist.add(new Product1(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getFloat(6), res.getFloat(7), res.getFloat(8), res.getFloat(9), photo));
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
      do{  
        FilteredList<Product1> filteredData = new FilteredList<>(oblist, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(emp-> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                               	String lowerCaseFilter = newValue.toLowerCase();
                                int s = emp.getMarque().toLowerCase().indexOf(lowerCaseFilter); 
				int s1=emp.getProduct_name().toLowerCase().indexOf(lowerCaseFilter);
                                
                               int s2= emp.getReference().toLowerCase().indexOf(lowerCaseFilter);
				if (s != -1 ) {
					return true; // Filter matches first name.
				} else if (s1 != -1) {
					return true; // Filter matches last name.
				}
				else if (s2!=-1){
				     return true;}
				     else  
				    	 return false; // Does not match.
			});
		});

             SortedList<Product1> sortedData = new SortedList<>(filteredData);
	     sortedData.comparatorProperty().bind(Table.comparatorProperty());
             Table.setItems(sortedData);
      }while(add.isPressed());    
    
    }

    @FXML
    private void ADD(MouseEvent event) {
        try {
            
            AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/Add_Product.fxml"));
        AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {

        }

    }

    @FXML
    private void take(MouseEvent event) {
        Product1 person = Table.getSelectionModel().getSelectedItem();
        id=person.getId_product();
        if (person == null) {
            JOptionPane.showMessageDialog(null, "No Selected Item", "message", JOptionPane.PLAIN_MESSAGE);
            return;
        }
try {
            
           // AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/Edit_Product.fxml"));
       // AnchorPane.getChildren().setAll(root);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Edit_Product.fxml"));
            Parent root = loader.load();
            EditProductController controller = (EditProductController) loader.getController();
            controller.SetItems(person);
            AnchorPane.getChildren().setAll(root);
            
        
        } catch (IOException ex) {

        }
        
    }
    @FXML
    private void search(KeyEvent event) {
      
    }

    @FXML
    private void ConvertPDF(MouseEvent event) {
        
        this.download("file");
        
    }
    
    

    
    
    public void download(String file) {
        Document document = new Document(PageSize.A4);
        document.addAuthor("brahim");
        document.addTitle("Facture");
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file+".pdf"));
            document.open();
            System.out.println("aaaa");
            for(Product1 p: oblist){
                 Paragraph para = new Paragraph(p.toPdf());
                 document.add(para);

            }
            
            document.close();
            System.out.println("Document generated");
        } catch (DocumentException ex) {
        } catch (FileNotFoundException ex) {
        } 
    }

    private void Facebook(MouseEvent event) {
      try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/FacebookFXML.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {

        }

    }

    @FXML
    private void facebookRedirect(MouseEvent event) {
        
          try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Facebook_Post.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {

        }
        
    }

    @FXML
    private void TwitterRedirect(MouseEvent event) {
        
        
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/TwitterFxml.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {

        }
   
        
        
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
         AnchorPane.getChildren().setAll(root);
    }



      
    }