package front;

import com.core.Player;
import com.core.SeaBattleGame;
import com.core.Tools;
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

    public final void toMainGameUI() throws Exception {
        SeaBattleGame seaBattleGame = new SeaBattleGame(chooseMap.getSelectionModel().getSelectedItem().toString());
        Player player1 = new Player(playerName.getText());
        System.out.println("huy");
        System.out.println(player1.getName());
        App.app.brushMainGameUI(App.stage);
    }

    public final void nameGenerator(){
        playerName.setText(Tools.getRandomName());
    }
}
