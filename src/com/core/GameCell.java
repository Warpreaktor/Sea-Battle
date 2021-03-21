package com.core;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class GameCell extends Rectangle {
    private boolean isShip = false;//Является ли эта клетка кораблем или его частью.
    private char cellLabel = '~';//Какой символ отображать на консольной карте поля боя.
    private Ship shipRef;  //Ссылка на объект находящийся в этой клетке.
    private int coordinateX;
    private int coordinateY;

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

    public GameCell() {
    }

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {

            System.out.println("Тыдыщь!");
        }
    };
}
