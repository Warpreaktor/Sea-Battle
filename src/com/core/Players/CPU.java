package com.core.Players;

import com.core.ImageName;
import com.core.MapObjects.MapObject;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import com.core.Tools;
import front.App;
import javafx.scene.image.Image;

import java.util.ArrayList;

import static com.core.SeaBattleGame.setGreenDotsAround;

public class CPU extends Player{
    private Difficult difficult = Difficult.EASY;
    private ArrayList<Memory> memory = new ArrayList<>();
    private boolean finishing = false;

    public boolean isCPU() {
        return true;
    }

    public CPU(Difficult difficult) {
        super();
        this.difficult = difficult;
        int rand = Tools.getRandomNumber(0, 9);
        this.setPortrait(App.getAllPortraits()[rand]);
    }

    public boolean toFinishHim(){
        if (memory.size() < 2){
            lookAround(memory.get(0).Y, memory.get(0).X);
            return true;
        }
        for (int i = 0; i < memory.size(); i++) {
            if (!lookAround(memory.get(i).Y, memory.get(i).X)) continue;
            return true;
        }
        return true;
    }
    private boolean lookAround(int Y, int X){
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
        int z = Y -1;
        if (Tools.isOutOfBoards(map, z, X) || map[z][X].getLabel() == '+'
                || map[z][X].getLabel() == 'X'){
            //pass
        }else{
            return shootOne(z, X);
        }
        z = Y + 1;
        if (Tools.isOutOfBoards(map,z, X) || map[z][X].getLabel() == '+'
                || map[z][X].getLabel() == 'X'){
            //pass
        }else{
            return shootOne(z, X);
        }
        z = X - 1;
        if (Tools.isOutOfBoards(map,Y, z) || map[Y][z].getLabel() == '+'
                || map[Y][z].getLabel() == 'X'){
            //pass
        }else{

            return shootOne(Y, z);
        }
        z = X + 1;
        if (Tools.isOutOfBoards(map,Y, z) || map[Y][z].getLabel() == '+'
                || map[Y][z].getLabel() == 'X'){
            //pass
        }else{
            return shootOne(Y, z);
        }
        return false;
    }

    public boolean shoot(int Y, int X){
        if (isFinishing()){
            toFinishHim();
            return true;
        }else{
            Y = Tools.getRandomCoordinate();
            X = Tools.getRandomCoordinate();
            return shootOne(Y, X);
        }
    }
    public boolean shootOne(int Y, int X){
        Player CPU = App.SEA_BATTLE_GAME.getCPU();
        Player human = App.SEA_BATTLE_GAME.getHuman();
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
                setFinishing(true);//включить режим добивания
                memory.add(new Memory(Y, X));
                App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " поврежден!");  //Выводим на экран сообщение
            }
            if (enemyShip.getHp()<=0) {
                human.getOurFleetMap()[Y][X].setLabel('X');
                theShipIsDestroyed(enemyShip.getShipOwner(), human, CPU);
                setFinishing(false);//выключить режим добивания
                memory.clear();
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

    public void setFinishing(boolean finishing) {
        this.finishing = finishing;
    }

    public boolean isFinishing() {
        return finishing;
    }

    public Difficult getDifficult() {
        return difficult;
    }
    private class Memory{
        int Y;
        int X;

        private Memory(int y, int x) {
            Y = y;
            X = x;
        }
    }
}
