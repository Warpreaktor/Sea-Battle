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
    private ImageView chestView;
    @FXML
    private TextFlow textFlow;
    @FXML
    private VBox textBox;
    @FXML
    private Button toMainMenu;
    @FXML
    private Button nextLevel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/victoryScreen.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        anchorPane.setBackground(new Background(backgroundImage));
        if (App.SEA_BATTLE_GAME.getDifficult()==EASY) nextLevel.setText("Try next level " + NORMAL);
        if (App.SEA_BATTLE_GAME.getDifficult()==NORMAL) nextLevel.setText("Try next level " + HARD);
        if (App.SEA_BATTLE_GAME.getDifficult()==HARD) nextLevel.setText("Try again");
        //if (App.SEA_BATTLE_GAME.getDifficult()==DIVINE) nextLevel.setText("Try again");
        App.musicPlayer.stopLoop();
        if (App.musicPlayer.getPlayingTrack().isPlaying())App.musicPlayer.getPlayingTrack().stopPlaying();
    }

    @FXML
    private void toMainMenu() throws IOException {
        App.getAPP().restartGame(NORMAL);
    }
    @FXML
    private void nextLevel() throws IOException {
        if (App.SEA_BATTLE_GAME.getDifficult()==EASY){
            App.getAPP().restartGame(NORMAL);
        return;
        }
        if (App.SEA_BATTLE_GAME.getDifficult()==NORMAL){
            App.getAPP().restartGame(HARD);
            return;
        }
        if (App.SEA_BATTLE_GAME.getDifficult()==HARD){
            App.getAPP().restartGame(HARD);
            return;
        }
    }

    @FXML
    private void chestOpened(){
        if (!chestIsOpen) {
            chest = new Chest();
            textBox.getChildren().addAll(
                    chest.getItems().get(0),
                    chest.getItems().get(1),
                    chest.getItems().get(2),
                    chest.getItems().get(3),
                    chest.getItems().get(4));
            chestIsOpen = true;
            anchorPane.setBackground(new Background(new BackgroundImage(new Image("/resources/victoryScreenText.jpg"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
    }

}
