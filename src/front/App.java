package front;

import com.core.*;
import com.core.MapObjects.GameCell;
import com.core.MapObjects.GameObject;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class App extends Application {
    public static final App app = new App();
    public static final Stage stage = new Stage();
    public static final SeaBattleGame seaBattleGame = new SeaBattleGame(); // Должен быть public static final singleton и только в этом классе. Первая инициализация происходит в StartMenuController
    public static BattleFieldController BATTLE_FIELD_CONTROLLER;
    public static final ShipSettingController shipSettingController = new ShipSettingController();
    public static boolean isHumanTurn = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage = this.stage;
        stage.setResizable(false);
        //VictoryScreenController victoryScreenController = new VictoryScreenController();//test
        //brushTheVictroryScreen();//Тест. Потом удалить.
        brushStartMenu(); //закомеченно только для теста. Вернуть обратно.
    }

    public final void brushStartMenu() throws IOException {
        try {
            stage.setX(300);
            stage.setY(100);
            Parent root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
            Group group = new Group(root);
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            brushTheErrorMessage("Файл \"StartMenu.fxml\" поврежден или отсутствует");
        }
    }

    public final static void brushShipSettingMenu() {
        stage.setX(300);
        stage.setY(0);
        for (int y = 0; y < seaBattleGame.getSIZE(); y++) {
            for (int x = 0; x < seaBattleGame.getSIZE(); x++) {
                GameObject gameCell = new GameCell(y, x);
                shipSettingController.gethBoxes()[y].getChildren().add(gameCell);
                seaBattleGame.getHuman().setGameCellToOurFleetMap(gameCell, y, x);
                gameCell.setCoordinateY(y);
                gameCell.setCoordinateX(x);
                Tools.setDragTargetZone(gameCell);
            }
        }
        Scene scene = new Scene(shipSettingController.getShipSetPan());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * В это м методе отображается вся динамическая информация и вьюшки на поле.
     */
    public final static void brushTheBattleField() {
        BATTLE_FIELD_CONTROLLER = new BattleFieldController();
        stage.setX(300);
        stage.setY(0);
        //Отрисовка поля с нашим флотом
        VBox leftVBox = shipSettingController.getField();
        leftVBox.setLayoutX(20);
        leftVBox.setLayoutY(200);
        //Отрисовка поля с вражеским полем
        for (int y = 0; y < seaBattleGame.getSIZE(); y++) {
            HBox rightHBox = new HBox();
            BATTLE_FIELD_CONTROLLER.rightVBox.getChildren().add(rightHBox);
            for (int x = 0; x < seaBattleGame.getSIZE(); x++) {
                GameObject gameCell = new GameCell(y, x);
                rightHBox.getChildren().add(gameCell);
                seaBattleGame.getHuman().setGameCellToEnemyFleetMap(gameCell, y, x);
                gameCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (isHumanTurn) {
                            if (seaBattleGame.shootHuman(gameCell.getCoordinateY(), gameCell.getCoordinateX())) {
                                isHumanTurn = false;//ход передается компьютеру
                                Player human = App.seaBattleGame.getHuman();
                                human.setCountOfTurns(human.getCountOfTurns() + 1);
                                try {
                                    seaBattleGame.isVictory();
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
        seaBattleGame.octopusAtack(seaBattleGame.getHuman(), seaBattleGame.getOctopus());
        seaBattleGame.octopusAtack(seaBattleGame.getCPU(), seaBattleGame.getOctopus());
        BATTLE_FIELD_CONTROLLER.selfShipsNum.setText("Наши корабли - " + App.seaBattleGame.getHuman().getNumberOfShip());
        BATTLE_FIELD_CONTROLLER.enemyShipsNum.setText("Корабли противника - " + App.seaBattleGame.getCPU().getNumberOfShip());
        BATTLE_FIELD_CONTROLLER.anchorPane.getChildren().add(leftVBox);
        Scene scene = new Scene(BATTLE_FIELD_CONTROLLER.anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public final void brushTheVictroryScreen() throws IOException {
        VictoryScreenController victoryScreenController = new VictoryScreenController();
        try {
            stage.setX(600);
            stage.setY(200);
            Parent root = FXMLLoader.load(getClass().getResource("VictoryScreen.fxml"));
            Group group = new Group(root);
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
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
