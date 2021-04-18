package com.core.Ships;

import com.core.GameObject;
import com.core.ImageName;
import com.core.Player;
import front.App;

public class Submarine extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[1]; //[y][x] Это, по сути, корпус корабля который состоит из нескольких partOfLinkor

    public Submarine(Player owner) {
        super.setOwner(owner);
        super.setShipSize(decks.length);
        super.setName(naming());
        super.setHp(decks.length);
        for (int i = 0; i < decks.length; i++) {
            decks[i] = new DeckOfShip(i,this);
        }
    }

    @Override
    public DeckOfShip[] getDecks() {
        return decks;
    }

    @Override
    public char getLabel() {
        return super.getLabel();
    }

    @Override
    public void shipOnTheSeaX(int Y, int X){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        map[Y][X].setImage(ImageName.SUBMARINE);
        map[Y][X].setEffect(null);
        map[Y][X] = decks[0];
        App.seaBattleGame.playerShipIncrement(this.getOwner());
    }

    @Override
    public void shipOnTheSeaY(int Y, int X){
        shipOnTheSeaX(Y, X);
    }
}
