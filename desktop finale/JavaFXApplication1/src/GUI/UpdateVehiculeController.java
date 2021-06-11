package GUI;

import Entity.Vehicule;
import Service.VehiculeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import Connexion.DataSource;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class UpdateVehiculeController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_matricule;
    @FXML
    private TextField txt_weight;
    @FXML
    private AnchorPane anchropane;
    @FXML
    private Button btn_retour;
    @FXML
    private TextField txt_marque;
    @FXML
    private TextArea txt_description;
    @FXML
    private TextField txt_etat;
    private Vehicule v;
      ResultSet rs;
    Statement st;
    PreparedStatement pst;
    private Connection cnx;

    public Vehicule getV() {
        return v;
    }

    public void setV(Vehicule v) {
        this.v = v;
    }
    public void getData(Vehicule v)
    {
        txt_matricule.setText(v.getMatricule());
        txt_weight.setText(Integer.toString((int) v.getWeight()));
        txt_etat.setText(v.getEtat());
        txt_marque.setText(v.getMarque());
        txt_description.setText(v.getDescription());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) throws SQLException {
         
         VehiculeService vs=new VehiculeService();
        float weight = Float.parseFloat(txt_weight.getText());
        int var=Integer.parseInt(txt_id.getText());
        vs.update(new Vehicule(var,txt_matricule.getText(), weight,txt_etat.getText(),txt_marque.getText(),txt_description.getText()));
    
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(getClass().getResource("AfficherVehicule.fxml"));
         anchropane.getChildren().setAll(root);
    }

    @FXML
    private void update2(KeyEvent event) throws SQLException {
        int i=Integer.parseInt(txt_id.getText());
        cnx=DataSource.getInstance().getCon();
            String query="select * from vehicule where 	id_vehicule=?";
            
            pst=cnx.prepareStatement(query);
            pst.setInt(1,i);
            rs=pst.executeQuery();
              while(rs.next())
            {
                txt_description.setText(rs.getString(6));
                txt_etat.setText(rs.getString(4));
                txt_marque.setText(rs.getString(5));
                txt_matricule.setText(rs.getString(2));
                float t=rs.getFloat(3);
                String change=Float.toString(t);
                txt_weight.setText(change);
            }
        
    }
    
}