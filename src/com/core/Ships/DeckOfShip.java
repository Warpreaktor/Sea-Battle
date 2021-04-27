package com.core.Ships;

import com.core.ImageName;
import com.core.MapObjects.MapObject;
import front.App;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;


public class DeckOfShip extends MapObject {
    private boolean isDamaged = false;
    private int dekcNumber;
    private Ship shipOwner;//Ссылка на корабль, владельца этой деки.

    @Override
    public String getName() {
        return shipOwner.getName();
    }

    public int getHp() {
        return shipOwner.getHp();
    }

    public int shipSize() {
        return shipOwner.getShipSize();
    }

    public Ship getShipOwner() {
        return shipOwner;
    }

    public DeckOfShip(int deckNumber, Ship shipowner, char label) {
        this.dekcNumber = deckNumber;
        this.shipOwner = shipowner;
        this.setLabel(label);
        this.setImage(shipowner.getImage());
    }

    @Override
    public boolean spruting() {
        for (int i = 0; i < shipOwner.getDecks().length; i++) {
            shipOwner.getDecks()[i].setImage(ImageName.KRAKEN);
        }
        if (shipOwner.getOwner().isCPU()) {
            shipOwner.getOwner().theShipIsDestroyed(shipOwner, App.SEA_BATTLE_GAME.getCPU(), App.SEA_BATTLE_GAME.getHuman());
        } else {
            shipOwner.getOwner().theShipIsDestroyed(shipOwner, App.SEA_BATTLE_GAME.getHuman(), App.SEA_BATTLE_GAME.getCPU());
        }
        return true;
    }

    @Override
    public boolean isShip() {
        return true;
    }

    public void setOnMouseEvent() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCursor(Cursor.DEFAULT);
                App.BATTLE_FIELD_CONTROLLER.setShipName("Корабль: " + getName());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                App.BATTLE_FIELD_CONTROLLER.setShipName("Корабль: ");
            }
        });
    }
}
