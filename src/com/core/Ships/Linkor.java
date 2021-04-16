package com.core.Ships;

import com.core.GameObject;
import com.core.ImageName;
import com.core.Player;
import com.core.SeaBattleGame;
import front.App;

public class Linkor extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[4]; //[y][x] Это, по сути, корпус корабля который состоит из нескольких partOfLinkor

    public Linkor(Player owner) {
        super.setOwner(owner);
        super.setShipSize(decks.length);
        super.setName(naming());
        super.setHp(decks.length);
        for (int i = 0; i < decks.length; i++) {
             decks[i] = new DeckOfShip(i,this);
        }
    }

    @Override
    public void shipOnTheSeaX(int Y, int X){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        int i = 3;
        for (int x = X - 2; x <= X+1; x++) {
            map[Y][x].setImage(ImageName.LINKOR);
            map[Y][x].setEffect(null);
            map[Y][x] = decks[i];
            i--;
        }
        App.seaBattleGame.PlayerShipIncrement(this.getOwner());
    }
    @Override
    public void shipOnTheSeaY(int Y, int X){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        int i = 3;
        for (int y = Y - 2; y <= Y+1; y++) {
            map[y][X].setImage(ImageName.LINKOR);
            map[y][X].setEffect(null);
            map[y][X] = decks[i];
            i--;
        }
        App.seaBattleGame.PlayerShipIncrement(this.getOwner());
    }

    public DeckOfShip[] getDecks() {
        return decks;
    }
}
