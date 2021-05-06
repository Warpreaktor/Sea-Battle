package front;

import com.core.MapObjects.MapCell;
import com.core.MapObjects.MapObject;
import com.core.Players.CPU;
import com.core.Players.Player;
import com.core.Players.ReplicasImage;
import com.core.Tests;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

/**
 * Width - Ширина
 * Height - Высота
 */
public class BattleFieldController {
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
    private Label shipName;
    private Label humanName;
    private ImageView ribbonNameHuman;
    private ImageView ribbonNameCPU;
    private Label CPUName;
    public TextArea replicaArea;
    public Text replica;    //updateble
    public VBox leftField;
    public VBox rightField = new VBox(1);
    private ImageView comics;
    private ImageView currentTurnFrame;

    public AnchorPane anchorPane;

    public BattleFieldController() {
        if (App.musicPlayer.getPlayingTrack().isPlaying()) App.musicPlayer.getPlayingTrack().stopPlaying();
        App.musicPlayer.loopPlaying();

        //Создание компьютерного игрока и расстановка им кораблей
        CPU cpu = new CPU(App.SEA_BATTLE_GAME.getDifficulty());
        cpu.setName(CPU.getRandomName());
        App.SEA_BATTLE_GAME.setAI(cpu);
        App.SEA_BATTLE_GAME.createCPUBattleField(App.SEA_BATTLE_GAME.getAI());
        App.SEA_BATTLE_GAME.getAI().shipsOnGame();



        personFramesInit();
        ribbonNameInit();
        rightFieldInit();
        leftFieldInit();
        nextTurnButtonInit();
        replicasInit();
        stateFrameInit();
        shipNameInit();
        currentTurnFrameInit();
        anchorPaneInit();
        stateUpdate();
    }


    private void currentTurnFrameInit(){
        currentTurnFrame = new ImageView("resources/turnsFrame.png");
        currentTurnFrame.setLayoutY(0);
        currentTurnFrame.setLayoutX(360);
        currentTurnFrame.setFitWidth(200);
        currentTurnFrame.setFitHeight(200);
    }

    public void personFramesInit() {
        leftPersonFrame = new ImageView("/resources/persons/portraitFrame250x250.png");
        leftPersonFrame.setLayoutY(10);
        leftPersonFrame.setLayoutX(50);
        leftPersonFrame.setFitWidth(200);
        leftPersonFrame.setFitHeight(200);

        leftPersonPortrait = new ImageView(App.SEA_BATTLE_GAME.getHuman().getPortrait());
        leftPersonPortrait.setLayoutY(20);
        leftPersonPortrait.setLayoutX(60);
        leftPersonPortrait.setFitWidth(180);
        leftPersonPortrait.setFitHeight(180);

        rightPersonFrame = new ImageView("/resources/persons/portraitFrame250x250.png");
        rightPersonFrame.setLayoutY(10);
        rightPersonFrame.setLayoutX(1050);
        rightPersonFrame.setFitWidth(200);
        rightPersonFrame.setFitHeight(200);

        rightPersonPortrait = new ImageView(App.SEA_BATTLE_GAME.getAI().getPortrait());
        rightPersonPortrait.setLayoutY(20);
        rightPersonPortrait.setLayoutX(1060);
        rightPersonPortrait.setFitWidth(180);
        rightPersonPortrait.setFitHeight(180);
    }

    private void ribbonNameInit() {
        int allY = 0;
        int allX = 0;
        ribbonNameHuman = new ImageView(new Image("/resources/nameRibbon1.png"));
        ribbonNameHuman.setLayoutY(180);
        ribbonNameHuman.setLayoutX(25);
        ribbonNameHuman.setFitHeight(60);
        ribbonNameHuman.setFitWidth(260);

        humanName = new Label(App.SEA_BATTLE_GAME.getHuman().getName());
        humanName.setLayoutY(190);
        humanName.setLayoutX(80);
        humanName.setFont(new Font(14));

        ribbonNameCPU = new ImageView(new Image("/resources/nameRibbon1.png"));
        ribbonNameCPU.setLayoutY(180);
        ribbonNameCPU.setLayoutX(1020);
        ribbonNameCPU.setFitHeight(60);
        ribbonNameCPU.setFitWidth(260);

        CPUName = new Label(App.SEA_BATTLE_GAME.getAI().getName());
        CPUName.setLayoutY(190);
        CPUName.setLayoutX(1070);
        CPUName.setFont(new Font(14));
    }

