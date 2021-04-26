package com.core;

import com.core.MapObjects.MapObject;
import com.core.Players.CPU;
import com.core.Ships.Ship;
import front.App;
import javafx.event.EventHandler;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.input.*;

import java.util.ArrayList;

public class Tools {
    private static Ship dragObject;

    public static void easyStat() {
        int[] stat = {35,};
        int sum = 0;
        for (int itr : stat) {
            sum += itr;
        }
        System.out.println(sum / stat.length);
    }
    public static void normalStat() {
        int[] stat = {};
        int sum = 0;
        for (int itr : stat) {
            sum += itr;
        }
        System.out.println(sum / stat.length);
    }
    public static void hardStat() {
        int[] stat = {};
        double sum = 0;
        for (int itr : stat) {
            sum += itr;
        }
        System.out.println(sum / stat.length);
    }

    public static ArrayList compareMax(ArrayList a, ArrayList b){
        if (a.size() > b.size()) {
            return a;
        }else return b;
    }
    public static ArrayList compareMin(ArrayList a, ArrayList b){
        if (a.size() > b.size()) {
            return a;
        }else return b;
    }
    /**
     * Возвращает true если по соседству с ячейкой [Y][X] нет объектов типа DeckOfShip
     *
     * @param map - передается массив MapObject на котором нужно осуществить проверку.
     * @return
     */
    public static boolean isNoShipsArround(MapObject[][] map, int Y, int X) {
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
     * Возвращает true если [Y][X] выходят за границы переданного массива массива.
     * @param map - передается массив MapObject на котором нужно осуществить проверку.
     * @return
     */
    public static boolean isOutOfBoards(MapObject[][] map, int Y, int X) {
        if (Y >= map.length) {
            return true;
        }
        if (Y < 0) {
            return true;
        }
        if (X >= map[Y].length) {
            return true;
        }
        if (X < 0) {
            return true;
        }
        return false;
    }

    public static int getRandomNumber(int from, int to) {
        //нужно погонять в тестах тут зарыта ошибка. Могут вылехать не те числа что ты думаешь.
        int rand = from + (int) (Math.random() * to);
        return rand;
    }

    //Получаем рандомную координату в всего поля
    public static int getRandomCoordinate() {
        int rand = Tools.getRandomNumber(0, 10);//Так должно быть. Trust me.
        if (rand == 10) {
            return 9;
        }
        return rand;
    }



    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        } else return false;
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

    public static void setDragTargetZone(MapObject targetZone) {

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
                        event.getDragboard().hasImage() && targetZone.getClass().getSimpleName().equals("MapCell")) {
                    Effect fx = new Shadow();
                    int imgSize = (int) event.getDragboard().getImage().getWidth();
                    if (App.SHIP_SETTING_CONTROLLER.isVertical() == false) {
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
                if (App.SHIP_SETTING_CONTROLLER.isVertical() == false) {
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
                if (db.hasImage() && targetZone.getClass().getSimpleName().equals("MapCell")) {
                    //int imgSize = (int) event.getDragboard().getImage().getWidth();
                    if (App.SHIP_SETTING_CONTROLLER.isVertical() == false) {
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
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
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
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
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
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
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
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
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
        MapObject[][] map = ship.getOwner().getOurFleetMap();
        int shipSize = ship.getShipSize();
        switch (shipSize) {
            case 4:
                for (int x = X - 2; x <= X + 1; x++) {
                    if (isOutOfBoards(map, Y, x)) return false;
                    ;
                    if (!isNoShipsArround(map, Y, x)) {
//                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaX(Y, X);
                return true;
            case 3:
                for (int x = X - 1; x <= X + 1; x++) {
                    if (isOutOfBoards(map, Y, x)) return false;
                    ;
                    if (!isNoShipsArround(map, Y, x)) {
//                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaX(Y, X);
                return true;
            case 2:
                for (int x = X - 1; x <= X; x++) {
                    if (isOutOfBoards(map, Y, x)) return false;
                    ;
                    if (!isNoShipsArround(map, Y, x)) {
//                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaX(Y, X);
                return true;
            case 1:
                if (isOutOfBoards(map, Y, X) || !isNoShipsArround(map, Y, X)) {
//                    System.out.println("Здесь нельзя разместить корабль");
                    return false;
                }
                ship.shipOnTheSeaX(Y, X);
                return true;
        }
        return false; //Если не подошел ни один из кейсов
    }

    public static boolean setShipToCellsY(Ship ship, int Y, int X) {
        MapObject[][] map = ship.getOwner().getOurFleetMap();
        int shipSize = ship.getShipSize();
        switch (shipSize) {
            case 4:
                for (int y = Y - 2; y <= Y + 1; y++) {
                    if (isOutOfBoards(map, y, X)) return false;
                    ;
                    if (!isNoShipsArround(map, y, X)) {
//                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaY(Y, X);
                return true;
            case 3:
                for (int y = Y - 1; y <= Y + 1; y++) {
                    if (isOutOfBoards(map, y, X)) return false;
                    ;
                    if (!isNoShipsArround(map, y, X)) {
//                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaY(Y, X);
                return true;
            case 2:
                for (int y = Y - 1; y <= Y; y++) {
                    if (isOutOfBoards(map, y, X)) return false;
                    ;
                    if (!isNoShipsArround(map, y, X)) {
//                        System.out.println("Нельзя размещать свои корабли вплотную к другим кораблям");
                        return false;
                    }
                }
                ship.shipOnTheSeaY(Y, X);
                return true;
            case 1:
                if (isOutOfBoards(map, Y, X) || !isNoShipsArround(map, Y, X)) {
//                    System.out.println("Здесь нельзя разместить корабль");
                    return false;
                }
                ship.shipOnTheSeaY(Y, X);
                return true;
        }
        return false;
    }
}
