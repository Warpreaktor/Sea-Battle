package com.core;

import front.App;
import javafx.event.EventHandler;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

/*Необходимо сделать этот класс абстрактным и от него наследовать несколько объектов. Корабли там всякие, и прочее.
 */
public class GameCell extends ImageView {
    private boolean isShip = false;//Является ли эта клетка кораблем или его частью.
    private char cellLabel = '~';//Какой символ отображать на консольной карте поля боя.
    private String name = "Морская волна";
    //private Ship objRef;  //На стадии рефакторинга.
    private int coordinateX;
    private int coordinateY;
    public Image redCross = new Image(getClass().getResourceAsStream("/resources/redCRoss150x150.png"));
    public Image target = new Image(getClass().getResourceAsStream("/resources/target150x150.png"));
    public Image wave = new Image(getClass().getResourceAsStream("/resources/waveminBoard.jpg"));
    public Image dot = new Image(getClass().getResourceAsStream("/resources/dot150x150.png"));
    public Image ship = new Image(getClass().getResourceAsStream("/resources/ship150x150.jpg"));
    public Image dotGreen = new Image(getClass().getResourceAsStream("/resources/dotGreen150x150.png"));
    public Image linkor = new Image("/resources/linkor150x150.png");
    public Image cruiser = new Image("/resources/cruiser60x60.png");
    public Image destroyer = new Image("/resources/destroyer60x60.png");
    public Image submarine = new Image("/resources/submarine60x60.png");

    public String getName(){
        return this.name;
    };
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


    public GameCell() {
    }
}
