package front;

import com.core.SeaBattleGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class Controller implements Initializable {
    @FXML
    private Label label;
    @FXML
    private HBox leftHBox = new HBox();
    @FXML
    private HBox rightHBox = new HBox();
    @FXML
    private ComboBox chooseMap;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Hello World!");
    }

    @FXML
    private void clickOnField(MouseEvent event){
    }

    public void clickOnField(javafx.scene.input.MouseEvent mouseEvent) {
        label.setText("Ты-дыщ!!!");

    }

    ObservableList<String> list  = FXCollections.observableArrayList("10 by 10","20 by 20");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseMap.setItems(list);
    }

    public void brushTheField() throws Exception {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("BattleField.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
