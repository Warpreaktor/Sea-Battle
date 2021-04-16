package com.core;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import front.App;
import front.MainController;

public class SeaBattleGame {
    private static int SIZE = 10;
    private static int totalShips = 0;
    private final Player human = new Player();
    private final Player CPU = new Player();

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

    public void battle(Player human, Player playerCPU, int Y, int X) {
        if (human.getEnemyFleetMap()[Y][X].getLabel() == '+' || human.getEnemyFleetMap()[Y][X].getLabel() == 'X'){
            return;
        }
        this.shoot(Y, X);
            //После хода игрока ожидаем нажатие кнопки next turn игроком.
        isVictory();

    }
    public void shoot(int Y, int X) {
        System.out.println(App.seaBattleGame.getCPU().getOurFleetMap()[Y][X].getName());
        System.out.println(App.seaBattleGame.getCPU().getOurFleetMap()[Y][X].getClass().getSimpleName());
        try {
            if (CPU.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
                String enemyShipName = CPU.getOurFleetMap()[Y][X].getName();
                DeckOfShip enemyShip = (DeckOfShip)CPU.getOurFleetMap()[Y][X];
                if (enemyShip.getHp()>0) {
                    App.mainController.textOutput("Корабль " + enemyShipName + " поврежден!");
                    theShipIsDamaged(enemyShip.getShipOwner(), CPU, human, Y, X);
                }
                if (enemyShip.getHp()<=0) {
                    App.mainController.textOutput("Корабль " + enemyShipName + " уничтожен!");
                    theShipIsDestroyed(enemyShip.getShipOwner(), CPU, human, Y, X);
                }
            } else {
                App.mainController.textOutput(human.getName() + " промахнулся!");
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
                App.mainController.textOutput("Корабль " + enemyShipName + " поврежден!");  //Выводим на экран сообщение
                theShipIsDamaged(enemyShip.getShipOwner(), human, CPU, Y, X);
            }
            if (enemyShip.getHp()<=0) {
                App.mainController.textOutput("Корабль " + enemyShipName + " уничтожен!");
                theShipIsDestroyed(enemyShip.getShipOwner(), human, CPU, Y, X);
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);;
        } else {
            App.mainController.textOutput(CPU.getName() + " стреляет и промахивается.");
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
                //Исправить проверку на DeckOfShip
                ship.getOwner().getOurFleetMap()[y][x].setLabel('0');
            }
        }
    }
    public void isVictory(){
        if (human.getNumberOfShip() == 0) {
            App.brushTheVictoryMessage("Победил " + CPU.getName());
            System.out.println("Победил " + CPU.getName());
        } else
            if (CPU.getNumberOfShip() == 0){
                App.brushTheVictoryMessage("Победил " + human.getName());
                System.out.println("Победил " + human.getName());
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
        enemy.setNumberOfShip(enemy.getNumberOfShip()-1);
    }
    public void missed(Player enemy, Player self, int Y, int X){
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.DOT);
        self.getEnemyFleetMap()[Y][X].setLabel('+');
        self.getEnemyFleetMap()[Y][X].setImage(ImageName.DOT);
    }

    public void PlayerShipIncrement(Player owner){
        owner.setNumberOfShip(owner.getNumberOfShip()+1);
        SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() + 1);
    }
    public void PlayerShipDecrement(Player owner){
        owner.setNumberOfShip(owner.getNumberOfShip() - 1);
        SeaBattleGame.setTotalShips(SeaBattleGame.getTotalShips() - 1);
    }
}
