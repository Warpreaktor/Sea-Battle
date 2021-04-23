package front;

import com.core.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.core.Players.Player.getRandomName;


public class StartMenuController implements Initializable {
    @FXML private ComboBox chooseMap;
    @FXML private TextField playerName;
    @FXML private ImageView mainImage;
    @FXML private ImageView portrait;
    @FXML private Button nameGenerator;
    @FXML private Button nextPortrait;
    @FXML private Button previousPortrait;
    @FXML private AnchorPane mainPanel;

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
//        chooseMap.setItems(list);
//        chooseMap.setValue(list.get(0));
    }

    public final void toMainGameUI() {
//        String sizeMap = chooseMap.getSelectionModel().getSelectedItem().toString(); удалить
        App.SEA_BATTLE_GAME.getHuman().setName(playerName.getText());
        App.SEA_BATTLE_GAME.getHuman().setPortrait(portrait.getImage());
        App.brushShipSettingMenu();
        App.SEA_BATTLE_GAME.createCPUBattleField(App.SEA_BATTLE_GAME.getCPU());
        App.SEA_BATTLE_GAME.getCPU().shipsOnGame();
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
}
