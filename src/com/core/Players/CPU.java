package com.core.Players;

import com.core.Ships.DeckOfShip;
import com.core.Tools;
import front.App;

public class CPU extends Player{
    Difficult difficult = Difficult.EASY;
    public boolean isCPU() {
        return true;
    }
    public CPU(Difficult difficult) {
        super();
        this.difficult = difficult;
    }
    public boolean shoot(int Y, int X){
        Player CPU = App.SEA_BATTLE_GAME.getCPU();
        Player human = App.SEA_BATTLE_GAME.getHuman();
        Y = Tools.getRandomCoordinate();
        X = Tools.getRandomCoordinate();
        if (CPU.getEnemyFleetMap()[Y][X].getLabel() == '+' || CPU.getEnemyFleetMap()[Y][X].getLabel()=='X'){
            shoot(Y, X);
            return true;
        }
        if (human.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
            String enemyShipName = human.getOurFleetMap()[Y][X].getName();
            DeckOfShip enemyShip = (DeckOfShip) human.getOurFleetMap()[Y][X];
            if (enemyShip.getHp()>0) {
                App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " поврежден!");  //Выводим на экран сообщение
                App.SEA_BATTLE_GAME.theShipIsDamaged(enemyShip.getShipOwner(), human, CPU, Y, X);
            }
            if (enemyShip.getHp()<=0) {
                App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " уничтожен!");
                App.SEA_BATTLE_GAME.theShipIsDestroyed(enemyShip.getShipOwner(), human, CPU);
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);;
        } else {
            App.BATTLE_FIELD_CONTROLLER.textOutput(CPU.getName() + " стреляет и промахивается.");
            if (CPU.getEnemyFleetMap()[Y][X].getLabel()=='X'){

            }else {
                App.SEA_BATTLE_GAME.missed(human, CPU, Y, X);
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);
        }
        return true;
    }
}
