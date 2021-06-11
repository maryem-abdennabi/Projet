package GUI;

import Entity.Vehicule;
import Service.VehiculeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AjoutVehiculeController implements Initializable {

    @FXML
    private TextField txt_matricule;
    @FXML
    private TextField txt_weight;
    @FXML
    private TextField txt_etat;
    @FXML
    private TextField txt_marque;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextArea txt_description;
    @FXML
    private Button btn_retour;
    @FXML
    private AnchorPane anchropane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterVehicule(ActionEvent event) throws SQLException {
        VehiculeService vs=new VehiculeService();
        int weight = Integer.parseInt(txt_weight.getText());
        vs.insert(new Vehicule(txt_matricule.getText(), weight,txt_etat.getText(),txt_marque.getText(),txt_description.getText()));
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("AfficherVehicule.fxml"));
         anchropane.getChildren().setAll(root);
    }
    
}