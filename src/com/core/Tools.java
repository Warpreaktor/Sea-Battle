package com.core;

public class Tools {
    public static String[] nounsBook = {"Махаон","Герой","Стальной","Бриз", "Император","Массон", "Сахарок", "Бог", "Минотавр", "Враг", "Победитель",
            "Карбид", "Китобоец", "Христианин", "Гнев", "Нормандец", "Плот", "Водолаз", "Туз", "Шарабан", "Капкан", "Маньяк", "Варяг", "Ковбой",
    "Гром", "Малышь", "Кашалот", "Воин"};

    public static String[] adjectivesBook = {"Геройский", "Матёрый", "Дорогой", "Морской", "Яростный", "Козырный", "Стальной", "Дубовый", "Кричайщий",
    "Фантастический", "Дерзкий", "Древний", "Неуязвимый", "Неуловимый", "Прыткий", "Страшный", "Молниеносный", "Черный", "Бешанный", "Пьяный"};

    String ANSI_BLACK = "\u001B[30m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_BLUE = "\u001B[34m";
    String ANSI_PURPLE = "\u001B[35m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_WHITE = "\u001B[37m";

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
    //Получаем рандомную координату в пропорциях установленного поля
    public static int getRandomCoordinate() {
        return (int) (Math.random() * Game.getSIZE() - 1);
    }
}
