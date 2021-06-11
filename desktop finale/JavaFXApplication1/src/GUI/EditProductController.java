package GUI;

import Entity.Product;
import Entity.Stocks;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Connexion.DataSource;
import Connexion.image;
import Entity.Entrepot;
import Entity.Product1;
import Service.EntrepotService;
import Service.StockService;
import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EditProductController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField type;
    @FXML
    private TextField reference;
    @FXML
    private TextField marque;
    @FXML
    private TextField priceHT;
    @FXML
    private TextField priceTTC;
    @FXML
    private TextField Weight;
    @FXML
    private TextField TVA;
    @FXML
    private Button edit;

    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    private int ID;
    @FXML
    private TextField id;
    @FXML
    private Button delete;
    @FXML
    private Label entrepots;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label retour;
    @FXML
    private TextField text6;
    @FXML
    private Button choose;
    @FXML
    private ComboBox<String> available;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setVisible(false);

    }

    public void SetItems(Entity.Product1 p) {
        StockService stockService=new StockService();
        EntrepotService entrepotService=new EntrepotService();
        String ch="";
        for(Stocks s: stockService.displayByIdProduct(p.getId_product())){
            System.out.println(entrepotService.findById(s.getId_entrepot()));
            ch+=entrepotService.findById(s.getId_entrepot()).toString()+"\n";
            
        }
        available.setValue("Product available in");
        available.getItems().setAll(ch);
//        entrepots.setText(ch);
        name.setText(p.getProduct_name());
        type.setText(p.getProduct_type());
        reference.setText(p.getReference());
        marque.setText(p.getMarque());
        priceHT.setText(String.valueOf(p.getPriceHT()));
        priceTTC.setText(String.valueOf(p.getPriceTTC()));
        Weight.setText(String.valueOf(p.getWeight()));
        TVA.setText(String.valueOf(p.getTVA()));
        id.setText(String.valueOf(p.getId_product()));
        try {
            this.cnx = DataSource.getInstance().getCon();
            //String req="SELECT photo from product where WHERE id_product='"+ ProductFXMLController.id +"'";
            String req="Select photo from product where id_product='"+ProductFXMLController.id +"'";
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
              while (res.next()) {
           String PhotoName=res.getString("photo");
            text6.setText(PhotoName);
                  System.out.println(PhotoName);
              }
        } catch (SQLException ex) {
            Logger.getLogger(EditProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        id.setEditable(false);
    }

    @FXML
    private void Edit_Product(MouseEvent event) {
        
        
          if((name.getText().isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Name field is empty ");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
       else if((type.getText().isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Type field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
        else if((reference.getText().isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Reference field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
         else if((marque.getText().isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Marque field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
        
        else if((!priceHT.getText().matches("[-+]?[0-9]*\\.?[0-9]+"))&&(priceHT.getText().isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("PriceHT must contain only float or integer values");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
         
        else if((!priceTTC.getText().matches("[-+]?[0-9]*\\.?[0-9]+"))&&(priceTTC.getText().isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("PriceTTC must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         else if((!Weight.getText().matches("[-+]?[0-9]*\\.?[0-9]+"))&&(Weight.getText().isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Weight must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         else if((!TVA.getText().matches("[-+]?[0-9]*\\.?[0-9]+"))&&(TVA.getText().isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("TVA must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         
         else{
    

        try {
            
            this.cnx = DataSource.getInstance().getCon();
            String photo=text6.getText().toString();
            if(photo.isEmpty()){
            PreparedStatement stmt = cnx.
                    prepareStatement("UPDATE product SET  product_name='" + name.getText() + "', product_type='" + type.getText() + "', reference='" + reference.getText() + "', marque='" + marque.getText() + "', priceHT='" + priceHT.getText() + "', priceTTC='" + priceTTC.getText() + "', TVA='" + TVA.getText() + "', weight='" + Weight.getText() + "', photo='" +"no.png"+ "' WHERE id_product='" + ProductFXMLController.id + "'");
            stmt.executeUpdate();
            
             Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Updated Product successfully");
            a.setHeaderText("Success");
            a.show();
            }
            else{
            PreparedStatement stmt = cnx.
                    prepareStatement("UPDATE product SET  product_name='" + name.getText() + "', product_type='" + type.getText() + "', reference='" + reference.getText() + "', marque='" + marque.getText() + "', priceHT='" + priceHT.getText() + "', priceTTC='" + priceTTC.getText() + "', TVA='" + TVA.getText() + "', weight='" + Weight.getText() + "', photo='" + photo + "' WHERE id_product='" + ProductFXMLController.id + "'");
            stmt.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Updated Product successfully");
            a.setHeaderText("Success");
            a.show();            }          

        } catch (Exception ex) {
            ex.printStackTrace();

        }
         try {
            AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/ProductFXML.fxml"));
        AnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {

        }

    }
    }
    @FXML
    private void Delete_Product(MouseEvent event) {
        
        String sql = "delete from product where id_product= '"+ ProductFXMLController.id+"'";

        try {
            this.cnx = DataSource.getInstance().getCon();
            pst = cnx.prepareStatement(sql);
            pst.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Product deleted successfully");
            a.setHeaderText("Success");
            a.show();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
          
        }
        
         try {
             AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/ProductFXML.fxml"));
        AnchorPane.getChildren().setAll(root);
            
        } catch (IOException ex) {

        }
        
    }
      private void showAlertWithHeaderText(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Path");
        alert.setHeaderText("Error:");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/ProductFXML.fxml"));
         AnchorPane.getChildren().setAll(root);
    }

    @FXML
    private void capture(MouseEvent event) throws IOException {
         String nom=text6.getText().toString();
        Webcam webcam = Webcam.getDefault();
webcam.open();
ImageIO.write(webcam.getImage(), "PNG", new File("C:\\Users\\Ibrahim\\Desktop\\img\\"+nom+".png"));
webcam.close();
    }

    @FXML
    private void upload(ActionEvent event) throws IOException {
         image img=new image();
      img.filen();
      String path=img.getp();
      if(path==null)
      {
       showAlertWithHeaderText("path is null");
      }
else
    {
        
       // String str = "C:/Users/hp/Desktop/photo/italy.jpg";
        int startIndex = path.indexOf("");
        int endIndex = path.lastIndexOf("\\");
        String replacement = "";
        String toBeReplaced = path.substring(startIndex, endIndex+1);
        System.out.println(path.replace(toBeReplaced, replacement));
        String namePhoto=path.replace(toBeReplaced, replacement);
        //System.out.println(str.substring(startIndex+1, endIndex));
    text6.setText(namePhoto);
    
    Path src = Paths.get(path); 
    Path dest = Paths.get("C:/wamp64/www/pidev/web/uploads/post/"+namePhoto);
    Files.copy(src, dest);

    }
      
    }

}
