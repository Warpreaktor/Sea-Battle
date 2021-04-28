package com.core.Ships;

import com.core.MapObjects.MapObject;
import com.core.ImageName;
import com.core.Players.Player;
import front.App;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class Frigate extends Ship {
    private DeckOfShip[] decks = new DeckOfShip[2]; //[y][x] Это, по сути, корпус корабля который состоит из нескольких partOfLinkor

    public Frigate(Player owner) {
        super.setOwner(owner);
        super.setShipSize(decks.length);
        super.setName(naming());
        super.setHp(decks.length);
        for (int i = 0; i < decks.length; i++) {
            decks[i] = new DeckOfShip(i, this, 'D');
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
    public void shipOnTheSeaX(int Y, int X) {
        MapObject[][] map = this.getOwner().getOurFleetMap();
        int i = 1;
        for (int x = X - 1; x <= X; x++) {
            map[Y][x] = decks[i];
            decks[i].setCoordinateY(Y);
            decks[i].setCoordinateX(x);
            if (!this.getOwner().isCPU()) {
                //Делаем графические изменения на экране игрока
                map[Y][x].setEffect(null);
                HBox[] hBoxes = App.SHIP_SETTING_CONTROLLER.getFieldRows();
                hBoxes[Y].getChildren().remove(x);
                hBoxes[Y].getChildren().add(x, decks[i]);
                map[Y][x].setImage(ImageName.FRIGATE);
                decks[i].setOnMouseEvent();
            }else{
                map[Y][x].setEffect(null);
                map[Y][x].setImage(ImageName.FRIGATE);
            }
            i--;
        }
        App.SEA_BATTLE_GAME.playerShipIncrement(this.getOwner());

    }

    @Override
    public void shipOnTheSeaY(int Y, int X) {
        MapObject[][] map = this.getOwner().getOurFleetMap();
        int i = 1;
        for (int y = Y - 1; y <= Y; y++) {
            map[y][X] = decks[i];
            decks[i].setCoordinateY(y);
            decks[i].setCoordinateX(X);
            if (!this.getOwner().isCPU()) {
                //Делаем графические изменения на экране игрока
                map[y][X].setEffect(null);
                HBox[] hBoxes = App.SHIP_SETTING_CONTROLLER.getFieldRows();
                hBoxes[y].getChildren().remove(X);
                hBoxes[y].getChildren().add(X, decks[i]);
                map[y][X].setImage(new Image("/resources/frigate60x60.png"));
                decks[i].setOnMouseEvent();
            }else{
                map[y][X].setEffect(null);
                map[y][X].setImage(new Image("/resources/frigate60x60.png"));
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
