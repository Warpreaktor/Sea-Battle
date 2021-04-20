package com.core.Ships;

import com.core.GameObjects.MapObject;
import com.core.Players.Player;

/**
 * shipSize - Количество занимаемых на поле ячеек кораблеём. 4 - Линкор, 3 - Крейсер(), 2 - Эсминец, 1 - Подлодка
 */
public abstract class Ship extends MapObject {
    private int shipSize;        //Количество палуб у корабля
    private Player owner;
    private int hp;
    public static String[] nounsBookMan = {"Махаон", "Герой", "Брат", "Бриз", "Император", "Массон", "Сахарок", "Бог", "Минотавр", "Враг", "Победитель",
            "Карбид", "Китобоец", "Христианин", "Гнев", "Нормандец", "Плот", "Водолаз", "Туз", "Шарабан", "Капкан", "Маньяк", "Варяг", "Ковбой",
            "Гром", "Малыш", "Кашалот", "Воин", "Царь", "Сон", "День", "Свет", "Ураган", "Шторм", "Тайфун", "Смерч", "Катер", "Цунами", "Линкольн", "Сталин", "Айсберг",
            "Характер", "Свидетель", "Адвокат", "Всадник", "Аркхем"};

    public static String[] nounsBookWoman = {"Месть", "Королева", "Гидра", "Мигера", "Горгона", "Дьявольщина", "Касатка", "Удача", "Стихия", "Жена",
            "Химера", "Тварь", "Посудина", "Фантазия", "Мечта", "Ночь", "Лань", "Буря", "Лодка", "Шхуна", "Рыба", "Ярость", "Нехристь", "Аврора", "Аляска",
            "Арабелла", "Земля", "Особенность", "Конструкция", "Беда", "Гибель", "Кобыла", "Вдова"};

    public static String[] nounsBookIt = {"Приключение", "Возмездие", "Наводнение", "Остервенение", "Бешенство", "Господство", "Корыто", "Предложение",
            "Мероприятие", "Поручение", "Искусство", "Воспитание", "Достоинство", "Убийство", "Рождение", "Становление", "Наказание", "Убеждение", "Мучение",
            "Свидетельство", "Послание", "Буйство", "Хитроумие", "Чудище", "Чудо-юдо"};

    public static String[] adjectivesBookMan = {"Геройский", "Матёрый", "Дорогой", "Морской", "Яростный", "Козырный",
            "Стальной", "Дубовый", "Кричайщий", "Фантастический", "Дерзкий", "Древний", "Неуязвимый", "Неуловимый",
            "Прыткий", "Страшный", "Молниеносный", "Черный", "Бешенный", "Пустынный", "Пьяный", "Небесный",
            "Счастливый", "Отвратительный", "Быстрый", "Мужественный", "Огромный", "Стальной", "Унылый", "Корявый"};

    public static String[] adjectivesBookWoman = {"Геройская", "Матёрая", "Дорогая", "Морская", "Яростная", "Козырная",
            "Стальная", "Дубовая", "Кричащая", "Фантастическая", "Дерзкая", "Древняя", "Неуязвимая", "Неуловимая",
            "Прыткая", "Страшная", "Молниеносная", "Черная", "Бешенная", "Пьяная", "Небесная", "Счастливая", "Золотая",
            "Прекрасная", "Изящная", "Ловкая", "Скользкая", "Опалённая"};

    public static String[] whomBook = {"капитана", "дракона", "Дьявола", "пирата", "брата", "короля", "королевы",
            "вселенной", "по сути", "в море", "в океане", "cреди ясного неба", "из тумана", "без правил", "из ада"};

    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp() {
        return hp;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public Player getOwner() {
        return owner;
    }
    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }
    public int getShipSize() {
        return shipSize;
    }
    public abstract void shipOnTheSeaX(int y, int x);
    public abstract void shipOnTheSeaY(int y, int x);
    public abstract DeckOfShip[] getDecks();



    public String naming(){
        int randomNum = 1 + (int)(Math.random()*6);
        String name = "Безымянный";
        switch (randomNum) {
            case (1)://сущ муж + прил муж
            name = adjectivesBookMan[(int) (Math.random() * (adjectivesBookMan.length - 1))] +
                    " " + nounsBookMan[(int) (Math.random() * (nounsBookMan.length - 1))];
            break;
            case (2)://сущ жен + прил жен
                name = adjectivesBookWoman[(int) (Math.random() * (adjectivesBookWoman.length - 1))] +
                        " " + nounsBookWoman[(int) (Math.random() * (nounsBookWoman.length - 1))];
                break;
            case (3)://сущ сред + спецэффект
                name = nounsBookIt[(int) (Math.random() * (nounsBookIt.length - 1))] +
                        " " + whomBook[(int) (Math.random() * (whomBook.length - 1))];
                break;
            case (4)://сущ муж + спецэффект
                name = nounsBookMan[(int) (Math.random() * (nounsBookMan.length - 1))] +
                        " " + whomBook[(int) (Math.random() * (whomBook.length - 1))];
                break;
            case (5)://сущ муж + спецэффект
                name = nounsBookWoman[(int) (Math.random() * (nounsBookWoman.length - 1))] +
                        " " + whomBook[(int) (Math.random() * (whomBook.length - 1))];
                break;
            case (6)://сущ муж + сущ жен
                name = nounsBookMan[(int) (Math.random() * (nounsBookMan.length - 1))] +
                        " и " + nounsBookWoman[(int) (Math.random() * (nounsBookWoman.length - 1))];
                break;
        }
        return name;
    }

}
