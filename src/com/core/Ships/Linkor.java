package com.core.Ships;

import com.core.GameObject;
import com.core.ImageName;
import com.core.Player;

public class Linkor extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[4]; //[y][x] Это, по сути, корпус корабля который состоит из нескольких partOfLinkor
    private Player owner;

    public Linkor(Player owner) {
        this.owner = owner;
        super.setShipSize(4);
        super.setName(naming());
        for (int i = 0; i < super.getShipSize(); i++) {
             decks[i] = new DeckOfShip(i,this);
             decks[i].setLabel('s');
        }
    }

    @Override
    public String getName(){
        return super.getName();
    }
    @Override
    public char getLabel() {
        return super.getLabel();
    }
    @Override
    public void shipOnTheSea(int partOfShip, int y, int x){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        map[y][x].setImage(ImageName.LINKOR);
        map[y][x] = decks[partOfShip];
        map[y][x].setLabel('L');
    }

    public DeckOfShip[] getDecks() {
        return decks;
    }
}
