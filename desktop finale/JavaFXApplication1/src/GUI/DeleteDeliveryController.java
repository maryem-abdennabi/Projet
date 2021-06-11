package GUI;

import Service.DeliveryService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class DeleteDeliveryController implements Initializable {

    @FXML
    private AnchorPane anchropane;
    @FXML
    private TextField txt_idvehicule;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        DeliveryService vs=new DeliveryService();
        int id =Integer.parseInt(txt_idvehicule.getText());
        int opt=JOptionPane.showConfirmDialog(null, "Are you sure to delete", "Delete Vehicule", JOptionPane.YES_NO_OPTION);
        if(opt==0)
        {
        vs.delete(id);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("AfficherDelivery.fxml"));
         anchropane.getChildren().setAll(root);
    }
    
}