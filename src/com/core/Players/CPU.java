package com.core.Players;

import com.core.ImageName;
import com.core.MapObjects.MapObject;
import com.core.SeaBattleGame;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import com.core.Tools;
import front.App;

import java.util.ArrayList;

import static com.core.Tools.getRandomNumber;
import static com.core.Tools.isOutOfBoards;

public class CPU extends Player {
    private Difficult difficult;//Инициализируется при создании компьютерного игрока.
    private boolean finishing = false;//Включение режима добивания.
    private ArrayList<Memory> finishingMemory = new ArrayList<>();//сюда сохраняются координаты в режиме добивания
    private ArrayList<Memory> intelCoordinates1 = new ArrayList<>();//список из координат для поиска линкоров и галлеонов
    private ArrayList<Memory> intelCoordinates2 = new ArrayList<>();//список из координат для поиска фрегатов
    private ArrayList<Memory> shipsSettingCoordinates1 = new ArrayList<>();//список из координат для расстановки кораблей.
    private ArrayList<ArrayList<Memory>> zones = new ArrayList<>();//Поле поделённое на четыре зоны координат.
    private Memory lastZone;//Последняя зона в которую стрелял
    //private boolean isBigShipDestroyed;

    public boolean isCPU() {
        return true;
    }

    private void zonesInit() {
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

    private void intelCoordinateInit() {
        //Координаты для первой фазы игры
        intelCoordinates1.add(new Memory(3, 0));
        intelCoordinates1.add(new Memory(7, 0));
        intelCoordinates1.add(new Memory(2, 1));
        intelCoordinates1.add(new Memory(6, 1));
        intelCoordinates1.add(new Memory(9, 2));
        intelCoordinates1.add(new Memory(5, 2));
        intelCoordinates1.add(new Memory(1, 2));
        intelCoordinates1.add(new Memory(0, 3));
        intelCoordinates1.add(new Memory(4, 3));
        intelCoordinates1.add(new Memory(8, 3));
        intelCoordinates1.add(new Memory(3, 4));
        intelCoordinates1.add(new Memory(7, 4));
        intelCoordinates1.add(new Memory(2, 5));
        intelCoordinates1.add(new Memory(6, 5));
        intelCoordinates1.add(new Memory(1, 6));
        intelCoordinates1.add(new Memory(5, 6));
        intelCoordinates1.add(new Memory(9, 6));
        intelCoordinates1.add(new Memory(0, 7));
        intelCoordinates1.add(new Memory(4, 7));
        intelCoordinates1.add(new Memory(8, 7));
        intelCoordinates1.add(new Memory(3, 8));
        intelCoordinates1.add(new Memory(7, 8));
        intelCoordinates1.add(new Memory(2, 9));
        intelCoordinates1.add(new Memory(6, 9));

        //Координаты для первой фазы игры
        intelCoordinates2.add(new Memory(1, 0));
        intelCoordinates2.add(new Memory(5, 0));
        intelCoordinates2.add(new Memory(9, 0));
        intelCoordinates2.add(new Memory(0, 1));
        intelCoordinates2.add(new Memory(4, 1));
        intelCoordinates2.add(new Memory(8, 1));
        intelCoordinates2.add(new Memory(3, 2));
        intelCoordinates2.add(new Memory(7, 2));
        intelCoordinates2.add(new Memory(2, 3));
        intelCoordinates2.add(new Memory(6, 3));
        intelCoordinates2.add(new Memory(1, 4));
        intelCoordinates2.add(new Memory(5, 4));
        intelCoordinates2.add(new Memory(9, 4));
        intelCoordinates2.add(new Memory(0, 5));
        intelCoordinates2.add(new Memory(4, 5));
        intelCoordinates2.add(new Memory(8, 5));
        intelCoordinates2.add(new Memory(3, 6));
        intelCoordinates2.add(new Memory(7, 6));
        intelCoordinates2.add(new Memory(2, 7));
        intelCoordinates2.add(new Memory(6, 7));
        intelCoordinates2.add(new Memory(1, 8));
        intelCoordinates2.add(new Memory(5, 8));
        intelCoordinates2.add(new Memory(9, 8));
        intelCoordinates2.add(new Memory(0, 9));
        intelCoordinates2.add(new Memory(4, 9));
        intelCoordinates2.add(new Memory(8, 9));
    }

    @Override
    public void shipsOnGame() {
        switch (this.difficult) {
            case EASY:
                while (this.getShipyard().size() > 0) {
                    for (int i = this.getShipyard().size() - 1; i >= 0; i--) {
                        setShipRandomizer(this.getShipyard().get(i));
                    }
                }
                break;
            case NORMAL:
                while (this.getShipyard().size() > 0) {
                    for (int i = this.getShipyard().size() - 1; i >= 0; i--) {
                        setShipRandomizer(this.getShipyard().get(i));
                    }
                }
                break;
            case HARD:
                ShipsSettings.setShips(this.getShipyard(), Settings.RANDOM);
                while (this.getShipyard().size() > 0) {
                    for (int i = this.getShipyard().size() - 1; i >= 0; i--) {
                        setShipRandomizer(this.getShipyard().get(i));
                    }
                }
                break;
        }
    }

    public CPU(Difficult difficult) {
        super();
        this.difficult = difficult;
        int rand = Tools.getRandomNumber(0, 9);
        this.setPortrait(App.getAllPortraits()[rand]);
        zonesInit();
        if (difficult == Difficult.HARD) intelCoordinateInit();
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
                else if (oneShot(finishingMemory.get(0).Y, finishingMemory.get(0).X + 1)) ;
                return true;
            }
            if (finishingMemory.get(z).X > finishingMemory.get(z - 1).X) {
                if (oneShot(finishingMemory.get(z).Y, finishingMemory.get(z).X + 1)) return true;
                else oneShot(finishingMemory.get(0).Y, finishingMemory.get(0).X - 1);
                return true;
            }
            if (finishingMemory.get(z).Y < finishingMemory.get(z - 1).Y) {
                if (oneShot(finishingMemory.get(z).Y - 1, finishingMemory.get(z).X)) return true;
                else oneShot(finishingMemory.get(0).Y + 1, finishingMemory.get(0).X);
                return true;
            }
            if (finishingMemory.get(z).Y > finishingMemory.get(z - 1).Y) {
                if (oneShot(finishingMemory.get(z).Y + 1, finishingMemory.get(z).X)) return true;
                else oneShot(finishingMemory.get(0).Y - 1, finishingMemory.get(0).X);
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
                if (isFinishing()) {//Если находимся в режиме добивания
                    toFinishHim();
                    return true;
                }
                if (!isBigShipsDestroyed() && this.intelCoordinates1.size() > 0) {
                    System.out.println(isBigShipsDestroyed());
                    Memory coordinates = getCoordinateFromList(intelCoordinates1);
                    while (oneShot(coordinates.Y, coordinates.X) == false) {
                        coordinates = getCoordinate(chooseZone());
                    }
                    return true;
                }
                if (!isFrigatesDestroyed() && this.intelCoordinates2.size() > 0) {
                    Memory coordinates = getCoordinateFromList(intelCoordinates2);
                    while (oneShot(coordinates.Y, coordinates.X) == false) {
                        coordinates = getCoordinate(chooseZone());
                    }
                    return true;
                }
                Memory coordinates = getCoordinate(chooseZone());
                while (oneShot(coordinates.Y, coordinates.X) == false) {
                    coordinates = getCoordinate(chooseZone());
                }
                return true;
            }
            case NORMAL -> {
                if (isFinishing()) {//Если находимся в режиме добивания
                    toFinishHim();
                    return true;
                }
                if (getTurnCounter() == 0) {//Если это первый ход
                    Memory coordinates = getCoordinate();
                    oneShot(coordinates.Y, coordinates.X);
                } else {
                    Memory coordinates = getCoordinate(chooseZone());
                    while (oneShot(coordinates.Y, coordinates.X) == false) {
                        coordinates = getCoordinate(chooseZone());
                    }
                }
                return true;
            }
            case EASY -> {
                if (isFinishing()) {
                    toFinishHim();
                    return true;
                } else {
                    Memory coordinates = getCoordinate(chooseZone());
                    while (oneShot(coordinates.Y, coordinates.X) == false) {
                        coordinates = getCoordinate(chooseZone());
                    }
                }
                return true;
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
                App.BATTLE_FIELD_CONTROLLER.setReplica(Player.getRandomReplica(Replicas.HUMAN_DAMAGED));  //Выводим на экран сообщение
                App.BATTLE_FIELD_CONTROLLER.setComicsImage(ReplicasImage.EMOTIONAL_TALK);
            }
            if (enemyShip.getHp() <= 0) {
                human.getOurFleetMap()[Y][X].setLabel('X');
                theShipIsDestroyed(enemyShip.getShipOwner(), human, CPU);
                setFinishing(false);//выключить режим добивания
                finishingMemory.clear();
                lastZone = new Memory(Y, X);
                App.BATTLE_FIELD_CONTROLLER.setReplica(Player.getRandomReplica(Replicas.HUMAN_KILLED));
                App.BATTLE_FIELD_CONTROLLER.setComicsImage(ReplicasImage.ANGRY_TALK);
            }
        } else {
            human.getOurFleetMap()[Y][X].setLabel('+');
            App.BATTLE_FIELD_CONTROLLER.setReplica(Player.getRandomReplica(Replicas.CPU_MISSED));
            App.BATTLE_FIELD_CONTROLLER.setComicsImage(ReplicasImage.CALM_TALK);
            missed(human, CPU, Y, X);
            lastZone = new Memory(Y, X);
            CPU.setTurnCounter(CPU.getTurnCounter() + 1);
            App.setNexTurn();
        }
        return true;
    }

    public Memory getCoordinate() {
        int zoneIndex = Tools.getRandomNumber(0, zones.size() - 1);//Получаем рандомную зону координат.
        int memoryIndex = getRandomNumber(0, zones.get(zoneIndex).size() - 1);//Получаем рандомный индекс ячейки из зоны коордлинат
        Memory mem = zones.get(zoneIndex).get(memoryIndex); //Получаем ячейку из зоны координат.
        zones.get(zoneIndex).remove(memoryIndex);//Удаляем ячейку из зоны
        if (zones.get(zoneIndex).size() < 1) {
            zones.remove(zoneIndex);//Удаляем зону в которой закончились все координаты
        }
        return mem;
    }

    /**
     * Подумать как можно переписать методы с зонами, так чтобы не было повторяющихся методов. Пусть будет только
     * метод, который берет в качестве параметра список Memory с координатами выбирает от туда рандомную координату
     * удаляет её из списка и отдает объект обратно.
     *
     * @return
     */
    public ArrayList<Memory> chooseZone() {
        if (zones.size() > 1) {
            ArrayList<Memory> zone = zones.get(0);
            for (int i = 1; i < zones.size(); i++) {
                zone = Tools.compareMax(zone, zones.get(i));
            }
            return zone;
        } else {
            return zones.get(0);
        }
    }

    public Memory getCoordinate(ArrayList<Memory> zone) {
        int memoryIndex = getRandomNumber(0, zone.size() - 1);//Получаем рандомный индекс ячейки из зоны коордлинат
        Memory mem = zone.get(memoryIndex); //Получаем ячейку из зоны координат.
        zone.remove(memoryIndex);//Удаляем ячейку из зоны
        if (zone.size() < 1) {
            zones.remove(zone);//Удаляем зону в которой закончились все координаты
        }
        return mem;
    }

    public Memory getCoordinateFromList(ArrayList<Memory> listOfCoordinates) {
        int memoryIndex = getRandomNumber(0, listOfCoordinates.size() - 1);//Получаем рандомный индекс ячейки.
        Memory mem = listOfCoordinates.get(memoryIndex); //Получаем ячейку из списка координат.
        listOfCoordinates.remove(memoryIndex);//Удаляем координату из списка
        return mem;
    }


    @Override
    public void missed(Player enemy, Player self, int Y, int X) {
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.DOT);
        enemy.getOurFleetMap()[Y][X].setLabel('+');
        removeCoordinate(Y, X);
        removeCoordinate(Y, X, intelCoordinates1);
        removeCoordinate(Y, X, intelCoordinates2);
    }

    @Override
    public void theShipIsDamaged(Ship ship, Player enemy, Player self, int Y, int X) {
        enemy.getOurFleetMap()[Y][X].setLabel('X');
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.RED_CROSS);
        removeCoordinate(Y, X);
        removeCoordinate(Y, X, intelCoordinates1);
        removeCoordinate(Y, X, intelCoordinates2);
        ship.setHp(ship.getHp() - 1);
    }

    @Override
    public void theShipIsDestroyed(Ship ship, Player enemy, Player self) {
        setGreenDotsAround(enemy.getOurFleetMap(), ship);
        setDestroyedShip(ship);
        App.SEA_BATTLE_GAME.playerShipDecrement(enemy);
        App.BATTLE_FIELD_CONTROLLER.stateUpdate();
        App.SEA_BATTLE_GAME.isVictory();
    }

    public void setGreenDotsAround(MapObject[][] map, Ship ship) {
        for (int i = 0; i < ship.getDecks().length; i++) {
            int Y = ship.getDecks()[i].getCoordinateY();
            int X = ship.getDecks()[i].getCoordinateX();
            for (int y = Y - 1; y < Y + 2; y++) {
                for (int x = X - 1; x < X + 2; x++) {
                    if (Tools.isOutOfBoards(map, y, x)) continue;
                    if (map[y][x].getLabel() == '+') continue;
                    if (map[y][x].getLabel() == 'X') continue;
                    if (map[y][x].isShip()) continue;
                    map[y][x].setImage(ImageName.GREEN_DOT);
                    removeCoordinate(y, x);
                    removeCoordinate(y, x, intelCoordinates1);
                    removeCoordinate(y, x, intelCoordinates2);
                }
            }
        }
    }

    public void removeCoordinate(int Y, int X) {
        for (int j = 0; j < zones.size(); j++) {
            for (int k = zones.get(j).size() - 1; k >= 0; k--) {
                if (zones.get(j).get(k).Y == Y && zones.get(j).get(k).X == X) {
                    zones.get(j).remove(k);
                    return;
                }
            }
        }
    }

    public void removeCoordinate(int Y, int X, ArrayList<Memory> listOfCoordinates) {
        for (int i = 0; i < listOfCoordinates.size(); i++) {
            if (listOfCoordinates.get(i).Y == Y && listOfCoordinates.get(i).X == X) {
                listOfCoordinates.remove(i);
                return;
            }
        }
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
