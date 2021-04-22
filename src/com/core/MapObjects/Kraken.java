package com.core.MapObjects;

import com.core.ImageName;

public class Kraken extends MapObject {

    public Kraken(int Y, int X) {
        super.setCoordinateY(Y);
        super.setCoordinateX(X);
        this.setImage(ImageName.KRAKEN);
    }

    @Override
    public boolean isShip() {
        return false;
    }

    @Override
    public boolean spruting() {
        return false;
    }
}