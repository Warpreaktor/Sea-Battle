package com.core.Ships;

import com.core.GameObject;
import com.core.ImageName;
import com.core.Player;
import com.core.Tools;


public class DeckOfShip extends GameObject {
    private boolean isDamaged = false;
    private int dekcNumber;
    private Ship shipOwner;//Ссылка на корабль, владельца этой деки.

    @Override
    public String getName(){
        return shipOwner.getName();
    }

    public int getHp(){
        return shipOwner.getHp();
    }
    public int shipSize(){
        return shipOwner.getShipSize();
    }

    public Ship getShipOwner() {
        return shipOwner;
    }

    public DeckOfShip(int deckNumber, Ship shipowner) {
        this.dekcNumber = deckNumber;
        this.shipOwner = shipowner;
    }

//    @Override
//    public void setImage(ImageName imageName){
//        this.setImage(imageName);
//    }
}
