package GUI;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static GUI.Twitter_PostController.Path_File;
import static GUI.Twitter_PostController.status;
import Connexion.DataSource;
import Connexion.image;
import com.github.sarxos.webcam.Webcam;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Add_ProductController implements Initializable {

    @FXML
    private TextField jname;
    @FXML
    private TextField jtype;
    @FXML
    private TextField jreference;
    @FXML
    private TextField jmarque;
    @FXML
    private TextField jpriceHT;
    @FXML
    private TextField jpriceTTC;
    @FXML
    private TextField jWeight;
    @FXML
    private TextField jTVA;
    @FXML
    private Button Add_btn;
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    @FXML
    private AnchorPane add_form;
    @FXML
    private Button reset;
    static String name_File;
    static String Path_File;
    private Image image;
    private FileInputStream fis;
    private File file;
    @FXML
    private Label retour;
    @FXML
    private TextField text6;
    @FXML
    private Button choose;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void Add_Product(MouseEvent event) {
    
        String name = jname.getText();
        String type = jtype.getText();
        String reference = jreference.getText();
        String marque = jmarque.getText();
        String priceHT = jpriceHT.getText();
        String priceTTC = jpriceTTC.getText();
        String Weight = jWeight.getText();
        String TVA = jTVA.getText();
        String photo=text6.getText().toString();
        
        
       
      if((name.isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Name field is empty ");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
       else if((type.isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Type field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
        else if((reference.isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Reference field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
         else if((marque.isEmpty())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Marque field is empty");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
        
        else if((!priceHT.matches("[-+]?[0-9]*\\.?[0-9]+"))&&(priceHT.isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("PriceHT must contain only float or integer values");
            a.setHeaderText("Invalid Value");
            a.show();
            
 
        }
         
         else  if((!priceTTC.matches("[-+]?[0-9]*\\.?[0-9]+"))&&(priceTTC.isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("PriceTTC must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         else if((!Weight.matches("[-+]?[0-9]*\\.?[0-9]+"))&&(Weight.isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Weight must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         else if((!TVA.matches("[-+]?[0-9]*\\.?[0-9]+"))&&(TVA.isEmpty())){
        Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("TVA must contain only float or integer values");
             a.setHeaderText("Invalid Value");
            a.show();
 
        }
         
         else{
        try {
            
            
            
            this.cnx = DataSource.getInstance().getCon();
            String req="INSERT INTO product (product_name,product_type,reference,marque,priceHT,priceTTC,TVA,weight,photo)VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt=cnx.prepareStatement(req);
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, reference);
            stmt.setString(4, marque);
            stmt.setString(5, priceHT);
            stmt.setString(6, priceTTC);
            stmt.setString(7, Weight);
            stmt.setString(8, TVA);
            if(photo.isEmpty()){
            stmt.setString(9, "no.png");}
            else{
            stmt.setString(9, photo);}
            
               stmt.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Product Added successfully");
            a.setHeaderText("Success");
            a.show();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        
         try {
            AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/ProductFXML.fxml"));
        add_form.getChildren().setAll(root);
            
        } catch (IOException ex) {

        }
         }
        
    }

    @FXML
    private void Reset(MouseEvent event) {
    
    jname.setText("");
    jtype.setText("");
    jreference.setText("");
    jmarque.setText("");
    jpriceHT.setText("");
    jpriceTTC.setText("");
    jWeight.setText("");
    jTVA.setText("");
    }
    
    

private static boolean validateFloatNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\f")) return true;
		//validating phone number with -, . or spaces
		else return false;
		
	}

    private void add_photo(MouseEvent event) {
        
          final FileChooser dirchooser=new FileChooser();
        Stage stage=(Stage) add_form.getScene().getWindow();
        File file=dirchooser.showOpenDialog(stage);
         if(file!=null){
             status="nonvide";
         System.out.println(status);

             Path_File=file.getAbsolutePath();
             name_File=file.getName();

       
    }
         else{
         status="vide";
             System.out.println(status);
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
         add_form.getChildren().setAll(root);
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
    