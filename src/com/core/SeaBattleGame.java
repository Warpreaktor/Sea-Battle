package com.core;

import com.core.MapObjects.MapCell;
import com.core.MapObjects.MapObject;
import com.core.Players.Difficult;
import com.core.Players.Player;
import com.core.Ships.Ship;
import front.App;

public class SeaBattleGame {
    private static int SIZE = 10;
    private static int totalShips = 0;
    private Player human;
    private Player AI;
    private int krakenChance = 0; //Количество нападений спрута на корабли перед началом боя.
    private Difficult difficulty;
    public boolean music = true;

    public void setDifficulty(Difficult difficulty) {
        this.difficulty = difficulty;
    }

    public Difficult getDifficulty() {
        return difficulty;
    }

    public int getKrakenChance() {
        return krakenChance;
    }

    public Player getHuman() {
        return human;
    }

    public Player getAI() {
        return AI;
    }

    public void setAI(Player AI) {
        this.AI = AI;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static int getTotalShips() {
        return totalShips;
    }

    public static void setTotalShips(int ships) {
        totalShips = ships;
    }

    public SeaBattleGame(Difficult difficulty, Player player) {
        human = player;
        System.out.println("difficult = " + difficulty);

        this.difficulty = difficulty;
    }

    public static void createCPUBattleField(Player player) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                MapObject cell1 = new MapCell(y, x);
                player.setGameCellToOurFleetMap(cell1, y, x);
            }
        }
    }

    public static void setGreenDotsAround(MapObject[][] map, Ship ship) {
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
                }
            }
        }
    }

    /**
     * Проверка на победу.
     * @return
     */
    public boolean isVictory(){
        if (human.getNumberOfShip() == 0) {
            App.getAPP().brushTheLooseScreen(AI);
            return true;
        } else if (AI.getNumberOfShip() == 0) {
            App.APP.brushTheVictroryScreen();
            return true;
        }
        return false;
    }

    public void playerShipIncrement(Player owner) {
        owner.setNumberOfShip(owner.getNumberOfShip() + 1);
        this.setTotalShips(this.getTotalShips() + 1);
    }

    public void playerShipDecrement(Player owner) {
        owner.setNumberOfShip(owner.getNumberOfShip() - 1);
        this.setTotalShips(this.getTotalShips() - 1);
    }

    /**
     * Метод размещает на карте Player спрутов в количестве quantity.
     *
     * @param player - игрок которому нужно подсунуть спрутов на его карту.
     */
    public void krakenAtack(Player player) {
        int Y = Tools.getRandomCoordinate();
        int X = Tools.getRandomCoordinate();
        if (player.getOurFleetMap()[Y][X].spruting()) {
            player.getEnemyFleetMap()[Y][X].setLabel('X');
            System.out.println("корабль " + player.getOurFleetMap()[Y][X].getName() + " был атакован " +
                    "гигантским спрутом и уничтожен");
            if (player.isCPU()) {
                human.getEnemyFleetMap()[Y][X].setImage(ImageName.KRAKEN);
            } else {
                AI.getEnemyFleetMap()[Y][X].setImage(ImageName.KRAKEN);
            }
        }
    }

    public void event() {
        if (Tools.getRandomNumber(1, 100) <= App.SEA_BATTLE_GAME.getKrakenChance()) {
            App.SEA_BATTLE_GAME.krakenAtack(App.SEA_BATTLE_GAME.getHuman());
            System.out.println("Вас атаковал КРАКЕН!");
        }
    }
}
