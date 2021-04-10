package front;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShipSettingController implements Initializable {
    @FXML private AnchorPane shipSetPan = new AnchorPane();
    @FXML private VBox vBox = new VBox();
    @FXML private HBox hBox1 = new HBox();
    @FXML private HBox hBox2 = new HBox();
    @FXML private HBox hBox3 = new HBox();
    @FXML private HBox hBox4 = new HBox();
    @FXML private HBox hBox5 = new HBox();
    @FXML private HBox hBox6 = new HBox();
    @FXML private HBox hBox7 = new HBox();
    @FXML private HBox hBox8 = new HBox();
    @FXML private HBox hBox9 = new HBox();
    @FXML private HBox hBox10 = new HBox();



    public void dragNDrop(){
        System.out.println("тык");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void main(String[] args) {
        ShipSettingController shipSettingController = new ShipSettingController();


    }
}
