/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Connexion.Cryptage;
import Connexion.DataSource;
import Connexion.image;
import Entity.Reclamation;
import Entity.Reservermatriel;
import Entity.Staff;
import Entity.User;
import Entity.Usermaster;
import static GUI.Update_ManagementFXMLController.isEmailAdress;
import Service.ReclamationService;
import Service.StaffService;
import Service.UserService;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class home2Controller implements Initializable {

    @FXML
    private Label label;
    @FXML
    private AnchorPane list;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    public AnchorPane list200;
    @FXML
    private VBox box;
    @FXML
    private Button bot;
    @FXML
    private Button bot1;
    @FXML
    private Button bot2;
    private Button bot3;
    private Button bot4;
    private Button bot5;
    @FXML
    private Button bot6;
    @FXML
    private Button bot61;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    UserService us;
    StaffService st;
    @FXML
    private JFXTextField txtf1;
    @FXML
    private JFXButton jbot1;
    private JFXButton jbot2;
    @FXML
    private JFXButton jbot3;
    @FXML
    private JFXButton jbot4;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img3;
    @FXML
    private TableView<Usermaster> table;
    @FXML
    private TableColumn<Usermaster, String> firstname;
    @FXML
    private TableColumn<Usermaster,String> lastname;
    @FXML
    private TableColumn<Usermaster, String> type;
    @FXML
    private TableColumn<Usermaster, String> mail;
    @FXML
    private TableColumn<Object, ImageView> photo;
    @FXML
    private TableColumn<Usermaster, Integer> number;
    @FXML
    private TableView<Staff> tableview;
    @FXML
    private TableColumn<Staff, String> Fname;
    @FXML
    private TableColumn<Staff, String> Lname;
    @FXML
    private TableColumn<Staff, Float> salary;
    @FXML
    private TableColumn<Staff, String> post;
    @FXML
    private TableColumn<Staff, String> rib;
    @FXML
    private TableColumn<Staff, Float> prime;
    @FXML
    private TableColumn<Staff, Integer> statut;
    @FXML
    private TableColumn<Staff, Date> date;
    @FXML
    private TableColumn<Staff, Integer> nb_con;
    @FXML
    private TableColumn<Staff, Integer> nb_heur;
    @FXML
    private TableColumn<Staff, Integer> phone;
    @FXML
    private Pane anchorpane21;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private ChoiceBox box1;
    @FXML
    private ChoiceBox box2;
    @FXML
    private Button bot21;
    @FXML
    private TextField txt3;
    @FXML
    private ChoiceBox box3;
    @FXML
    private TextField txt4;
    @FXML
    private TextField txt5;
    @FXML
    private TextField txt51;
    @FXML
    private Pane anchorpane2;
    @FXML
    private TextField text1;
    @FXML
    private TextField text11;
    @FXML
    private ChoiceBox choiceb1;
    @FXML
    private ChoiceBox choiceb2;
    @FXML
    private TextField text111;
    @FXML
    private Button button2;
    @FXML
    private TextField text11111;
    @FXML
    private DatePicker picker;
    @FXML
    private TextField text1111;
    @FXML
    private AnchorPane updte;
    @FXML
    private TextField txt12;
    @FXML
    private TextField txt11;
    @FXML
    private TextField txt111;
    @FXML
    private TextField txt112;
    @FXML
    private TextField txt1121;
    @FXML
    private Button bot22;
    ObservableList<Integer> list2=FXCollections.observableArrayList(0,1);
    ObservableList<Float> list3=FXCollections.observableArrayList(500.f,800.f,950.f,1000.f,1200.f);
    ObservableList<String> list1=FXCollections.observableArrayList("chauffeur","chef atelier","Technicien electricité","technicien mécanique","nettyoage","ouvrier");
    @FXML
    private JFXButton jbot5;
    @FXML
    private ImageView img5;
    @FXML
    private Text txt;
    @FXML
    private Button bot31;
    @FXML
    private ChoiceBox<String> choice;
    @FXML
    private Pane pane;
    public String nom;
    public String prenom;
    public String RIB;
    public String REF;
    public Date date1;
    public int ID;
    @FXML
    private JFXButton jbot6;
    @FXML
    private ImageView img6;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private JFXButton jbot11;
    @FXML
    private JFXButton jbot41;
    @FXML
    private JFXButton jbot31;
    @FXML
    private JFXButton jbot21;
    @FXML
    private ImageView img11;
    @FXML
    private ImageView img21;
    @FXML
    private ImageView img31;
        StaffService sf;
    @FXML
    private PieChart chart;
    @FXML
    private JFXTextField txtf2;
    @FXML
    private TableColumn<Staff, String> refrence;
    @FXML
    private Pane panedel;
    @FXML
    private Text txt6;
    @FXML
    private ChoiceBox choiceref;
    @FXML
    private Button del;
private ObservableList<Usermaster> data;
    @FXML
    private Button botmat;
    @FXML
    private TableView<Reservermatriel> tableres;
    @FXML
    private TableColumn<Reservermatriel, String> stfname;
    @FXML
    private TableColumn<Reservermatriel, String> matname;
    @FXML
    private TableColumn<Reservermatriel, Date> dateres;
    @FXML
    private TableColumn<Reservermatriel, Date> dateret;
    @FXML
    private VBox boxGestionnaire;
    @FXML
    private Button botstock;
    @FXML
    private Button botProd;
    @FXML
    private Button botFin;
    @FXML
    private Button botupd;
    @FXML
    private Button btn_vehicule;
    @FXML
    private Button btn_delivery;
    @FXML
    private AnchorPane anchorpanel1;
    @FXML
    private VBox boxTransp;
    @FXML
    private Button botupd1;
   
    @FXML
    private AnchorPane anchorpanel2;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableres.setVisible(false);
        panedel.setVisible(false);
//       paneTransport.setVisible(false);
        sf=new StaffService();    
       pane1.setVisible(false);
        chart.setVisible(false);
        anchorpanel2.setVisible(false);
       pane2.setVisible(false);
        pane.setVisible(false);
        table.setVisible(false);
        tableview.setVisible(false);
        anchorpane2.setVisible(false);
        anchorpane21.setVisible(false);
        txtf1.setVisible(false);
        txtf2.setVisible(false);
        updte.setVisible(false);
        us=new UserService();
        st=new StaffService();
        firstname.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("userName"));