    public void shipNameInit() {
        shipName = new Label("Корабль: ");
        shipName.setLayoutY(255);
        shipName.setLayoutX(250);
        shipName.setTextFill(Color.BROWN);
        shipName.setFont(Font.font("Cambria", 24));

//        enemyShipName = new Label("Nут можно видеть имя корабля \nпотопленного вами");
//        enemyShipName.setAlignment(Pos.CENTER);
//        enemyShipName.setLayoutY(255);
//        enemyShipName.setLayoutX(650);
//        enemyShipName.setTextFill(Color.BROWN);
//        enemyShipName.setFont(Font.font("Cambria", 22));
    }

    public void setShipName(String name) {
        shipName.setText(name);
    }

    public void stateUpdate() {
        selfShipsNum.setText("Флот ||\n" + App.SEA_BATTLE_GAME.getHuman().getNumberOfShip());
        enemyShipsNum.setText("Флот ||\n" + App.SEA_BATTLE_GAME.getAI().getNumberOfShip());
        turnsUpdate();
        App.SEA_BATTLE_GAME.isVictory();
        if (App.getIsHumanTurn()) currentTurnFrame.setImage(new Image("resources/turnHumFrame.png"));
        else currentTurnFrame.setImage(new Image("resources/turnAIFrame.png"));
    }

    public void replicasInit() {
        comics = new ImageView(new Image("/resources/comics/talk1.png"));
        comics.setLayoutY(30);
        comics.setLayoutX(600);
        comics.setFitWidth(480);
        comics.setFitHeight(200);

        replica = new Text("Рад снова видеть твою рожу!");
        replica.setFont(Font.font("Cambria", 24));
        replica.setFill(Color.RED);
        replica.setLayoutY(150);
        replica.setLayoutX(700);
        replica.setTextAlignment(TextAlignment.CENTER);
    }

    public void setReplica(String text) {
        replica.setText(text);
    }

    public void setComicsImage(ReplicasImage image) {
        switch (image) {
            case CALM_TALK:
                comics.setImage(new Image("/resources/comics/talk1.png"));
                comics.setFitWidth(450);
                comics.setFitHeight(210);
                comics.setLayoutY(40);
                comics.setLayoutX(580);
                replica.setLayoutY(160);
                replica.setLayoutX(680);
                break;
            case EMOTIONAL_TALK:
                comics.setImage(new Image("/resources/comics/talk2.png"));
                comics.setFitWidth(480);
                comics.setFitHeight(220);
                comics.setLayoutY(30);
                comics.setLayoutX(580);
                replica.setLayoutY(130);
                replica.setLayoutX(720);
                break;
            case ANGRY_TALK:
                comics.setImage(new Image("/resources/comics/talk3.png"));
                comics.setFitWidth(480);
                comics.setFitHeight(250);
                comics.setLayoutY(30);
                comics.setLayoutX(590);
                replica.setLayoutY(150);
                replica.setLayoutX(720);
                break;
            case KA_BOOM:
                comics.setImage(new Image("/resources/comics/kaboom.png"));
                comics.setFitWidth(480);
                comics.setFitHeight(300);
                comics.setLayoutY(15);
                comics.setLayoutX(570);
                replica.setLayoutY(150);
                replica.setLayoutX(700);
                break;
        }
    }

    public void turnsUpdate() {
        selfTurns.setText("Ходов \n" + App.SEA_BATTLE_GAME.getHuman().getTurnCounter());
        enemyTurns.setText("Ходов \n" + App.SEA_BATTLE_GAME.getAI().getTurnCounter());
    }

    /**
     * Выводит на экран сообщение. При этом, сообщение что было на экране до этого сохраняется, и в следующий раз оно
     * выводится с измененным текстом сдивигаясь вверх.
     */

