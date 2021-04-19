package com.core.MapObjects;

import com.core.ImageName;

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

    @Override
    public boolean spruting() {
        return false;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public GameCell(int Y, int X){
        this.coordinateY = Y;
        this.coordinateX = X;
        this.setFitHeight(60);
        this.setFitWidth(60);
        this.setImage(ImageName.WAVE);
    }

    public class Octopus extends GameCell{

        public Octopus(int Y, int X) {
            super(Y, X);
            this.setImage(ImageName.OCTOPUS);
        }
    }
}
