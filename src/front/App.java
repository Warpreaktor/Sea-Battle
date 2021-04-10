package front;

import com.core.GameCell;
import com.core.Player;
import com.core.SeaBattleGame;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class App extends Application {
    public static final App app = new App();
    public static final Stage stage = new Stage();
    public static final MainController mainController = new MainController();
    public static SeaBattleGame seaBattleGame; // Должен быть public static final singleton и только в этом классе. Первая инициализация происходит в StartMenuController
    public static boolean isHumanTurn = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage = this.stage;
        stage.setResizable(false);
        brushShipSettingMenu(stage);
        //brushStartMenu(stage);
    }

    private final AnchorPane anchorPaneInit(VBox leftBox, VBox rightBox, Label textLabel, Button nextTurn) {
        AnchorPane anchorPane = new AnchorPane(leftBox, rightBox, textLabel, nextTurn);
        anchorPane.setBackground(Background.EMPTY);
        anchorPane.setPrefWidth(1280);
        anchorPane.setPrefHeight(1024);
        return anchorPane;
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

    public final void brushShipSettingMenu(Stage stage) throws IOException {
        try{
            stage.setX(300);
            stage.setY(100);
            Parent root = FXMLLoader.load(ShipSettingController.class.getResource("ShipSettingMenu.fxml"));
            final ImageView source = new ImageView("/resources/ship60x60.jpg");
            source.setX(50);
            source.setY(100);
            source.setFitHeight(60);
            source.setFitWidth(60);
            final ImageView target = new ImageView("/resources/target150x150.png");
            target.setX(300);
            target.setY(100);
            target.setFitHeight(60);
            target.setFitWidth(60);
            Group group = new Group(root, source, target);

            //Событие при обнаружении перетаскивания
            source.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start a drag-and-drop gesture*/
                    /* allow any transfer mode */
                    Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
                    /* Put a image on a dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(source.getImage());
                    db.setContent(content);
                    event.consume();
                }
            });
            //Событие при заходе в зону
            target.setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* Ресурс находится внутри зоны */
                    /* accept it only if it is not dragged from the same node
                     * and if it has a image data */
                    if (event.getGestureSource() != target &&
                            event.getDragboard().hasImage()) {
                        /* allow for both copying and moving, whatever user chooses */
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                }
            });
            //Событие которое наступает при входе ресурса в зону
            target.setOnDragEntered(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag-and-drop gesture entered the target */
                    /* show to the user that it is an actual gesture target */
                    if (event.getGestureSource() != target &&
                            event.getDragboard().hasImage()) {
                        target.setEffect(new BoxBlur());
                    }
                    event.consume();
                }
            });
            //Событие наступающее при выходе ресурса из зоны
            target.setOnDragExited(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* mouse moved away, remove the graphical cues */
                    target.setEffect(null);

                    event.consume();
                }
            });
            //Событие наступающее при отпускании ресурса в зоне
            target.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* data dropped */
                    /* if there is a string data on dragboard, read it and use it */
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        target.setImage(db.getImage());
                        success = true;
                    }
                    /* let the source know whether the string was successfully
                     * transferred and used */
                    event.setDropCompleted(success);
                    event.consume();
                }
            });
            //Событие при завершении перетаскивания
            source.setOnDragDone(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag and drop gesture ended */
                    /* if the data was successfully moved, clear it */
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        source.setImage(null);
                    }
                    event.consume();
                }
            });
            Scene scene = new Scene(group);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


        public final void brushTheBattleField(SeaBattleGame game, Stage stage, Player player, Player playerCPU) throws IOException{
        try {
            stage.setX(300);
            stage.setY(100);
            Parent root = FXMLLoader.load(getClass().getResource("BattleField.fxml"));
            //Отрисовка поля с нашим флотом
            VBox leftVBox = new VBox();
            leftVBox.setTranslateY(200.0);
            leftVBox.setTranslateX(15.0);
            for (int y = 0; y < game.getSIZE(); y++) {
                HBox leftHBox = new HBox();
                leftVBox.getChildren().add(leftHBox);
                for (int x = 0; x < game.getSIZE(); x++) {
                    GameCell gameCell = new GameCell();
                    gameCell.setFitHeight(60);
                    gameCell.setFitWidth(60);
                    gameCell.setWave();
                    leftHBox.getChildren().add(gameCell);
                    player.setGameCellToOurFleetMap(gameCell, y, x);
                    gameCell.setCoordinateY(y);
                    gameCell.setCoordinateX(x);
                }
            }
            //Отрисовка поля с вражеским полем
            VBox rightVBox = new VBox();
            rightVBox.setTranslateX(660);
            rightVBox.setTranslateY(200.0);
            for (int y = 0; y < game.getSIZE(); y++) {
                HBox rightHBox = new HBox();
                rightVBox.getChildren().add(rightHBox);
                for (int x = 0; x < game.getSIZE(); x++) {
                    GameCell gameCell = new GameCell();
                    gameCell.setFitHeight(60);
                    gameCell.setFitWidth(60);
                    gameCell.setWave();
                    rightHBox.getChildren().add(gameCell);
                    player.setGameCellToEnemyFleetMap(gameCell, y, x);
                    gameCell.setCoordinateY(y);
                    gameCell.setCoordinateX(x);
                    gameCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if (isHumanTurn) {
                                game.battle(player, playerCPU, gameCell.getCoordinateY(), gameCell.getCoordinateX());
                                isHumanTurn = false;
                            }
                        }
                    });
                }
            }
            AnchorPane anchorPane = anchorPaneInit(leftVBox, rightVBox, mainController.getBattleLogView(), mainController.nextTurn);
            Group group = new Group(root, anchorPane);
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            brushTheErrorMessage("Некоторые файлы с игрой повреждены или отсутствуют");
        }
    }


    public void brushTheErrorMessage(String message){
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
