package front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.event.MouseEvent;

public class BasicFXMLController {
    @FXML
    private Label label;

    public void initialize() {

    }

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
}
