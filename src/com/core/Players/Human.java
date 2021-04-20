package com.core.Players;

import com.core.Ships.DeckOfShip;
import front.App;

public class Human extends Player{
    public boolean isCPU() {
        return false;
    }

    public Human() {
        super();
    }
    public boolean shoot(int Y, int X) {
        Player CPU = App.SEA_BATTLE_GAME.getCPU();
        Player human = App.SEA_BATTLE_GAME.getHuman();
        System.out.println(human.getEnemyFleetMap()[Y][X].getClass().getSimpleName());
        if (human.getEnemyFleetMap()[Y][X].getLabel() == '+' || human.getEnemyFleetMap()[Y][X].getLabel() == 'X'){
            return false;
        }else {
            try {
                if (CPU.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
                    String enemyShipName = CPU.getOurFleetMap()[Y][X].getName();
                    DeckOfShip enemyShip = (DeckOfShip) CPU.getOurFleetMap()[Y][X];
                    if (enemyShip.getHp() > 0) {
                        App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " поврежден!");
                        App.SEA_BATTLE_GAME.theShipIsDamaged(enemyShip.getShipOwner(), CPU, human, Y, X);
                    }
                    if (enemyShip.getHp() <= 0) {
                        App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " уничтожен!");
                        App.SEA_BATTLE_GAME.theShipIsDestroyed(enemyShip.getShipOwner(), CPU, human);
                    }
                } else {
                    App.BATTLE_FIELD_CONTROLLER.textOutput(human.getName() + " промахнулся!");
                    if (human.getEnemyFleetMap()[Y][X].getLabel() == 'X') {

                    } else {
                        human.getEnemyFleetMap()[Y][X].setLabel('+');
                    }
                    App.SEA_BATTLE_GAME.missed(CPU, human, Y, X);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getLocalizedMessage());
                System.out.println("Некорректный ввод, попробуйте еще раз");
                return false;
            }
            return true;
        }
    }
}
