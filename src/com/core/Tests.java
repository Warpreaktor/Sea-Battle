package com.core;

public class Tests {
    /**
     * Набор тестов для класса Player
     */
    public static void testForPlayerClass() {
        Player player = new Player();
        if (player.getOurFleetMap().length < 1) {
            System.out.println("Ошибка! При создании игрока не было создано поле игрока");
        }
        if (player.getOurFleetMap().length > SeaBattleGame.getSIZE() || player.getOurFleetMap().length < SeaBattleGame.getSIZE()) {
            System.out.println("Ошибка! При создании игрока поле игрока создается не того размера");
            System.out.println("Размер созданного поля = " + player.getOurFleetMap().length);
        }
        //Проверяем чтобы в игровом поле не было null объектов.
        for (int i = 0; i < player.getOurFleetMap().length; i++) {
            for (int j = 0; j < player.getOurFleetMap().length; j++) {
                if (Tools.isNull(player.getOurFleetMap()[i][j])) {
                    System.out.println("NullPointerException! Игровое поле c нашим флотом заполняется не корректно.");
                    System.out.println("Координаты = " + "x = " + j + " y = " + i);
                }
            }
        }
        for (int i = 0; i < player.getEnemyFleetMap().length; i++) {
            for (int j = 0; j < player.getEnemyFleetMap().length; j++) {
                if (Tools.isNull(player.getEnemyFleetMap()[i][j])) {
                    System.out.print("NullPointerException! Игровое поле с флотом противник заполняется не корректно.");
                    System.out.println("Координаты = " + "x = " + j + " y = " + i);
                }
            }
        }
        //Проверяем чтобы при создании игрока в его верфи создавалось нужное количество кораблей
        if (SeaBattleGame.getSIZE() == 10) {
            if (player.getShipyard().length != 10) {
                System.out.println("В верфи игрока ");
            }
        }
    }

    /**
     * Тест запускает игру создав двух компьютерных игроков и рубиться сам с собой проверяя игру.
     */
    public static void mainTest(){
        //Tests.testForPlayerClass();
        Player playerCPU1 = new Player();
        Player playerCPU2 = new Player();
        playerCPU1.setCPU(true);
        playerCPU2.setCPU(true);
        playerCPU1.shipsOnTheField();
        System.out.println("Количество кораблей у игрока = " + playerCPU1.getNumberOfShip());
        playerCPU2.shipsOnTheField();
        //SeaBattleGame.battle(playerCPU1, playerCPU2,6,8);
    }
    public static int testRandomNumbers(){
        int number=0;
        for (int i = 0; i < 100; i++) {
             number = Tools.getRandomCoordinate();
            System.out.println(number);
        }
        return number;
    }

    public static void main(String[] args) {
        Player player = new Player();
        Ship cruiser = new Cruiser(player);
        System.out.println(cruiser.getHp());
    }

}
