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

    public static String[] titulM = {"Матрос", "Старшина", "Мичман", "Лейтенант", "Капитан", "Адмирал"};

    public static String[] firstNames = {"Эдвард", "Джек", "Франсуа", "Стид", "Джон", "Стив", "Пью", "Сильвер",
            "Флинт", "Билли", "Джонни", "Джеки", "Генри", "Джордж", "Бен", "Бенни", "Марти"};

    public static String[] nounsW = {"Борода", "Челюсть", "Рука", "Голова", "Шляпа", "Нога", "Шхуна", "Бухта",
            "Ярость", "Муха", "Пушка", "Игра", "Улыбка", "Дыра", "Фигура", "Смерть", "Нечисть", "Мысль",
            "Кровь", "Гора", "Губа", "Беседа", "Воля", "Щека", "Жопа", "Тюрьма", "Ошибка", "Корма"};

    public static String[] nickname = {"Чёрный", "Матёрый", "Меткий", "Чуткий", "Слепой", "Одноглазый",
            "Длинноногий", "Тупоголовый", "Яростный", "Слюнявый", "Дерзкий", "Старый", "Неуловимый", "Скользкий",
            "Одинокий", "Уродливый", "Подлый", "Обрубленный", "Бешенный", "Безбашенный", "Пьяный", "Справедливый",
            "Саблезубый", "Волосатый", "Деревянный", "Мужественный", "Огромный", "Долговязый", "Грустный", "Весёлый",
            "Рыжий", "Кровавый", "Беззубый", "Подозрительный"};

    public static String[] adjectivesW = {"Чёрная", "Рваная", "Жуткая", "Длинная", "Синяя", "Рыжая",
            "Стальная", "Дубовая", "Нечеловеческая", "Фантастическая", "Рыхлая", "Дряблая", "Непробиваемая", "Таинственная",
            "Белая", "Страшная", "Противная", "Скользкая", "Позолоченная", "Изящная", "Опалённая"};

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
        int randomNum = 1 + (int) (Math.random() * 4);
        switch (randomNum) {
            case (1)://прозвище + имя. напр: Беззубый Джон
                name = nickname[(int) (Math.random() * (nickname.length - 1))] +
                        " " + firstNames[(int) (Math.random() * (firstNames.length - 1))];
                break;
            case (2)://прил жен + сущ. жен. напр: Чёрная Борода
                name = adjectivesW[(int) (Math.random() * (adjectivesW.length - 1))] +
                        " " + nounsW[(int) (Math.random() * (nounsW.length - 1))];
                break;
            case (3)://имя + прил жен + сущ. жен. напр: Джек Чёрная Борода
                name = firstNames[(int) (Math.random() * (firstNames.length - 1))] +
                        " " + adjectivesW[(int) (Math.random() * (adjectivesW.length - 1))] +
                        " " + nounsW[(int) (Math.random() * (nounsW.length - 1))];
                break;
            case (4)://титул + прозвище + имя. напр: Капитан Беззубый Джон
                name = titulM[(int) (Math.random() * (titulM.length - 1))] +
                        " " + nickname[(int) (Math.random() * (nickname.length - 1))] +
                        " " + firstNames[(int) (Math.random() * (firstNames.length - 1))];
                break;
            case (5)://титул + прил жен + сущ. жен.: Адмирал Чёрная Борода
                name = titulM[(int) (Math.random() * (titulM.length - 1))] +
                        " " + adjectivesW[(int) (Math.random() * (adjectivesW.length - 1))] +
                        " " + nounsW[(int) (Math.random() * (nounsW.length - 1))];
                break;
        }
        return name;
    }
}