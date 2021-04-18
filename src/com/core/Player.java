package com.core;

import com.core.Ships.*;
import front.App;

import java.util.ArrayList;

public class Player {
    private boolean isCPU;
    private String name;
    private int countOfTurns = 0;   //Счетчик ходов сделанных игроком.
    private int numberOfShip = 0;   //Итоговое количество кораблей игрока на поле, которое уменьшается по ходу их уничтожения.
    private ArrayList<Ship> shipyard;
    private GameObject[][] ourFleetMap;
    private GameObject[][] enemyFleetMap;

    public Player() {
        ourFleetMap = new GameObject[App.seaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];
        enemyFleetMap = new GameObject[App.seaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];
        //1 Линкор(4), 2 Крейсера(3), 3 Эсминца(2), 4 Подлодки(1)
        /**Каждая клетка игрового поля заполняется объектами пустыми объектами GameCell
         */
        shipyard = new ArrayList<Ship>();
        shipyard.add(new Linkor(this));
        shipyard.add(new Cruiser(this));
        shipyard.add(new Cruiser(this));
        shipyard.add(new Destroyer(this));
        shipyard.add(new Destroyer(this));
        shipyard.add(new Destroyer(this));
        shipyard.add(new Submarine(this));
        shipyard.add(new Submarine(this));
        shipyard.add(new Submarine(this));
        shipyard.add(new Submarine(this));
    }

    public void setCPU(boolean CPU) {
        isCPU = CPU;
    }

    public boolean isCPU() {
        return isCPU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameObject[][] getEnemyFleetMap() {
        return enemyFleetMap;
    }

    public GameObject[][] getOurFleetMap() {
        return ourFleetMap;
    }

    public void setGameCellToEnemyFleetMap(GameObject gameCell, int y, int x) {
        this.enemyFleetMap[y][x] = gameCell;
    }

    public void setGameCellToOurFleetMap(GameObject gameCell, int y, int x) {
        this.ourFleetMap[y][x] = gameCell;
    }

    public void setNumberOfShip(int numberOfShip) {
        this.numberOfShip = numberOfShip;
    }

    public int getNumberOfShip() {
        return numberOfShip;
    }

    public ArrayList<Ship> getShipyard() {
        return shipyard;
    }

    public void setCountOfTurns(int countOfTurns) {
        this.countOfTurns = countOfTurns;
    }

    public int getCountOfTurns() {
        return countOfTurns;
    }

    /**
     * Метод вызывается у конкретного игрока и проходит по всей его верфи.
     * На каждом шаге определяется с каким типом корабля мы имеем дело и для дальнейшей его установки на поле
     * вызывается отдельный метод в который мы передаем конкретный объект.
     */
    public void shipsOnGame() {
        if (shipyard.size() < 1) {
            System.out.println("Корабли кончились");
            return;
        } else {
            while (shipyard.size() > 0) {
                for (int i = shipyard.size() - 1; i >= 0; i--) {
                    setShipRandomizer(shipyard.get(i));
                }
            }
        }
    }

    /**
     * Метод принимает корабль в качестве параметра и выставляет его на игровое поле, проверяя
     * перед этим не соседствует ли или не заполнена эта ячейка другим кораблем.
     */
    public void setShipRandomizer(Ship ship) {
        int Y = Tools.getRandomCoordinate();  //Получаем рандомную координату Y
        int X = Tools.getRandomCoordinate();  //Получаем рандомную координату X
        int side = 1 + (int) (Math.random() * 4);  //Получаем рандомное направление для размещения корабля
        int tempY = Y;
        int tempX = X;
        switch (side) { //4 кейса на каждую сторону света
            case (1)://от точки координат вправо
                //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                if (!Tools.setShipToCellsX(ship, tempY, tempX)) {
                    setShipRandomizer(ship);
                    return;
                }else {
                    //Устанавливаем корабль на восток
                    //ship.shipOnTheSeaX(Y, X);
                    shipyard.remove(ship);
                    ship.setImage(ImageName.NULL);
                    break;
                }
            case (2)://от точки координат вниз
                //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                if (!Tools.setShipToCellsY(ship, tempY, tempX)) {
                    setShipRandomizer(ship);
                    return;
                }else {
                    //Устанавливаем корабль на восток
                    //ship.shipOnTheSeaY(Y, X);
                    shipyard.remove(ship);
                    ship.setImage(ImageName.NULL);
                    break;
                }
        }
    }
}
