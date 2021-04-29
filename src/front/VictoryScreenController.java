package front;

import com.core.MapObjects.Chest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class VictoryScreenController {
    private Chest chest;
    private boolean chestIsOpen = false;

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

    @FXML
    private void toMainMenu() throws IOException {
        App.getAPP().resetGame();
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
