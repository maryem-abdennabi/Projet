package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class TwitterFxmlController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label nbrefollowing;
    @FXML
    private Label createdAt;
    @FXML
    private Label screenName;
    @FXML
    private Label email;
    @FXML
    private Label nbrFollowers;
    @FXML
    private Button tweet;
    @FXML
    private Circle img;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        twitter4j.User user;
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthAccessToken("1232035847865802752-ScSdDPQsolkWMZkNivLhKDW05v5zkv");
        cb.setOAuthAccessTokenSecret("dO9mtNhPQKG7bXYQ3XtSMn5w1MdESMHVGDd1pNeiB0i17");
        cb.setOAuthConsumerSecret("Q5SH2rKBy8Kdi3WqAX94Jz890YMaN8r6Zt9QoYxbodOY1T8y0e");
        cb.setOAuthConsumerKey("YaGj2J9JH0ALYBWyh3XKazhv6");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitt = tf.getInstance();
        User userg;
        try {
            userg = twitt.showUser(twitt.getScreenName());
            name.setText(userg.getName());
            nbrefollowing.setText(String.valueOf(userg.getFriendsCount()));
            createdAt.setText(userg.getCreatedAt().toString());
            screenName.setText(userg.getScreenName());
            email.setText(userg.getEmail());
            nbrFollowers.setText(String.valueOf(userg.getFollowersCount()));
            String path = userg.getOriginalProfileImageURL();
            Image image = new Image(path);
            img.setFill(new ImagePattern(image));

        } catch (TwitterException ex) {
            Logger.getLogger(TwitterFxmlController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TwitterFxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Tweet_Post(MouseEvent event) {
       
            try {
            AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/Twitter_Post.fxml"));
        AnchorPane.getChildren().setAll(root);
            
        } catch (IOException ex) {

        }
           

    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         AnchorPane root=FXMLLoader.load(getClass().getResource("/GUI/EntrepotFXML.fxml"));
         AnchorPane.getChildren().setAll(root);
    }

}