package com.core;
import com.core.GameObjects.MapCell;
import com.core.GameObjects.MapObject;
import com.core.Players.CPU;
import com.core.Players.Difficult;
import com.core.Players.Human;
import com.core.Players.Player;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import front.App;

import java.io.IOException;

public class SeaBattleGame {
    private static int SIZE = 10;
    private static int totalShips = 0;
    private final Player human = new Human();
    private final Player CPU = new CPU(Difficult.EASY);
    private int krakenChance = 20; //Количество нападений спрута на корабли перед началом боя.

    public int getKrakenChance() {
        return krakenChance;
    }

    public Player getHuman() {
        return human;
    }
    public Player getCPU() {
        return CPU;
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

    public SeaBattleGame() {
        CPU.setName(CPU.getRandomName());
    }

    public static void createCPUBattleField(Player player) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                MapObject cell1 = new MapCell(y, x);
                MapObject cell2 = new MapCell(y, x);
                player.setGameCellToOurFleetMap(cell1, y, x);
                player.setGameCellToEnemyFleetMap(cell2, y, x);
            }
        }
    }



    public static void setGreenDotsAround(MapObject[][] map, Ship ship) {
        for (int i = 0; i < ship.getDecks().length; i++) {
            int Y = ship.getDecks()[i].getCoordinateY();
            int X = ship.getDecks()[i].getCoordinateX();
            for (int y = Y - 1; y < Y + 2; y++) {
                for (int x = X - 1; x < X + 2; x++) {
                    if(!Tools.isOutOfBoards(map, y, x)) continue;
                    if (map[y][x].isShip()) continue;
                    if (map[y][x].getLabel()=='X') continue;
                    map[y][x].setImage(ImageName.GREEN_DOT);
                }
            }
        }
    }
    public void isVictory() throws IOException {
        if (human.getNumberOfShip() == 0) {
            App.brushTheVictoryMessage("Победил " + CPU.getName());
        } else
            if (CPU.getNumberOfShip() == 0){
                App.APP.brushTheVictroryScreen();
        }
    }

    public void theShipIsDamaged(Ship ship, Player enemy, Player self, int Y, int X){
        enemy.getOurFleetMap()[Y][X].setLabel('X');                 //Ставим отмеку в карте противнка
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.RED_CROSS); //Рисуем крест на карте противнка
        self.getEnemyFleetMap()[Y][X].setLabel('X');                //Ставим отметку в своей карте "Радар"
        self.getEnemyFleetMap()[Y][X].setImage(ImageName.RED_CROSS);//Рисуем крест в своей карте "Радар"
        ship.setHp(ship.getHp()-1);

    }
    public void theShipIsDestroyed(Ship ship, Player enemy, Player self){
        playerShipDecrement(enemy);
        setGreenDotsAround(self.getEnemyFleetMap(), ship);
        setGreenDotsAround(enemy.getOurFleetMap(), ship);
        App.BATTLE_FIELD_CONTROLLER.stateUpdate();
    }
    public void missed(Player enemy, Player self, int Y, int X){
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.DOT);
        self.getEnemyFleetMap()[Y][X].setLabel('+');
        self.getEnemyFleetMap()[Y][X].setImage(ImageName.DOT);
    }

    public void playerShipIncrement(Player owner){
        owner.setNumberOfShip(owner.getNumberOfShip()+1);
        this.setTotalShips(this.getTotalShips() + 1);
    }
    public void playerShipDecrement(Player owner){
        owner.setNumberOfShip(owner.getNumberOfShip() - 1);
        this.setTotalShips(this.getTotalShips() - 1);
    }

    /**
     * Метод размещает на карте Player спрутов в количестве quantity.
     * @param player - игрок которому нужно подсунуть спрутов на его карту.
     */
    public void krakenAtack(Player player){
            int Y = Tools.getRandomCoordinate();
            int X = Tools.getRandomCoordinate();
            if(player.getOurFleetMap()[Y][X].spruting()){
                player.getEnemyFleetMap()[Y][X].setLabel('X');
                System.out.println("корабль " + player.getOurFleetMap()[Y][X].getName() + " был атакован " +
                        "гигантским спрутом и уничтожен");
                if (player.isCPU()) {
                    human.getEnemyFleetMap()[Y][X].setImage(ImageName.KRAKEN);
                }else {
                    CPU.getEnemyFleetMap()[Y][X].setImage(ImageName.KRAKEN);
                }
            }
    }
    public void event(){
        if(Tools.getRandomNumber(1, 100) <= App.SEA_BATTLE_GAME.getKrakenChance()){
            App.SEA_BATTLE_GAME.krakenAtack(App.SEA_BATTLE_GAME.getHuman());
            System.out.println("Вас атаковал КРАКЕН!");
        }
    }
}
