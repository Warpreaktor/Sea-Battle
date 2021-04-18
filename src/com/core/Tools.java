package com.core;

import com.core.Ships.Ship;
import front.App;
import javafx.event.EventHandler;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.input.*;

import java.util.ArrayList;

public class Tools {
    private static Ship dragObject;
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

    public static String[] adjectivesBookMan = {"Геройский", "Матёрый", "Дорогой", "Морской", "Яростный", "Козырный", "Стальной", "Дубовый", "Кричайщий",
            "Фантастический", "Дерзкий", "Древний", "Неуязвимый", "Неуловимый", "Прыткий", "Страшный", "Молниеносный", "Черный", "Бешенный", "Пустынный", "Пьяный", "Небесный",
            "Счастливый", "Отвратительный", "Быстрый", "Мужественный", "Огромный", "Стальной", "Унылый", "Корявый"};

    public static String[] adjectivesBookWoman = {"Геройская", "Матёрая", "Дорогая", "Морская", "Яростная", "Козырная", "Стальная", "Дубовая", "Кричащая",
            "Фантастическая", "Дерзкая", "Древняя", "Неуязвимая", "Неуловимая", "Прыткая", "Страшная", "Молниеносная", "Черная", "Бешенная", "Пьяная", "Небесная",
            "Счастливая", "Золотая", "Прекрасная", "Изящная", "Ловкая", "Скользкая", "Опалённая"};

    public static String[] whomBook = {"капитана", "дракона", "Дьявола", "пирата", "брата", "короля", "королевы", "вселенной", "по сути", "в душе", "в море",
            "в натуре", "cреди ясного неба", "из тумана", "без правил", "из ада"};

