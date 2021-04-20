package front;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class BattleFieldController {
    public String[] battleHistory = {"",""};
    public static Button nextTurn = new Button("Next turn \n  [SPACE]");
    public Label enemyShipsNum = new Label();
    public Label selfShipsNum = new Label();
    public TextFlow textFlow = new TextFlow();
    public VBox infoBox = new VBox(enemyShipsNum, selfShipsNum);
    public VBox rightVBox = new VBox(1);
    AnchorPane anchorPane;

    public BattleFieldController() {
        rightVBoxInit();
        infoBoxInit();
        nextTurnInit();
        textFlowInit();
        anchorPaneInit();

    }

    /**
     * Выводит на экран сообщение. При этом, сообщение что было на экране до этого сохраняется и в следующий раз оно
     * выводится с измененным текстом сдивигаясь вверх.
     * @param text
     */
    public void textOutput(String text) {
        ObservableList list = textFlow.getChildren();

        battleHistory[1] = battleHistory[0];//Old
        battleHistory[0] = text; //New

        Text textOld = new Text(battleHistory[1] + "\n");
        textOld.setFont(new Font(14));
        textOld.setFill(Color.BLACK);

        Text textNew = new Text(battleHistory[0] + "\n");
        textNew.setFont(new Font(20));
        textNew.setFill(Color.BLUE);

        list.remove(1);
        list.remove(0);
        list.add(0, textOld);
        list.add(1, textNew);

    }

    public final void nextTurn(){
        if (App.isHumanTurn == false) {
            App.SEA_BATTLE_GAME.shootCPU();
            App.isHumanTurn = true;
        }else {
            //Сообщение пользователю о том, что его ход еще не завершен
        }
    }

    public void nextTurnInit(){
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

    public void infoBoxInit(){
        infoBox.setLayoutY(100);
        infoBox.setLayoutX(50);
    }
    public void rightVBoxInit(){
        rightVBox.setLayoutX(670);
        rightVBox.setLayoutY(200);
    }
    public void anchorPaneInit(){
        anchorPane = new AnchorPane(rightVBox, nextTurn, infoBox, textFlow);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/sea.jpg"),
                BackgroundRepeat.SPACE, BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);
        anchorPane.setPrefWidth(1280);
        anchorPane.setPrefHeight(1024);
    }
    public void textFlowInit(){
        textFlow.setLayoutY(80);
        textFlow.setLayoutX(240);
        textFlow.setPrefWidth(700);
        textFlow.setPrefHeight(130);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setLineSpacing(2);
        Text text1 = new Text("Шторм и гром! \n");
        text1.setFont(new Font(20));
        text1.setFill(Color.RED);
        battleHistory[1] = text1.getText();
        Text text2 = new Text( "Бурю в паруса, попутного ветра в шляпы! \n");
        text2.setFont(new Font(20));
        text2.setFill(Color.RED);
        battleHistory[0] = text2.getText();
        textFlow.getChildren().addAll(text1, text2);
    }
}
