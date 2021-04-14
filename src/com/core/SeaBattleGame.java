package com.core;
import front.App;
import front.MainController;

public class SeaBattleGame {
    public MainController mainController;
    private static int SIZE = 10;
    private static int totalShips = 0;
    private final Player human = new Player();
    private Player CPU = new Player();

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
        mainController = App.mainController;
        CPU.setName("Адмирал " + Tools.getRandomName());
        CPU.setCPU(true);
        createCPUBattleField(CPU);
        //human.shipsOnTheField();
        CPU.shipsOnTheField();
    }

    private void createCPUBattleField(Player player) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                player.setGameCellToOurFleetMap(new GameCell(), y, x);
                player.setGameCellToEnemyFleetMap(new GameCell(), y, x);
            }
        }
    }

    public void battle(Player human, Player playerCPU, int Y, int X) {
        if (human.getEnemyFleetMap()[Y][X].getLabel() == '+' || human.getEnemyFleetMap()[Y][X].getLabel() == 'X'){
            return;
        }
        this.shoot(human, playerCPU, Y, X);
            //После хода игрока ожидаем нажатие кнопки next turn игроком.
        isVictory();

    }
    public void shoot(Player human, Player CPU, int Y, int X) {
        try {
            if (CPU.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("Ship")) {
                String enemyShipName = CPU.getOurFleetMap()[Y][X].getName();
                Ship enemyShip = (Ship)CPU.getOurFleetMap()[Y][X];
                enemyShip.setHp(enemyShip.getHp()-1);
                if (enemyShip.getHp()>0) {
                    mainController.textOutput("Корабль " + enemyShipName + " поврежден!");
                    theShipIsDamaged(CPU, human, Y, X);
                }
                if (enemyShip.getHp()<=0) {
                    mainController.textOutput("Корабль " + enemyShipName + " уничтожен!");
                    CPU.getOurFleetMap()[Y][X].setLabel('X'); //Ставим отмеку у противнка в его карте
                    CPU.getOurFleetMap()[Y][X] = new GameCell(Y, X, ImageName.RED_CROSS) {
                    }; //Ставим отмеку у противнка в его карте
                    human.getEnemyFleetMap()[Y][X].setLabel('X');
                    human.getEnemyFleetMap()[Y][X] = new GameCell(Y, X, ImageName.RED_CROSS);          //Ставим отметку в своей "вражеской" карте
                    CPU.setNumberOfShip(CPU.getNumberOfShip()-1);
                }
                human.setCountOfTurns(human.getCountOfTurns()+1);
            } else {
                mainController.textOutput(human.getName() + " промахнулся!");
                if (human.getEnemyFleetMap()[Y][X].getLabel()=='X'){

                }else {human.getEnemyFleetMap()[Y][X].setLabel('+');}
                human.getEnemyFleetMap()[Y][X] = new GameCell(Y, X, ImageName.DOT);          //Ставим точку в своей "вражеской" карте
                human.setCountOfTurns(human.getCountOfTurns()+1);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getLocalizedMessage());
            System.out.println("Некорректный ввод, попробуйте еще раз");
        }
    }
    public void shootCPU(){
        int Y = Tools.getRandomCoordinate();
        int X = Tools.getRandomCoordinate();
        //Делаем проверку. Если компьютер уже стрелял в эту точку, то изменить координаты.
        //for (int i = 0; i < CPU.getEnemyFleetMap().length; i++) {
        //    for (int j = 0; j < CPU.getEnemyFleetMap()[i].length; j++) {
        if (CPU.getEnemyFleetMap()[Y][X].getLabel() == '+' || CPU.getEnemyFleetMap()[Y][X].getLabel()=='X'){
            shootCPU();
            return;
        }
        //    }
        isVictory();
        //}
        if (human.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("Ship")) {
            String enemyShipName = human.getOurFleetMap()[Y][X].getName();
            Ship enemyShip = (Ship)human.getOurFleetMap()[Y][X];
            enemyShip.setHp(enemyShip.getHp()-1);
            if (enemyShip.getHp()>0) {
                mainController.textOutput("Корабль " + enemyShipName + " поврежден!");  //Выводим на экран сообщение
                theShipIsDamaged(human, CPU, Y, X);
            }
            if (enemyShip.getHp()<=0) {
                mainController.textOutput("Корабль " + enemyShipName + " уничтожен!");
                human.getOurFleetMap()[Y][X].setLabel('X');         //Ставим отмеку у противнка в его карте
                human.getOurFleetMap()[Y][X] = new GameCell(Y, X, ImageName.RED_CROSS);             //Ставим отмеку у противнка в его карте
                CPU.getEnemyFleetMap()[Y][X].setLabel('X');
                CPU.getEnemyFleetMap()[Y][X] = new GameCell(Y, X, ImageName.RED_CROSS);
                human.setNumberOfShip(human.getNumberOfShip()-1);
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);;
        } else {
            mainController.textOutput(CPU.getName() + " стреляет и промахивается.");
            if (CPU.getEnemyFleetMap()[Y][X].getLabel()=='X'){

            }else {
                human.getOurFleetMap()[Y][X] = new GameCell(Y, X, ImageName.DOT); //Ставим отмеку у противнка в его карте
                CPU.getEnemyFleetMap()[Y][X].setLabel('+');
                CPU.getEnemyFleetMap()[Y][X] = new GameCell(Y, X, ImageName.DOT);
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);;
        }
    }
    public static void setNeighbors(int Y, int X, Ship ship) {
        for (int y = Y - 1; y <= Y + 1; y++) {
            for (int x = X - 1; x <= X + 1; x++) {
                if (y < 0 || y >= SIZE) {continue;}
                if (x < 0 || x >= SIZE) {continue;}
                String className = ship.getOwner().getOurFleetMap()[y][x].getClass().getSimpleName();
                if (className.equals("Linkor") || className.equals("Cruiser") || className.equals("Destroyer") || className.equals("Submarine")) {continue;}
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

    public void theShipIsDamaged(Player enemy, Player self, int Y, int X){
        enemy.getOurFleetMap()[Y][X].setLabel('X');    //Ставим отмеку в карте противнка
        enemy.getOurFleetMap()[Y][X] = new GameCell(Y, X, ImageName.RED_CROSS);        //Рисуем крест на карте противнка
        self.getEnemyFleetMap()[Y][X].setLabel('X');    //Ставим отметку в своей карте "Радар"
        self.getEnemyFleetMap()[Y][X] = new GameCell(Y, X, ImageName.RED_CROSS);        //Рисуем крест в своей карте "Радар"
    }

    public void manualShipSetting(){

    }
}