    public final void nextTurn() {
            App.SEA_BATTLE_GAME.event();
            Thread thread = new Thread(()->{
                while (App.getIsHumanTurn() == false) {
                    App.SEA_BATTLE_GAME.getAI().shoot(0, 0);//Сюда можно передавать любые координаты, все равно они изменятся внутри метода.
                    stateUpdate();
                    try {
                        Thread.currentThread().sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            stateUpdate();
    }

    public void nextTurnButtonInit() {
        nextTurnButton = new Button(" Next turn \n  [SPACE]");
        nextTurnButton.setPrefHeight(60);
        nextTurnButton.setPrefWidth(120);
        nextTurnButton.setLayoutX(580);
        nextTurnButton.setLayoutY(940);
        nextTurnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nextTurn();
            }
        });
        nextTurnButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode keyCode = keyEvent.getCode();
                if (keyCode.equals(KeyCode.SPACE)) {
                    nextTurn();
                }
            }
        });
    }

    public void leftFieldInit() {
        leftField = App.SHIP_SETTING_CONTROLLER.getField();
        leftField.setLayoutX(20);
        leftField.setLayoutY(305);
        leftField.setAlignment(Pos.TOP_CENTER);
    }

    public void rightFieldInit() {
        rightField.setLayoutX(670);
        rightField.setLayoutY(305);
        rightField.setAlignment(Pos.TOP_CENTER);
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

    public void anchorPaneInit() {
        anchorPane = new AnchorPane(rightField, leftField, nextTurnButton, leftStateFrame,
                rightStateFrame, selfShipsNum, enemyShipsNum, selfTurns, enemyTurns,
                leftPersonFrame, rightPersonFrame, leftPersonPortrait, rightPersonPortrait,
                comics, replica, ribbonNameHuman, ribbonNameCPU, humanName, CPUName,
                currentTurnFrame, shipName);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/battleFieldWall1280x1024.png"),
                BackgroundRepeat.SPACE, BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);
        anchorPane.setPrefWidth(1280);
        anchorPane.setPrefHeight(1024);
        anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stateUpdate();
            }
        });
        anchorPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode key = keyEvent.getCode();
                if (key.equals(KeyCode.C)) Tests.killThemAll();
            }
        });
    }

    public void stateFrameInit() {
        int allY = 10;
        int leftFrameX = 55;
        int rightFrameX = -40;
        leftStateFrame = new ImageView(new Image("/resources/stateFrame.jpg"));
        leftStateFrame.setFitHeight(70);
        leftStateFrame.setFitWidth(160);
        leftStateFrame.setLayoutY(220 + allY);
        leftStateFrame.setLayoutX(20 + leftFrameX);

        selfShipsNum = new Text("Флот ||\n" + App.SEA_BATTLE_GAME.getHuman().getNumberOfShip());
        selfShipsNum.setTextAlignment(TextAlignment.CENTER);
        selfShipsNum.setFont(new Font(17));
        selfShipsNum.setLineSpacing(2);
        selfShipsNum.setFill(Color.BROWN);
        selfShipsNum.setLayoutY(250 + allY);
        selfShipsNum.setLayoutX(35 + leftFrameX);

        selfTurns = new Text("Ходов \n" + App.SEA_BATTLE_GAME.getHuman().getTurnCounter());
        selfTurns.setTextAlignment(TextAlignment.CENTER);
        selfTurns.setFont(new Font(17));
        selfTurns.setLineSpacing(2);
        selfTurns.setFill(Color.BROWN);
        selfTurns.setLayoutY(250 + allY);
        selfTurns.setLayoutX(95 + leftFrameX);

        rightStateFrame = new ImageView(new Image("/resources/stateFrame.jpg"));
        rightStateFrame.setFitHeight(70);
        rightStateFrame.setFitWidth(160);
        rightStateFrame.setLayoutY(220 + allY);
        rightStateFrame.setLayoutX(1110 + rightFrameX);

        enemyShipsNum = new Text("Флот ||\n" + App.SEA_BATTLE_GAME.getAI().getNumberOfShip());
        enemyShipsNum.setTextAlignment(TextAlignment.CENTER);
        enemyShipsNum.setFont(new Font(17));
        enemyShipsNum.setLineSpacing(2);
        enemyShipsNum.setFill(Color.BROWN);
        enemyShipsNum.setLayoutY(250 + allY);
        enemyShipsNum.setLayoutX(1125 + rightFrameX);

        enemyTurns = new Text("Ходов \n" + App.SEA_BATTLE_GAME.getAI().getTurnCounter());
        enemyTurns.setTextAlignment(TextAlignment.CENTER);
        enemyTurns.setFont(new Font(17));
        enemyTurns.setLineSpacing(2);
        enemyTurns.setFill(Color.BROWN);
        enemyTurns.setLayoutY(250 + allY);
        enemyTurns.setLayoutX(1190 + rightFrameX);

    }

}
