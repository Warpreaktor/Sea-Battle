package com.core;

import front.App;
import javafx.event.EventHandler;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

public class Tools {
    public static String[] nounsBookMan = {"Махаон","Герой","Брат","Бриз", "Стул", "Император","Массон", "Сахарок", "Бог", "Минотавр", "Враг", "Победитель",
            "Карбид", "Китобоец", "Христианин", "Гнев", "Нормандец", "Плот", "Водолаз", "Туз", "Шарабан", "Капкан", "Маньяк", "Варяг", "Ковбой",
    "Гром", "Малыш", "Кашалот", "Воин", "Царь", "Сон", "День", "Свет", "Ураган", "Шторм", "Тайфун", "Смерч", "Катер", "Цунами", "Линкольн", "Сталин", "Айсберг",
            "Характер", "Свидетель", "Адвокат", "Всадник", "Аркхем"};

    public static String[] nounsBookWoman = {"Месть", "Королева", "Гидра", "Уида", "Мигера", "Горгона", "Дьявольщина", "Касатка", "Удача", "Стихия", "Жена",
    "Химера", "Мокрядь", "Тварь", "Посудина", "Фантазия", "Мечта", "Ночь", "Лань", "Буря", "Лодка", "Шхуна", "Рыба", "Ярость", "Нехристь", "Аврора", "Аляска",
            "Арабелла", "Земля", "Особенность", "Конструкция", "Беда", "Гибель", "Кобыла", "Вдова"};

    public static String[] nounsBookIt = {"Приключение", "Возмездие", "Наводнение", "Остервенение", "Бешенство", "Господство", "Корыто", "Предложение",
    "Мероприятие", "Поручение", "Искусство", "Воспитание", "Достоинство","Убийство", "Рождение", "Становление", "Наказание", "Убеждение", "Мучение",
            "Свидетельство", "Послание", "Буйство", "Хитроумие", "Чудище", "Чудо-юдо"};

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
                        " и " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
        }
        return name;
    }

    public static void setDragSource(ImageView source) {
        //Событие при обнаружении перетаскивания
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
                /* Put a image on a dragboard */
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
                    source.setImage(null);
                }
                event.consume();
            }
        });
    }

    public static void setDragTargetZone(GameCell targetZone){
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
                        event.getDragboard().hasImage()) {
                    Effect fx = new Shadow();
                    int imgSize = (int)event.getDragboard().getImage().getWidth();
                    targetZone.setEffect(fx);
                    setFxForNeighbors(targetZone.getCoordinateY(), targetZone.getCoordinateX(), targetZone, fx, imgSize);
                }
                event.consume();
            }
        });
        //Событие наступающее при выходе ресурса из зоны
        targetZone.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                targetZone.setEffect(null);
                int imgSize = (int)event.getDragboard().getImage().getWidth();
                clearFxForNeighbors(targetZone.getCoordinateY(), targetZone.getCoordinateX(), targetZone, imgSize);
                event.consume();
            }
        });
        //Событие наступающее при отпускании ресурса в зоне
        targetZone.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    targetZone.setImage(db.getImage());
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }
    public static void setFxForNeighbors(int Y, int X, GameCell cell, Effect fx, int imgSize) {
        GameCell[][] map = App.seaBattleGame.getHuman().getOurFleetMap();
        imgSize /= 60;
        System.out.println(imgSize);
        switch (imgSize){
            case 4:
                if (X + 1 <= SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(fx);
                if (X - 1 > 0) map[Y][X - 1].setEffect(fx);
                if (X - 2 > 0) map[Y][X - 2].setEffect(fx);
                break;
            case 3:
                if (X + 1 <= SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(fx);
                if (X - 1 > 0) map[Y][X - 1].setEffect(fx);
                break;
            case 2:
                if (X - 1 > 0) map[Y][X - 1].setEffect(fx);
                break;
        }
    }
    public static void clearFxForNeighbors(int Y, int X, GameCell cell, int imgSize) {
        GameCell[][] map = App.seaBattleGame.getHuman().getOurFleetMap();
        imgSize /= 60;
        System.out.println(imgSize);
        switch (imgSize){
            case 4:
                if (X + 1 <= SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(null);
                if (X - 1 > 0) map[Y][X - 1].setEffect(null);
                if (X - 2 > 0) map[Y][X - 2].setEffect(null);
                break;
            case 3:
                if (X + 1 <= SeaBattleGame.getSIZE()) map[Y][X + 1].setEffect(null);
                if (X - 1 > 0) map[Y][X - 1].setEffect(null);
                break;
            case 2:
                if (X - 1 > 0) map[Y][X - 1].setEffect(null);
                break;
        }
    }
}
