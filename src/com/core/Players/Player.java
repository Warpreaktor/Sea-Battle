package com.core.Players;

import com.core.GameObjects.MapObject;
import com.core.ImageName;
import com.core.SeaBattleGame;
import com.core.Ships.*;
import com.core.Tools;
import front.App;

import java.util.ArrayList;

public abstract class Player {
    //private boolean isCPU = false;
    private String name;
    private int countOfTurns = 0;   //Счетчик ходов сделанных игроком.
    private int numberOfShip = 0;   //Итоговое количество кораблей игрока на поле, которое уменьшается по ходу их уничтожения.
    private ArrayList<Ship> shipyard;
    private MapObject[][] ourFleetMap;
    private MapObject[][] enemyFleetMap;

    public static String[] nounsBookMan = {"Герой", "Брат", "Бриз", "Император", "Массон", "Сахарок", "Бог", "Минотавр", "Враг", "Победитель",
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

    public Player() {
        ourFleetMap = new MapObject[App.seaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];
        enemyFleetMap = new MapObject[App.seaBattleGame.getSIZE()][SeaBattleGame.getSIZE()];
        //1 Линкор(4), 2 Крейсера(3), 3 Эсминца(2), 4 Подлодки(1)
        /**Каждая клетка игрового поля заполняется объектами пустыми объектами MapCell
         */
        shipyard = new ArrayList<Ship>();
        shipyard.add(new Linkor(this));
        shipyard.add(new Cruiser(this));
        shipyard.add(new Cruiser(this));
        shipyard.add(new Destroyer(this));
        shipyard.add(new Destroyer(this));
        shipyard.add(new Destroyer(this));
        shipyard.add(new Submarine(this));
        shipyard.add(new Submarine(this));
        shipyard.add(new Submarine(this));
        shipyard.add(new Submarine(this));
    }

    public abstract boolean isCPU();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MapObject[][] getEnemyFleetMap() {
        return enemyFleetMap;
    }

    public MapObject[][] getOurFleetMap() {
        return ourFleetMap;
    }

    public void setGameCellToEnemyFleetMap(MapObject gameCell, int y, int x) {
        this.enemyFleetMap[y][x] = gameCell;
    }

    public void setGameCellToOurFleetMap(MapObject gameCell, int y, int x) {
        this.ourFleetMap[y][x] = gameCell;
    }

    public void setNumberOfShip(int numberOfShip) {
        this.numberOfShip = numberOfShip;
    }

    public int getNumberOfShip() {
        return numberOfShip;
    }

    public ArrayList<Ship> getShipyard() {
        return shipyard;
    }

    public void setCountOfTurns(int countOfTurns) {
        this.countOfTurns = countOfTurns;
    }

    public int getCountOfTurns() {
        return countOfTurns;
    }

    /**
     * Метод вызывается у конкретного игрока и проходит по всей его верфи.
     * На каждом шаге определяется с каким типом корабля мы имеем дело и для дальнейшей его установки на поле
     * вызывается отдельный метод в который мы передаем конкретный объект.
     */
    public void shipsOnGame() {
        if (shipyard.size() < 1) {
            System.out.println("Корабли кончились");
            return;
        } else {
            while (shipyard.size() > 0) {
                for (int i = shipyard.size() - 1; i >= 0; i--) {
                    setShipRandomizer(shipyard.get(i));
                }
            }
        }
    }

    /**
     * Метод принимает корабль в качестве параметра и выставляет его на игровое поле, проверяя
     * перед этим не соседствует ли или не заполнена эта ячейка другим кораблем.
     */
    public void setShipRandomizer(Ship ship) {
        int Y = Tools.getRandomCoordinate();  //Получаем рандомную координату Y
        int X = Tools.getRandomCoordinate();  //Получаем рандомную координату X
        int side = 1 + (int) (Math.random() * 4);  //Получаем рандомное направление для размещения корабля
        int tempY = Y;
        int tempX = X;
        switch (side) {
            case (1)://По горизонтали
                //Пытаемся установить корабль по рандомным координатам
                if (!Tools.setShipToCellsX(ship, tempY, tempX)) {
                    setShipRandomizer(ship);
                    return;
                }else {
                    //Удаляем установленный корабль из верфи игрока и стираем картинку корабля со сцены.
                    shipyard.remove(ship);
                    ship.setImage(ImageName.NULL);
                    break;
                }
            case (2)://По вертикали
                if (!Tools.setShipToCellsY(ship, tempY, tempX)) {
                    setShipRandomizer(ship);
                    return;
                }else {
                    shipyard.remove(ship);
                    ship.setImage(ImageName.NULL);
                    break;
                }
        }
    }
    public static String getRandomName() {
        String name = "Безымянный";
        int randomNum = 1 + (int) (Math.random() * 6);
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
