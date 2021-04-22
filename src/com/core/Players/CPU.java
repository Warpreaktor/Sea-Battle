package com.core.Players;

import com.core.ImageName;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import com.core.Tools;
import front.App;
import javafx.scene.image.Image;

import static com.core.SeaBattleGame.setGreenDotsAround;

public class CPU extends Player{
    Difficult difficult = Difficult.EASY;
    public boolean isCPU() {
        return true;
    }

    public CPU(Difficult difficult) {
        super();
        this.difficult = difficult;
        int rand = Tools.getRandomNumber(0, 9);
        this.setPortrait(App.getAllPortraits()[rand]);
    }

    public boolean shoot(int Y, int X){
        Player CPU = App.SEA_BATTLE_GAME.getCPU();
        Player human = App.SEA_BATTLE_GAME.getHuman();
        Y = Tools.getRandomCoordinate();
        X = Tools.getRandomCoordinate();
        if (human.getOurFleetMap()[Y][X].getLabel() == '+' || human.getOurFleetMap()[Y][X].getLabel()== 'X'){
            shoot(Y, X);
            return true;
        }
        if (human.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
            String enemyShipName = human.getOurFleetMap()[Y][X].getName();
            DeckOfShip enemyShip = (DeckOfShip) human.getOurFleetMap()[Y][X];
            theShipIsDamaged(enemyShip.getShipOwner(), human, CPU, Y, X);
            if (enemyShip.getHp()>0) {
                human.getOurFleetMap()[Y][X].setLabel('X');
                App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " поврежден!");  //Выводим на экран сообщение
            }
            if (enemyShip.getHp()<=0) {
                human.getOurFleetMap()[Y][X].setLabel('X');
                theShipIsDestroyed(enemyShip.getShipOwner(), human, CPU);
                App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " уничтожен!");
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);;
        } else {
            human.getOurFleetMap()[Y][X].setLabel('+');
            App.BATTLE_FIELD_CONTROLLER.textOutput(CPU.getName() + " стреляет и промахивается.");
            missed(human, CPU, Y, X);
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);
        }
        return true;
    }

    @Override
    public void theShipIsDamaged(Ship ship, Player enemy, Player self, int Y, int X) {
        enemy.getOurFleetMap()[Y][X].setLabel('X');
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.RED_CROSS);
        ship.setHp(ship.getHp()-1);
    }

    @Override
    public void theShipIsDestroyed(Ship ship, Player enemy, Player self) {
        setGreenDotsAround(enemy.getOurFleetMap(), ship);
        App.SEA_BATTLE_GAME.playerShipDecrement(enemy);
        App.BATTLE_FIELD_CONTROLLER.stateUpdate();
    }

    @Override
    public void missed(Player enemy, Player self, int Y, int X) {
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.DOT);
        enemy.getOurFleetMap()[Y][X].setLabel('+');
    }
}
