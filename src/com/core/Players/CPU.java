package com.core.Players;

import com.core.ImageName;
import com.core.MapObjects.MapObject;
import com.core.SeaBattleGame;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import com.core.Tools;
import front.App;

import java.util.ArrayList;

import static com.core.SeaBattleGame.setGreenDotsAround;
import static com.core.Tools.getRandomNumber;
import static com.core.Tools.isOutOfBoards;

public class CPU extends Player {
    private Difficult difficult = Difficult.EASY;
    private boolean finishing = false;//Режим добивания
    private ArrayList<Memory> finishingMemory = new ArrayList<>();//сюда сохраняются координаты в режиме добивания
    private ArrayList<ArrayList<Memory>> zones = new ArrayList<>();
    private Memory lastZone;//Последняя зона в которую стрелял

    public boolean isCPU() {
        return true;
    }

    public CPU(Difficult difficult) {
        super();
        this.difficult = difficult;
        int rand = Tools.getRandomNumber(0, 9);
        this.setPortrait(App.getAllPortraits()[rand]);

        for (int i = 0; i < 4; i++) {
            zones.add(new ArrayList<Memory>());
        }

        for (int y = 0; y < SeaBattleGame.getSIZE(); y++) {
            for (int x = 0; x < SeaBattleGame.getSIZE(); x++) {
                if (y <= 4 && x <= 4) zones.get(0).add(new Memory(y, x));
                if (y <= 4 && x > 4) zones.get(1).add(new Memory(y, x));
                if (y > 4 && x <= 4) zones.get(2).add(new Memory(y, x));
                if (y > 4 && x > 4) zones.get(3).add(new Memory(y, x));
            }
        }
    }

