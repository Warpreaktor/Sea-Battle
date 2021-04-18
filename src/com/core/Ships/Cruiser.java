package com.core.Ships;

import com.core.GameObject;
import com.core.ImageName;
import com.core.Player;
import front.App;

public class Cruiser extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[3]; //[y][x] Это, по сути, корпус корабля который состоит из нескольких partOfLinkor

    public Cruiser(Player owner) {
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
        int i = 2;
        for (int x = X - 1; x <= X+1; x++) {
            map[Y][x].setImage(ImageName.CRUISER);
            map[Y][x].setEffect(null);
            map[Y][x] = decks[i];
            i--;
        }
        App.seaBattleGame.playerShipIncrement(this.getOwner());
    }
    @Override
    public void shipOnTheSeaY(int Y, int X){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        Player owner = this.getOwner();
        int i = 2;
        for (int y = Y - 1; y <= Y+1; y++) {
            map[y][X].setImage(ImageName.CRUISER);
            map[y][X].setEffect(null);
            map[y][X] = decks[i];
            i--;
        }
        App.seaBattleGame.playerShipIncrement(this.getOwner());
    }
}
