package com.core;


import front.App;
import front.MainController;

import java.io.IOException;

public class SeaBattleGame {
    public MainController mainController;
    private static int SIZE;
    private static int totalShips = 0;

    public static int getSIZE() {
        return SIZE;
    }
    public static int getTotalShips() {
        return totalShips;
    }
    public static void setTotalShips(int ships) {
        totalShips = ships;
    }

    public SeaBattleGame(String size, String playerName) throws IOException {
        mainController = App.mainController;
        if (size.equals("10 на 10")){
            SIZE = 10;
        }else {
            SIZE = 20;
        }
        Player player1 = new Player(playerName);
        Player playerCPU = new Player("CPU");
        playerCPU.setCPU(true);
        App.app.brushTheBattleField(this, App.stage, player1, playerCPU);
        createCPUBattleField(playerCPU);
        player1.shipsOnTheField();
        playerCPU.shipsOnTheField();
        //battle(player1, playerCPU);
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
        if (human.getEnemyFleetMap()[Y][X].getCellLabel() == '+' || human.getEnemyFleetMap()[Y][X].getCellLabel() == 'X'){
            return;
        }
        this.shoot(human, playerCPU, Y, X);
        playerCPU.shootCPU(human);

///////////////////Тут нужно описать условия победы./////////////////////
        /*if (human.getNumberOfShip() == 0) {
            System.out.println("Победил игрок " + playerCPU.getName());
        } else {
            System.out.println("Победил игрок " + human.getName());
        }*/
    }
    public void shoot(Player human, Player CPU, int y, int x) {
        try {
            if (CPU.getOurFleetMap()[y][x].isShip()) {
                String enemyShipName = CPU.getOurFleetMap()[y][x].getShipRef().getName();
                Ship enemyShip = CPU.getOurFleetMap()[y][x].getShipRef();
                enemyShip.setHp(enemyShip.getHp()-1);
                if (enemyShip.getHp()>0) {
                    //System.out.println("Корабль " + enemyShipName + " поврежден!");
                    mainController.textOutput("Корабль " + enemyShipName + " поврежден!");
                    CPU.getOurFleetMap()[y][x].setCellLabel('X'); //Ставим отмеку у противнка в его карте
                    CPU.getOurFleetMap()[y][x].setRedCross(); //Ставим отмеку у противнка в его карте
                    human.getEnemyFleetMap()[y][x].setCellLabel('X');          //Ставим отметку в своей "вражеской" карте
                    human.getEnemyFleetMap()[y][x].setRedCross();          //Ставим отметку в своей "вражеской" карте
                    CPU.getOurFleetMap()[y][x].setShip(false);
                }
                if (enemyShip.getHp()<=0) {
                    //System.out.println(enemyShipName + " уничтожен!");
                    CPU.getOurFleetMap()[y][x].setCellLabel('X'); //Ставим отмеку у противнка в его карте
                    CPU.getOurFleetMap()[y][x].setRedCross(); //Ставим отмеку у противнка в его карте
                    human.getEnemyFleetMap()[y][x].setCellLabel('X');
                    human.getEnemyFleetMap()[y][x].setRedCross();          //Ставим отметку в своей "вражеской" карте
                    CPU.getOurFleetMap()[y][x].setShip(false);
                    CPU.setNumberOfShip(CPU.getNumberOfShip()-1);
                }
                human.setCountOfTurns(human.getCountOfTurns()+1);
            } else {
                //System.out.println("Промах");
                if (human.getEnemyFleetMap()[y][x].getCellLabel()=='X'){

                }else {human.getEnemyFleetMap()[y][x].setCellLabel('+');}
                human.getEnemyFleetMap()[y][x].setDot();          //Ставим точку в своей "вражеской" карте
                human.setCountOfTurns(human.getCountOfTurns()+1);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getLocalizedMessage());
            //System.out.println("Некорректный ввод, попробуйте еще раз");
        }
    }
}
