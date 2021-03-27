package front;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML public Label battleHistory = new Label();
    public String oldHistory = "";

    public MainController() {
        this.oldHistory = "";
        this.battleHistory.setText("- Битва началась! \n");;
    }

    public Label getBattleHistory() {
        return battleHistory;
    }

    public void textOutput(String text){
        this.oldHistory = this.battleHistory.getText();
        this.battleHistory.setText("- " + oldHistory + "\n" + text + "\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
