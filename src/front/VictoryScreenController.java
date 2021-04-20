package front;

import com.core.Chest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class VictoryScreenController {
    private final Chest chestLeft = new Chest();
    private final Chest chestCentre = new Chest();
    private final Chest chestRight = new Chest();

    @FXML
    ImageView chest1;
    @FXML
    ImageView chest2;
    @FXML
    ImageView chest3;
    @FXML
    Label label;

    @FXML
    public final void chestOpened(){
            label.setText(chestLeft.getItems().get(0) + "\n" +
                    chestLeft.getItems().get(1) + "\n" +
                    chestLeft.getItems().get(2) + "\n" +
                    chestLeft.getItems().get(3) + "\n" +
                    chestLeft.getItems().get(4) + "\n");
    }

}