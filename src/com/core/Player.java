package com.core;

import com.core.Ships.*;

public class Player {
    private boolean isCPU;
    private String name;
    private int countOfTurns = 0;   //Счетчик ходов сделанных игроком.
    private int numberOfShip = 0;   //Итоговое количество кораблей игрока на поле, которое уменьшается по ходу их уничтожения.
    private Ship[] shipyard;           //Массив со списком доступных в начале типов кораблей.
    private GameObject[][] ourFleetMap = new GameObject[SeaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];
    private GameObject[][] enemyFleetMap = new GameObject[SeaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];

    public Player() {
        //1 Линкор(4), 2 Крейсера(3), 3 Эсминца(2), 4 Подлодки(1)
        /**Каждая клетка игрового поля заполняется объектами пустыми объектами GameCell
         */
            shipyard = new Ship[10];
            shipyard[0] = new Linkor( this);
            shipyard[1] = new Cruiser(this);
            shipyard[2] = new Cruiser( this);
            shipyard[3] = new Destroyer(this);
            shipyard[4] = new Destroyer(this);
            shipyard[5] = new Destroyer(this);
            shipyard[6] = new Submarine(this);
            shipyard[7] = new Submarine(this);
            shipyard[8] = new Submarine(this);
            shipyard[9] = new Submarine(this);
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
    public void setName(String name) {
        this.name = name;
    }
    public GameObject[][] getEnemyFleetMap() {
        return enemyFleetMap;
    }
    public GameObject[][] getOurFleetMap() {
        return ourFleetMap;
    }
    public void setGameCellToEnemyFleetMap(GameObject gameCell, int y, int x ) {
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
     * Отрисовка карты старой консольной карты игрока. Сохранено для истории.
     */
    public void brushOldMap() {
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
                        if (!ourFleetMap[tempY][tempX].getClass().getSimpleName().equals("GameCell") ||
                                ourFleetMap[tempY][tempX].getLabel() == '0') {
                            otherShipOnGameField(ship);
                            return;
                        } else {tempX += 1;}

                    }
                    //Устанавливаем корабль на восток
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        ship.shipOnTheSea(i,Y,X);
                        if (ship.getShipSize() == 4) {
                            ourFleetMap[Y][X].setImage(ImageName.LINKOR);
                        } else {
                            //ourFleetMap[Y][X].setShip();
                        }
                        SeaBattleGame.setNeighbors(Y,X, ship);
                        X += 1;
                    }
                    SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                    break;
                case (2)://от точки координат вниз
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        if (!ourFleetMap[tempY][tempX].getClass().getSimpleName().equals("GameCell")||
                                ourFleetMap[tempY][tempX].getLabel()=='0') {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            tempY += 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        ship.shipOnTheSea(i,Y,X);
                        if (ship.getOwner().getOurFleetMap()[Y][X].getLabel() == 'L') {
                            //ourFleetMap[Y][X].setLinkor();
                        } else {
                            //ourFleetMap[Y][X].setShip();
                        }
                       SeaBattleGame.setNeighbors(Y,X, ship);
                        Y += 1;
                    }
                    SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                    break;
                case (3)://от точки координат влево
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        if (!ourFleetMap[tempY][tempX].getClass().getSimpleName().equals("GameCell")||
                                ourFleetMap[tempY][tempX].getLabel()=='0') {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            tempX -= 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        ship.shipOnTheSea(i,Y,X);
                        if (ship.getOwner().getOurFleetMap()[Y][X].getLabel() == 'L') {
                            //ourFleetMap[Y][X].setLinkor();
                        } else {
                            //ourFleetMap[Y][X].setShip();
                        }
                        SeaBattleGame.setNeighbors(Y,X, ship);
                        X -= 1;
                    }
                    SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
                    break;
                case (4)://от точки координат вверх
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        if (!ourFleetMap[tempY][tempX].getClass().getSimpleName().equals("GameCell") ||
                                ourFleetMap[tempY][tempX].getLabel()=='0') {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            tempY -= 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipSize(); i++) {
                        ship.shipOnTheSea(i,Y,X);
                        if (ship.getOwner().getOurFleetMap()[Y][X].getLabel() == 'L') {
                            //ourFleetMap[Y][X].setLinkor();
                        } else {
                            //ourFleetMap[Y][X].setShip();
                        }
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
