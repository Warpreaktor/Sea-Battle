package com.core;

import com.core.Ships.*;

import java.util.ArrayList;

public class Player {
    private boolean isCPU;
    private String name;
    private int countOfTurns = 0;   //Счетчик ходов сделанных игроком.
    private int numberOfShip = 0;   //Итоговое количество кораблей игрока на поле, которое уменьшается по ходу их уничтожения.
    private ArrayList<Ship> shipyard;
    private GameObject[][] ourFleetMap = new GameObject[SeaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];
    private GameObject[][] enemyFleetMap = new GameObject[SeaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];

    public Player() {
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
     * Отрисовка карты старой консольной карты игрока. Сохранено для истории.
     */
    public void brushOldMap() {
        int size = SeaBattleGame.getSIZE();
        //Рисуем шапку карты
        System.out.println();
        System.out.println("---------------------------" + this.name + "----------------------");
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
                        System.out.print(ourFleetMap[i][k].getLabel());
                        System.out.print(" ");
                    }
                    if (j == 1) {
                        System.out.print(enemyFleetMap[i][k].getLabel());
                        System.out.print(" ");
                    }
                }
                System.out.print("            ");
            }
            System.out.println();
        }
    }

    /**
     * Метод вызывается у конкретного игрока и проходит по всей его верфи.
     * На каждом шаге определяется с каким типом корабля мы имеем дело и для дальнейшей его установки на поле
     * вызывается отдельный метод в который мы передаем конкретный объект.
     */
    public void shipsOnGame() {
        if (shipyard.isEmpty()){
            System.out.println("Корабли кончились");
            return;
        }else {
            shipOnGame(shipyard.get(0));
            shipyard.remove(0);
            if (!shipyard.isEmpty()){
                shipsOnGame();
                return;
            }
        }
    }

    /**
     * Метод принимает корабль в качестве параметра и выставляет его на игровое поле, проверяя
     * перед этим не соседствует ли или не заполнена эта ячейка другим кораблем.
     */
    public void shipOnGame(Ship ship) {
        int Y = Tools.getRandomCoordinate();  //Получаем рандомную координату Y
        int X = Tools.getRandomCoordinate();  //Получаем рандомную координату X
        int side = 1 + (int) (Math.random() * 4);  //Получаем рандомное направление для размещения корабля
        int tempY = Y;
        int tempX = X;
        switch (side) { //4 кейса на каждую сторону света
            case (1)://от точки координат вправо
                //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                for (int i = 0; i < ship.getShipSize(); i++) {
                    if (!Tools.isOutOfBoards(ourFleetMap, tempY, tempX) ||
                            !Tools.isNoShipsArround(ourFleetMap, tempY, tempX)) {
                        shipOnGame(ship);
                        return;
                    } else {
                        tempX += 1;
                    }
                }
                //Устанавливаем корабль на восток
                for (int i = 0; i < ship.getShipSize(); i++) {
                    ship.shipOnTheSea(i, Y, X);
                    X += 1;
                }
                this.numberOfShip +=1;
                SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                break;
            case (2)://от точки координат вниз
                //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                for (int i = 0; i < ship.getShipSize(); i++) {
                    if (!Tools.isOutOfBoards(ourFleetMap, tempY, tempX) ||
                            !Tools.isNoShipsArround(ourFleetMap, tempY, tempX)) {
                        shipOnGame(ship);
                        return;
                    } else {
                        tempY += 1;
                    }
                }
                //Если прошли проверку устанавливаем корабль
                for (int i = 0; i < ship.getShipSize(); i++) {
                    ship.shipOnTheSea(i, Y, X);
                    Y += 1;
                }
                this.numberOfShip ++;
                SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                break;
            case (3)://от точки координат влево
                //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                for (int i = 0; i < ship.getShipSize(); i++) {
                    if (!Tools.isOutOfBoards(ourFleetMap, tempY, tempX) ||
                            !Tools.isNoShipsArround(ourFleetMap, tempY, tempX)) {
                        shipOnGame(ship);
                        return;
                    } else {
                        tempX -= 1;
                    }
                }
                //Если прошли проверку устанавливаем корабль
                for (int i = 0; i < ship.getShipSize(); i++) {
                    ship.shipOnTheSea(i, Y, X);
                    X -= 1;
                }
                this.numberOfShip ++;
                SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                break;
            case (4)://от точки координат вверх
                //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                for (int i = 0; i < ship.getShipSize(); i++) {
                    if (!Tools.isOutOfBoards(ourFleetMap, tempY, tempX) ||
                            !Tools.isNoShipsArround(ourFleetMap, tempY, tempX)) {
                        shipOnGame(ship);
                        return;
                    } else {
                        tempY -= 1;
                    }
                }
                //Если прошли проверку устанавливаем корабль
                for (int i = 0; i < ship.getShipSize(); i++) {
                    ship.shipOnTheSea(i, Y, X);
                    Y -= 1;
                }
                this.numberOfShip ++;
                SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                break;
        }
    }
}
