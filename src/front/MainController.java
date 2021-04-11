package front;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextAlignment;

public class MainController {
    public String[] battleHistory = {"","","",""};
    public static Button nextTurn = new Button();
    public static Label battleLogView = new Label();


    public MainController() {
        battleLogView.setText("- Битва началась! \n");
        this.battleLogView.setAlignment(Pos.CENTER);
        this.battleLogView.setTextAlignment(TextAlignment.CENTER);
        //Параметры кнопки Next turn
        nextTurn.setText("Next turn");
        nextTurn.setPrefHeight(60);
        nextTurn.setPrefWidth(120);
        nextTurn.setLayoutX(580);
        nextTurn.setLayoutY(866);
        nextTurn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextTurn();
            }
        });
        nextTurn.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode keyCode = keyEvent.getCode();
                if (keyCode.equals(KeyCode.SPACE)){
                    nextTurn();
                }
            }
        });
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

    public final void nextTurn(){
        if (App.isHumanTurn == false) {
            App.seaBattleGame.shootCPU();
            App.isHumanTurn = true;
        }else {
            //Сообщение пользователю о том, что его ход еще не завершен
        }
    }
}
