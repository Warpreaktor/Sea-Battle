package front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ListView<String> listView;
    @FXML public Label battleHistory = new Label();
    public String oldHistory = "";

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    @FXML
    public void clickOnField(MouseEvent mouseEvent) {
        System.out.println("Ты-дыщ!!!");
    }

    public MainController() {
        this.battleHistory.setText("Битва началась");;
        this.oldHistory = "";
    }

    public void textOutput(String text){
        this.oldHistory = this.battleHistory.getText();
        this.battleHistory.setText(oldHistory + text);
        System.out.println(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    private final AnchorPane anchorPaneInit(StackPane leftStackPane, StackPane rightStackPane){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(Background.EMPTY);
        anchorPane.setPrefWidth(1280);
        anchorPane.setPrefHeight(1024);
        return anchorPane;
    }
}
