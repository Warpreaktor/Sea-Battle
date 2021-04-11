package front;

import com.core.GameCell;
import com.core.SeaBattleGame;
import com.core.Tools;
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
    public static final App app = new App();
    public static final Stage stage = new Stage();
    public static final MainController mainController = new MainController();
    public static final ShipSettingController shipSettingController = new ShipSettingController();
    public static final SeaBattleGame seaBattleGame = new SeaBattleGame(); // Должен быть public static final singleton и только в этом классе. Первая инициализация происходит в StartMenuController
    public static boolean isHumanTurn = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage = this.stage;
        stage.setResizable(false);
        brushStartMenu(stage);
    }

    public final void brushStartMenu(Stage stage) throws IOException {
        try {
            stage.setX(300);
            stage.setY(100);
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Group group = new Group(root);
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            brushTheErrorMessage("Файл \"Menu.fxml\" поврежден или отсутствует");
        }
    }

    public final static void brushShipSettingMenu() {
        stage.setX(300);
        stage.setY(0);
        for (int y = 0; y < seaBattleGame.getSIZE(); y++) {
//            HBox hBox = new HBox();
//            shipSettingController.getvBox().getChildren().add(hBox);
            for (int x = 0; x < seaBattleGame.getSIZE(); x++) {
                GameCell gameCell = new GameCell();
                gameCell.setFitHeight(60);
                gameCell.setFitWidth(60);
                gameCell.setWave();
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
        public final static void brushTheBattleField(){//SeaBattleGame game, Stage stage, Player player, Player playerCPU) throws IOException{
            stage.setX(300);
            stage.setY(0);
            //Parent root = FXMLLoader.load(getClass().getResource("BattleField.fxml"));
            //Отрисовка поля с нашим флотом
            VBox leftVBox = shipSettingController.getvBox();
            leftVBox.setTranslateY(200.0);
            leftVBox.setTranslateX(15.0);
            for (int y = 0; y < SeaBattleGame.getSIZE(); y++) {
                //HBox leftHBox = new HBox();
                //leftVBox.getChildren().add(leftHBox);
                for (int x = 0; x < seaBattleGame.getSIZE(); x++) {
                    GameCell gameCell = seaBattleGame.getHuman().getOurFleetMap()[y][x];
//                    gameCell.setFitHeight(60);
//                    gameCell.setFitWidth(60);
//                    gameCell.setWave();
                    //leftHBox.getChildren().add(gameCell);
                    //player.setGameCellToOurFleetMap(gameCell, y, x);
                    //gameCell.setCoordinateY(y);
                    //gameCell.setCoordinateX(x);
                }
            }
            //Отрисовка поля с вражеским полем
            VBox rightVBox = new VBox();
            rightVBox.setTranslateX(660);
            rightVBox.setTranslateY(200.0);
            for (int y = 0; y < seaBattleGame.getSIZE(); y++) {
                HBox rightHBox = new HBox();
                rightVBox.getChildren().add(rightHBox);
                for (int x = 0; x < seaBattleGame.getSIZE(); x++) {
                    GameCell gameCell = new GameCell();
                    gameCell.setFitHeight(60);
                    gameCell.setFitWidth(60);
                    gameCell.setWave();
                    rightHBox.getChildren().add(gameCell);
                    seaBattleGame.getHuman().setGameCellToEnemyFleetMap(gameCell, y, x);
                    gameCell.setCoordinateY(y);
                    gameCell.setCoordinateX(x);
                    gameCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if (isHumanTurn) {
                                seaBattleGame.battle(seaBattleGame.getHuman(), seaBattleGame.getCPU(), gameCell.getCoordinateY(), gameCell.getCoordinateX());
                                isHumanTurn = false;
                            }
                        }
                    });
                }
            }
            //AnchorPane anchorPane = anchorPaneInit(leftVBox, rightVBox, mainController.getBattleLogView(), mainController.nextTurn);
            AnchorPane anchorPane = new AnchorPane(leftVBox, rightVBox, mainController.getBattleLogView(), mainController.nextTurn);
            anchorPane.setBackground(Background.EMPTY);
            anchorPane.setPrefWidth(1280);
            anchorPane.setPrefHeight(1024);
            //Group group = new Group(anchorPane);
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        }


    public static void brushTheErrorMessage(String message){
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

    public static void brushTheVictoryMessage(String message){
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
