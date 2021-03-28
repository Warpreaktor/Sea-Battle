package com.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
    public void setDot(){
        this.setImage(dot);
    }
    public void setDotGreen(){this.setImage(dotGreen);}
    public void setlinkor(){this.setImage(linkor);}
    public void setShip(){
        this.setImage(ship);
    }

    public GameCell() {
    }
}