lastname.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("userfname"));
  type.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("userType"));
  mail.setCellValueFactory(new PropertyValueFactory<Usermaster,String>("mail"));
  photo.setCellValueFactory(new PropertyValueFactory<Object,ImageView>("userPhoto"));
  number.setCellValueFactory(new PropertyValueFactory<Usermaster,Integer>("number"));
        if(FXMLIdentificationController.nom!=null && FXMLIdentificationController.prenom!=null && FXMLIdentificationController.usr_type!=null)
        {
        lab1.setText(FXMLIdentificationController.nom+" "+FXMLIdentificationController.prenom);
        if(FXMLIdentificationController.usr_type.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
        {
        lab2.setText("ADMIN");}
        if(FXMLIdentificationController.usr_type.equals("a:1:{i:0;s:19:\"ROLE_AGENTFINANCIER\";}"))
        {
        lab2.setText("AGENT_FINANCIER");}
        if(FXMLIdentificationController.usr_type.equals("a:1:{i:0;s:19:\"ROLE_AGENTTRANSPORT\";}"))
        {
        lab2.setText("AGENT_TRANSPORT");}
        if(FXMLIdentificationController.usr_type.equals("a:1:{i:0;s:17:\"ROLE_GESTIONNAIRE\";}"))
        {
        lab2.setText("AGENT_GESTIONNAIRE");}
    }    
     if(FXMLInscriptionController.nom!=null && FXMLInscriptionController.prenom!=null && FXMLInscriptionController.usr_type!=null)
        {
      
        lab1.setText(FXMLInscriptionController.nom+" "+FXMLInscriptionController.prenom);
        lab2.setText(FXMLInscriptionController.usr_type);
        
    }    
     String type="";
        String type1="";
        String ad="ROLE_ADMIN";
        String agf="ROLE_AGENTFINANCIER";
        String agt="ROLE_AGENTTRANSPORT";
        String agg="ROLE_GESTIONNAIRE";
        type=FXMLIdentificationController.usr_type;
        type1=FXMLInscriptionController.usr_type;
        if(type!=null)
        {
        if(type.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
        {
            box.setVisible(true);
            boxGestionnaire.setVisible(false);
            boxTransp.setVisible(false);
        }
        if(type.equals("a:1:{i:0;s:19:\"ROLE_AGENTFINANCIER\";}"))
        {
            bot.setVisible(false);
            bot1.setVisible(false);
            bot2.setVisible(false);
            bot3.setVisible(false);
            bot6.setVisible(false);
            bot5.setVisible(false);
        }
        if(type.equals("a:1:{i:0;s:19:\"ROLE_AGENTTRANSPORT\";}"))
        {
            boxTransp.setVisible(true);
            box.setVisible(false);
            boxGestionnaire.setVisible(false);
        } 
        if(type.equals("a:1:{i:0;s:17:\"ROLE_GESTIONNAIRE\";}"))
        {
             boxTransp.setVisible(false);
            box.setVisible(false);
            boxGestionnaire.setVisible(true);
        }   
        }
        if(type1!=null)
        {
              if(type1==ad)
        {
             boxTransp.setVisible(false);
            box.setVisible(true);
            boxGestionnaire.setVisible(false);
        }
        if(type1==agf)
        {
            bot.setVisible(false);
            bot1.setVisible(false);
            bot2.setVisible(false);
            bot3.setVisible(false);
            bot6.setVisible(false);
            bot5.setVisible(false);
        }
        if(type1==agt)
        {
            boxTransp.setVisible(true);
            box.setVisible(false);
            boxGestionnaire.setVisible(false);
        } 
        if(type1==agg)
        {
            boxTransp.setVisible(false);
            box.setVisible(false);
            boxGestionnaire.setVisible(true);
        }   
        }
        
        try {
            label1.setText(us.number()+" users");
            label2.setText(st.nombre()+" Staff");
        } catch (SQLException ex) {
            Logger.getLogger(home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    @FXML
    private void handleButtonAction(MouseEvent event) {
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                           Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                           stage.show();
                           Stage stage1 = (Stage) label.getScene().getWindow(); 
                           stage1.close();
                          } catch (IOException ex) {
                                Logger.getLogger(FXMLInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    FXMLIdentificationController.usr_type=null;
    FXMLInscriptionController.usr_type=null;
    FXMLInscriptionController.prenom=null;
    FXMLInscriptionController.nom=null;
    FXMLIdentificationController.nom=null;
    FXMLIdentificationController.prenom=null;
        FXMLIdentificationController.id=0;

    }

    @FXML
    private void User_Management(MouseEvent event) {
    box.setVisible(false);
    panedel.setVisible(false);
    list.setVisible(false);
    pane2.setVisible(false);
    chart.setVisible(false);
    pane1.setVisible(true);
    table.setVisible(false);
    tableview.setVisible(false);
    anchorpanel2.setVisible(false);
    anchorpane2.setVisible(false);
    anchorpane21.setVisible(false);
    txtf1.setVisible(false);
    txtf2.setVisible(false);
    updte.setVisible(false);
    pane.setVisible(false);        
    tableres.setVisible(false);
        list200.setVisible(false);

    }

    @FXML
    private void Worker_management(MouseEvent event) {
    box.setVisible(false);
    list.setVisible(false);
    anchorpanel2.setVisible(false);
    chart.setVisible(false);
    pane1.setVisible(false);
    pane2.setVisible(true);
    table.setVisible(false);
    tableview.setVisible(false);
    anchorpane2.setVisible(false);
    anchorpane21.setVisible(false);
        list200.setVisible(false);

    txtf1.setVisible(false);
    txtf2.setVisible(false);
    updte.setVisible(false);
    pane.setVisible(false);
    panedel.setVisible(false);
    }

    @FXML
    private void Purchase_management(MouseEvent event) throws IOException {
    tableres.setVisible(false);
    updte.setVisible(false);        
     anchorpane2.setVisible(false);
    anchorpanel1.setVisible(false);
     anchorpane21.setVisible(false);
    list.setVisible(false);
        list200.setVisible(true);
        AnchorPane root=FXMLLoader.load(getClass().getResource("MainAchatFXML.fxml"));
        list200.getChildren().setAll(root);
    }

    @FXML
    private void Provider_management(MouseEvent event) throws IOException {
    tableres.setVisible(false);
     anchorpane2.setVisible(false);
    anchorpane21.setVisible(false);
    list.setVisible(false);
    updte.setVisible(false);
        list200.setVisible(true);
anchorpanel1.setVisible(false);
        AnchorPane root=FXMLLoader.load(getClass().getResource("MainFournisseurs.fxml"));
        list200.getChildren().setAll(root);
    }

    @FXML
    private void Financial_management(MouseEvent event) {
    tableres.setVisible(false);
    }

    @FXML
    private void Complaint_management(MouseEvent event) throws IOException {
    anchorpanel1.setVisible(false);
    list.setVisible(false);
    anchorpanel2.setVisible(true);
    pane1.setVisible(false);
    pane2.setVisible(false);
    table.setVisible(false);
    tableview.setVisible(false);
    anchorpane2.setVisible(false);
    anchorpane21.setVisible(false);
    txtf1.setVisible(false);
    txtf2.setVisible(false);
    updte.setVisible(false);
    pane.setVisible(false);
    panedel.setVisible(false);
    AnchorPane root=FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
        anchorpanel2.getChildren().setAll(root);
    
    }

    @FXML
    private void update(MouseEvent event) {
        updte.setVisible(true);
            anchorpanel2.setVisible(false);
        list.setVisible(false);
        pane2.setVisible(false);
        pane1.setVisible(false);
        table.setVisible(false);
        list200.setVisible(false);
        tableview.setVisible(false);
        tableres.setVisible(false);
        anchorpane2.setVisible(false);
        anchorpane21.setVisible(false);
        txtf1.setVisible(false);
        pane.setVisible(false);
        panedel.setVisible(false);
        anchorpanel1.setVisible(false);
    }

    @FXML
    private void afficher(MouseEvent event) throws SQLException {
    String nom=txt2.getText();
        String prenom=txt1.getText();
        ResultSet rs=sf.display(""+nom+"", ""+prenom+"");
        float sal=0.f;
        float prim=0.f;
        String post="";
        int status=0;
        int phone=0;
        int nb_leave=0;
        int nb_heure=0;
        while(rs.next())
        {
            prim=rs.getFloat(7);
            sal=rs.getFloat(4);
            post=rs.getString(5);
            status=rs.getInt(8);
            phone=rs.getInt(12);
            nb_leave=rs.getInt(10);
            nb_heure=rs.getInt(11);
            date1=rs.getDate(9);
            RIB=rs.getString(6);
            ID=rs.getInt(1);     
            REF=rs.getString(13);
                    
        }
        box1.setValue(sal);
        box2.setValue(post);
        txt51.setText(""+prim);
        String num=""+phone;
        txt3.setText(num);
        box3.setValue(status);
        txt4.setText(""+nb_leave);
        txt5.setText(""+nb_heure);
        box1.setItems(list3);
        box2.setItems(list1);
        box3.setItems(list2);
    }

    @FXML
    private void modify2(ActionEvent event) {
     String F_name=txt12.getText().toString();
    String mail=txt11.getText().toString();
    String Numero=txt111.getText().toString();
    String pass=txt112.getText().toString();
    String photo=txt1121.getText().toString();
     if(F_name.length()!=0 && mail.length()!=0 && Numero.length()!=0 && photo.length()!=0 && pass.length()!=0)
   {
        if(pass.length()>=8)
        {
            if(Numero.length()==8 && Numero.matches("^[0-9]+$"))
            {
                if(isEmailAdress(mail))
                {
                String bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(13,pass.toCharArray());
  
                UserService usrs=new UserService();  
                if(FXMLIdentificationController.id !=0 && FXMLIdentificationController.usr_type!=null)
                {
                 if(usrs.update(new User(FXMLIdentificationController.id,F_name,F_name,mail,mail,bcryptChars,Integer.parseInt(Numero),photo))==true)
                {
                   JOptionPane.showMessageDialog(null, "            success");
               }
               else{
                   showAlertWithHeaderText("Error");
               }
            }
              if(FXMLInscriptionController.nom !=null && FXMLInscriptionController.usr_type!=null)
                {
               if(usrs.update2(new User(F_name,F_name,mail,mail,bcryptChars,Integer.parseInt(Numero),photo),FXMLInscriptionController.nom,FXMLInscriptionController.prenom)==true)
               {
                   JOptionPane.showMessageDialog(null, "            success");
               }
               else{
                   showAlertWithHeaderText("Error");
               }   
                }
                }
                else{
                showAlertWithHeaderText("invalid mail address");
            }
            }
             else{
                showAlertWithHeaderText("invalid phone number");
            }
    }   
     else{
            showAlertWithHeaderText("the size of password should be greater then 8 character");
               }
   }
     else
     {
         showAlertWithHeaderText("all fields should be not null");
     }
     pane.setVisible(false);
    }
      private void showAlertWithHeaderText(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ADD");
        alert.setHeaderText("Results:");
        alert.setContentText(msg);
        alert.showAndWait();
    }
   public static boolean isEmailAdress(String email){
Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
Matcher m = p.matcher(email.toUpperCase());
return m.matches();
}
    @FXML
    private void capture(MouseEvent event) throws IOException {
     String nom=txt12.getText().toString();
        Webcam webcam = Webcam.getDefault();
webcam.open();
ImageIO.write(webcam.getImage(), "PNG", new File("C:\\Users\\HP\\Desktop\\img\\"+nom+".png"));
webcam.close();
    }

    @FXML
    private void ajouterIm(ActionEvent event) {
 image img=new image();
      img.filen();
      String pth=img.getp();
      if(pth==null)
      {
       showAlertWithHeaderText("path is null");
      }
else
{
    txt1121.setText(pth);
    }
    }

    @FXML
    private void home(MouseEvent event) {
        box.setVisible(true);
        list.setVisible(true);
        panedel.setVisible(false);
        table.setVisible(false);
        txtf2.setVisible(false);
        tableview.setVisible(false);
        anchorpane2.setVisible(false);
        anchorpane21.setVisible(false);
        txtf1.setVisible(false);
        chart.setVisible(false);
        updte.setVisible(false);
        pane.setVisible(false);
        pane1.setVisible(false);
        pane2.setVisible(false);
    }


    @FXML
    private void display(MouseEvent event) {
           pane.setVisible(false);
           pane2.setVisible(false);
           table.setVisible(true);
           txtf1.setVisible(true);
           panedel.setVisible(false);     
data = FXCollections.observableArrayList();
try{      
ResultSet rs=us.displayAll2();
while(rs.next()){
Usermaster cm = new Usermaster(); 
String imageUrl = "file:///C:/Users/Ibrahim/Desktop/img/"+rs.getString("username")+".png";
Image img = new Image(imageUrl);                
ImageView mv = new ImageView();
mv.setImage(img);
mv.setFitWidth(70);
mv.setFitHeight(80);
cm.userPhoto.set(mv);
cm.userName.set(rs.getString("name"));
cm.userfname.set(rs.getString("surname"));
cm.userType.set(rs.getString("roles"));
cm.mail.set(rs.getString("email"));
cm.number.set(rs.getInt("num_tel"));
data.add(cm);
}
table.setItems(data);
}
catch(Exception e){
showAlertWithHeaderText("Error on Building Data");            
}

  do
    {
        FilteredList<Usermaster> filteredData = new FilteredList<>(data, b -> true);
		
		txtf1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
			 String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (user.getUserfname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;	}
				else if (String.valueOf(user.getUserType()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
		
		SortedList<Usermaster> sortedData = new SortedList<>(filteredData);	
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
               
    }while(bot1.isPressed());
    }

    @FXML
    private void ajouter(MouseEvent event) {
        box.setVisible(false);
        list.setVisible(false);
        table.setVisible(false);
        tableview.setVisible(false);
        anchorpane2.setVisible(true);
        anchorpane21.setVisible(false);
        txtf1.setVisible(false);
         txtf2.setVisible(false);
        updte.setVisible(false);
        pane.setVisible(false);
        panedel.setVisible(false);
        chart.setVisible(false);
       choiceb1.setItems(list3);
        choiceb2.setItems(list1);
    }
    @FXML
    private void add(ActionEvent event) {
    String F_name=text1.getText().toString();
    String L_name=text11.getText().toString();
    String salary=choiceb1.getValue().toString();
    String post=choiceb2.getValue().toString();
    String rib=text111.getText().toString();
    String number=text11111.getText().toString();
    String ref=text1111.getText().toString();
   if(F_name.length()!=0 && L_name.length()!=0 && salary.length()!=0 && post.length()!=0 && rib.length()!=0 && number.length()!=0 && ref.length()!=0)
   {
       if( number.length()==8)
       {
       float sal=Float.parseFloat(salary);    
       int numb=Integer.parseInt(number);
       LocalDate date=picker.getValue();
       Date date1 = java.sql.Date.valueOf(date);
    if(sf.insert(new Staff(F_name,L_name,sal,rib,post,date1,numb,ref))==true)
    {
        JOptionPane.showMessageDialog(null, "            success");
    }
    else{
        showAlertWithHeaderText("error");
    }
    }
       else{
           showAlertWithHeaderText("invalid phone number");
       }
   }
   else{
           showAlertWithHeaderText("all fields should be not null");
       
   }
    }

    @FXML
    private void stat(MouseEvent event) throws SQLException {
        updte.setVisible(false);
        list.setVisible(false);
        panedel.setVisible(false);
        pane1.setVisible(false);
        table.setVisible(false);
        tableview.setVisible(false);
        anchorpane2.setVisible(false);
        anchorpane21.setVisible(false);
        txtf1.setVisible(false);
        pane.setVisible(false); 
        chart.setVisible(true);
        chart.setTitle("Worker Statistics");
        ObservableList<PieChart.Data> piechart=FXCollections.observableArrayList(); 
        List<String> pren=new ArrayList<>();
        List<Integer>nb=new ArrayList<>();
        ResultSet rs=sf.displayNom_nb();
        while(rs.next())
        {
            piechart.add(new PieChart.Data(rs.getString(1),rs.getInt(2)));
        pren.add(rs.getString(1));
        nb.add(rs.getInt(2));
        
       }
       chart.setData(piechart);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        String mail=choice.getValue();
    if(choice.getValue().toString()!=null)
    {
        us.delete(mail);
        JOptionPane.showMessageDialog(null, "       user was deleted");
        choice.hide();
    }
    }

    @FXML
    private void supprimer1(MouseEvent event) {
        pane.setVisible(true);
        table.setVisible(false);
        tableview.setVisible(false);
        anchorpane2.setVisible(false);
        anchorpane21.setVisible(false);
        txtf1.setVisible(false);
        updte.setVisible(false);
        ObservableList<String> lst=FXCollections.observableArrayList();
        us=new UserService();
        ResultSet rs=us.displayemail();
        try {
            while(rs.next())
            {
              lst.add(rs.getString(1));
            }
        } catch (SQLException ex) {
           showAlertWithHeaderText("message"+ex); 
        }
           choice.setItems(lst);
        
    }

    @FXML
    private void display1(MouseEvent event) {
        chart.setVisible(false);
        anchorpane2.setVisible(false);
        tableview.setVisible(true);
        anchorpane21.setVisible(false);
        label.setVisible(true);
        txtf2.setVisible(true);
        txtf1.setVisible(false);
        chart.setVisible(false);
        pane.setVisible(false);
        panedel.setVisible(false);
         ObservableList<Staff> list=FXCollections.observableArrayList();
   List<Staff> lis=sf.displayAll();
    for(Staff ls:lis)
    {
        list.add(ls);
    }
  Fname.setCellValueFactory(new PropertyValueFactory<Staff,String>("prenom"));
  Lname.setCellValueFactory(new PropertyValueFactory<Staff,String>("nom"));
  salary.setCellValueFactory(new PropertyValueFactory<Staff,Float>("salary"));
  post.setCellValueFactory(new PropertyValueFactory<Staff,String>("post"));
  rib.setCellValueFactory(new PropertyValueFactory<Staff,String>("rib"));
  prime.setCellValueFactory(new PropertyValueFactory<Staff,Float>("prime"));
  statut.setCellValueFactory(new PropertyValueFactory<Staff,Integer>("statut"));
  date.setCellValueFactory(new PropertyValueFactory<Staff,Date>("date"));
  nb_con.setCellValueFactory(new PropertyValueFactory<Staff,Integer>("nb_c"));
  nb_heur.setCellValueFactory(new PropertyValueFactory<Staff,Integer>("nb_heur"));
  phone.setCellValueFactory(new PropertyValueFactory<Staff,Integer>("number"));
  refrence.setCellValueFactory(new PropertyValueFactory<Staff,String>("reference"));
  tableview.setItems(list);
    do
    {
        FilteredList<Staff> filteredData = new FilteredList<>(list, b -> true);
		
		txtf2.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(staff -> {
				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
			 String lowerCaseFilter = newValue.toLowerCase();
				
				if (staff.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (staff.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;	}
				else if (String.valueOf(staff.getSalary()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
		
		SortedList<Staff> sortedData = new SortedList<>(filteredData);	
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		tableview.setItems(sortedData);
               
    }while(jbot1.isPressed() );
    }
    
    @FXML
    private void supprim(MouseEvent event) {
       chart.setVisible(false);
       panedel.setVisible(true);
       anchorpane2.setVisible(false);
       anchorpane21.setVisible(false);
        tableview.setVisible(false);
        txtf2.setVisible(false);        
        ObservableList<String> lst=FXCollections.observableArrayList();
        
        ResultSet rs=sf.search();
        try {
            while(rs.next())
            {
              lst.add(rs.getString(13));
            }
        } catch (SQLException ex) {
            Logger.getLogger(home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
           choiceref.setItems(lst);
    }

    @FXML
    private void modify(MouseEvent event) {
    chart.setVisible(false);
    anchorpane21.setVisible(true);
    list.setVisible(false);
        panedel.setVisible(false);
        pane1.setVisible(false);
        table.setVisible(false);
        tableview.setVisible(false);
        anchorpane2.setVisible(false);
        txtf2.setVisible(false);
        txtf1.setVisible(false);
        pane.setVisible(false); 
    anchorpanel1.setVisible(false);
    }

    @FXML
    private void modify1(ActionEvent event) {
     String nom=txt2.getText();
        String prenom=txt1.getText();
        String sal=box1.getValue().toString();
        String post1=box2.getValue().toString();
        String status=box3.getValue().toString();
        String Number=txt3.getText();
        String nb_l=txt4.getText();
        String pr=txt51.getText();
        String nb_h=txt5.getText();
        float salaire=Float.parseFloat(sal);
         boolean r=sf.update(new Staff(ID, nom, prenom, salaire, post1, RIB, Float.parseFloat(pr),Integer.parseInt(status), date1, Integer.parseInt(nb_l),Integer.parseInt(nb_h), Integer.parseInt(Number),REF));
         if(r==true)
         {
             JOptionPane.showMessageDialog(null,"             succes");
         }
         else{
             showAlertWithHeaderText("error");
         }
    }

    @FXML
    private void deleteworker(ActionEvent event) {
     String ref=choiceref.getValue().toString();
    if(choiceref.getValue().toString()!=null)
    {
        sf.delete(ref);
        JOptionPane.showMessageDialog(null, "         user was deleted");
        choice.hide();
    }
    
    
    }

    @FXML
    private void show_reservation(MouseEvent event) {
           tableres.setVisible(true);
           list.setVisible(false);
            updte.setVisible(false);
            anchorpanel1.setVisible(false);
            list200.setVisible(false);       
 ObservableList<Reservermatriel> list1=FXCollections.observableArrayList();
   List<Reservermatriel> lis=sf.displayAllMat();
    for(Reservermatriel ls:lis)
    {
        list1.add(ls);
    }
  stfname.setCellValueFactory(new PropertyValueFactory<Reservermatriel,String>("ids"));
  matname.setCellValueFactory(new PropertyValueFactory<Reservermatriel,String>("idm"));
  dateres.setCellValueFactory(new PropertyValueFactory<Reservermatriel,Date>("date_res"));
  dateret.setCellValueFactory(new PropertyValueFactory<Reservermatriel,Date>("date_ret"));
  
  tableres.setItems(list1);
    }
    
     @FXML
    private void delivery(ActionEvent event) throws IOException {
    anchorpanel1.setVisible(true);
        AnchorPane root=FXMLLoader.load(getClass().getResource("AfficherDelivery.fxml"));
         anchorpanel1.getChildren().setAll(root);
    updte.setVisible(false);
    list.setVisible(false);
    }
     @FXML
    private void vehicule(ActionEvent event) throws IOException {
        anchorpanel1.setVisible(true);
        updte.setVisible(false);
        AnchorPane root=FXMLLoader.load(getClass().getResource("AfficherVehicule.fxml"));
         anchorpanel1.getChildren().setAll(root);
    list.setVisible(false);
    }

    @FXML
    private void Stock_management(MouseEvent event) throws IOException {
 panedel.setVisible(false);
       pane1.setVisible(false);
       //panecomp.setVisible(false);
        chart.setVisible(false);
       pane2.setVisible(false);
        pane.setVisible(false);
        table.setVisible(false);
        tableview.setVisible(false);
        anchorpane2.setVisible(false);
        anchorpane21.setVisible(false);
        txtf1.setVisible(false);
        txtf2.setVisible(false);
        updte.setVisible(false);
        //panegestion.setVisible(true);
        list200.setVisible(false);
        list.setVisible(false);
        AnchorPane root=FXMLLoader.load(getClass().getResource("EntrepotFXML.fxml"));
         anchorpanel1.getChildren().setAll(root);
         anchorpanel1.setVisible(true);
    }

   

    }
    

