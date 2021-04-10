package com.core;

import front.App;
import javafx.event.EventHandler;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;


public class GameCell extends ImageView {
    private boolean isShip = false;//Является ли эта клетка кораблем или его частью.
    private char cellLabel = '~';//Какой символ отображать на консольной карте поля боя.
    private Ship shipRef;  //Ссылка на объект находящийся в этой клетке.
    private int coordinateX;
    private int coordinateY;
    private Image redCross = new Image(getClass().getResourceAsStream("/resources/redCRoss150x150.png"));
    private Image target = new Image(getClass().getResourceAsStream("/resources/target150x150.png"));
    private Image wave = new Image(getClass().getResourceAsStream("/resources/waveminBoard.jpg"));
    private Image dot = new Image(getClass().getResourceAsStream("/resources/dot150x150.png"));
    private Image ship = new Image(getClass().getResourceAsStream("/resources/ship150x150.jpg"));
    private Image dotGreen = new Image(getClass().getResourceAsStream("/resources/dotGreen150x150.png"));
    private Image linkor = new Image(getClass().getResourceAsStream("/resources/linkor150x150.png"));
    private Image cruiser = new Image("/resources/cruiser60x60.png");
    private Image destroyer = new Image("/resources/destroyer60x60.png");
    private Image submarine = new Image("/resources/submarine60x60.png");


    public int getCoordinateX() {
        return coordinateX;
    }
    public int getCoordinateY() {
        return coordinateY;
    }
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
    public void setShipRef(Ship shipRef) {
        this.shipRef = shipRef;
    }
    public Ship getShipRef() {
        return shipRef;
    }
    public void setCellLabel(char cellLabel) {
        this.cellLabel = cellLabel;
    }
    public char getCellLabel() {
        return cellLabel;
    }
    public void setIsShip(boolean ship) {
        isShip = ship;
    }
    public boolean isShip() {
        return isShip;
    }
    public void setRedCross(){
        this.setImage(redCross);
    }
    public void setTarget(){
        this.setImage(target);
    }
    public void setWave(){
        this.setImage(wave);
    }
    public void setDot(){
        this.setImage(dot);
    }
    public void setDotGreen(){this.setImage(dotGreen);}
    public void setLinkor(){this.setImage(linkor);}
    public void setCruiser(){this.setImage(cruiser);}
    public void setDestroyer(){this.setImage(destroyer);}
    public void setSubmarine(){this.setImage(submarine);}
    public void setShip(){
        this.setImage(ship);
    }

    public void setDragTargetZone(GameCell targetZone){
        //Событие при заходе в зону
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
                        event.getDragboard().hasImage()) {
                    targetZone.setEffect(new BoxBlur());
                }
                event.consume();
            }
        });
        //Событие наступающее при выходе ресурса из зоны
        targetZone.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                targetZone.setEffect(null);

                event.consume();
            }
        });
        //Событие наступающее при отпускании ресурса в зоне
        targetZone.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    targetZone.setImage(db.getImage());
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }


    public GameCell() {
    }
}
