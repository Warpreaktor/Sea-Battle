package front;

import com.core.*;
import com.core.GameObjects.MapCell;
import com.core.GameObjects.MapObject;
import com.core.Players.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class App extends Application {
    public static final App APP = new App();
    public static final Stage STAGE = new Stage();
    public static final SeaBattleGame SEA_BATTLE_GAME = new SeaBattleGame(); // Должен быть public static final singleton и только в этом классе. Первая инициализация происходит в StartMenuController
    public static BattleFieldController BATTLE_FIELD_CONTROLLER;
    public static final ShipSettingController SHIP_SETTING_CONTROLLER = new ShipSettingController();
    public static boolean isHumanTurn = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage = this.STAGE;
        stage.setResizable(false);
        //VictoryScreenController victoryScreenController = new VictoryScreenController();//test
        //brushTheVictroryScreen();//Тест. Потом удалить.
        brushStartMenu(); //закомеченно только для теста. Вернуть обратно.
    }

    public final void brushStartMenu() throws IOException {
        try {
            STAGE.setX(300);
            STAGE.setY(100);
            Parent root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
            Group group = new Group(root);
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            STAGE.setScene(scene);
            STAGE.show();
        } catch (IOException e) {
            brushTheErrorMessage("Файл \"StartMenu.fxml\" поврежден или отсутствует");
        }
    }

    public final static void brushShipSettingMenu() {
        STAGE.setX(300);
        STAGE.setY(0);
        for (int y = 0; y < SEA_BATTLE_GAME.getSIZE(); y++) {
            for (int x = 0; x < SEA_BATTLE_GAME.getSIZE(); x++) {
                MapObject gameCell = new MapCell(y, x);
                SHIP_SETTING_CONTROLLER.gethBoxes()[y].getChildren().add(gameCell);
                SEA_BATTLE_GAME.getHuman().setGameCellToOurFleetMap(gameCell, y, x);
                gameCell.setCoordinateY(y);
                gameCell.setCoordinateX(x);
                Tools.setDragTargetZone(gameCell);
            }
        }
        Scene scene = new Scene(SHIP_SETTING_CONTROLLER.getShipSetPan());
        STAGE.setScene(scene);
        STAGE.show();
    }

    /**
     * В это м методе отображается вся динамическая информация и вьюшки на поле.
     */
    public final static void brushTheBattleField() {
        BATTLE_FIELD_CONTROLLER = new BattleFieldController();
        STAGE.setX(300);
        STAGE.setY(0);
        //Отрисовка поля с вражеским флотом
        for (int y = 0; y < SEA_BATTLE_GAME.getSIZE(); y++) {
            HBox rightHBox = new HBox();
            BATTLE_FIELD_CONTROLLER.rightField.getChildren().add(rightHBox);
            for (int x = 0; x < SEA_BATTLE_GAME.getSIZE(); x++) {
                MapObject gameCell = new MapCell(y, x);
                rightHBox.getChildren().add(gameCell);
                SEA_BATTLE_GAME.getHuman().setGameCellToEnemyFleetMap(gameCell, y, x);
                gameCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (isHumanTurn) {
                            if (SEA_BATTLE_GAME.getHuman().shoot(gameCell.getCoordinateY(), gameCell.getCoordinateX())) {
                                isHumanTurn = false;//ход передается компьютеру
                                Player human = App.SEA_BATTLE_GAME.getHuman();
                                human.setCountOfTurns(human.getCountOfTurns() + 1);
                                try {
                                    SEA_BATTLE_GAME.isVictory();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                isHumanTurn = true;//холостой выстрел ход остается у игрока
                            }
                        }
                    }
                });
            }
        }
        BATTLE_FIELD_CONTROLLER.selfShipsNum.setText("Наши корабли - " + App.SEA_BATTLE_GAME.getHuman().getNumberOfShip());
        BATTLE_FIELD_CONTROLLER.enemyShipsNum.setText("Корабли противника - " + App.SEA_BATTLE_GAME.getCPU().getNumberOfShip());
        Scene scene = new Scene(BATTLE_FIELD_CONTROLLER.anchorPane);
        STAGE.setScene(scene);
        STAGE.show();
    }

    public final void brushTheVictroryScreen() throws IOException {
        VictoryScreenController victoryScreenController = new VictoryScreenController();
        try {
            STAGE.setX(600);
            STAGE.setY(200);
            Parent root = FXMLLoader.load(getClass().getResource("VictoryScreen.fxml"));
            Group group = new Group(root);
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            STAGE.setScene(scene);
            STAGE.show();
        } catch (IOException e) {
            e.printStackTrace();
            brushTheErrorMessage("Файл \"Victory.fxml\" поврежден или отсутствует");
        }
    }

    public static void brushTheErrorMessage(String message) {
        Stage stage = new Stage();
        TextArea error = new TextArea();
        error.setText(message);
        Button button = new Button();
        button.setText("OK");
        button.setLayoutX(200);
        button.setLayoutY(100);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        Group group = new Group(error, button);
        stage.setScene(new Scene(group));
        stage.show();
    }

    public static void brushTheVictoryMessage(String message) {
        Stage stage = new Stage();
        TextArea error = new TextArea();
        error.setText(message);
        Button button = new Button();
        button.setText("OK");
        button.setLayoutX(200);
        button.setLayoutY(100);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        Group group = new Group(error, button);
        stage.setScene(new Scene(group));
        stage.show();
    }
}
