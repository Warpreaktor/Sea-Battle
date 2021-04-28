package front;

import com.core.MapObjects.MapCell;
import com.core.MapObjects.MapObject;
import com.core.Players.Player;
import com.core.Ships.Ship;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import static java.lang.Thread.sleep;

/**
 * Width - Ширина
 * Height - Высота
 */
public class BattleFieldController {
    public String[] battleHistory = {"",""};    //updateble
    public static Button nextTurnButton;
    public ImageView leftStateFrame;
    public ImageView rightStateFrame;
    public ImageView leftPersonFrame;
    public ImageView leftPersonPortrait;
    public ImageView rightPersonFrame;
    public ImageView rightPersonPortrait;
    public Text enemyShipsNum;   //updateble
    public Text selfShipsNum;    //updateble
    public Text enemyTurns;    //updateble
    public Text selfTurns;    //updateble
    public Label ourFleetMapName = new Label();
    public Label enemyFleetMapName = new Label();
    private Label shipName;
    public TextFlow textFlow = new TextFlow();
    public VBox leftField;
    public VBox rightField = new VBox(1);
    AnchorPane anchorPane;

    public BattleFieldController() {
        App.mediaPlayer.loopPlaying();
        personFramesInit();
        rightFieldInit();
        leftFieldInit();
        nextTurnButtonInit();
        textFlowInit();
        mapLabelsInit();
        stateFrameInit();
        shipNameInit();
        anchorPaneInit();
    }


