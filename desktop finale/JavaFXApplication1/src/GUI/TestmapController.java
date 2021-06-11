package GUI;

import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;


/**
 * FXML Controller class
 *
 * @author ghassen
 */
public class TestmapController implements Initializable {

    @FXML
    private WebView webview;
    @FXML
    private TextField dep;
    @FXML
    private TextField def;
    private Double lng[]={10.171078,
10.760278,
10.603131,
10.098196,
11.112155,
10.096333,
9.87391,
8.784167,
10.73763,
10.193371,
8.836506,
10.781591,
10.451767,
9.181692,
8.780239,
8.704863,
11.062222,
9.484935,
8.13352,
9.370818,
8.969034,
11.219649,
10.142925,
10.701353,
10.636991,
10.218889,
10.505478,
10.097205,
10.833333,
10.1829,
10.1339,
10.1504,
10.1773,
10.1745,
10.1661,
10.1577,
10.1717,
10.1516,
10.1367,
10.1731,
10.1587,
10.1563
};
    private Double lat[]={36.806112,
34.740556,
35.860904,
33.881457,
33.503981,
35.678102,
37.274423,
34.425,
36.456058,
36.860117,
35.167578,
35.697731,
32.929674,
36.725638,
36.501136,
36.174239,
35.504722,
35.038234,
33.919683,
36.084966,
33.704387,
33.13783,
36.402907,
32.007992,
35.825388,
36.753056,
33.354947,
36.808029,
35.783333,
36.8397,
36.8612,
36.855,
36.8402,
36.8484,
36.8491,
36.8525,
36.8566,
36.847,
36.8956,
36.8898,
36.8616,
36.8646

};
    private String loc[]={"Tunis",
"Sfax",
"Hammam Sousse",
"Gabès",
"Zarzis",
"Kairouan",
"Bizerte",
"Gafsa",
"Nabeul",
"Ariana",
"Kasserine",
"Mnara",
"Tataouine",
"Béja",
"Jendouba",
"El Kef",
"Mahdia",
"Sidi Bouzid",
"Tozeur",
"Siliana",
"Kebili",
"Ben Gardane",
"Zaghouan",
"Dehiba",
"Sousse",
"Ben Arous",
"Medenine",
"Manouba",
"Monastir",
"Manzeh1",
"Manzeh2",
"Manzeh3",
"Manzeh4",
"Manzeh5",
"Manzeh6",
"Manzeh7",
"Manzeh8",
"Manzeh9",
"Esprit Prepa",
"Cité La Gazelle",
"Enaser2",
"Enaser"
    };
    @FXML
    private Button btn_retour;
    @FXML
    private AnchorPane AnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(dep,loc);
        TextFields.bindAutoCompletion(def,loc);
        WebEngine webEngine = webview.getEngine();
        File f = new File(getClass().getClassLoader().getResource("Testmap/map3.html").getFile());
        webEngine.load(f.toURI().toString());
        
        // TODO
    }    

    

    @FXML
    private void ajout(ActionEvent event) {
        int i=0;
        int k=0;
        for(int j=0;j<loc.length;j++)
        {
            if(dep.getText().equals(loc[j]))
            {
            i=j;
            }
            if(def.getText().equals(loc[j]))
            {
            k=j;
            }
        
        }
        
       Double distance=Math.sqrt(Math.pow((lng[i]-lng[k]),2)+Math.pow((lat[i]-lat[k]),2))*111.16;
       double prix=distance*3;
       

    
        
        
        WebEngine webEngine =webview.getEngine();
        webEngine.reload();
        webEngine.loadContent(
        "<form method=\"get\" action=\"https://www.google.com/maps\" target=\"mapframe\">"+
        "<input name=\"saddr\" type=\"hidden\" id=\"saddr\" value=\"'"+loc[k]+"'\">"+
        "<input name=\"output\" type=\"hidden\" value=\"embed\">"+
        "<input name=\"f\" type=\"hidden\" value=\"d\">"+
        "<input name=\"z\" type=\"hidden\" value=\"11\">"+
        "<input name=\"daddr\" type=\"hidden\" id=\"daddr\" value=\"'"+loc[i]+"'\">"+
        "<input type=\"submit\" name=\"Submit\" value=\"Submit\">"+
        "</form>"+
        "<iframe"+
        "name=\"mapframe\" width=\"800\" height=\"900\""+ 
        "src=\"https://www.google.com/maps?z=11&amp;f=d&amp;output=embed&amp;ll='"+lat[i]+"','"+lng[i]+"'\">"
        +"</iframe>");
      
        //Alert alert = new Alert(Alert.AlertType.NONE, "votre trajet est: "+distance+"\n"+"prix a payer: "+prix, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        //alert.show();
        //System.out.println(lat.length+" "+loc.length+" "+lng.length);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          AnchorPane root=FXMLLoader.load(getClass().getResource("AfficherDelivery.fxml"));
          AnchorPane.getChildren().setAll(root);
         
         //webview.getChildren().setAll(root);
         //webview.getChildrenUnmodifiable().setAll(root);
            
        
    }
    
    
}