    public boolean toFinishHim() {
        if (finishingMemory.size() == 1) {//Добиваем Фрегат
            lookAround(finishingMemory.get(0).Y, finishingMemory.get(0).X);
            return true;
        }
        if (finishingMemory.size() > 1) {//Добиваем Галлеон
            int z = finishingMemory.size() - 1;
            if (finishingMemory.get(z).X < finishingMemory.get(z - 1).X) {
                if (oneShot(finishingMemory.get(z).Y, finishingMemory.get(z).X - 1)) return true;
                else if (oneShot(finishingMemory.get(z - 1).Y, finishingMemory.get(z - 1).X + 1)) ;
                return true;
            }
            if (finishingMemory.get(z).X > finishingMemory.get(z - 1).X) {
                if (oneShot(finishingMemory.get(z).Y, finishingMemory.get(z).X + 1)) return true;
                else oneShot(finishingMemory.get(z - 1).Y, finishingMemory.get(z - 1).X - 1);
                return true;
            }
            if (finishingMemory.get(z).Y < finishingMemory.get(z - 1).Y) {
                if (oneShot(finishingMemory.get(z).Y - 1, finishingMemory.get(z).X)) return true;
                else oneShot(finishingMemory.get(z - 1).Y + 1, finishingMemory.get(z - 1).X);
                return true;
            }
            if (finishingMemory.get(z).Y > finishingMemory.get(z - 1).Y) {
                if (oneShot(finishingMemory.get(z).Y + 1, finishingMemory.get(z).X)) return true;
                else oneShot(finishingMemory.get(z - 1).Y - 1, finishingMemory.get(z - 1).X);
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
                if (isOutOfBoards(map, z, X) || map[z][X].getLabel() == '+'
                        || map[z][X].getLabel() == 'X') {
                    lookAround(Y, X);
                } else {
                    return oneShot(z, X);
                }
                break;
            case (2):
                z = Y + 1;
                if (isOutOfBoards(map, z, X) || map[z][X].getLabel() == '+'
                        || map[z][X].getLabel() == 'X') {
                    lookAround(Y, X);
                } else {
                    return oneShot(z, X);
                }
                break;
            case (3):
                z = X - 1;
                if (isOutOfBoards(map, Y, z) || map[Y][z].getLabel() == '+'
                        || map[Y][z].getLabel() == 'X') {
                    lookAround(Y, X);
                } else {

                    return oneShot(Y, z);
                }
                break;
            case (4):
                z = X + 1;
                if (isOutOfBoards(map, Y, z) || map[Y][z].getLabel() == '+'
                        || map[Y][z].getLabel() == 'X') {
                    lookAround(Y, X);
                } else {
                    return oneShot(Y, z);
                }
                break;
        }
        return false;
    }

    public boolean shoot(int Y, int X) {
        switch (this.difficult) {
            case HARD -> {
                //в разработке
            }
            case NORMAL -> {
                if (isFinishing()) {
                    toFinishHim();
                    return true;
                }
                Memory coordinates = chooseCoordinates();
                if (getTurnCounter() == 0) oneShot(coordinates.Y, coordinates.X);
                else zonalShot();
            }
            case EASY -> {
                if (isFinishing()) {
                    toFinishHim();
                    return true;
                } else {
                    //            int y = Tools.getRandomCoordinate();
                    //            int x = Tools.getRandomCoordinate(); //пробуем новый интеллектуальный выбор координат
                    Memory coordinates = chooseCoordinates();
                    while (oneShot(coordinates.Y, coordinates.X) == false) {
                        //                y = Tools.getRandomCoordinate();
                        //                x = Tools.getRandomCoordinate(); пробуем новый интеллектуальны выбор координат
                        coordinates = chooseCoordinates();
                    }
                    App.SEA_BATTLE_GAME.isVictory();
                }
            }
        }
        return true;
    }

    public boolean oneShot(int Y, int X) {
        Player CPU = App.SEA_BATTLE_GAME.getCPU();
        Player human = App.SEA_BATTLE_GAME.getHuman();
        if (isOutOfBoards(human.getOurFleetMap(), Y, X)
                || human.getOurFleetMap()[Y][X].getLabel() == '+'
                || human.getOurFleetMap()[Y][X].getLabel() == 'X') {
            return false;
        }
        if (human.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
            String enemyShipName = human.getOurFleetMap()[Y][X].getName();
            DeckOfShip enemyShip = (DeckOfShip) human.getOurFleetMap()[Y][X];
            theShipIsDamaged(enemyShip.getShipOwner(), human, CPU, Y, X);
            if (enemyShip.getHp() > 0) {
                human.getOurFleetMap()[Y][X].setLabel('X');
                setFinishing(true);//включить режим добивания
                finishingMemory.add(new Memory(Y, X));
                lastZone = new Memory(Y, X);
                App.BATTLE_FIELD_CONTROLLER.textUpdate("Корабль " + enemyShipName + " поврежден!");  //Выводим на экран сообщение
            }
            if (enemyShip.getHp() <= 0) {
                human.getOurFleetMap()[Y][X].setLabel('X');
                theShipIsDestroyed(enemyShip.getShipOwner(), human, CPU);
                setFinishing(false);//выключить режим добивания
                finishingMemory.clear();
                lastZone = new Memory(Y, X);
                App.BATTLE_FIELD_CONTROLLER.textUpdate("Корабль " + enemyShipName + " уничтожен!");
            }
        } else {
            human.getOurFleetMap()[Y][X].setLabel('+');
            App.BATTLE_FIELD_CONTROLLER.textUpdate(CPU.getName() + " стреляет и промахивается.");
            missed(human, CPU, Y, X);
            lastZone = new Memory(Y, X);
            CPU.setTurnCounter(CPU.getTurnCounter() + 1);
            App.setNexTurn();
        }
        return true;
    }

    public void zonalShot() {

    }

    public Memory chooseCoordinates() {
        int zoneIndex = Tools.getRandomNumber(0, zones.size() - 1);//Получаем рандомную зону координат.
        int memoryIndex = getRandomNumber(0, zones.get(zoneIndex).size() - 1);//Получаем рандомный индекс ячейки из зоны коордлинат
        Memory mem = zones.get(zoneIndex).get(memoryIndex); //Получаем ячейку из зоны координат.
        zones.get(zoneIndex).remove(memoryIndex);//Удаляем ячейку из зоны
        if (zones.get(zoneIndex).size() < 1) {
            zones.remove(zoneIndex);//Удаляем зону в которой закончились все координаты
        }
        return mem;
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
        char zone;

        private Memory(int y, int x) {
            Y = y;
            X = x;
            if (y <= 4 && x <= 4) zone = 'A';
            if (y <= 4 && x > 4) zone = 'B';
            if (y > 4 && x <= 4) zone = 'C';
            if (y > 4 && x > 4) zone = 'D';
        }
    }
}
