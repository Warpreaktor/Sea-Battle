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

    //Получаем рандомную координату в пропорциях установленного поля
    public static int getRandomCoordinate() {
        return (int) (Math.random() * Game.getSIZE());
    }
    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        } else return false;
    }
}
