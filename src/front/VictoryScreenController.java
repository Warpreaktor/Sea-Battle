package front;

import com.core.Chest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class VictoryScreenController {
    private Chest chestLeft = new Chest();
    private final Chest chestCentre = new Chest();
    private final Chest chestRight = new Chest();
    private boolean chestIsOpen = false;

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
        if (!chestIsOpen) {
            chestLeft = new Chest();
            label.setText(chestLeft.getItems().get(0) + "\n" +
                    chestLeft.getItems().get(1) + "\n" +
                    chestLeft.getItems().get(2) + "\n" +
                    chestLeft.getItems().get(3) + "\n" +
                    chestLeft.getItems().get(4) + "\n");
            chestIsOpen = true;
        }
    }

}
