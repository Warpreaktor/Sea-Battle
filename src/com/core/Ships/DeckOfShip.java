package com.core.Ships;

import com.core.ImageName;
import com.core.GameObjects.MapObject;


public class DeckOfShip extends MapObject {
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
    @Override
    public boolean spruting() {
        for (int i = 0; i < shipOwner.getDecks().length; i++) {
            shipOwner.getDecks()[i].setImage(ImageName.KRAKEN);
        }
        return true;
    }

    @Override
    public boolean isShip() {
        return true;
    }
}
