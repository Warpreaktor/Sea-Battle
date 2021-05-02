package front;

import com.core.Players.Difficult;
import com.core.Tools;
import com.sun.media.jfxmedia.AudioClip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.core.Players.Player.getRandomName;


public class StartMenuController implements Initializable {
    @FXML private ComboBox<Difficult> chooseLevel;
    @FXML private TextField playerName;
    @FXML private ImageView mainImage;
    @FXML private ImageView portrait;
    @FXML private Button nameGenerator;
    @FXML private Button nextPortrait;
    @FXML private Button previousPortrait;
    @FXML private AnchorPane mainPanel;
    @FXML private CheckBox music;
    private AudioClip audioClip;

    private int portraitIndex;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/seaBattleMain800x600.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        mainPanel.setBackground(background);
        portraitIndex = Tools.getRandomNumber(0, 9);
        portrait.setImage(App.getAllPortraits()[portraitIndex]);

        chooseLevel.getItems().add(Difficult.EASY);
        chooseLevel.getItems().add(Difficult.NORMAL);
        chooseLevel.getItems().add(Difficult.HARD);
        //chooseLevel.getItems().add(Difficult.DIVINE);
        chooseLevel.setValue(Difficult.NORMAL);


    }

    public final void startShipSetting() {
        App.SEA_BATTLE_GAME.setDifficult(chooseLevel.getValue());
        App.SEA_BATTLE_GAME.getHuman().setName(playerName.getText());
        App.SEA_BATTLE_GAME.getHuman().setPortrait(portrait.getImage());
        App.brushShipSettingMenu();

    }

    public final void nameGenerator(){
        playerName.setText(getRandomName());
    }

    public final void nextPortrait(){
        portraitIndex+=1;
        if (portraitIndex >= App.getAllPortraits().length){
            portraitIndex = 0;
        }
        portrait.setImage(App.getAllPortraits()[portraitIndex]);
    }
    public final void previousPortrait(){
        portraitIndex-=1;
        if (portraitIndex < 0){
            portraitIndex = App.getAllPortraits().length-1;
        }
        portrait.setImage(App.getAllPortraits()[portraitIndex]);
    }
    public final void music(){
        App.SEA_BATTLE_GAME.music = !App.SEA_BATTLE_GAME.music;
    }
}
