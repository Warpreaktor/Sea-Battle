package com.core.GameObjects;

import com.core.ImageName;

public class MapCell extends MapObject {
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

    public MapCell(int Y, int X){
        this.coordinateY = Y;
        this.coordinateX = X;
        this.setImage(ImageName.WAVE);
    }



    @Override
    public boolean isShip() {
        return false;
    }
}
