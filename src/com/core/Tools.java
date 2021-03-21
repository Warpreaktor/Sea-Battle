package com.core;

public class Tools {
    public static String[] nounsBookMan = {"Махаон","Герой","Брат","Бриз", "Стул", "Император","Массон", "Сахарок", "Бог", "Минотавр", "Враг", "Победитель",
            "Карбид", "Китобоец", "Христианин", "Гнев", "Нормандец", "Плот", "Водолаз", "Туз", "Шарабан", "Капкан", "Маньяк", "Варяг", "Ковбой",
    "Гром", "Малыш", "Кашалот", "Воин", "Царь", "Сон", "День", "Свет", "Ураган", "Шторм", "Тайфун", "Смерч", "Катер", "Цунами", "Линкольн", "Сталин", "Айсберг",
            "Характер", "Свидетель", "Адвокат", "Всадник"};

    public static String[] nounsBookWoman = {"Месть", "Королева", "Гидра", "Уида", "Мигера", "Горгона", "Дьявольщина", "Касатка", "Удача", "Стихия", "Жена",
    "Химера", "Мокрядь", "Тварь", "Посудина", "Фантазия", "Мечта", "Ночь", "Лань", "Буря", "Лодка", "Шхуна", "Рыба", "Ярость", "Нехристь", "Аврора", "Аляска",
            "Арабелла", "Земля", "Аркхем", "Особенность", "Конструкция", "Беда", "Гибель", "Кобыла"};

    public static String[] nounsBookIt = {"Приключение", "Возмездие", "Наводнение", "Остервенение", "Бешенство", "Господство", "Корыто", "Предложение",
    "Мероприятие", "Поручение", "Искусство", "Воспитание", "Достоинство","Убийство", "Рождение", "Становление", "Наказание", "Убеждение", "Мучение",
            "Свидетельство", "Послание", "Буйство", "Хитроумие"};

    public static String[] adjectivesBookMan = {"Геройский", "Матёрый", "Дорогой", "Морской", "Яростный", "Козырный", "Стальной", "Дубовый", "Кричайщий",
    "Фантастический", "Дерзкий", "Древний", "Неуязвимый", "Неуловимый", "Прыткий", "Страшный", "Молниеносный", "Черный", "Бешенный", "Пустынный", "Пьяный", "Небесный",
            "Счастливый", "Отвратительный", "Быстрый", "Мужественный", "Огромный", "Стальной", "Унылый", "Корявый"};

    public static String[] adjectivesBookWoman = {"Геройская", "Матёрая", "Дорогая", "Морская", "Яростная", "Козырная", "Стальная", "Дубовая", "Кричайщая",
            "Фантастическая", "Дерзкая", "Древняя", "Неуязвимая", "Неуловимая", "Прыткая", "Страшная", "Молниеносная", "Черная", "Бешенная", "Пьяная", "Небесная",
            "Счастливая", "Золотая", "Прекрасная", "Изящная", "Ловкая", "Скользкая", "Опалённая"};

    public static String[] whomBook = {"капитана", "дракона", "Дьявола", "пирата", "брата", "короля", "королевы", "вселенной", "по сути", "в душе", "в море",
    "в натуре","cреди ясного неба", "из тумана", "без правил", "из ада"};

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
        return (int) (Math.random() * SeaBattleGame.getSIZE());
    }
    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        } else return false;
    }
    public static String getRandomName(){
        String name = "Безымянный";
        int randomNum = 1 + (int)(Math.random()*6);
        switch (randomNum) {
            case (1)://сущ муж + прил муж
                name = Tools.adjectivesBookMan[(int) (Math.random() * (Tools.adjectivesBookMan.length - 1))] +
                        " " + Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))];
                break;
            case (2)://сущ жен + прил жен
                name = Tools.adjectivesBookWoman[(int) (Math.random() * (Tools.adjectivesBookWoman.length - 1))] +
                        " " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
            case (3)://сущ сред + спецэффект
                name = Tools.nounsBookIt[(int) (Math.random() * (Tools.nounsBookIt.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (4)://сущ муж + спецэффект
                name = Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (5)://сущ муж + спецэффект
                name = Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (6)://сущ муж + сущ жен
                name = Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))] +
                        " " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
        }
        return name;
    }

}
