package front;

import com.core.ImageName;
import com.core.MapObjects.MapCell;
import com.core.MapObjects.MapObject;
import com.core.SeaBattleGame;
import com.core.Ships.Ship;
import com.core.Tools;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ShipSettingController {
    private boolean isVertical = false;
    private HBox[] fieldRows;
    private VBox field;
    private Button startButton;
    private Button resetButton;
    private Button randomButton;
    private VBox shipYard;
    private Label shipName;
    private Label aboutRotation;
    private AnchorPane mainPanel;
    private static Ship dragObject;

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
        if (App.musicPlayer.getPlayingTrack().isPlaying());
        else App.musicPlayer.play(App.musicPlayer.getMusicTracks()[0]);
        hBoxesInit();
        fieldInit();
        shipYardInit();
        startButtonInit();
        resetButtonInit();
        randomButtonInit();
        shipNameInit();
        aboutRotationInit();
        mainPanelInit();
    }

    public void aboutRotationInit(){
        aboutRotation = new Label();
        aboutRotation.setLayoutY(200);
        aboutRotation.setLayoutX(30);
        aboutRotation.setFont(Font.font("Cambria", 20));
        aboutRotation.setTextFill(Color.BLACK);
        aboutRotation.setText("R button for rotation ship");
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
                    ship.setImage(new Image("/resources/battleship60x240.png"));
                    shipYard.getChildren().add(ship);
                    setDragSource(ship);
                    break;
                case 3:
                    shipyard.get(i).setFitWidth(180);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/galleon60x180.png"));
                    shipYard.getChildren().add(shipyard.get(i));
                    setDragSource(ship);
                    break;
                case 2:
                    shipyard.get(i).setFitWidth(120);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/frigate60x120.png"));
                    shipYard.getChildren().add(shipyard.get(i));
                    setDragSource(ship);
                    break;
                case 1:
                    shipyard.get(i).setFitWidth(60);
                    shipyard.get(i).setFitHeight(60);
                    shipyard.get(i).setImage(new Image("/resources/schooner60x60.png"));
                    shipYard.getChildren().add(shipyard.get(i));
                    setDragSource(ship);
                    break;
            }
        }
    }
    public void shipNameInit(){
        shipName = new Label();
        shipName.setLayoutY(160);
        shipName.setLayoutX(30);
        shipName.setFont(Font.font("Cambria", 24));
        shipName.setTextFill(Color.BROWN);
        shipName.setText("Имя корабля: ");
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
        for (int y = 0; y < App.SEA_BATTLE_GAME.getSIZE(); y++) {
            for (int x = 0; x < App.SEA_BATTLE_GAME.getSIZE(); x++) {
                MapObject gameCell = new MapCell(y, x);
                getFieldRows()[y].getChildren().add(gameCell);
                App.SEA_BATTLE_GAME.getHuman().setGameCellToOurFleetMap(gameCell, y, x);
                setDragTargetZone(gameCell);
            }
        }
    }

    public void mainPanelInit() {
        BackgroundImage background = new BackgroundImage(new Image("/resources/shipSettingWallpaper1280x1024.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        mainPanel = new AnchorPane(field, shipYard, startButton, randomButton, resetButton, shipName, aboutRotation);
        mainPanel.setPrefHeight(1024);
        mainPanel.setPrefWidth(1280);
        mainPanel.setLayoutY(0);
        mainPanel.setBackground(new Background(background));
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
        startButton  = new Button("Start\n[SPACE]");
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
                        setDragTargetZone(gameCell);
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
    public void setDragSource(Ship source) {
        //Событие при обнаружении перетаскивания
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
                /* Put a image on a dragboard and object to global variable */
                dragObject = source;
                ClipboardContent content = new ClipboardContent();
                content.putImage(source.getImage());
                db.setContent(content);
                event.consume();
            }
        });
        source.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                source.setCursor(Cursor.CLOSED_HAND);
            }
        });
        source.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                source.setCursor(Cursor.OPEN_HAND);
                shipName.setText("Имя корабля: " + source.getName());
            }
        });
        //Событие при завершении перетаскивания
        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag and drop gesture ended */
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setImage(ImageName.NULL);
                }
                shipName.setText("Имя корабля: ");
                event.consume();
            }
        });
    }

    public void setDragTargetZone(MapObject targetZone) {
        targetZone.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* Ресурс находится внутри зоны */
                /* accept it only if it is not dragged from the same node
                 * and if it has a image data */
                if (event.getGestureSource() != targetZone &&
                        event.getDragboard().hasImage()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
        //Событие которое наступает при входе ресурса в зону
        targetZone.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the targetZone */
                /* show to the user that it is an actual gesture targetZone */
                if (event.getGestureSource() != targetZone &&
                        event.getDragboard().hasImage() && targetZone.getClass().getSimpleName().equals("MapCell")) {
                    Effect fx = new Shadow();
                    int imgSize = (int) event.getDragboard().getImage().getWidth();
                    if (App.SHIP_SETTING_CONTROLLER.isVertical() == false) {
                        setFxX(targetZone.getCoordinateY(), targetZone.getCoordinateX(), fx, imgSize);
                    } else {
                        setFxY(targetZone.getCoordinateY(), targetZone.getCoordinateX(), fx, imgSize);
                    }
                }
                event.consume();
            }
        });
        //Событие наступающее при выходе ресурса из зоны
        targetZone.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                int imgSize = (int) event.getDragboard().getImage().getWidth();
                if (App.SHIP_SETTING_CONTROLLER.isVertical() == false) {
                    clearFxX(targetZone.getCoordinateY(), targetZone.getCoordinateX(), imgSize);
                } else {
                    clearFxY(targetZone.getCoordinateY(), targetZone.getCoordinateX(), imgSize);
                }
                event.consume();
            }
        });
        //Drop event
        targetZone.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a image data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage() && targetZone.getClass().getSimpleName().equals("MapCell")) {
                    if (App.SHIP_SETTING_CONTROLLER.isVertical() == false) {
                        success = Tools.setShipToCellsX(dragObject, targetZone.getCoordinateY(), targetZone.getCoordinateX());
                        dragObject.getOwner().getShipyard().remove(dragObject);
                    } else {
                        success = Tools.setShipToCellsY(dragObject, targetZone.getCoordinateY(), targetZone.getCoordinateX());
                        dragObject.getOwner().getShipyard().remove(dragObject);
                    }
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    public static void setFxX(int Y, int X, Effect fx, int imgSize) {
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
        imgSize /= 60;
        switch (imgSize) {
            case 4:
                if (X + 1 < SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(fx);
                map[Y][X].setEffect(fx);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(fx);
                if (X - 2 >= 0) map[Y][X - 2].setEffect(fx);
                break;
            case 3:
                if (X + 1 < SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(fx);
                map[Y][X].setEffect(fx);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(fx);
                break;
            case 2:
                map[Y][X].setEffect(fx);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(fx);
                break;
            case 1:
                map[Y][X].setEffect(fx);
                break;
        }
    }

    public static void setFxY(int Y, int X, Effect fx, int imgSize) {
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
        imgSize /= 60;
        switch (imgSize) {
            case 4:
                if (Y + 1 < SeaBattleGame.getSIZE()) map[Y + 1][X].setEffect(fx);
                map[Y][X].setEffect(fx);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(fx);
                if (Y - 2 >= 0) map[Y - 2][X].setEffect(fx);
                break;
            case 3:
                if (Y + 1 < SeaBattleGame.getSIZE()) map[Y + 1][X].setEffect(fx);
                map[Y][X].setEffect(fx);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(fx);
                break;
            case 2:
                map[Y][X].setEffect(fx);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(fx);
                break;
            case 1:
                map[Y][X].setEffect(fx);
                break;
        }
    }

    public static void clearFxX(int Y, int X, int imgSize) {
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
        imgSize /= 60;
        switch (imgSize) {
            case 4:
                map[Y][X].setEffect(null);
                if (X + 1 < SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(null);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(null);
                if (X - 2 >= 0) map[Y][X - 2].setEffect(null);
                break;
            case 3:
                map[Y][X].setEffect(null);
                if (X + 1 < SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(null);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(null);
                break;
            case 2:
                map[Y][X].setEffect(null);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(null);
                break;
            case 1:
                map[Y][X].setEffect(null);
                break;
        }
    }

    public static void clearFxY(int Y, int X, int imgSize) {
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
        imgSize /= 60;
        switch (imgSize) {
            case 4:
                map[Y][X].setEffect(null);
                if (Y + 1 < SeaBattleGame.getSIZE()) map[Y + 1][X].setEffect(null);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(null);
                if (Y - 2 >= 0) map[Y - 2][X].setEffect(null);
                break;
            case 3:
                map[Y][X].setEffect(null);
                if (Y + 1 < SeaBattleGame.getSIZE()) map[Y + 1][X].setEffect(null);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(null);
                break;
            case 2:
                map[Y][X].setEffect(null);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(null);
                break;
            case 1:
                map[Y][X].setEffect(null);
                break;
        }
    }

}