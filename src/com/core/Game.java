package com.core;

public class Game {
    private static int SIZE = 10;
    private static int shipsInShipyard = 10;
    static GameCell[][] gameField = new GameCell[SIZE][SIZE];
    private static int totalShips = 0;

    public static void setShipsInShipyard(int shipsInShipyard) {
        Game.shipsInShipyard = shipsInShipyard;
    }

    public static int getShipsInShipyard() {
        return shipsInShipyard;
    }

    public static void main(String[] args) {
        Player player1 = new Player(shipsInShipyard);
        Player player2CPU = new Player(shipsInShipyard);
        initializeGameField();
        shipsOnTheField(player2CPU);
        Tools.brushFullMap();
        //battle(player1, player2CPU);
    }

    /**
     * Создается матрица величиной SIZExSIZE, каждая клетка которой заполняется объектами GameCell
     */
    public static void initializeGameField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gameField[i][j] = new GameCell();
            }
        }
    }

    /**
     * Метод берет конкретного игрока и проходитпо всей его верфи.
     * На каждом шаге определяется с каким типом корабля мы имеем дело и для дальнейшей его установки на поле
     * вызывается отдельный метод в который мы передаем конкретный объект.
     */
    public static void shipsOnTheField(Player player) {
        for (int i = 0; i < player.getShipyard().length; i++) {
            int playerShipType = player.getShipyard()[i].getShipType();
            Ship playerShip = player.getShipyard()[i];
            if (playerShipType == 1) {
                smallShipOnGameCell(playerShip);
                //player.setShipyard()[i]Удалить корабль из верфи
            } else {
                otherShipOnGameCell(playerShip);
            }
        }
    }

    /**
     * Метод принимает корабль в качестве параметра и выставляет его на игровое поле, проверяя
     * перед этим не заполнена ли эта ячейка другим кораблем.
     */
    public static void smallShipOnGameCell(Ship playerShip) {
        //Добавить остановку бесконечной рекурсии в случае если корабль не может найти ни одного пустого места на поле.
        int diceX = 0 + (int) (Math.random() * SIZE - 1);  //Получаем рандомную координату X
        int diceY = 0 + (int) (Math.random() * SIZE - 1);  //Получаем рандомную координату Y
        if (gameField[diceY][diceX].isShip()) {
            smallShipOnGameCell(playerShip);
        } else {
            playerShip.setCoordinates(0, 0, diceX);
            playerShip.setCoordinates(0, 1, diceY);
            gameField[diceY][diceX].setShip(true);
            gameField[diceY][diceX].setCellLabel('S');
            totalShips++;
        }
    }

    public static void otherShipOnGameCell(Ship playerShip) {
        int diceX = 0 + (int) (Math.random() * SIZE - 1);  //Получаем рандомную координату X
        int diceY = 0 + (int) (Math.random() * SIZE - 1);  //Получаем рандомную координату Y
        int side = 1 + (int) (Math.random() * 4);  //Получаем рандомное направление для размещения корабля
        if (gameField[diceY][diceX].isShip()) {
            otherShipOnGameCell(playerShip);
        } else {
            try {
                int localDiceX = diceX;
                int localDiceY = diceY;
                switch (side) { //4 кейса на каждую сторону света
                    case (1)://от точки координат вправо
                        //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                        for (int i = 0; i < playerShip.getShipType(); i++) {
                            if (gameField[diceY][localDiceX].isShip()) {
                                otherShipOnGameCell(playerShip);
                            } else {
                                localDiceX += 1;
                            }
                        }
                        //Если прошли проверку устанавливаем корабль
                        for (int i = 0; i < playerShip.getShipType(); i++) {
                            playerShip.setCoordinates(i, 0, diceY);
                            playerShip.setCoordinates(i, 1, diceX);
                            gameField[diceY][diceX].setShip(true);
                            gameField[diceY][diceX].setCellLabel(playerShip.getShipLabel());
                            diceX += 1;
                        }
                        totalShips++;
                        break;
                    case (2)://от точки координат вниз
                        //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                        for (int i = 0; i < playerShip.getShipType(); i++) {
                            if (gameField[localDiceY][diceX].isShip()) {
                                otherShipOnGameCell(playerShip);
                            } else {
                                localDiceY += 1;
                            }
                        }
                        //Если прошли проверку устанавливаем корабль
                        for (int i = 0; i < playerShip.getShipType(); i++) {
                            playerShip.setCoordinates(i, 0, diceY);
                            playerShip.setCoordinates(i, 1, diceX);
                            gameField[diceY][diceX].setShip(true);
                            gameField[diceY][diceX].setCellLabel(playerShip.getShipLabel());
                            diceY += 1;
                        }
                        totalShips++;
                        break;
                    case (3)://от точки координат влево
                        //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                        for (int i = 0; i < playerShip.getShipType(); i++) {
                            if (gameField[diceY][localDiceX].isShip()) {
                                otherShipOnGameCell(playerShip);
                            } else {
                                localDiceX -= 1;
                            }
                        }
                        //Если прошли проверку устанавливаем корабль
                        for (int i = 0; i < playerShip.getShipType(); i++) {
                            playerShip.setCoordinates(i, 0, diceY);
                            playerShip.setCoordinates(i, 1, diceX);
                            gameField[diceY][diceX].setShip(true);
                            gameField[diceY][diceX].setCellLabel(playerShip.getShipLabel());
                            diceX -= 1;
                        }
                        totalShips++;
                        break;
                    case (4)://от точки координат вверх
                        //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                        for (int i = 0; i < playerShip.getShipType(); i++) {
                            if (gameField[localDiceY][diceX].isShip()) {
                                otherShipOnGameCell(playerShip);
                            } else {
                                localDiceY -= 1;
                            }
                        }
                        //Если прошли проверку устанавливаем корабль
                        for (int i = 0; i < playerShip.getShipType(); i++) {
                            playerShip.setCoordinates(i, 0, diceY);
                            playerShip.setCoordinates(i, 1, diceX);
                            gameField[diceY][diceX].setShip(true);
                            gameField[diceY][diceX].setCellLabel(playerShip.getShipLabel());
                            diceY -= 1;
                        }
                        totalShips++;
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Не получилось установить корабль " + playerShip.getShipType());
                otherShipOnGameCell(playerShip);
            }
        }
    }

    public static void battle(Player player1, Player player2) {
        while (totalShips > 0) {
            player1.shoot();
            System.out.println("Ходов пройдено " + player1.getCountOfTurns());
            Tools.brushFullMap();
        }
        System.out.println("Победа!");
    }

    public static GameCell[][] getGameField() {
        return gameField;
    }

    public static void setGameField(GameCell[][] gameField) {
        Game.gameField = gameField;
    }

    public static void setSIZE(int SIZE) {
        Game.SIZE = SIZE;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static void setTotalShips(int totalShips) {
        Game.totalShips = totalShips;
    }

    public static int getTotalShips() {
        return totalShips;
    }

    /**
     * Метод заполняет каждую клетку игрового поля gameField чем-нибудь одноклеточным.
     * Пока это метод пустышка. Создан на будущее, если кроме кораблей появятся в игре еще какие-нибудь объекты.
     */
    public static void fillTheField() {
        System.out.println("Метод пустышка");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int dice = 1 + (int) (Math.random() * 2); // Бросим кубик от 1 до 2. 1 - Что-то такое, 2 - Что-то сякое.
                if (dice == 1) {
                    //gameField[i][j] = new GameObject();
                    //gameField[i][j].setCellLabel('?');
                } else {
                    gameField[i][j].setCellLabel('0');
                }
            }
        }
    }
}