    String ANSI_BLACK = "\u001B[30m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_BLUE = "\u001B[34m";
    String ANSI_PURPLE = "\u001B[35m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_WHITE = "\u001B[37m";

    public String shipsNaming() {
        int randomNum = 1 + (int) (Math.random() * 7);
        String name = "Безымянный";
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
                        " и " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
            case (7)://Уникальные имена
                name = "Уида";
                break;
        }
        return name;
    }

    /**
     * Возвращает true если по соседству с ячейкой [Y][X] нет объектов типа DeckOfShip
     *
     * @param map - передается массив GameObject на котором нужно осуществить проверку.
     * @return
     */
    public static boolean isNoShipsArround(GameObject[][] map, int Y, int X) {
        for (int y = Y - 1; y < Y + 2; y++) {
            for (int x = X - 1; x < X + 2; x++) {
                if (y >= map.length) {
                    continue;
                }
                if (y < 0) {
                    continue;
                }
                if (x >= map[Y].length) {
                    continue;
                }
                if (x < 0) {
                    continue;
                }
                if (map[y][x].getClass().getSimpleName().equals("DeckOfShip")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Возвращает true если [Y][X] НЕ выходят за границы переданного массива массива.
     *
     * @param map - передается массив GameObject на котором нужно осуществить проверку.
     * @return
     */
    public static boolean isOutOfBoards(GameObject[][] map, int Y, int X) {
        if (Y >= map.length) {
            return false;
        }
        if (Y < 0) {
            return false;
        }
        if (X >= map[Y].length) {
            return false;
        }
        if (X < 0) {
            return false;
        }
        return true;
    }

    //Получаем рандомную координату в пропорциях установленного поля
    public static int getRandomCoordinate() {
        return (int) (Math.random() * SeaBattleGame.getSIZE());
    }

    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        } else return false;
    }

    public static String getRandomName() {
        String name = "Безымянный";
        int randomNum = 1 + (int) (Math.random() * 6);
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
                        " и " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
        }
        return name;
    }

    public static void setDragSource(Ship source) {
        //Событие при обнаружении перетаскивания
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
                /* Put a image on a dragboard and object to global variable */
                dragObject = source;
                System.out.println(source.getName());
                ClipboardContent content = new ClipboardContent();
                content.putImage(source.getImage());
                db.setContent(content);
                event.consume();
            }
        });
        //Событие при завершении перетаскивания
        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag and drop gesture ended */
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setImage(ImageName.NULL);
                }
                event.consume();
            }
        });
    }

    public static void setDragTargetZone(GameObject targetZone) {

        //Событие при заходе в зону
        targetZone.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* Ресурс находится внутри зоны */
                /* accept it only if it is not dragged from the same node
                 * and if it has a image data */
                if (event.getGestureSource() != targetZone &&
                        event.getDragboard().hasImage()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
        //Событие которое наступает при входе ресурса в зону
        targetZone.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the targetZone */
                /* show to the user that it is an actual gesture targetZone */
                if (event.getGestureSource() != targetZone &&
                        event.getDragboard().hasImage() && targetZone.getClass().getSimpleName().equals("GameCell")) {
                    Effect fx = new Shadow();
                    int imgSize = (int) event.getDragboard().getImage().getWidth();
                    if (App.shipSettingController.isVertical() == false) {
                        setFxX(targetZone.getCoordinateY(), targetZone.getCoordinateX(), fx, imgSize);
                    } else {
                        setFxY(targetZone.getCoordinateY(), targetZone.getCoordinateX(), fx, imgSize);
                    }
                }
                event.consume();
            }
        });
        //Событие наступающее при выходе ресурса из зоны
        targetZone.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                int imgSize = (int) event.getDragboard().getImage().getWidth();
                if (App.shipSettingController.isVertical() == false) {
                    clearFxX(targetZone.getCoordinateY(), targetZone.getCoordinateX(), imgSize);
                } else {
                    clearFxY(targetZone.getCoordinateY(), targetZone.getCoordinateX(), imgSize);
                }
                event.consume();
            }
        });
        //Drop event
        targetZone.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a image data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage() && targetZone.getClass().getSimpleName().equals("GameCell")) {
                    //int imgSize = (int) event.getDragboard().getImage().getWidth();
                    if (App.shipSettingController.isVertical() == false) {
                        success = setShipToCellsX(dragObject, targetZone.getCoordinateY(), targetZone.getCoordinateX());
                        dragObject.getOwner().getShipyard().remove(dragObject);
                    } else {
                        success = setShipToCellsY(dragObject, targetZone.getCoordinateY(), targetZone.getCoordinateX());
                        dragObject.getOwner().getShipyard().remove(dragObject);
                    }
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    public static void setFxX(int Y, int X, Effect fx, int imgSize) {
        GameObject[][] map = App.seaBattleGame.getHuman().getOurFleetMap();
        imgSize /= 60;
        switch (imgSize) {
            case 4:
                if (X + 1 < SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(fx);
                map[Y][X].setEffect(fx);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(fx);
                if (X - 2 >= 0) map[Y][X - 2].setEffect(fx);
                break;
            case 3:
                if (X + 1 < SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(fx);
                map[Y][X].setEffect(fx);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(fx);
                break;
            case 2:
                map[Y][X].setEffect(fx);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(fx);
                break;
            case 1:
                map[Y][X].setEffect(fx);
                break;
        }
    }

    public static void setFxY(int Y, int X, Effect fx, int imgSize) {
        GameObject[][] map = App.seaBattleGame.getHuman().getOurFleetMap();
        imgSize /= 60;
        switch (imgSize) {
            case 4:
                if (Y + 1 < SeaBattleGame.getSIZE()) map[Y + 1][X].setEffect(fx);
                map[Y][X].setEffect(fx);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(fx);
                if (Y - 2 >= 0) map[Y - 2][X].setEffect(fx);
                break;
            case 3:
                if (Y + 1 < SeaBattleGame.getSIZE()) map[Y + 1][X].setEffect(fx);
                map[Y][X].setEffect(fx);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(fx);
                break;
            case 2:
                map[Y][X].setEffect(fx);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(fx);
                break;
            case 1:
                map[Y][X].setEffect(fx);
                break;
        }
    }

    public static void clearFxX(int Y, int X, int imgSize) {
        GameObject[][] map = App.seaBattleGame.getHuman().getOurFleetMap();
        imgSize /= 60;
        switch (imgSize) {
            case 4:
                map[Y][X].setEffect(null);
                if (X + 1 < SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(null);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(null);
                if (X - 2 >= 0) map[Y][X - 2].setEffect(null);
                break;
            case 3:
                map[Y][X].setEffect(null);
                if (X + 1 < SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(null);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(null);
                break;
            case 2:
                map[Y][X].setEffect(null);
                if (X - 1 >= 0) map[Y][X - 1].setEffect(null);
                break;
            case 1:
                map[Y][X].setEffect(null);
                break;
        }
    }

    public static void clearFxY(int Y, int X, int imgSize) {
        GameObject[][] map = App.seaBattleGame.getHuman().getOurFleetMap();
        imgSize /= 60;
        switch (imgSize) {
            case 4:
                map[Y][X].setEffect(null);
                if (Y + 1 < SeaBattleGame.getSIZE()) map[Y + 1][X].setEffect(null);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(null);
                if (Y - 2 >= 0) map[Y - 2][X].setEffect(null);
                break;
            case 3:
                map[Y][X].setEffect(null);
                if (Y + 1 < SeaBattleGame.getSIZE()) map[Y + 1][X].setEffect(null);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(null);
                break;
            case 2:
                map[Y][X].setEffect(null);
                if (Y - 1 >= 0) map[Y - 1][X].setEffect(null);
                break;
            case 1:
                map[Y][X].setEffect(null);
                break;
        }
    }

    public static boolean setShipToCellsX(Ship ship, int Y, int X) {
        GameObject[][] map = ship.getOwner().getOurFleetMap();
        int shipSize = ship.getShipSize();
        switch (shipSize) {
            case 4:
                for (int x = X - 2; x <= X + 1; x++) {
                    if (!isOutOfBoards(map, Y, x)) return false;;
                    if (!isNoShipsArround(map, Y, x)) {
                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaX(Y, X);
                return true;
            case 3:
                for (int x = X - 1; x <= X + 1; x++) {
                    if (!isOutOfBoards(map, Y, x)) return false;;
                    if (!isNoShipsArround(map, Y, x)) {
                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaX(Y, X);
                return true;
            case 2:
                for (int x = X - 1; x <= X; x++) {
                    if (!isOutOfBoards(map, Y, x)) return false;;
                    if (!isNoShipsArround(map, Y, x)) {
                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaX(Y, X);
                return true;
            case 1:
                if (!isOutOfBoards(map, Y, X) || !isNoShipsArround(map, Y, X)) {
                    System.out.println("Здесь нельзя разместить корабль");
                    return false;
                }
                ship.shipOnTheSeaX(Y, X);
                return true;
        }
        return false; //Если не подошел ни один из кейсов
    }

    public static boolean setShipToCellsY(Ship ship, int Y, int X) {
        GameObject[][] map = ship.getOwner().getOurFleetMap();
        int shipSize = ship.getShipSize();
        switch (shipSize) {
            case 4:
                for (int y = Y - 2; y <= Y + 1; y++) {
                    if (!isOutOfBoards(map, y, X)) return false;;
                    if (!isNoShipsArround(map, y, X)) {
                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaY(Y, X);
                return true;
            case 3:
                for (int y = Y - 1; y <= Y + 1; y++) {
                    if (!isOutOfBoards(map, y, X)) return false;;
                    if (!isNoShipsArround(map, y, X)) {
                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaY(Y, X);
                return true;
            case 2:
                for (int y = Y - 1; y <= Y; y++) {
                    if (!isOutOfBoards(map, y, X)) return false;;
                    if (!isNoShipsArround(map, y, X)) {
                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaY(Y, X);
                return true;
            case 1:
                if (!isOutOfBoards(map, Y, X) || !isNoShipsArround(map, Y, X)) {
                    System.out.println("Здесь нельзя разместить корабль");
                    return false;
                }
                ship.shipOnTheSeaY(Y, X);
                return true;
        }
        return false;
    }
}
