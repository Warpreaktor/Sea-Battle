package front;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * Width - Ширина
 * Height - Высота
 */
public class BattleFieldController {
    public String[] battleHistory = {"",""};    //updateble
    public static Button nextTurnButton;
    public ImageView leftStateFrame;
    public ImageView rightStateFrame;
    public Text enemyShipsNum;   //updateble
    public Text selfShipsNum;    //updateble
    public Label ourFleetMapName = new Label();
    public Label enemyFleetMapName = new Label();
    public TextFlow textFlow = new TextFlow();
    public VBox leftField;
    public VBox rightField = new VBox(1);
    AnchorPane anchorPane;

    public BattleFieldController() {
        rightFieldInit();
        leftFieldInit();
        nextTurnButtonInit();
        textFlowInit();
        mapLabelsInit();
        stateFrameInit();
        anchorPaneInit();
    }

    /**
     * Выводит на экран сообщение. При этом, сообщение что было на экране до этого сохраняется и в следующий раз оно
     * выводится с измененным текстом сдивигаясь вверх.
     * @param text - Текст выводимый на экран.
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
            App.SEA_BATTLE_GAME.event();
            App.SEA_BATTLE_GAME.getCPU().shoot(0, 0);//Сюда можно передавать любые координаты, все равно они изменятся внутри метода.
            App.isHumanTurn = true;
        }else {
            //Сообщение пользователю о том, что его ход еще не завершен
        }
    }

    public void nextTurnButtonInit(){
        nextTurnButton = new Button("Next turn \n  [SPACE]");
        nextTurnButton.setPrefHeight(60);
        nextTurnButton.setPrefWidth(120);
        nextTurnButton.setLayoutX(580);
        nextTurnButton.setLayoutY(940);
        nextTurnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextTurn();
            }
        });
        nextTurnButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode keyCode = keyEvent.getCode();
                if (keyCode.equals(KeyCode.SPACE)){
                    nextTurn();
                }
            }
        });
    }

    public void leftFieldInit(){
        leftField = App.SHIP_SETTING_CONTROLLER.getField();
        leftField.setLayoutX(20);
        leftField.setLayoutY(280);
        leftField.setAlignment(Pos.TOP_CENTER);
        leftField.getChildren().add(0, ourFleetMapName);
    }

    public void rightFieldInit(){
        rightField.setLayoutX(670);
        rightField.setLayoutY(280);
        rightField.setAlignment(Pos.TOP_CENTER);
        rightField.getChildren().add(0, enemyFleetMapName);
    }

    public void anchorPaneInit(){
        anchorPane = new AnchorPane(rightField, leftField, nextTurnButton, textFlow, leftStateFrame,
                rightStateFrame, selfShipsNum, enemyShipsNum);
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
        //textFlow.setBackground(); зафигачить сюда крутую рамку для текста
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
    public void mapLabelsInit(){
        ourFleetMapName.setLayoutY(600);
        ourFleetMapName.setLayoutX(100);
        //Text text1 = new Text("Наш флот");
        //ourFleetMapName.setFont(new Font(16));
        //text1.setFill(Color.BROWN);
        ourFleetMapName.setFont(new Font(16));
        ourFleetMapName.setText("Наш флот");
        ourFleetMapName.setLabelFor(leftField);

        enemyFleetMapName.setLayoutY(1000);
        enemyFleetMapName.setLayoutX(100);
//        Text text2 = new Text("Вражеский флот");
//        text2.setFill(Color.BROWN);
        enemyFleetMapName.setFont(new Font(16));
        enemyFleetMapName.setText("Вражеский флот");
        ourFleetMapName.setLabelFor(rightField);
    }

    public void stateFrameInit(){
        leftStateFrame = new ImageView(new Image("/resources/stateFrame.jpg"));
        leftStateFrame.setFitHeight(70);
        leftStateFrame.setFitWidth(160);
        leftStateFrame.setLayoutY(220);
        leftStateFrame.setLayoutX(20);

        selfShipsNum = new Text("Наш флот \n" + App.SEA_BATTLE_GAME.getHuman().getNumberOfShip());
        selfShipsNum.setTextAlignment(TextAlignment.CENTER);
        selfShipsNum.setFont(new Font(16));
        selfShipsNum.setFill(Color.BROWN);
        selfShipsNum.setLayoutY(240);
        selfShipsNum.setLayoutX(60);

        rightStateFrame = new ImageView(new Image("/resources/stateFrame.jpg"));
        rightStateFrame.setFitHeight(70);
        rightStateFrame.setFitWidth(160);
        rightStateFrame.setLayoutY(220);
        rightStateFrame.setLayoutX(1110);

        enemyShipsNum = new Text("Флот врага \n" + App.SEA_BATTLE_GAME.getCPU().getNumberOfShip());
        enemyShipsNum.setTextAlignment(TextAlignment.CENTER);
        enemyShipsNum.setFont(new Font(16));
        enemyShipsNum.setFill(Color.BROWN);
        enemyShipsNum.setLayoutY(240);
        enemyShipsNum.setLayoutX(1150);

    }

    public void stateUpdate(){
        selfShipsNum.setText("Наш флот \n" + App.SEA_BATTLE_GAME.getHuman().getNumberOfShip());
        enemyShipsNum.setText("Флот врага \n" + App.SEA_BATTLE_GAME.getCPU().getNumberOfShip());
    }
}
