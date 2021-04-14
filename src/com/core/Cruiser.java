package com.core;

import javafx.scene.image.Image;

public class Cruiser extends Ship {
    private Ship[] ships; //[y][x] Это по сути корпус корабля, который состоит из нескольких ячеек

    public Cruiser(Player owner) {
        super.setShipSize(3);
        ships = new Ship[super.getShipSize()];
        for (int i = 0; i < super.getShipSize(); i++) {
            ships[i] = new Ship();
            ships[i].setImage(new Image("/resources/cruiser60x60.png"));
            super.setLabel('s');
            super.setOwner(owner);
        }
    }
    @Override
    public char getLabel() {
        return super.getLabel();
    }
    @Override
    public void shipOnTheSea(int partOfShip, int y, int x){
        GameObject[][] map = super.getOwner().getOurFleetMap();
        map[y][x] = ships[partOfShip];
        map[y][x].setLabel('C');
    }
}
