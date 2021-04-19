package com.core;
import com.core.MapObjects.GameCell;
import com.core.MapObjects.GameObject;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import front.App;

import java.io.IOException;

public class SeaBattleGame {
    private static int SIZE = 10;
    private static int totalShips = 0;
    private final Player human = new Player();
    private final Player CPU = new Player();
    private int octopus = 3; //Количество нападений спрута на корабли перед началом боя.

    public int getOctopus() {
        return octopus;
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
        CPU.setName("Адмирал " + Tools.getRandomName());
        CPU.setCPU(true);
    }

    public static void createCPUBattleField(Player player) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                GameObject cell1 = new GameCell(y, x);
                GameObject cell2 = new GameCell(y, x);
                player.setGameCellToOurFleetMap(cell1, y, x);
                player.setGameCellToEnemyFleetMap(cell2, y, x);
            }
        }
    }

    public void battle(int Y, int X) throws IOException {
        if (human.getEnemyFleetMap()[Y][X].getLabel() == '+' || human.getEnemyFleetMap()[Y][X].getLabel() == 'X'){
            return;
        }
        shoot(Y, X);
            //После хода игрока ожидаем нажатие кнопки next turn игроком.
        isVictory();

    }
    public void shoot(int Y, int X) {
        System.out.println(human.getEnemyFleetMap()[Y][X].getLabel());
        try {
            if (CPU.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
                String enemyShipName = CPU.getOurFleetMap()[Y][X].getName();
                DeckOfShip enemyShip = (DeckOfShip)CPU.getOurFleetMap()[Y][X];
                if (enemyShip.getHp()>0) {
                    App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " поврежден!");
                    theShipIsDamaged(enemyShip.getShipOwner(), CPU, human, Y, X);
                }
                if (enemyShip.getHp()<=0) {
                    App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " уничтожен!");
                    theShipIsDestroyed(enemyShip.getShipOwner(), CPU, human, Y, X);
                }
            } else {
                App.BATTLE_FIELD_CONTROLLER.textOutput(human.getName() + " промахнулся!");
                if (human.getEnemyFleetMap()[Y][X].getLabel()=='X'){

                }else {human.getEnemyFleetMap()[Y][X].setLabel('+');}
                missed(CPU, human, Y, X);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            System.out.println("Некорректный ввод, попробуйте еще раз");
        }
    }
    public void shootCPU(){
        int Y = Tools.getRandomCoordinate();
        int X = Tools.getRandomCoordinate();
        if (CPU.getEnemyFleetMap()[Y][X].getLabel() == '+' || CPU.getEnemyFleetMap()[Y][X].getLabel()=='X'){
            shootCPU();
            return;
        }
        if (human.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
            String enemyShipName = human.getOurFleetMap()[Y][X].getName();
            DeckOfShip enemyShip = (DeckOfShip) human.getOurFleetMap()[Y][X];
            if (enemyShip.getHp()>0) {
                App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " поврежден!");  //Выводим на экран сообщение
                theShipIsDamaged(enemyShip.getShipOwner(), human, CPU, Y, X);
            }
            if (enemyShip.getHp()<=0) {
                App.BATTLE_FIELD_CONTROLLER.textOutput("Корабль " + enemyShipName + " уничтожен!");
                theShipIsDestroyed(enemyShip.getShipOwner(), human, CPU, Y, X);
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);;
        } else {
            App.BATTLE_FIELD_CONTROLLER.textOutput(CPU.getName() + " стреляет и промахивается.");
            if (CPU.getEnemyFleetMap()[Y][X].getLabel()=='X'){

            }else {
                missed(human, CPU, Y, X);
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);
        }
    }
    public static void setNeighbors(int Y, int X, Ship ship) {
        for (int y = Y - 1; y <= Y + 1; y++) {
            for (int x = X - 1; x <= X + 1; x++) {
                if (y < 0 || y >= SIZE) {continue;}
                if (x < 0 || x >= SIZE) {continue;}
                String className = ship.getOwner().getOurFleetMap()[y][x].getClass().getSimpleName();
                if (className.equals("DeckOfShip")) {continue;}
                ship.getOwner().getOurFleetMap()[y][x].setLabel('0');
            }
        }
    }
    public void isVictory() throws IOException {
        if (human.getNumberOfShip() == 0) {
            App.brushTheVictoryMessage("Победил " + CPU.getName());
        } else
            if (CPU.getNumberOfShip() == 0){
                App.app.brushTheVictroryScreen();
        }
    }

    public void theShipIsDamaged(Ship ship, Player enemy, Player self, int Y, int X){
        enemy.getOurFleetMap()[Y][X].setLabel('X');                 //Ставим отмеку в карте противнка
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.RED_CROSS); //Рисуем крест на карте противнка
        self.getEnemyFleetMap()[Y][X].setLabel('X');                //Ставим отметку в своей карте "Радар"
        self.getEnemyFleetMap()[Y][X].setImage(ImageName.RED_CROSS);//Рисуем крест в своей карте "Радар"
        ship.setHp(ship.getHp()-1);

    }
    public void theShipIsDestroyed(Ship ship, Player enemy, Player self, int Y, int X){
        enemy.getOurFleetMap()[Y][X].setLabel('X');                  //Ставим отмеку в карте противнка
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.RED_CROSS);  //Рисуем крест на карте противнка
        self.getEnemyFleetMap()[Y][X].setLabel('X');                 //Ставим отметку в своей карте "Радар"
        self.getEnemyFleetMap()[Y][X].setImage(ImageName.RED_CROSS); //Рисуем крест в своей карте "Радар"
        playerShipDecrement(enemy);
    }
    public void missed(Player enemy, Player self, int Y, int X){
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.DOT);
        self.getEnemyFleetMap()[Y][X].setLabel('+');
        self.getEnemyFleetMap()[Y][X].setImage(ImageName.DOT);
    }

    public void playerShipIncrement(Player owner){
        owner.setNumberOfShip(owner.getNumberOfShip()+1);
        System.out.println(owner.getName() + " - " + owner.getNumberOfShip());
        this.setTotalShips(this.getTotalShips() + 1);
    }
    public void playerShipDecrement(Player owner){
        owner.setNumberOfShip(owner.getNumberOfShip() - 1);
        this.setTotalShips(this.getTotalShips() - 1);
    }

    /**
     * Метод размещает на карте Player спрутов в количестве quantity.
     * @param player - игрок которому нужно подсунуть спрутов на его карту.
     * @param quantity - количество спрутов
     */
    public void octopusAtack(Player player, int quantity){
        for (int i = 0; i < quantity; i++) {
            int Y = Tools.getRandomCoordinate();
            int X = Tools.getRandomCoordinate();
            if(player.getOurFleetMap()[Y][X].spruting()){
                playerShipDecrement(player);
                player.getEnemyFleetMap()[Y][X].setLabel('X');
                System.out.println("корабль " + player.getOurFleetMap()[Y][X].getName() + " был атакован " +
                        "гигантским спрутом и уничтожен");
                if (player.isCPU()) {
                    human.getEnemyFleetMap()[Y][X].setImage(ImageName.OCTOPUS);
                }else {
                    CPU.getEnemyFleetMap()[Y][X].setImage(ImageName.OCTOPUS);
                }
            }
        }

    }
}
