package com.core.Players;

import com.core.ImageName;
import com.core.MapObjects.MapObject;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import com.core.Tools;
import front.App;

import java.util.ArrayList;

import static com.core.SeaBattleGame.setGreenDotsAround;

public class CPU extends Player {
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

    public boolean toFinishHim() {
        if (memory.size() == 1) {//Добиваем Фрегат
            lookAround(memory.get(0).Y, memory.get(0).X);
            return true;
        }
        if (memory.size() > 1) {//Добиваем Галлеон
            int z = memory.size() - 1;
            if (memory.get(z).X < memory.get(z - 1).X) {
                if (shootOne(memory.get(z).Y, memory.get(z).X - 1)) return true;
                else if (shootOne(memory.get(z - 1).Y, memory.get(z - 1).X + 1)) ;
                return true;
            }
            if (memory.get(z).X > memory.get(z - 1).X) {
                if (shootOne(memory.get(z).Y, memory.get(z).X + 1)) return true;
                else shootOne(memory.get(z - 1).Y, memory.get(z - 1).X - 1);
                return true;
            }
            if (memory.get(z).Y < memory.get(z - 1).Y) {
                if (shootOne(memory.get(z).Y - 1, memory.get(z).X)) return true;
                else shootOne(memory.get(z - 1).Y + 1, memory.get(z - 1).X);
                return true;
            }
            if (memory.get(z).Y > memory.get(z - 1).Y) {
                if (shootOne(memory.get(z).Y + 1, memory.get(z).X)) return true;
                else shootOne(memory.get(z-1).Y - 1, memory.get(z-1).X);
                return true;
            }
        }
        return true;
    }

    private boolean lookAround(int Y, int X) {
        MapObject[][] map = App.SEA_BATTLE_GAME.getHuman().getOurFleetMap();
        int rand = Tools.getRandomNumber(1, 4);
        switch (rand) {
            case (1):
                int z = Y - 1;
                if (Tools.isOutOfBoards(map, z, X) || map[z][X].getLabel() == '+'
                        || map[z][X].getLabel() == 'X') {
                    lookAround(Y, X);
                } else {
                    return shootOne(z, X);
                }
                break;
            case (2):
                z = Y + 1;
                if (Tools.isOutOfBoards(map, z, X) || map[z][X].getLabel() == '+'
                        || map[z][X].getLabel() == 'X') {
                    lookAround(Y, X);
                } else {
                    return shootOne(z, X);
                }
                break;
            case (3):
                z = X - 1;
                if (Tools.isOutOfBoards(map, Y, z) || map[Y][z].getLabel() == '+'
                        || map[Y][z].getLabel() == 'X') {
                    lookAround(Y, X);
                } else {

                    return shootOne(Y, z);
                }
                break;
            case (4):
                z = X + 1;
                if (Tools.isOutOfBoards(map, Y, z) || map[Y][z].getLabel() == '+'
                        || map[Y][z].getLabel() == 'X') {
                    lookAround(Y, X);
                } else {
                    return shootOne(Y, z);
                }
                break;
        }
        return false;
    }

    public boolean shoot(int Y, int X) {
        if (isFinishing()) {
            toFinishHim();
            return true;
        } else {
            int y = Tools.getRandomCoordinate();
            int x = Tools.getRandomCoordinate();
            while (shootOne(y, x) == false) {
                y = Tools.getRandomCoordinate();
                x = Tools.getRandomCoordinate();
            }
            return true;
        }
    }

    public boolean shootOne(int Y, int X) {
        Player CPU = App.SEA_BATTLE_GAME.getCPU();
        Player human = App.SEA_BATTLE_GAME.getHuman();
        if (human.getOurFleetMap()[Y][X].getLabel() == '+' || human.getOurFleetMap()[Y][X].getLabel() == 'X') {
            return false;
        }
        if (human.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
            String enemyShipName = human.getOurFleetMap()[Y][X].getName();
            DeckOfShip enemyShip = (DeckOfShip) human.getOurFleetMap()[Y][X];
            theShipIsDamaged(enemyShip.getShipOwner(), human, CPU, Y, X);
            if (enemyShip.getHp() > 0) {
                human.getOurFleetMap()[Y][X].setLabel('X');
                setFinishing(true);//включить режим добивания
                memory.add(new Memory(Y, X));
                App.BATTLE_FIELD_CONTROLLER.textUpdate("Корабль " + enemyShipName + " поврежден!");  //Выводим на экран сообщение
            }
            if (enemyShip.getHp() <= 0) {
                human.getOurFleetMap()[Y][X].setLabel('X');
                theShipIsDestroyed(enemyShip.getShipOwner(), human, CPU);
                setFinishing(false);//выключить режим добивания
                memory.clear();
                App.BATTLE_FIELD_CONTROLLER.textUpdate("Корабль " + enemyShipName + " уничтожен!");
            }
        } else {
            human.getOurFleetMap()[Y][X].setLabel('+');
            App.BATTLE_FIELD_CONTROLLER.textUpdate(CPU.getName() + " стреляет и промахивается.");
            missed(human, CPU, Y, X);
            CPU.setTurnCounter(CPU.getTurnCounter() + 1);
            App.setIsHumanTurn(true);
        }
        return true;
    }

    @Override
    public void theShipIsDamaged(Ship ship, Player enemy, Player self, int Y, int X) {
        enemy.getOurFleetMap()[Y][X].setLabel('X');
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.RED_CROSS);
        ship.setHp(ship.getHp() - 1);
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

    private class Memory {
        int Y;
        int X;

        private Memory(int y, int x) {
            Y = y;
            X = x;
        }
    }
}
