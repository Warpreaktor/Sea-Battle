package com.core.Ships;

import com.core.GameObject;
import com.core.Player;
import com.core.Tools;

/**
 * shipSize - Количество занимаемых на поле ячеек кораблеём. 4 - Линкор, 3 - Крейсер(), 2 - Эсминец, 1 - Подлодка
 */
public abstract class Ship extends GameObject {
    private int shipSize;        //Количество палуб у корабля
    private Player owner;
    private int hp;

    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp() {
        return hp;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public Player getOwner() {
        return owner;
    }
    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }
    public int getShipSize() {
        return shipSize;
    }
    public abstract void shipOnTheSeaX(int y, int x);
    public abstract void shipOnTheSeaY(int y, int x);
    public abstract DeckOfShip[] getDecks();

    public String naming(){
        int randomNum = 1 + (int)(Math.random()*6);
        String name = "Безымянный";
        switch (randomNum) {
            case (1)://сущ муж + прил муж
            name = Tools.adjectivesBookMan[(int) (Math.random() * (Tools.adjectivesBookMan.length - 1))] +
                    " " + Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))];
            break;
            case (2)://сущ жен + прил жен
                name = Tools.adjectivesBookWoman[(int) (Math.random() * (Tools.adjectivesBookWoman.length - 1))] +
                        " " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
            case (3)://сущ сред + спецэффект
                name = Tools.nounsBookIt[(int) (Math.random() * (Tools.nounsBookIt.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (4)://сущ муж + спецэффект
                name = Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (5)://сущ муж + спецэффект
                name = Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (6)://сущ муж + сущ жен
                name = Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))] +
                        " и " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
        }
        return name;
    }

}
