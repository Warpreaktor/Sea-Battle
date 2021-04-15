package com.core.Ships;

import com.core.GameObject;
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

    public DeckOfShip(int deckNumber, Ship shipowner) {
        this.dekcNumber = deckNumber;
        this.shipOwner = shipowner;
    }
}
