package com.core;

import java.util.Scanner;

public class Player {
    private final String name;
    private int countOfTurns = 0;   //Счетчик ходов сделанных игроком.
    private int numberOfShip = 0;   //Итоговое количество кораблей игрока, которое уменьшается по ходу их уничтожения.
    private Ship[] shipyard;           //Массив со списком доступных в начале типов кораблей.
    private final GameCell[][] ourFleetMap;
    private final GameCell[][] enemyFleetMap;

    public Player(String name) {
        //Сделать наполнение верфи изходя из размера карты. Исходя из пропорции - Размер 10 = 1 Линкор(4), 2 Крейсера(3), 3 Эсминца(2), 4 Подлодки(1)
        /**Каждая клетка игрового поля заполняется объектами пустыми объектами GameCell
         */
        this.name = name;
        ourFleetMap = new GameCell[Game.getSIZE()][Game.getSIZE()];
        enemyFleetMap = new GameCell[Game.getSIZE()][Game.getSIZE()];
        for (int i = 0; i < Game.getSIZE(); i++) {
            for (int j = 0; j < Game.getSIZE(); j++) {
                ourFleetMap[i][j] = new GameCell();
                enemyFleetMap[i][j] = new GameCell();
            }
        }
        if (Game.getSIZE() == 10) {
            shipyard = new Ship[Game.getSIZE()];
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
        }
    }

    public String getName() {
        return name;
    }

    public GameCell[][] getEnemyFleetMap() {
        return enemyFleetMap;
    }


    public void setOurFleetMap(GameCell gameCell, int y, int x) {
        this.ourFleetMap[y][x] = gameCell;
    }

