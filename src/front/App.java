package front;

import com.core.GameCell;
import com.core.SeaBattleGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
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
        stage.setScene(scene);
        stage.show();


//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

    }
    private final AnchorPane anchorPaneInit(VBox leftBox, VBox rightBox){
        AnchorPane anchorPane = new AnchorPane(leftBox, rightBox);
        anchorPane.setBackground(Background.EMPTY);
        anchorPane.setPrefWidth(1280);
        anchorPane.setPrefHeight(1024);

        return anchorPane;
    }

}
