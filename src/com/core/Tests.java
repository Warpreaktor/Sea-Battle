package com.core;

public class Tests {
    //Тест на
    public static void testForPlayerClass() {
        Player player = new Player();
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
                if (isNull(player.getOurFleetMap()[i][j])){
                    System.out.println("NullPointerException! Игровое поле c нашим флотом заполняется не корректно.");
                    System.out.println("Координаты = " + "x = " + j + " y = " + i);
                }
            }
        }
        for (int i = 0; i < player.getEnemyFleetMap().length; i++) {
            for (int j = 0; j < player.getEnemyFleetMap().length; j++) {
                if (isNull(player.getEnemyFleetMap()[i][j])){
                    System.out.print("NullPointerException! Игровое поле с флотом противник заполняется не корректно.");
                    System.out.println("Координаты = " + "x = " + j + " y = " + i);
                }
            }
        }
    }
    public static boolean isNull(Object object){
        if (object == null){
            return true;
        }else return false;
    }
}
