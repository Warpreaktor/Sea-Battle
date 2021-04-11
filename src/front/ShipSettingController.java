package front;

import com.core.Tools;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ShipSettingController {
    @FXML private HBox hBox1 = new HBox();
    @FXML private HBox hBox2 = new HBox();
    @FXML private HBox hBox3 = new HBox();
    @FXML private HBox hBox4 = new HBox();
    @FXML private HBox hBox5 = new HBox();
    @FXML private HBox hBox6 = new HBox();
    @FXML private HBox hBox7 = new HBox();
    @FXML private HBox hBox8 = new HBox();
    @FXML private HBox hBox9 = new HBox();
    @FXML private HBox hBox10 = new HBox();
    @FXML private VBox vBox = new VBox(1, hBox1,hBox2,hBox3,hBox4,hBox5,hBox6,hBox7,hBox8,hBox9,hBox10);
    private Button startButton = new Button("Start");
    private final ImageView fullLinkor = new ImageView("/resources/fullLinkor60x240.png");
    private final ImageView fullCruiser1 = new ImageView("/resources/fullCruiser60x180.png");
    private final ImageView fullCruiser2 = new ImageView("/resources/fullCruiser60x180.png");
    private final ImageView fullDestroyer1 = new ImageView("/resources/fullDestroyer60x120.png");
    private final ImageView fullDestroyer2 = new ImageView("/resources/fullDestroyer60x120.png");
    private final ImageView fullDestroyer3 = new ImageView("/resources/fullDestroyer60x120.png");
    private final ImageView fullSubmarine1 = new ImageView("/resources/fullSubmarine60x60.png");
    private final ImageView fullSubmarine2 = new ImageView("/resources/fullSubmarine60x60.png");
    private final ImageView fullSubmarine3 = new ImageView("/resources/fullSubmarine60x60.png");
    private final ImageView fullSubmarine4 = new ImageView("/resources/fullSubmarine60x60.png");
    private Group shipsGroup = new Group(fullLinkor, fullCruiser1, fullCruiser2, fullDestroyer1, fullDestroyer2,
            fullDestroyer3, fullSubmarine1, fullSubmarine2, fullSubmarine3, fullSubmarine4);
    @FXML private AnchorPane shipSetPan = new AnchorPane(vBox, shipsGroup, startButton);

    public AnchorPane getShipSetPan() {
        return shipSetPan;
    }
    public VBox getvBox() {
        return vBox;
    }
    public HBox gethBox1() {
        return hBox1;
    }
    public HBox gethBox2() {
        return hBox2;
    }
    public HBox gethBox3() {
        return hBox3;
    }
    public HBox gethBox4() {
        return hBox4;
    }
    public HBox gethBox5() {
        return hBox5;
    }
    public HBox gethBox6() {
        return hBox6;
    }
    public HBox gethBox7() {
        return hBox7;
    }
    public HBox gethBox8() {
        return hBox8;
    }
    public HBox gethBox9() {
        return hBox9;
    }
    public HBox gethBox10() {
        return hBox10;
    }

    public ShipSettingController() {
        this.shipSetPan.setPrefHeight(1024);
        this.shipSetPan.setPrefWidth(1280);
        this.shipSetPan.setLayoutY(0);
        this.vBox.setLayoutX(340);
        this.vBox.setLayoutY(251);
        this.vBox.setPrefHeight(600);
        this.vBox.setPrefWidth(600);
        this.fullLinkor.setX(150); this.fullLinkor.setY(50);
        this.fullCruiser1.setX(400); this.fullCruiser1.setY(50);
        this.fullCruiser2.setX(590); this.fullCruiser2.setY(50);
        this.fullSubmarine1.setX(780); this.fullSubmarine1.setY(50);
        this.fullSubmarine2.setX(850); this.fullSubmarine2.setY(50);
        this.fullDestroyer1.setX(210); this.fullDestroyer1.setY(150);
        this.fullDestroyer2.setX(340); this.fullDestroyer2.setY(150);
        this.fullDestroyer3.setX(470); this.fullDestroyer3.setY(150);
        this.fullSubmarine3.setX(780); this.fullSubmarine3.setY(150);
        this.fullSubmarine4.setX(850); this.fullSubmarine4.setY(150);
        Tools.setDragSource(fullLinkor);
        Tools.setDragSource(fullCruiser1);
        Tools.setDragSource(fullCruiser2);
        Tools.setDragSource(fullSubmarine1);
        Tools.setDragSource(fullSubmarine2);
        Tools.setDragSource(fullSubmarine3);
        Tools.setDragSource(fullSubmarine4);
        Tools.setDragSource(fullDestroyer1);
        Tools.setDragSource(fullDestroyer2);
        Tools.setDragSource(fullDestroyer3);
        startButton.setLayoutX(510);
        startButton.setLayoutY(900);
        startButton.setPrefWidth(120);
        startButton.setPrefHeight(60);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.brushTheBattleField();
            }
        });
    }
}
