package com.core.Ships;

import com.core.GameObject;
import com.core.ImageName;
import com.core.Player;

public class Submarine extends Ship {
    private Ship[] ships; //[y][x] Это по сути корпус корабля, который состоит из нескольких ячеек
    private int hp;
    private int shipSize;        //Количество палуб у корабля
    private Player owner;

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public int getShipSize() {
        return shipSize;
    }
    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public Submarine(Player owner) {
        super.setShipSize(1);
        ships = new Ship[super.getShipSize()];
        for (int i = 0; i < super.getShipSize(); i++) {
            ships[i] = new Ship();
            //ships[i].setImage(ImageName.SUBMARINE);
            super.setLabel('s');
            super.setOwner(owner);
            super.setName(naming());
        }
    }
    @Override
    public char getLabel() {
        return super.getLabel();
    }

    @Override
    public void shipOnTheSea(int partOfShip, int y, int x){
        GameObject[][] map = super.getOwner().getOurFleetMap();
        map[y][x].setImage(ImageName.SUBMARINE);
        map[y][x] = ships[partOfShip];
        map[y][x].setLabel('S');
    }
}
