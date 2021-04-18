package com.core;

import javafx.scene.image.Image;

public class GameCell extends GameObject {
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

    public GameCell(int Y, int X, ImageName imageName) {
        this.coordinateY = Y;
        this.coordinateX = X;
        this.setImage(imageName);
    }

    public GameCell(int Y, int X){
        this.setCoordinateY(Y);
        this.setCoordinateX(X);
        this.setFitHeight(60);
        this.setFitWidth(60);
        this.setImage(ImageName.WAVE);
    }
}