    public GameCell[][] getOurFleetMap() {
        return ourFleetMap;
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

    public void shoot(Player enemy) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите координату Y от 0 до " + (Game.getSIZE() - 1) + ": ");
            int y = scanner.nextInt();
            System.out.print("Введите координату X от 0 до " + (Game.getSIZE() - 1) + ": ");
            int x = scanner.nextInt();
            if (enemy.getOurFleetMap()[y][x].isShip()) {
                String enemyShipName = enemy.getOurFleetMap()[y][x].getShipRef().getName();
                Ship enemyShip = enemy.getOurFleetMap()[y][x].getShipRef();
                enemyShip.setHp(enemyShip.getHp()-1);
                if (enemyShip.getHp()>0) {
                    System.out.println("Корабль " + enemyShipName + " поврежден!");
                    enemyFleetMap[y][x].setCellLabel('X');
                    enemy.getOurFleetMap()[y][x].setShip(false);
                }
                if (enemyShip.getHp()<=0) {
                    System.out.println(enemyShipName + " уничтожен!");
                    enemyFleetMap[y][x].setCellLabel('X');
                    enemy.getOurFleetMap()[y][x].setShip(false);
                }
                countOfTurns++;
            } else {
                System.out.println("Промах");
                if (enemyFleetMap[y][x].getCellLabel()=='X'){

                }else {enemyFleetMap[y][x].setCellLabel('+');}
                countOfTurns++;
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод, попробуйте еще раз");
        }
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
        int size = Game.getSIZE();
        /**Рисуем шапку карты
         *  Боевая карта
         *  0 1 2 3 4 5 6 7 8 9
         *  --------------------
         */
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
        /**Рисуем матрицу отображая все объекты карты
         * 0|~ M ~ ~ L L L ~ ~ ~
         * 1|~ M ~ ~ ~ ~ ~ ~ ~ ~
         * 2|~ ~ ~ ~ ~ ~ ~ ~ S ~
         * 3|M M ~ S ~ L ~ ~ ~ ~
         * 4|~ ~ ~ ~ ~ L ~ S ~ ~
         * 5|~ B ~ ~ ~ L ~ ~ ~ ~
         * 6|~ B ~ ~ ~ ~ ~ ~ ~ ~
         * 7|~ B ~ ~ ~ ~ ~ ~ ~ ~
         * 8|~ B ~ ~ ~ ~ ~ ~ ~ ~
         * 9|~ ~ ~ ~ ~ ~ ~ ~ ~ ~
         */
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
            if (ship.getShipType() == 1) {
                smallShipOnGameField(ship);
                //player.setShipyard()[i]Удалить корабль из верфи
            } else {
                otherShipOnGameField(ship);
            }
        }
    }

    /**
     * Метод принимает корабль в качестве параметра и выставляет его на игровое поле, проверяя
     * перед этим не заполнена ли эта ячейка другим кораблем.
     */
    public void smallShipOnGameField(Ship ship) {
        //Добавить остановку бесконечной рекурсии в случае если корабль не может найти ни одного пустого места на поле.
        int diceX = (int) (Math.random() * Game.getSIZE() - 1);  //Получаем рандомную координату X
        int diceY = (int) (Math.random() * Game.getSIZE() - 1);  //Получаем рандомную координату Y
        if (ourFleetMap[diceY][diceX].isShip()) {
            smallShipOnGameField(ship);
            return;
        } else {
            ship.setCoordinates(0, 0, diceY);
            ship.setCoordinates(0, 1, diceX);
            ourFleetMap[diceY][diceX].setShip(true);
            ourFleetMap[diceY][diceX].setCellLabel('S');
            ourFleetMap[diceY][diceX].setShipRef(ship);
            Game.setTotalShips(Game.getTotalShips() + 1);
        }
    }

    public void otherShipOnGameField(Ship ship) {
        int diceX = (int) (Math.random() * (Game.getSIZE() - 1));  //Получаем рандомную координату X
        int diceY = (int) (Math.random() * (Game.getSIZE() - 1));  //Получаем рандомную координату Y
        int side = 1 + (int) (Math.random() * 4);  //Получаем рандомное направление для размещения корабля
        try {
            int localDiceX = diceX;
            int localDiceY = diceY;
            switch (side) { //4 кейса на каждую сторону света
                case (1)://от точки координат вправо
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipType(); i++) {
                        if (ourFleetMap[localDiceY][localDiceX].isShip()) {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            localDiceX += 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipType(); i++) {
                        ship.setCoordinates(i, 0, diceY);
                        ship.setCoordinates(i, 1, diceX);
                        ourFleetMap[diceY][diceX].setShip(true);
                        ourFleetMap[diceY][diceX].setCellLabel(ship.getShipLabel());
                        ourFleetMap[diceY][diceX].setShipRef(ship);
                        diceX += 1;
                    }
                    Game.setTotalShips(Game.getTotalShips() + 1);
                    break;
                case (2)://от точки координат вниз
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipType(); i++) {
                        if (ship.getOwner().getOurFleetMap()[localDiceY][localDiceX].isShip()) {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            localDiceY += 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipType(); i++) {
                        ship.setCoordinates(i, 0, diceY);
                        ship.setCoordinates(i, 1, diceX);
                        ship.getOwner().getOurFleetMap()[diceY][diceX].setShip(true);
                        ship.getOwner().getOurFleetMap()[diceY][diceX].setCellLabel(ship.getShipLabel());
                        ourFleetMap[diceY][diceX].setShipRef(ship);
                        diceY += 1;
                    }
                    Game.setTotalShips(Game.getTotalShips() + 1);
                    break;
                case (3)://от точки координат влево
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipType(); i++) {
                        if (ship.getOwner().getOurFleetMap()[localDiceY][localDiceX].isShip()) {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            localDiceX -= 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipType(); i++) {
                        ship.setCoordinates(i, 0, diceY);
                        ship.setCoordinates(i, 1, diceX);
                        ship.getOwner().getOurFleetMap()[diceY][diceX].setShip(true);
                        ship.getOwner().getOurFleetMap()[diceY][diceX].setCellLabel(ship.getShipLabel());
                        ourFleetMap[diceY][diceX].setShipRef(ship);
                        diceX -= 1;
                    }
                    Game.setTotalShips(Game.getTotalShips() + 1);
                    break;
                case (4)://от точки координат вверх
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < ship.getShipType(); i++) {
                        if (ship.getOwner().getOurFleetMap()[localDiceY][localDiceX].isShip()) {
                            otherShipOnGameField(ship);
                            return;
                        } else {
                            localDiceY -= 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < ship.getShipType(); i++) {
                        ship.setCoordinates(i, 0, diceY);
                        ship.setCoordinates(i, 1, diceX);
                        ship.getOwner().getOurFleetMap()[diceY][diceX].setShip(true);
                        ship.getOwner().getOurFleetMap()[diceY][diceX].setCellLabel(ship.getShipLabel());
                        ourFleetMap[diceY][diceX].setShipRef(ship);
                        diceY -= 1;
                    }
                    Game.setTotalShips(Game.getTotalShips() + 1);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не получилось установить корабль попробуем еще раз =)" + ship.getShipType());
            otherShipOnGameField(ship);
            return;
        }
    }
}
