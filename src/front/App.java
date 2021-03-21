package front;

import com.core.GameCell;
import com.core.SeaBattleGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class App extends Application {
    public static final App app = new App();
    public static final Stage stage = new Stage();
    private final Controller mainController = new Controller();

    public static void main(String[] args) {
        SeaBattleGame seaBattleGame = new SeaBattleGame();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage = this.stage;
        brushStartMenu(stage);
    }

    private final AnchorPane anchorPaneInit(VBox leftBox, VBox rightBox) {
        AnchorPane anchorPane = new AnchorPane(leftBox, rightBox);
        anchorPane.setBackground(Background.EMPTY);
        anchorPane.setPrefWidth(1280);
        anchorPane.setPrefHeight(1024);

        return anchorPane;
    }

    public final void brushStartMenu(Stage stage) throws IOException {
        try {
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

    public final void brushMainGameUI(Stage stage) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BattleField.fxml"));
            VBox leftVBox = new VBox();
            leftVBox.setTranslateY(200.0);
            leftVBox.setTranslateX(15.0);
            for (int i = 0; i < 10; i++) {
                HBox leftHBox = new HBox();
                leftVBox.getChildren().add(leftHBox);
                for (int j = 0; j < 10; j++) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setHeight(50);
                    rectangle.setWidth(50);
                    rectangle.setFill(Color.AQUAMARINE);
                    rectangle.setStroke(Color.BLACK);
                    leftHBox.getChildren().add(rectangle);
                }
            }
            VBox rightVBox = new VBox();
            rightVBox.setTranslateX(750);
            rightVBox.setTranslateY(200.0);
            for (int i = 0; i < 10; i++) {
                HBox rightHBox = new HBox();
                rightVBox.getChildren().add(rightHBox);
                for (int j = 0; j < 10; j++) {
                    GameCell cell = new GameCell();
                    cell.setHeight(50);
                    cell.setWidth(50);
                    cell.setFill(Color.AQUAMARINE);
                    cell.setStroke(Color.BLACK);
                    rightHBox.getChildren().add(cell);
                }
            }
            AnchorPane anchorPane = anchorPaneInit(leftVBox, rightVBox);
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
}
