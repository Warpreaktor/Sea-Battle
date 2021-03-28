package com.core;

import front.MainController;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Scanner;

public class Player {
    private boolean isCPU;
    private final String name;
    private int countOfTurns = 0;   //Счетчик ходов сделанных игроком.
    private int numberOfShip = 0;   //Итоговое количество кораблей игрока на поле, которое уменьшается по ходу их уничтожения.
    private Ship[] shipyard;           //Массив со списком доступных в начале типов кораблей.
    private GameCell[][] ourFleetMap = new GameCell[SeaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];;
    private GameCell[][] enemyFleetMap = new GameCell[SeaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];;

    public Player(String name) {
        //Сделать наполнение верфи изходя из размера карты. Исходя из пропорции - Размер 10 = 1 Линкор(4), 2 Крейсера(3), 3 Эсминца(2), 4 Подлодки(1)
        /**Каждая клетка игрового поля заполняется объектами пустыми объектами GameCell
         */
        this.name = name;
       // if (SeaBattleGame.getSIZE() == 10) {

        shipyard = new Ship[10];
            shipyard[0] = new Ship(4, this);
            shipyard[1] = new Ship(3, this);
            shipyard[2] = new Ship(3, this);
            shipyard[3] = new Ship(2, this);
            shipyard[4] = new Ship(2, this);
            shipyard[5] = new Ship(2, this);
            shipyard[6] = new Ship(1, this);
            shipyard[7] = new Ship(1, this);
            shipyard[8] = new Ship(1, this);
            shipyard[9] = new Ship(1, this);
       // }
        this.numberOfShip = shipyard.length;
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
    public GameCell[][] getEnemyFleetMap() {
        return enemyFleetMap;
    }
    public GameCell[][] getOurFleetMap() {
        return ourFleetMap;
    }
    public void setGameCellToEnemyFleetMap(GameCell gameCell, int y, int x ) {
        this.enemyFleetMap[y][x] = gameCell;
    }
    public void setGameCellToOurFleetMap(GameCell gameCell, int y, int x) {
        this.ourFleetMap[y][x] = gameCell;
    }
    public void setNumberOfShip(int numberOfShip) {
        this.numberOfShip = numberOfShip;
    }
    public int getNumberOfShip() {
        return numberOfShip;
    }
    public Ship[] getShipyard() {
        return shipyard;
    }
    public void setCountOfTurns(int countOfTurns) {
        this.countOfTurns = countOfTurns;
    }
    public int getCountOfTurns() {
        return countOfTurns;
    }

    /**
     * Отрисовка карты игрока.
     */
    public void brushTheMap() {
        int size = SeaBattleGame.getSIZE();
        //Рисуем шапку карты
        System.out.println();
        System.out.println("---------------------------"+this.name+"----------------------");
        System.out.println("     <<<Наш флот>>>               <<<Вражеский флот>>>");
        for (int i = 0; i < 2; i++) {
            System.out.print("  ");                 //Отсутп от границы слева
            for (int j = 0; j < size; j++) {
                System.out.print(j);            //Цифры по оси X
                System.out.print(" ");          //Знаки между цифрами по оси X
            }
            System.out.print("            ");   //12 пробелов между картами
        }
        System.out.println();
        for (int i = 0; i < 2; i++) {
            System.out.print("  ");                 //Отсутп от границы слева
            for (int j = 0; j < size * 2; j++) {
                System.out.print('-');              //Разделитель
            }
            System.out.print("            ");
        }
        System.out.println();
        //Рисуем матрицу отображая все объекты карты
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; j++) {    //Отрисовка объектов на боевой карте
                System.out.print(i + "|");          //Цифры по оси Y + разделитель
                for (int k = 0; k < size; k++) {
                    if (j == 0) {
                        System.out.print(ourFleetMap[i][k].getCellLabel());
                        System.out.print(" ");
                    }
                    if (j == 1) {
                        System.out.print(enemyFleetMap[i][k].getCellLabel());
                        System.out.print(" ");
                    }
                }
                System.out.print("            ");
            }
            System.out.println();
        }
    }

    /**
     * Метод берет конкретного игрока и проходит по всей его верфи.
     * На каждом шаге определяется с каким типом корабля мы имеем дело и для дальнейшей его установки на поле
     * вызывается отдельный метод в который мы передаем конкретный объект.
     */
    public void shipsOnTheField() {
        for (Ship ship : shipyard) {
                otherShipOnGameField(ship);
        }
    }

    /**
     * Метод принимает корабль в качестве параметра и выставляет его на игровое поле, проверяя
     * перед этим не соседствует ли или не заполнена эта ячейка другим кораблем.
     */
    public void otherShipOnGameField(Ship ship) {
        int X = Tools.getRandomCoordinate();  //Получаем рандомную координату X
        int Y = Tools.getRandomCoordinate();  //Получаем рандомную координату Y
        int side = 1 + (int) (Math.random() * 4);  //Получаем рандомное направление для размещения корабля
        try {
            int tempX = X;
            int tempY = Y;
            switch (side) { //4 кейса на каждую сторону света
                case (1)://от точки координат вправо
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        if (ourFleetMap[tempY][tempX].isShip() ||
                                ourFleetMap[tempY][tempX].getCellLabel() == '0') {
                            otherShipOnGameField(ship);
                            return;
                        } else {tempX += 1;}

                    }
                    //Устанавливаем корабль на восток
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        ship.shipsOnTheSea(i,Y,X);
                        if (ship.getOwner().getOurFleetMap()[Y][X].getCellLabel() == 'B') {
                            ourFleetMap[Y][X].setlinkor();
                        } else {ourFleetMap[Y][X].setShip();}
                        SeaBattleGame.setNeighbors(Y,X, ship);
                        X += 1;
                    }
                    SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                    break;
                case (2)://от точки координат вниз
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        if (ourFleetMap[tempY][tempX].isShip()||
                                ourFleetMap[tempY][tempX].getCellLabel()=='0') {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            tempY += 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        ship.shipsOnTheSea(i,Y,X);
                        if (ship.getOwner().getOurFleetMap()[Y][X].getCellLabel() == 'B') {
                            ourFleetMap[Y][X].setlinkor();
                        } else {ourFleetMap[Y][X].setShip();}
                       SeaBattleGame.setNeighbors(Y,X, ship);
                        Y += 1;
                    }
                    SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                    break;
                case (3)://от точки координат влево
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        if (ourFleetMap[tempY][tempX].isShip()||
                                ourFleetMap[tempY][tempX].getCellLabel()=='0') {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            tempX -= 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        ship.shipsOnTheSea(i,Y,X);
                        if (ship.getOwner().getOurFleetMap()[Y][X].getCellLabel() == 'B') {
                            ourFleetMap[Y][X].setlinkor();
                        } else {ourFleetMap[Y][X].setShip();}
                        SeaBattleGame.setNeighbors(Y,X, ship);
                        X -= 1;
                    }
                    SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                    break;
                case (4)://от точки координат вверх
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        if (ourFleetMap[tempY][tempX].isShip() ||
                                ourFleetMap[tempY][tempX].getCellLabel()=='0') {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            tempY -= 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        ship.shipsOnTheSea(i,Y,X);
                        if (ship.getOwner().getOurFleetMap()[Y][X].getCellLabel() == 'B') {
                            ourFleetMap[Y][X].setlinkor();
                        } else {ourFleetMap[Y][X].setShip();}
                        SeaBattleGame.setNeighbors(Y,X, ship);
                        Y -= 1;
                    }
                    SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            otherShipOnGameField(ship);
            return;
        }
    }

}
