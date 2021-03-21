package com.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameCell extends ImageView {
    private boolean isShip = false;//Является ли эта клетка кораблем или его частью.
    private char cellLabel = '~';//Какой символ отображать на консольной карте поля боя.
    private Ship shipRef;  //Ссылка на объект находящийся в этой клетке.
    private int coordinateX;
    private int coordinateY;
    private Image redCross = new Image(getClass().getResourceAsStream("/resources/redCRoss.png"));
    private Image target = new Image(getClass().getResourceAsStream("/resources/target.png"));
    private Image wave = new Image(getClass().getResourceAsStream("/resources/waveminBoard.jpg"));


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

    public void setShip(boolean ship) {
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

    public GameCell() {
    }
}
