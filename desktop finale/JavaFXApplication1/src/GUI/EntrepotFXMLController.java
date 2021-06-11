package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import Entity.Entrepot;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Connexion.DataSource;
import Service.EntrepotService;
import Service.ProductService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EntrepotFXMLController implements Initializable {

    @FXML
    private TableView<Entrepot> Table;
    @FXML
    private TableColumn<Entrepot, String> address;
    @FXML
    private TableColumn<Entrepot, String> nbr;
    @FXML
    private TableColumn<Entrepot, String> phone;
    @FXML
    private TableColumn<Entrepot, String> phone_bis;
    @FXML
    private Button add;
    @FXML
    private Button Convert;
    ObservableList<Entrepot> oblist = FXCollections.observableArrayList();
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res, rs;
    @FXML
    private Button add_Product;
    ObservableList<Entrepot> ob = FXCollections.observableArrayList();
        ObservableList<Entrepot> oblist3= FXCollections.observableArrayList();

    @FXML
    private TextField sarch;
    int from = 0, to = 0, itemPerPage = 5;
    @FXML
    private Pagination pagination;
    @FXML
    private Button stocks;
    @FXML
    private Button facebook;
    @FXML
    private Button twitter;
    @FXML
    private Button search;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button load;
    
public static int id;
    @FXML
    private TableColumn<Entrepot, String> latitude;
    @FXML
    private TableColumn<Entrepot, String> longitude;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image Twitter_Img = new Image("/image/icontwitter.png");
        Image Facebook_Img = new Image("/image/iconfacebook.png");
        twitter.setGraphic(new ImageView(Twitter_Img));
        facebook.setGraphic(new ImageView(Facebook_Img));
       

        int count = 0;
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbrRangs"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phone_bis.setCellValueFactory(new PropertyValueFactory<>("phone_bis"));
        latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
//Table.setItems(oblist);

  
        try {
            this.cnx = DataSource.getInstance().getCon();
            String sql = "select Count(*) from entrepot ";
            stmt = cnx.createStatement();
            res = stmt.executeQuery(sql);

            res.first();
            count = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(EntrepotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        int PageCount = (count / itemPerPage) + 1;
        pagination.setPageCount(PageCount);
        pagination.setPageFactory(this::CreatePage);

    }

    private void take(MouseEvent event) {
        Entrepot person = Table.getSelectionModel().getSelectedItem();
        id=person.getId_entrepot();
        if (person == null) {
            JOptionPane.showMessageDialog(null, "No Selected Item", "message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

       try {
            AnchorPane root=FXMLLoader.load(getClass().getResource("Edit_Entrepot.fxml"));
         anchorpane.getChildren().setAll(root);
           
        } catch (IOException ex) {

        }
    }

    @FXML
    private void Add_Entrepot(MouseEvent event) {
        try {
            AnchorPane root=FXMLLoader.load(getClass().getResource("Add_Entrepot.fxml"));
         anchorpane.getChildren().setAll(root);
           
        } catch (IOException ex) {

        }
    }

    public List<Entrepot> getTableData() {
        List<Entrepot> DataList = new ArrayList<>();
        try {
            this.cnx = DataSource.getInstance().getCon();
            String sql = "select * from entrepot limit " + from + "," + to;
            stmt = cnx.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                DataList.add(new Entrepot(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return DataList;

    }

    @FXML
    private void ConvertPDF(MouseEvent event) {
        this.download("file");
    }

    @FXML
    private void Product_List(MouseEvent event) throws IOException {

     AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/ProductFXML.fxml"));
        anchorpane.getChildren().setAll(root);
    }

    @FXML
    private void search1(KeyEvent event) {
        
    }

    public void download(String file) {
          Document document = new Document(PageSize.A4);
        document.addAuthor("brahim");
        document.addTitle("Facture");
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file+".pdf"));
            document.open();
            System.out.println("aaaa");
            
        try {
            this.cnx = DataSource.getInstance().getCon();
            String sql = "select * from entrepot ";
            stmt = cnx.createStatement();
            res = stmt.executeQuery(sql);
         
            while (res.next()) {
                oblist.add(new Entrepot(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7)));


            }
            
            for(Entrepot e: oblist){
                 Paragraph para = new Paragraph(e.toPdf());
                 document.add(para);}
        } catch (SQLException ex) {
            Logger.getLogger(EntrepotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
            
            document.close();
            System.out.println("Document generated");
        } catch (DocumentException ex) {
        } catch (FileNotFoundException ex) {
        }

    }

    private Node CreatePage(int PageIndex) {
        from = PageIndex * itemPerPage;
        to = itemPerPage;
        Table.setItems(FXCollections.observableArrayList(getTableData()));
        return Table;
    }

    @FXML
    private void seaa(ActionEvent event) {
        
              if(event.getSource().equals(KeyCode.ENTER)){
                  
         try {
            this.cnx = DataSource.getInstance().getCon();
            String sql = "select * from Entrepot limit 7";
            stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
               ob.add(new Entrepot(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
      
        FilteredList<Entrepot> filteredData = new FilteredList<>(ob, b -> true);
        sarch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(emp-> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                               	String lowerCaseFilter = newValue.toLowerCase();
                                int s = emp.getAddress().toLowerCase().indexOf(lowerCaseFilter); 
				int s1=emp.getPhone().toLowerCase().indexOf(lowerCaseFilter);
                                
                               int s2= emp.getPhone_bis().toLowerCase().indexOf(lowerCaseFilter);
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

             SortedList<Entrepot> sortedData = new SortedList<>(filteredData);
	     sortedData.comparatorProperty().bind(Table.comparatorProperty());
             Table.setItems(sortedData);                        
  
              }while(add.isPressed());}

    private void Social_Media(MouseEvent event) {
      try {
            AnchorPane root=FXMLLoader.load(getClass().getResource("Social_Media.fxml"));
         anchorpane.getChildren().setAll(root);
           
        } catch (IOException ex) {

        }
        
    }

    @FXML
    private void Stocks(MouseEvent event) throws IOException {
        
        AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/Stocks_AddFXML.fxml"));
        anchorpane.getChildren().setAll(root);
        
    }

    @FXML
    private void facebookRedirect(MouseEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/Facebook_Post.fxml"));
        anchorpane.getChildren().setAll(root);
          
    }

    @FXML
    private void TwitterRedirect(MouseEvent event) throws IOException {
AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/TwitterFxml.fxml"));
        anchorpane.getChildren().setAll(root);
        
    }

    @FXML
    private void searchByAddress(MouseEvent event) {
        
        
       if(sarch.getText().isEmpty()){
            try {
                
                AnchorPane root =FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
                anchorpane.getChildren().setAll(root);
           
        } catch (IOException ex) {

        }  }
       else{
       EntrepotService es=new EntrepotService();
       Table.setItems(es.findByName(sarch.getText())); 
       
       }
        
    }

    @FXML
    private void load(MouseEvent event) {
        
         try {
                
                AnchorPane root =FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
                anchorpane.getChildren().setAll(root);
           
        } catch (IOException ex) {

        } 
    }

    @FXML
    private void takev(MouseEvent event) {
        
          Entrepot person = Table.getSelectionModel().getSelectedItem();
          id=person.getId_entrepot();
        if (person == null) {
            JOptionPane.showMessageDialog(null, "No Selected Item", "message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

       try {
           // AnchorPane root=FXMLLoader.load(getClass().getResource("Edit_Entrepot.fxml"));
        // anchorpane.getChildren().setAll(root);
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit_Entrepot.fxml"));
            Parent root = loader.load();
            Edit_EntrepotController controller = (Edit_EntrepotController) loader.getController();
            controller.SetItems(person);
            anchorpane.getChildren().setAll(root);

           
        } catch (IOException ex) {

        }
    }
    
        
    
    }