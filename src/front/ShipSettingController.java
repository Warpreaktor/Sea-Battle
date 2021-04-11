package front;

import com.core.Tools;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShipSettingController {
    private boolean isVertical = false;
    private HBox[] hBoxes = new HBox[10];
    private VBox vBox = new VBox(1);
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
    private AnchorPane shipSetPan = new AnchorPane(vBox, shipsGroup, startButton);

    public HBox[] gethBoxes() {
        return hBoxes;
    }
    public AnchorPane getShipSetPan() {
        return shipSetPan;
    }
    public VBox getvBox() {
        return vBox;
    }
    public boolean isVertical() {
        return isVertical;
    }
    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }
    public void changeRotation(){
        if (isVertical == true){
            isVertical = false;
        }else isVertical = true;
    }

    public ShipSettingController() {
        for (int i = 0; i < 10; i++) {
            hBoxes[i] = new HBox();
            vBox.getChildren().add(hBoxes[i]);
        }
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
        startButton.setLayoutX(580);
        startButton.setLayoutY(900);
        startButton.setPrefWidth(120);
        startButton.setPrefHeight(60);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.brushTheBattleField();
            }
        });
        shipSetPan.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode().equals(KeyCode.Z)){
                changeRotation();
                System.out.println("Rotation changed");
                }
            }
        });
    }
}
