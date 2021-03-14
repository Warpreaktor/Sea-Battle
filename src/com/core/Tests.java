package com.core;

public class Tests {
    //Тест на
    public static void testForPlayerClass() {
        Player player = new Player("testPlayer");
        if (player.getOurFleetMap().length < 1) {
            System.out.println("Ошибка! При создании игрока не было создано поле игрока");
        }
        if (player.getOurFleetMap().length > Game.getSIZE() || player.getOurFleetMap().length < Game.getSIZE()) {
            System.out.println("Ошибка! При создании игрока поле игрока создается не того размера");
            System.out.println("Размер созданного поля = " + player.getOurFleetMap().length);
        }
        //Проверяем чтобы в игровом поле не было null объектов.
        for (int i = 0; i < player.getOurFleetMap().length; i++) {
            for (int j = 0; j < player.getOurFleetMap().length; j++) {
                if (isNull(player.getOurFleetMap()[i][j])) {
                    System.out.println("NullPointerException! Игровое поле c нашим флотом заполняется не корректно.");
                    System.out.println("Координаты = " + "x = " + j + " y = " + i);
                }
            }
        }
        for (int i = 0; i < player.getEnemyFleetMap().length; i++) {
            for (int j = 0; j < player.getEnemyFleetMap().length; j++) {
                if (isNull(player.getEnemyFleetMap()[i][j])) {
                    System.out.print("NullPointerException! Игровое поле с флотом противник заполняется не корректно.");
                    System.out.println("Координаты = " + "x = " + j + " y = " + i);
                }
            }
        }
        //Проверяем чтобы при создании игрока в его верфи создавалось нужное количество кораблей
        if (Game.getSIZE() == 10) {
            if (player.getShipyard().length != 10) {
                System.out.println("В верфи игрока ");
            }
        }
    }

    public static void testArrayIndexOutOfBoundsException(Player player, int y, int x) {
        try {
            if (player.getOurFleetMap()[y][x].isShip()) {
                System.out.println("Условие выполнилось");;
            }
            System.out.println("Создался лищний корабль");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Рекурсия");
        }
    }

    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        } else return false;
    }

    public void testOtherShipOnGameField(Ship playerShip) {
        int diceX = (int) (Math.random() * (Game.getSIZE() - 1));  //Получаем рандомную координату X
        int diceY = (int) (Math.random() * (Game.getSIZE() - 1));  //Получаем рандомную координату Y
        int side = 1; //+ (int) (Math.random() * 4);  //Получаем рандомное направление для размещения корабля
        try {
            int localDiceX = diceX;
            int localDiceY = diceY;
            switch (side) { //4 кейса на каждую сторону света
                case (1)://от точки координат вправо
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    System.out.println("Устанавливаем корабль " + playerShip.getName());
                    System.out.println("Координаты корабля - "+diceY + " - " + diceX);
                    for (int i = 0; i < playerShip.getShipSize(); i++) {
                        if (playerShip.getOwner().getOurFleetMap()[localDiceY][localDiceX].isShip()) {
                            System.out.println("В координатах - "+localDiceY + " - " + localDiceX + " уже находится корабль. Начинаем расстановку сначала.");
                            testOtherShipOnGameField(playerShip);
                            return;
                        } else {
                            localDiceX += 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < playerShip.getShipSize(); i++) {
                        playerShip.setCoordinates(i, 0, diceY);
                        playerShip.setCoordinates(i, 1, diceX);
                        playerShip.getOwner().getOurFleetMap()[diceY][diceX].setShip(true);
                        System.out.println("Отметка на карте об установке корабля - " +diceY + " - " + diceX);
                        playerShip.getOwner().getOurFleetMap()[diceY][diceX].setCellLabel(playerShip.getShipLabel());
                        diceX += 1;
                    }
                    System.out.println("Корабль " + playerShip.getName() + " спущен на воду");
                    int[][] shipCoord = playerShip.getCoordinates();
                    for (int i = 0; i < shipCoord.length; i++) {
                        for (int j = 0; j < shipCoord[i].length; j++) {
                            System.out.print("Координата " + shipCoord[i][j] + " ");
                        }
                        System.out.println();
                    }
                    Game.setTotalShips(Game.getTotalShips() + 1);
                    playerShip.getOwner().brushTheMap();
                    break;
                case (2)://от точки координат вниз
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < playerShip.getShipSize(); i++) {
                        if (playerShip.getOwner().getOurFleetMap()[localDiceY][localDiceX].isShip()) {
                            testOtherShipOnGameField(playerShip);
                            return;
                        } else {
                            localDiceY += 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < playerShip.getShipSize(); i++) {
                        playerShip.setCoordinates(i, 0, diceY);
                        playerShip.setCoordinates(i, 1, diceX);
                        playerShip.getOwner().getOurFleetMap()[diceY][diceX].setShip(true);
                        playerShip.getOwner().getOurFleetMap()[diceY][diceX].setCellLabel(playerShip.getShipLabel());
                        diceY += 1;
                    }
                    Game.setTotalShips(Game.getTotalShips() + 1);
                    break;
                case (3)://от точки координат влево
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < playerShip.getShipSize(); i++) {
                        if (playerShip.getOwner().getOurFleetMap()[localDiceY][localDiceX].isShip()) {
                            testOtherShipOnGameField(playerShip);
                            return;
                        } else {
                            localDiceX -= 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < playerShip.getShipSize(); i++) {
                        playerShip.setCoordinates(i, 0, diceY);
                        playerShip.setCoordinates(i, 1, diceX);
                        playerShip.getOwner().getOurFleetMap()[diceY][diceX].setShip(true);
                        playerShip.getOwner().getOurFleetMap()[diceY][diceX].setCellLabel(playerShip.getShipLabel());
                        diceX -= 1;
                    }
                    Game.setTotalShips(Game.getTotalShips() + 1);
                    break;
                case (4)://от точки координат вверх
                    //делаем проверку, можем ли мы установить корабль по предполагаемым координатам
                    for (int i = 0; i < playerShip.getShipSize(); i++) {
                        if (playerShip.getOwner().getOurFleetMap()[localDiceY][localDiceX].isShip()) {
                            testOtherShipOnGameField(playerShip);
                            return;
                        } else {
                            localDiceY -= 1;
                        }
                    }
                    //Если прошли проверку устанавливаем корабль
                    for (int i = 0; i < playerShip.getShipSize(); i++) {
                        playerShip.setCoordinates(i, 0, diceY);
                        playerShip.setCoordinates(i, 1, diceX);
                        playerShip.getOwner().getOurFleetMap()[diceY][diceX].setShip(true);
                        playerShip.getOwner().getOurFleetMap()[diceY][diceX].setCellLabel(playerShip.getShipLabel());
                        diceY -= 1;
                    }
                    Game.setTotalShips(Game.getTotalShips() + 1);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не получилось установить корабль попробуем еще раз =)" + playerShip.getShipSize());
            testOtherShipOnGameField(playerShip);
            return;
        }
    }

}
