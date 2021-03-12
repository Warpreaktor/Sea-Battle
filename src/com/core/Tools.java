package com.core;

public class Tools {
    /** Метод используется для отладки, показывает полную карту всех объектов на карте.*/
    public static void brushFullMap(){
        int size = Game.getSIZE();
        GameCell[][] gameField = Game.getGameField();
        /**Рисуем шапку карты
         *  0 1 2 3 4 5 6 7 8 9
         *  --------------------
         */
        System.out.print("  ");
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
        /**Рисуем матрицу с объектами карты
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
        for(int i = 0; i < size; i++){
            System.out.print(i+"|");
            for (int j = 0; j < size; j++) {
                System.out.print(gameField[i][j].getCellLabel());
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}