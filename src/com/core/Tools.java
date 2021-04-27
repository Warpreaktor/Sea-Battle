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
import java.util.Locale;

public class Tools {

    /**
     * Возвращает копию переданной строки с первой заглавной буквой.
     * @param text
     * @return
     */
    public static String textFormatter(String text){
        char firstChar = text.toUpperCase().charAt(0);
        String textFormatted = firstChar + text.substring(1).toLowerCase(Locale.ROOT);
        return textFormatted;
    }

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
