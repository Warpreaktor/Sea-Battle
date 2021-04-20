package front;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static com.core.Players.Player.getRandomName;


public class StartMenuController implements Initializable {
    @FXML private ComboBox chooseMap;
    @FXML private TextField playerName;
    @FXML private ImageView imageView;
    @FXML private Button nameGenerator;

    ObservableList<String> list  = FXCollections.observableArrayList("10 на 10","20 на 20 (в разработке)");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseMap.setItems(list);
        chooseMap.setValue(list.get(0));
    }

    public final void toMainGameUI() {
        String sizeMap = chooseMap.getSelectionModel().getSelectedItem().toString();
        String name = playerName.getText();
        App.SEA_BATTLE_GAME.getHuman().setName(name);
        App.brushShipSettingMenu();
        App.SEA_BATTLE_GAME.createCPUBattleField(App.SEA_BATTLE_GAME.getCPU());
        App.SEA_BATTLE_GAME.getCPU().shipsOnGame();
    }

    public final void nameGenerator(){
        playerName.setText(getRandomName());
    }
}
