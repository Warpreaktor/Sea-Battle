package com.core;

import javafx.scene.image.Image;

public class GameCell extends GameObject {
//  private boolean isShip = false;//Зарефакторить все вызовы к этой переменной как определение класса var.getClass()
  private String name;
//  private Ship objRef;  //На стадии удаления.
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


    @Override
    public String getName() {
        return name;
    }

    public GameCell(int Y, int X, ImageName imageName) {
        this.coordinateY = Y;
        this.coordinateX = X;
        super.setImage(imageName);
    }


    public GameCell(){
    }
}
