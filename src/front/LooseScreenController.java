package front;

import com.core.Players.Difficult;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LooseScreenController  implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button tryAgain;
    @FXML
    private Text text;

    public LooseScreenController() {
        this.text = new Text();
        this.text.setLayoutY(100);
        this.text.setLayoutX(200);
        this.text.setFont(new Font(20));
        this.text.setTextAlignment(TextAlignment.CENTER);
        this.text.setFill(Color.BLACK);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/looseWall.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        anchorPane.setBackground(new Background(backgroundImage));
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public Text getText() {
        return text;
    }

    @FXML
    private void tryAgain() throws IOException {
        App.getAPP().resetGame(Difficult.EASY);
    }
}
