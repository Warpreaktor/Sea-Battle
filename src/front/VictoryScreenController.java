package front;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class VictoryScreenController {
    @FXML
    ImageView chest1;
    @FXML
    ImageView chest2;
    @FXML
    ImageView chest3;

    public final void chestOpened(){
        int randomNum = 1 + (int)Math.random() * 6;
        switch (randomNum){
            case 1:
                //Дописать сюда все кейсы для открытия сундука.
                break;
        }
    }

}
