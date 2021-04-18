package com.core.Ships;

import com.core.GameObject;
import com.core.ImageName;
import com.core.Player;
import front.App;

public class Destroyer extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[2]; //[y][x] Это, по сути, корпус корабля который состоит из нескольких partOfLinkor

    public Destroyer(Player owner) {
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
        int i = 1;
        for (int x = X - 1; x <= X; x++) {
            map[Y][x].setImage(ImageName.DESTROYER);
            map[Y][x].setEffect(null);
            map[Y][x] = decks[i];
            i--;
        }
        App.seaBattleGame.playerShipIncrement(this.getOwner());

    }
    @Override
    public void shipOnTheSeaY(int Y, int X){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        int i = 1;
        for (int y = Y - 1; y <= Y; y++) {
            map[y][X].setImage(ImageName.DESTROYER);
            map[y][X].setEffect(null);
            map[y][X] = decks[i];
            i--;
        }
        App.seaBattleGame.playerShipIncrement(this.getOwner());

    }
}
