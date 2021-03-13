package com.core;

public class Tools {
    public static String[] directory = {"Махаон","Геройский","Дорогой","Морской бриз", "Мокрядь","Массон", "Сахарок", "Ярость богов", "Гидра", "Вековечный враг", "Победитель",
            "Карбид", "Китобоец", "Христианин", "Козырный", "Нормандец", "Стальной плот"};


    /**
     * Метод используется для отладки, показывает обе карты обеих игроков с расставленными на них объектами.
     */
    /*public static void brushFullMap(Player player) {
        int size = Game.getSIZE();
        GameCell[][] gameField = player.getPlayersGameField();
        /**Рисуем шапку карты
         *  0 1 2 3 4 5 6 7 8 9
         *  --------------------
         */
        /*System.out.print("  ");
        for (int i = 0; i < size; i++) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < size * 2; i++) {
            System.out.print('-');
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
        /*for (int i = 0; i < size; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < size; j++) {
                System.out.print(gameField[i][j].getCellLabel());
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }*/

}
