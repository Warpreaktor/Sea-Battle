package com.core;

public class GameCell {
    private boolean isShip;//Является ли эта клетка кораблем или его частью
    private char cellLabel = '~';//Какой символ отображать на консольной карте поля боя

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
}
