package com.core.Players;

import com.core.Ships.Ship;
import com.core.Tools;

import java.util.ArrayList;

public class ShipsSettings {

    public static void setShips(ArrayList<Ship> shipyard, Settings setting){
        if (setting.equals(Settings.RANDOM))
        {
            int rand = Tools.getRandomNumber(0, Settings.values().length);
            setting = Settings.values()[rand];
        }
        switch (setting){
            case ALL_IN_TOP -> allInTop(shipyard);
            case ALL_IN_RIGHT -> allInRight(shipyard);
            case ALL_IN_LEFT -> allInLeft(shipyard);
            case ALL_IN_BOTTOM -> allInBottom(shipyard);
            case IN_LEFT_TOP_ANGLE -> inLeftTopAngle(shipyard);
            case IN_RIGHT_TOP_ANGLE -> inRightTopAngle(shipyard);
            case IN_LEFT_BOT_ANGLE -> inLeftBotAngle(shipyard);
            case IN_RIGHT_BOT_ANGLE -> inRightBotAngle(shipyard);
            case LEFT_AND_RIGHT_1 -> leftAndRight1(shipyard);
            case LEFT_AND_RIGHT_2 -> leftAndRight2(shipyard);
            case TOP_AND_BOTTOM_1 -> topAndBottom1(shipyard);
            case TOP_AND_BOTTOM_2 -> topAndBottom2(shipyard);
        }
    }
    public static void allInLeft(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsY(shipyard.get(9), 2, 0);
        shipyard.remove(9);
        Tools.setShipToCellsY(shipyard.get(8), 1, 2);
        shipyard.remove(8);
        Tools.setShipToCellsY(shipyard.get(7), 5, 2);
        shipyard.remove(7);
        Tools.setShipToCellsY(shipyard.get(6), 6, 0);
        shipyard.remove(6);
        Tools.setShipToCellsY(shipyard.get(5), 9, 0);
        shipyard.remove(5);
        Tools.setShipToCellsY(shipyard.get(4), 9, 2);
        shipyard.remove(4);
    }
    public static void allInRight(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsY(shipyard.get(9), 2, 9);
        shipyard.remove(9);
        Tools.setShipToCellsY(shipyard.get(8), 1, 7);
        shipyard.remove(8);
        Tools.setShipToCellsY(shipyard.get(7), 5, 7);
        shipyard.remove(7);
        Tools.setShipToCellsY(shipyard.get(6), 6, 9);
        shipyard.remove(6);
        Tools.setShipToCellsY(shipyard.get(5), 9, 9);
        shipyard.remove(5);
        Tools.setShipToCellsY(shipyard.get(4), 9, 7);
        shipyard.remove(4);
    }
    public static void allInTop(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsX(shipyard.get(9), 0, 2);
        shipyard.remove(9);
        Tools.setShipToCellsX(shipyard.get(8), 2, 1);
        shipyard.remove(8);
        Tools.setShipToCellsX(shipyard.get(7), 2, 5);
        shipyard.remove(7);
        Tools.setShipToCellsX(shipyard.get(6), 0, 6);
        shipyard.remove(6);
        Tools.setShipToCellsX(shipyard.get(5), 0, 9);
        shipyard.remove(5);
        Tools.setShipToCellsX(shipyard.get(4), 2, 9);
        shipyard.remove(4);
    }
    public static void allInBottom(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsX(shipyard.get(9), 9, 2);
        shipyard.remove(9);
        Tools.setShipToCellsX(shipyard.get(8), 7, 1);
        shipyard.remove(8);
        Tools.setShipToCellsX(shipyard.get(7), 7, 5);
        shipyard.remove(7);
        Tools.setShipToCellsX(shipyard.get(6), 9, 6);
        shipyard.remove(6);
        Tools.setShipToCellsX(shipyard.get(5), 9, 9);
        shipyard.remove(5);
        Tools.setShipToCellsX(shipyard.get(4), 7, 9);
        shipyard.remove(4);
    }
    public static void inLeftTopAngle(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsY(shipyard.get(9), 2, 0);
        shipyard.remove(9);
        Tools.setShipToCellsY(shipyard.get(8), 6, 0);
        shipyard.remove(8);
        Tools.setShipToCellsX(shipyard.get(7), 0, 3);
        shipyard.remove(7);
        Tools.setShipToCellsX(shipyard.get(6), 9, 1);
        shipyard.remove(6);
        Tools.setShipToCellsX(shipyard.get(5), 0, 7);
        shipyard.remove(5);
        Tools.setShipToCellsY(shipyard.get(4), 1, 9);
        shipyard.remove(4);
    }
    public static void inRightTopAngle(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsY(shipyard.get(9), 2, 9);
        shipyard.remove(9);
        Tools.setShipToCellsY(shipyard.get(8), 6, 9);
        shipyard.remove(8);
        Tools.setShipToCellsX(shipyard.get(7), 0, 6);
        shipyard.remove(7);
        Tools.setShipToCellsX(shipyard.get(6), 0, 3);
        shipyard.remove(6);
        Tools.setShipToCellsX(shipyard.get(5), 9, 9);
        shipyard.remove(5);
        Tools.setShipToCellsY(shipyard.get(4), 1, 0);
        shipyard.remove(4);
    }
    public static void inLeftBotAngle(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsX(shipyard.get(9), 9, 2);
        shipyard.remove(9);
        Tools.setShipToCellsY(shipyard.get(8), 6, 0);
        shipyard.remove(8);
        Tools.setShipToCellsX(shipyard.get(7), 9, 6);
        shipyard.remove(7);
        Tools.setShipToCellsY(shipyard.get(6), 3, 0);
        shipyard.remove(6);
        Tools.setShipToCellsX(shipyard.get(5), 0, 1);
        shipyard.remove(5);
        Tools.setShipToCellsY(shipyard.get(4), 9, 9);
        shipyard.remove(4);
    }
    public static void inRightBotAngle(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsX(shipyard.get(9), 9, 8);
        shipyard.remove(9);
        Tools.setShipToCellsX(shipyard.get(8), 9, 3);
        shipyard.remove(8);
        Tools.setShipToCellsY(shipyard.get(7), 6, 9);
        shipyard.remove(7);
        Tools.setShipToCellsY(shipyard.get(6), 9, 0);
        shipyard.remove(6);
        Tools.setShipToCellsY(shipyard.get(5), 3, 9);
        shipyard.remove(5);
        Tools.setShipToCellsX(shipyard.get(4), 0, 9);
        shipyard.remove(4);
    }
    public static void leftAndRight1(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsY(shipyard.get(9), 2, 0);
        shipyard.remove(9);
        Tools.setShipToCellsY(shipyard.get(8), 1, 9);
        shipyard.remove(8);
        Tools.setShipToCellsY(shipyard.get(7), 8, 9);
        shipyard.remove(7);
        Tools.setShipToCellsY(shipyard.get(6), 6, 0);
        shipyard.remove(6);
        Tools.setShipToCellsY(shipyard.get(5), 9, 0);
        shipyard.remove(5);
        Tools.setShipToCellsY(shipyard.get(4), 5, 9);
        shipyard.remove(4);
    }
    public static void leftAndRight2(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsY(shipyard.get(9), 8, 9);
        shipyard.remove(9);
        Tools.setShipToCellsY(shipyard.get(8), 8, 0);
        shipyard.remove(8);
        Tools.setShipToCellsY(shipyard.get(7), 1, 0);
        shipyard.remove(7);
        Tools.setShipToCellsY(shipyard.get(6), 4, 9);
        shipyard.remove(6);
        Tools.setShipToCellsY(shipyard.get(5), 1, 9);
        shipyard.remove(5);
        Tools.setShipToCellsY(shipyard.get(4), 5, 0);
        shipyard.remove(4);
    }
    public static void topAndBottom1(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsX(shipyard.get(9), 9, 2);
        shipyard.remove(9);
        Tools.setShipToCellsX(shipyard.get(8), 0, 1);
        shipyard.remove(8);
        Tools.setShipToCellsX(shipyard.get(7), 0, 8);
        shipyard.remove(7);
        Tools.setShipToCellsX(shipyard.get(6), 9, 6);
        shipyard.remove(6);
        Tools.setShipToCellsX(shipyard.get(5), 9, 9);
        shipyard.remove(5);
        Tools.setShipToCellsX(shipyard.get(4), 0, 5);
        shipyard.remove(4);
    }
    public static void topAndBottom2(ArrayList<Ship> shipyard) {
        Tools.setShipToCellsX(shipyard.get(9), 0, 8);
        shipyard.remove(9);
        Tools.setShipToCellsX(shipyard.get(8), 9, 1);
        shipyard.remove(8);
        Tools.setShipToCellsX(shipyard.get(7), 9, 8);
        shipyard.remove(7);
        Tools.setShipToCellsX(shipyard.get(6), 0, 4);
        shipyard.remove(6);
        Tools.setShipToCellsX(shipyard.get(5), 0, 1);
        shipyard.remove(5);
        Tools.setShipToCellsX(shipyard.get(4), 9, 5);
        shipyard.remove(4);
    }
}
