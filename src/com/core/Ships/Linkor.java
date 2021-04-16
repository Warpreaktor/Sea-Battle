package com.core.Ships;

import com.core.GameObject;
import com.core.ImageName;
import com.core.Player;

public class Linkor extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[4]; //[y][x] Это, по сути, корпус корабля который состоит из нескольких partOfLinkor

    public Linkor(Player owner) {
        super.setOwner(owner);
        super.setShipSize(decks.length);
        super.setName(naming());
        super.setHp(decks.length);
        for (int i = 0; i < decks.length; i++) {
             decks[i] = new DeckOfShip(i,this);
             decks[i].setLabel('s');
        }
    }

    @Override
    public void shipOnTheSea(int partOfShip, int y, int x){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        map[y][x].setImage(ImageName.LINKOR);
        map[y][x].setEffect(null);
        map[y][x] = decks[partOfShip];
        map[y][x].setLabel('L');
    }

    public DeckOfShip[] getDecks() {
        return decks;
    }
}
