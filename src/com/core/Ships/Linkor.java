package com.core.Ships;

import com.core.MapObjects.GameObject;
import com.core.ImageName;
import com.core.Player;
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
             decks[i] = new DeckOfShip(i,this);
        }
    }

    @Override
    public void shipOnTheSeaX(int Y, int X){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        int i = 3;
        for (int x = X - 2; x <= X+1; x++) {
            map[Y][x] = decks[i];
            if (!this.getOwner().isCPU()) {
                //Делаем графические изменения на экране игрока
                map[Y][x].setEffect(null);
                HBox[] hBoxes = App.shipSettingController.gethBoxes();
                hBoxes[Y].getChildren().remove(x);
                hBoxes[Y].getChildren().add(x, decks[i]);
                map[Y][x].setImage(ImageName.LINKOR);
                i--;
            }
        }
        App.seaBattleGame.playerShipIncrement(this.getOwner());
    }
    @Override
    public void shipOnTheSeaY(int Y, int X){
        GameObject[][] map = this.getOwner().getOurFleetMap();
        int i = 3;
        for (int y = Y - 2; y <= Y+1; y++) {
            map[y][X] = decks[i];
            if (!this.getOwner().isCPU()) {
                //Делаем графические изменения на экране игрока
                map[y][X].setEffect(null);
                HBox[] hBoxes = App.shipSettingController.gethBoxes();
                hBoxes[y].getChildren().remove(X);
                hBoxes[y].getChildren().add(X, decks[i]);
                map[y][X].setImage(ImageName.LINKOR);
                i--;
            }
        }
        App.seaBattleGame.playerShipIncrement(this.getOwner());
    }

    @Override
    public boolean spruting() {
        for (int i = 0; i < decks.length; i++) {
            decks[i].setImage(ImageName.OCTOPUS);
        }
        return true;
    }
}
