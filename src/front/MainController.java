package front;

import com.core.Player;
import com.core.SeaBattleGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public String[] battleHistory = {"","","",""};
    @FXML public static Button nextTurn = new Button();
    @FXML public static Label battleLogView = new Label();


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

    public final void nextTurnButton(){
        if (App.isHumanTurn == false) {
            App.seaBattleGame.shootCPU();
            App.isHumanTurn = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.battleLogView.setAlignment(Pos.CENTER);
        this.battleLogView.setTextAlignment(TextAlignment.CENTER);

        //Рисуем кнопку Next turn
        nextTurn.setText("Next turn");
        nextTurn.setPrefHeight(67);
        nextTurn.setPrefWidth(121);
        nextTurn.setLayoutX(580);
        nextTurn.setLayoutY(866);
        nextTurn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextTurnButton();
            }
        });
        nextTurn.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode keyCode = keyEvent.getCode();
                if (keyCode.equals(KeyCode.SPACE)){
                    nextTurnButton();
                }
            }
        });
        }
}
