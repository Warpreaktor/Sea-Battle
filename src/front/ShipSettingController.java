package front;

import com.core.GameObjects.MapCell;
import com.core.GameObjects.MapObject;
import com.core.Ships.Ship;
import com.core.Tools;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
    private HBox[] fieldRows;
    private VBox field;
    private Button startButton;
    private Button resetButton;
    private Button randomButton;
    private VBox shipYard;
    private AnchorPane mainPanel;

    public void changeRotation() {
        if (isVertical == true) {
            isVertical = false;
        } else isVertical = true;
    }
    public VBox getField() {
        return field;
    }
    public HBox[] getFieldRows() {
        return fieldRows;
    }

    public VBox getShipYard() {
        return shipYard;
    }

    public AnchorPane getMainPanel() {
        return mainPanel;
    }

    public void hBoxesInit(){
        fieldRows = new HBox[10];
    }
    public boolean isVertical() {
        return isVertical;
    }
    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    public ShipSettingController() {
        hBoxesInit();
        fieldInit();
        shipYardInit();
        startButtonInit();
        resetButtonInit();
        randomButtonInit();
        mainPanelInit();
    }

    private void shipYardInit(){
        shipYard = new VBox(1);
        shipYard.setLayoutY(251);
        shipYard.setLayoutX(50);

        ArrayList<Ship> shipyard = App.SEA_BATTLE_GAME.getHuman().getShipyard();

        for (int i = 0; i < shipyard.size(); i++) {
            int shipSize = shipyard.get(i).getShipSize();
            Ship ship = shipyard.get(i);
            switch (shipSize) {
                case 4:
                    ship.setFitWidth(240);
                    ship.setFitHeight(60);
                    ship.setImage(new Image("/resources/linkor60x240.png"));
                    shipYard.getChildren().add(ship);
                    Tools.setDragSource(ship);
                    break;
                case 3:
                    shipyard.get(i).setFitWidth(180);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/cruiser60x180.png"));
                    shipYard.getChildren().add(shipyard.get(i));
                    Tools.setDragSource(ship);
                    break;
                case 2:
                    shipyard.get(i).setFitWidth(120);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/destroyer60x120.png"));
                    shipYard.getChildren().add(shipyard.get(i));
                    Tools.setDragSource(ship);
                    break;
                case 1:
                    shipyard.get(i).setFitWidth(60);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/submarine60x60.png"));
                    shipYard.getChildren().add(shipyard.get(i));
                    Tools.setDragSource(ship);
                    break;
            }
        }
    }


    public void fieldInit() {
        field  = new VBox(1);
        field.setLayoutY(251);
        field.setLayoutX(340);
        field.setPrefHeight(600);
        field.setPrefWidth(600);
        for (int i = 0; i < 10; i++) {
            fieldRows[i] = new HBox();
            field.getChildren().add(fieldRows[i]);
        }
    }

    public void mainPanelInit() {
        mainPanel = new AnchorPane(field, shipYard, startButton, randomButton, resetButton);
        mainPanel.setPrefHeight(1024);
        mainPanel.setPrefWidth(1280);
        mainPanel.setLayoutY(0);
        mainPanel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.R)) {
                    changeRotation();
                    System.out.println("Rotation changed");
                }
            }
        });
    }


    public void startButtonInit(){
        startButton  = new Button(" Start\n[SPACE]");
        startButton.setAlignment(Pos.CENTER);
        startButton.setLayoutY(900);
        startButton.setLayoutX(580);
        startButton.setPrefWidth(120);
        startButton.setPrefHeight(60);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (App.SEA_BATTLE_GAME.getHuman().getShipyard().size() > 0) {
                    System.out.println("Не все корабли спущены на воду");
                }else{
                    App.brushTheBattleField();
                }
            }
        });


    }
    public void randomButtonInit(){
        randomButton = new Button("Random");
        randomButton.setAlignment(Pos.CENTER);
        randomButton.setLayoutY(900);
        randomButton.setLayoutX(780);
        randomButton.setPrefWidth(120);
        randomButton.setPrefHeight(60);
        randomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                randomButton.setAlignment(Pos.CENTER);
                App.SEA_BATTLE_GAME.getHuman().shipsOnGame();
            }
        });
    }
    public void resetButtonInit(){
        resetButton = new Button("Reset");
        resetButton.setAlignment(Pos.CENTER);
        resetButton.setLayoutY(900);
        resetButton.setLayoutX(380);
        resetButton.setPrefWidth(120);
        resetButton.setPrefHeight(60);
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetButton.setAlignment(Pos.CENTER);
                for (int y = fieldRows.length-1; y >= 0; y--) {
                    for (int x = fieldRows[y].getChildren().size() - 1; x >= 0; x--) {
                        fieldRows[y].getChildren().remove(x);
                        MapObject gameCell = new MapCell(y, x);
                        fieldRows[y].getChildren().add(x, gameCell);
                        App.SEA_BATTLE_GAME.getHuman().setGameCellToOurFleetMap(gameCell, y, x);
                        Tools.setDragTargetZone(gameCell);
                    }
                }
                App.SEA_BATTLE_GAME.getHuman().shipYardInit();
                for(int i = shipYard.getChildren().size() - 1; i >= 0; i--){
                    shipYard.getChildren().remove(i);
                }
                App.SHIP_SETTING_CONTROLLER.shipYardInit();
                mainPanel.getChildren().add(shipYard);
                App.SEA_BATTLE_GAME.getHuman().setNumberOfShip(0);
            }
        });
    }
}