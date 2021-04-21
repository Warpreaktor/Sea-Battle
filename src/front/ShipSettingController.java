package front;

import com.core.Ships.Ship;
import com.core.Tools;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ShipSettingController {
    private boolean isVertical = false;
    private HBox[] hBoxes = new HBox[10];
    private VBox field = new VBox(1);
    private Button startButton = new Button("Start \n[SPACE]");
    private Button randomButton = new Button("Random");
    private VBox shipBox = new VBox(1);
    private AnchorPane shipSetPan = new AnchorPane(field, shipBox, startButton, randomButton);


    public HBox[] gethBoxes() {
        return hBoxes;
    }
    public AnchorPane getShipSetPan() {
        return shipSetPan;
    }
    public VBox getField() {
        return field;
    }
    public boolean isVertical() {
        return isVertical;
    }
    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    public void changeRotation() {
        if (isVertical == true) {
            isVertical = false;
        } else isVertical = true;
    }

    public ShipSettingController() {
        for (int i = 0; i < 10; i++) {
            hBoxes[i] = new HBox();
            field.getChildren().add(hBoxes[i]);
        }
        shipSetPanInit();
        vBoxInit();
        startButtonInit();
        randomButtonInit();
        shipBoxInit();
        ArrayList<Ship> shipyard = App.SEA_BATTLE_GAME.getHuman().getShipyard();

        for (int i = 0; i < shipyard.size(); i++) {
            int shipSize = shipyard.get(i).getShipSize();
            Ship ship = shipyard.get(i);
            switch (shipSize) {
                case 4:
                    ship.setFitWidth(240);
                    ship.setFitHeight(60);
                    ship.setImage(new Image("/resources/linkor60x240.png"));
                    shipBox.getChildren().add(ship);
                    Tools.setDragSource(ship);
                    break;
                case 3:
                    shipyard.get(i).setFitWidth(180);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/cruiser60x180.png"));
                    shipBox.getChildren().add(shipyard.get(i));
                    Tools.setDragSource(ship);
                    break;
                case 2:
                    shipyard.get(i).setFitWidth(120);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/destroyer60x120.png"));
                    shipBox.getChildren().add(shipyard.get(i));
                    Tools.setDragSource(ship);
                    break;
                case 1:
                    shipyard.get(i).setFitWidth(60);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/submarine60x60.png"));
                    shipBox.getChildren().add(shipyard.get(i));
                    Tools.setDragSource(ship);
                    break;
            }
        }

    }

    public void shipBoxInit() {
        shipBox.setLayoutY(251);
        shipBox.setLayoutX(50);
    }

    public void shipSetPanInit() {
        this.shipSetPan.setPrefHeight(1024);
        this.shipSetPan.setPrefWidth(1280);
        this.shipSetPan.setLayoutY(0);
    }

    public void vBoxInit() {
        this.field.setLayoutY(251);
        this.field.setLayoutX(340);
        this.field.setPrefHeight(600);
        this.field.setPrefWidth(600);
    }
    public void startButtonInit(){
        startButton.setLayoutY(900);
        startButton.setLayoutX(580);
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
                if (keyEvent.getCode().equals(KeyCode.Z)) {
                    changeRotation();
                    System.out.println("Rotation changed");
                }
            }
        });

    }
    public void randomButtonInit(){
        randomButton.setLayoutX(780);
        randomButton.setLayoutY(900);
        randomButton.setPrefWidth(120);
        randomButton.setPrefHeight(60);
        randomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.SEA_BATTLE_GAME.getHuman().shipsOnGame();
            }
        });
    }
}
