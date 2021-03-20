package front;

import com.core.SeaBattleGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class Controller implements Initializable {
    @FXML
    private Label label;
    @FXML
    private HBox leftHBox;
    @FXML
    private HBox rightHBox;
    @FXML
    private StackPane leftStackPane;
    @FXML
    private StackPane rightStackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ComboBox chooseMap;
    @FXML
    private ListView<String> listView;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Hello World!");
    }
    @FXML
    private void clickOnField(MouseEvent event){
    }
    @FXML
    public void clickOnField(javafx.scene.input.MouseEvent mouseEvent) {
        label.setText("Ты-дыщ!!!");

    }

    public HBox getLeftHBox() {
        return leftHBox;
    }

    public HBox getRightHBox() {
        return rightHBox;
    }

    public StackPane getLeftStackPane() {
        return leftStackPane;
    }

    public StackPane getRightStackPane() {
        return rightStackPane;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    ObservableList<String> list  = FXCollections.observableArrayList("10 by 10","20 by 20");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //chooseMap.setItems(list);
        //chooseMap.setValue(list.get(0));

    }

    public void brushTheField() throws Exception {
        Stage stage = new Stage();
        //Parent root = FXMLLoader.load(getClass().getResource("BattleField.fxml"));
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(50);
        rectangle.setWidth(50);
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setFill(Color.BLACK);
        //leftHBox.getChildren().add(rectangle);
        Group group1 = new Group(rectangle);
        //group1.getChildren().addAll(leftHBox);
        Scene scene = new Scene(group1);


        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


        stage.setScene(scene);
        stage.show();
    }
    private final AnchorPane anchorPaneInit(StackPane leftStackPane, StackPane rightStackPane){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(Background.EMPTY);
        anchorPane.setPrefWidth(1280);
        anchorPane.setPrefHeight(1024);
        return anchorPane;
    }
}