    public void shipNameInit(){
        shipName = new Label("Корабль: ");
        shipName.setLayoutY(250);
        shipName.setLayoutX(250);
        shipName.setFont(new Font(16));
        shipName.setTextFill(Color.BROWN);

    }
    public void setShipName(String name){
        shipName.setText(name);
    }
    public void stateUpdate(){
        selfShipsNum.setText("Флот ||\n" + App.SEA_BATTLE_GAME.getHuman().getNumberOfShip());
        enemyShipsNum.setText("Флот ||\n" + App.SEA_BATTLE_GAME.getCPU().getNumberOfShip());
        textOutput();
        turnsUpdate();
        App.SEA_BATTLE_GAME.isVictory();
    }
    public void textUpdate(String text){
        battleHistory[1] = battleHistory[0];//Old text
        battleHistory[0] = text; //New text
    }
    public void turnsUpdate(){
        selfTurns.setText("Ходов \n" + App.SEA_BATTLE_GAME.getHuman().getTurnCounter());
        enemyTurns.setText("Ходов \n" + App.SEA_BATTLE_GAME.getCPU().getTurnCounter());
    }
    /**
     * Выводит на экран сообщение. При этом, сообщение что было на экране до этого сохраняется и в следующий раз оно
     * выводится с измененным текстом сдивигаясь вверх.
     */
    public void textOutput() {
        ObservableList list = textFlow.getChildren();

        Text textOld = new Text(battleHistory[1] + "\n");
        textOld.setFont(new Font(14));
        textOld.setFill(Color.BLACK);

        Text textNew = new Text(battleHistory[0] + "\n");
        textNew.setFont(new Font(20));
        textNew.setFill(Color.BLUE);

        try {
            list.remove(0);
            sleep(50);
            list.remove(0);
            sleep(50);
            list.addAll(textOld, textNew);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public final void nextTurn(){
        if (!App.getIsHumanTurn()) {
            App.SEA_BATTLE_GAME.event();
            while (App.getIsHumanTurn() == false) {
                App.SEA_BATTLE_GAME.getCPU().shoot(0, 0);//Сюда можно передавать любые координаты, все равно они изменятся внутри метода.
            }
            stateUpdate();
            //App.setNexTurn(); проверить где еще вызывается этот метод и оставить только тут
        }
    }


    public void nextTurnButtonInit(){
        nextTurnButton = new Button(" Next turn \n[SPACE]");
        nextTurnButton.setPrefHeight(60);
        nextTurnButton.setPrefWidth(120);
        nextTurnButton.setLayoutX(580);
        nextTurnButton.setLayoutY(940);
//        разобраться как на кнопку назначить клик мыши и кнопку одновременно.
//        nextTurnButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                nextTurn();
//                event.consume();
//            }
//        });
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
        for (int y = 0; y < App.SEA_BATTLE_GAME.getSIZE(); y++) {
            HBox rightHBox = new HBox();
            rightField.getChildren().add(rightHBox);
            for (int x = 0; x < App.SEA_BATTLE_GAME.getSIZE(); x++) {
                MapObject gameCell = new MapCell(y, x);
                rightHBox.getChildren().add(gameCell);
                App.SEA_BATTLE_GAME.getHuman().setGameCellToEnemyFleetMap(gameCell, y, x);
                gameCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (App.getIsHumanTurn()) {
                            if (App.SEA_BATTLE_GAME.getHuman().shoot(gameCell.getCoordinateY(), gameCell.getCoordinateX())) {
                                Player human = App.SEA_BATTLE_GAME.getHuman();
                                human.setTurnCounter(human.getTurnCounter() + 1);
                                App.BATTLE_FIELD_CONTROLLER.stateUpdate();
                                App.setNexTurn();//ход передается компьютеру
                            } else {
                                //холостой клик, ход остается у игрока
                            }
                        }
                    }
                });
            }
        }
    }

    public void anchorPaneInit(){
        anchorPane = new AnchorPane(rightField, leftField, nextTurnButton, textFlow, leftStateFrame,
                rightStateFrame, selfShipsNum, enemyShipsNum, selfTurns, enemyTurns,
                leftPersonFrame, rightPersonFrame, leftPersonPortrait, rightPersonPortrait, shipName);
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
        textFlow.setPrefWidth(300);
        textFlow.setPrefHeight(130);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setLineSpacing(2);

        BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/comics/talk1.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(300.0, 130.0,false, false, false, false));
        Background backgroundText = new Background(backgroundImage);
        textFlow.setBackground(backgroundText); //зафигачить сюда крутую рамку для текста

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
        ourFleetMapName.setFont(new Font(16));
        ourFleetMapName.setText("Наш флот");
        ourFleetMapName.setLabelFor(leftField);

        enemyFleetMapName.setLayoutY(1000);
        enemyFleetMapName.setLayoutX(100);
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

        selfShipsNum = new Text("Флот ||\n" + App.SEA_BATTLE_GAME.getHuman().getNumberOfShip());
        selfShipsNum.setTextAlignment(TextAlignment.CENTER);
        selfShipsNum.setFont(new Font(17));
        selfShipsNum.setLineSpacing(2);
        selfShipsNum.setFill(Color.BROWN);
        selfShipsNum.setLayoutY(250);
        selfShipsNum.setLayoutX(35);

        selfTurns = new Text("Ходов \n" + App.SEA_BATTLE_GAME.getHuman().getTurnCounter());
        selfTurns.setTextAlignment(TextAlignment.CENTER);
        selfTurns.setFont(new Font(17));
        selfTurns.setLineSpacing(2);
        selfTurns.setFill(Color.BROWN);
        selfTurns.setLayoutY(250);
        selfTurns.setLayoutX(95);

        rightStateFrame = new ImageView(new Image("/resources/stateFrame.jpg"));
        rightStateFrame.setFitHeight(70);
        rightStateFrame.setFitWidth(160);
        rightStateFrame.setLayoutY(220);
        rightStateFrame.setLayoutX(1110);

        enemyShipsNum = new Text("Флот ||\n" + App.SEA_BATTLE_GAME.getCPU().getNumberOfShip());
        enemyShipsNum.setTextAlignment(TextAlignment.CENTER);
        enemyShipsNum.setFont(new Font(17));
        enemyShipsNum.setLineSpacing(2);
        enemyShipsNum.setFill(Color.BROWN);
        enemyShipsNum.setLayoutY(250);
        enemyShipsNum.setLayoutX(1125);

        enemyTurns = new Text("Ходов \n" + App.SEA_BATTLE_GAME.getCPU().getTurnCounter());
        enemyTurns.setTextAlignment(TextAlignment.CENTER);
        enemyTurns.setFont(new Font(17));
        enemyTurns.setLineSpacing(2);
        enemyTurns.setFill(Color.BROWN);
        enemyTurns.setLayoutY(250);
        enemyTurns.setLayoutX(1190);

    }



    public void personFramesInit(){
        leftPersonFrame = new ImageView("/resources/persons/portraitFrame250x250.png");
        leftPersonFrame.setLayoutY(30);
        leftPersonFrame.setLayoutX(20);
        leftPersonFrame.setFitWidth(160);
        leftPersonFrame.setFitHeight(160);

        leftPersonPortrait = new ImageView(App.SEA_BATTLE_GAME.getHuman().getPortrait());
        leftPersonPortrait.setLayoutY(40);
        leftPersonPortrait.setLayoutX(30);
        leftPersonPortrait.setFitWidth(140);
        leftPersonPortrait.setFitHeight(140);

        rightPersonFrame = new ImageView("/resources/persons/portraitFrame250x250.png");
        rightPersonFrame.setLayoutY(30);
        rightPersonFrame.setLayoutX(1110);
        rightPersonFrame.setFitWidth(160);
        rightPersonFrame.setFitHeight(160);

        rightPersonPortrait = new ImageView(App.SEA_BATTLE_GAME.getCPU().getPortrait());
        rightPersonPortrait.setLayoutY(40);
        rightPersonPortrait.setLayoutX(1120);
        rightPersonPortrait.setFitWidth(140);
        rightPersonPortrait.setFitHeight(140);
    }
}
