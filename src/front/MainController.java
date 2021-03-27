package front;

import com.core.Player;
import com.core.SeaBattleGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private SeaBattleGame seaBattleGame;
    private Player human;
    private Player CPU;

    public void setHuman(Player human) {
        this.human = human;
    }

    public void setCPU(Player CPU) {
        this.CPU = CPU;
    }

    public void setSeaBattleGame(SeaBattleGame seaBattleGame) {
        if (seaBattleGame == null) {
            this.seaBattleGame = seaBattleGame;
        }
    }

    @FXML public Label battleLogView = new Label();
    public String[] battleHistory = {"","","",""};

    public MainController() {
        battleLogView.setText("- Битва началась! \n");
    }

    public Label getBattleLogView() {
        return battleLogView;
    }

    public void textOutput(String text) {
        battleHistory[3] = battleHistory[2]; //Самое старое сообщение
        battleHistory[2] = battleHistory[1];
        battleHistory[1] = battleHistory[0];
        battleHistory[0] = text; //Самое актуальное сообщение
        battleLogView.setText(
                "- " + battleHistory[0] + "\n" +
                "- " + battleHistory[1] + "\n" +
                "- " + battleHistory[2] + "\n" +
                "- " + battleHistory[3] + "\n");
    }

    public void nextTurnButton(ActionEvent event){
        seaBattleGame.shootCPU(human, CPU);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        battleLogView.setAlignment(Pos.CENTER);
    }

}
