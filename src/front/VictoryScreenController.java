package front;

import com.core.MapObjects.Chest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.core.Players.Difficult.*;

public class VictoryScreenController implements Initializable {
    private Chest chest;
    private boolean chestIsOpen = false;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    ImageView chest1;
    @FXML
    ImageView chest2;
    @FXML
    ImageView chest3;
    @FXML
    TextFlow textFlow;
    @FXML
    VBox textBox;
    @FXML
    Button toMainMenu;
    @FXML
    Button nextLevel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/victoryScreen.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        anchorPane.setBackground(new Background(backgroundImage));
    }

    @FXML
    private void toMainMenu() throws IOException {
        App.getAPP().resetGame(NORMAL);
    }
    @FXML
    private void nextLevel() throws IOException {
        if (App.SEA_BATTLE_GAME.getDifficult()==EASY)App.getAPP().resetGame(NORMAL);
        if (App.SEA_BATTLE_GAME.getDifficult()==NORMAL)App.getAPP().resetGame(HARD);
        if (App.SEA_BATTLE_GAME.getDifficult()==HARD)App.getAPP().resetGame(DIVINE);
        if (App.SEA_BATTLE_GAME.getDifficult()==DIVINE)App.getAPP().resetGame(DIVINE);
    }

    @FXML
    private void chestOpened(){
        if (textBox.getChildren().size()>0) {
            for (int i = 4; i >= 0; i--) {
                textBox.getChildren().remove(i);
            }
        }
        if (!chestIsOpen) {
            chest = new Chest();
            textBox.getChildren().addAll(
                    chest.getItems().get(0),
                    chest.getItems().get(1),
                    chest.getItems().get(2),
                    chest.getItems().get(3),
                    chest.getItems().get(4));
            chestIsOpen = true;
        }
    }

}
