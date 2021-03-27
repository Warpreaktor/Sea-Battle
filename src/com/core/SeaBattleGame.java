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
        Player playerCPU = new Player("Адмирал " + Tools.getRandomName());
        playerCPU.setCPU(true);
        App.app.brushTheBattleField(this, App.stage, player1, playerCPU);
        createCPUBattleField(playerCPU);
        player1.shipsOnTheField();
        playerCPU.shipsOnTheField();
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
        this.shootCPU(human, playerCPU);

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
                    mainController.textOutput("Корабль " + enemyShipName + " поврежден!");
                    CPU.getOurFleetMap()[y][x].setCellLabel('X'); //Ставим отмеку у противнка в его карте
                    CPU.getOurFleetMap()[y][x].setRedCross(); //Ставим отмеку у противнка в его карте
                    human.getEnemyFleetMap()[y][x].setCellLabel('X');          //Ставим отметку в своей "вражеской" карте
                    human.getEnemyFleetMap()[y][x].setRedCross();          //Ставим отметку в своей "вражеской" карте
                    CPU.getOurFleetMap()[y][x].setShip(false);
                }
                if (enemyShip.getHp()<=0) {
                    mainController.textOutput("Корабль " + enemyShipName + " уничтожен!");
                    CPU.getOurFleetMap()[y][x].setCellLabel('X'); //Ставим отмеку у противнка в его карте
                    CPU.getOurFleetMap()[y][x].setRedCross(); //Ставим отмеку у противнка в его карте
                    human.getEnemyFleetMap()[y][x].setCellLabel('X');
                    human.getEnemyFleetMap()[y][x].setRedCross();          //Ставим отметку в своей "вражеской" карте
                    CPU.getOurFleetMap()[y][x].setShip(false);
                    CPU.setNumberOfShip(CPU.getNumberOfShip()-1);
                }
                human.setCountOfTurns(human.getCountOfTurns()+1);
            } else {
                mainController.textOutput(human.getName() + " промахнулся!");
                if (human.getEnemyFleetMap()[y][x].getCellLabel()=='X'){

                }else {human.getEnemyFleetMap()[y][x].setCellLabel('+');}
                human.getEnemyFleetMap()[y][x].setDot();          //Ставим точку в своей "вражеской" карте
                human.setCountOfTurns(human.getCountOfTurns()+1);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getLocalizedMessage());
            System.out.println("Некорректный ввод, попробуйте еще раз");
        }
    }
    public void shootCPU(Player playerHuman, Player CPU){
        int y = Tools.getRandomCoordinate();
        int x = Tools.getRandomCoordinate();
        //Делаем проверку. Если компьютер уже стрелял в эту точку, то изменить координаты.
        for (int i = 0; i < CPU.getEnemyFleetMap().length; i++) {
            for (int j = 0; j < CPU.getEnemyFleetMap()[i].length; j++) {
                if (CPU.getEnemyFleetMap()[y][x].getCellLabel()=='+' || CPU.getEnemyFleetMap()[y][x].getCellLabel()=='X'){
                    shootCPU(playerHuman, CPU);
                    return;
                }
            }
        }
        //Повторяющйся код
        //System.out.println("Компьютерный игрок стреляет по координатам Y - " + y + " X - " + x);
        if (playerHuman.getOurFleetMap()[y][x].isShip()) {
            String enemyShipName = playerHuman.getOurFleetMap()[y][x].getShipRef().getName();
            Ship enemyShip = playerHuman.getOurFleetMap()[y][x].getShipRef();
            enemyShip.setHp(enemyShip.getHp()-1);
            if (enemyShip.getHp()>0) {
                //System.out.println("Корабль " + enemyShipName + " поврежден!");
                playerHuman.getOurFleetMap()[y][x].setCellLabel('X'); //Ставим отмеку у противнка в его карте
                playerHuman.getOurFleetMap()[y][x].setRedCross(); //Ставим отмеку у противнка в его карте
                CPU.getEnemyFleetMap()[y][x].setCellLabel('X');
                CPU.getEnemyFleetMap()[y][x].setRedCross();          //Ставим отметку в своей "вражеской" карте
                playerHuman.getOurFleetMap()[y][x].setShip(false);
            }
            if (enemyShip.getHp()<=0) {
                //System.out.println(enemyShipName + " уничтожен!");
                playerHuman.getOurFleetMap()[y][x].setCellLabel('X'); //Ставим отмеку у противнка в его карте
                playerHuman.getOurFleetMap()[y][x].setRedCross(); //Ставим отмеку у противнка в его карте
                CPU.getEnemyFleetMap()[y][x].setCellLabel('X');
                CPU.getEnemyFleetMap()[y][x].setRedCross();
                playerHuman.getOurFleetMap()[y][x].setShip(false);
                playerHuman.setNumberOfShip(playerHuman.getNumberOfShip()-1);
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);;
        } else {
            //System.out.println("Промах");
            if (CPU.getEnemyFleetMap()[y][x].getCellLabel()=='X'){

            }else {
                playerHuman.getOurFleetMap()[y][x].setDot(); //Ставим отмеку у противнка в его карте
                CPU.getEnemyFleetMap()[y][x].setCellLabel('+');
                CPU.getEnemyFleetMap()[y][x].setDot();
            }
            CPU.setCountOfTurns(CPU.getCountOfTurns()+1);;
        }
    }

}
