package com.core.Ships;

import com.core.GameObjects.MapObject;
import com.core.ImageName;
import com.core.Players.Player;
import front.App;
import javafx.scene.layout.HBox;

public class Linkor extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[4]; //[y][x] Это, по сути, корпус корабля который состоит из нескольких partOfLinkor

    public DeckOfShip[] getDecks() {
        return decks;
    }

    public Linkor(Player owner) {
        super.setOwner(owner);
        super.setShipSize(decks.length);
        super.setName(naming());
        super.setHp(decks.length);
        for (int i = 0; i < decks.length; i++) {
             decks[i] = new DeckOfShip(i,this, 'L');
        }
    }

    @Override
    public void shipOnTheSeaX(int Y, int X){
        MapObject[][] map = this.getOwner().getOurFleetMap();
        int i = 3;
        for (int x = X - 2; x <= X + 1; x++) {
            map[Y][x] = decks[i];
            decks[i].setCoordinateY(Y);
            decks[i].setCoordinateX(x);
            if (!this.getOwner().isCPU()) {
                //Делаем графические изменения на экране игрока
                map[Y][x].setEffect(null);
                HBox[] hBoxes = App.SHIP_SETTING_CONTROLLER.gethBoxes();
                hBoxes[Y].getChildren().remove(x);
                hBoxes[Y].getChildren().add(x, decks[i]);
                map[Y][x].setImage(ImageName.LINKOR);
            }
            i--;
        }
        App.SEA_BATTLE_GAME.playerShipIncrement(this.getOwner());
    }
    @Override
    public void shipOnTheSeaY(int Y, int X){
        MapObject[][] map = this.getOwner().getOurFleetMap();
        int i = 3;
        for (int y = Y - 2; y <= Y+1; y++) {
            map[y][X] = decks[i];
            decks[i].setCoordinateY(y);
            decks[i].setCoordinateX(X);
            if (!this.getOwner().isCPU()) {
                //Делаем графические изменения на экране игрока
                map[y][X].setEffect(null);
                HBox[] hBoxes = App.SHIP_SETTING_CONTROLLER.gethBoxes();
                hBoxes[y].getChildren().remove(X);
                hBoxes[y].getChildren().add(X, decks[i]);
                map[y][X].setImage(ImageName.LINKOR);
            }
            i--;
        }
        App.SEA_BATTLE_GAME.playerShipIncrement(this.getOwner());
    }

    @Override
    public boolean spruting() {
        for (int i = 0; i < decks.length; i++) {
            decks[i].setImage(ImageName.KRAKEN);
            decks[i].setLabel('X');
        }
        return true;
    }

    @Override
    public boolean isShip() {
        return true;
    }
}
