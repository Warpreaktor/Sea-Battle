package com.core.Ships;

import com.core.MapObjects.MapObject;
import com.core.ImageName;
import com.core.Players.Player;
import front.App;
import javafx.scene.layout.HBox;

public class Schooner extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[1]; //[y][x] Корпус корабля который состоит из нескольких DeckOfShip

    public Schooner(Player owner) {
        super.setOwner(owner);
        super.setShipSize(decks.length);
        super.setName(naming());
        super.setHp(decks.length);
        for (int i = 0; i < decks.length; i++) {
            decks[i] = new DeckOfShip(i,this, 'S');
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
        MapObject[][] map = this.getOwner().getOurFleetMap();
        map[Y][X] = decks[0];
        decks[0].setCoordinateY(Y);
        decks[0].setCoordinateX(X);
        if (!this.getOwner().isCPU()) {
            //Делаем графические изменения только на экране игрока
            map[Y][X].setEffect(null);
            HBox[] hBoxes = App.SHIP_SETTING_CONTROLLER.getFieldRows();
            hBoxes[Y].getChildren().remove(X);
            hBoxes[Y].getChildren().add(X, decks[0]);
            map[Y][X].setImage(ImageName.SCHOONER);
            decks[0].setOnMouseEvent();
        }else{
            map[Y][X].setEffect(null);
            map[Y][X].setImage(ImageName.SCHOONER);
        }

        App.SEA_BATTLE_GAME.playerShipIncrement(this.getOwner());
    }

    @Override
    public void shipOnTheSeaY(int Y, int X){
        shipOnTheSeaX(Y, X);
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
