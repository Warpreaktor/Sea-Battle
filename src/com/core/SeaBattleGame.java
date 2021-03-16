package com.core;

import front.Color;
import front.Game;

public class SeaBattleGame extends Game {
    private static int SIZE = 10;
    private static int totalShips = 0;
    private static GameCell[][] gameField = new GameCell[SIZE][SIZE];

    public static int getSIZE() {
        return SIZE;
    }

    public static int getTotalShips() {
        return totalShips;
    }
    public static void setTotalShips(int ships) {
        totalShips = ships;
    }

    @Override
    public void initialize() {
        setScreenSize(SIZE, SIZE);
        Player player1 = new Player("Warper");
        Player playerCPU = new Player("CPU");
        playerCPU.setCPU(true);
        createBattleField(player1);
    }

    private void createBattleField(Player player){
        for (int y = 0; y < SeaBattleGame.getSIZE(); y++) {
            for (int x = 0; x < SeaBattleGame.getSIZE(); x++) {
                setCellValue(x,y,"");
                setCellColor(x, y, Color.ANTIQUEWHITE);
                player.setGameCellToOurFleetMap(new GameCell(), y, x);
                player.setGameCellToEnemyFleetMap(new GameCell(), y, x);
            }
        }
        for (int y = 0; y < SeaBattleGame.getSIZE(); y++) {
            for (int x = 0; x < SeaBattleGame.getSIZE(); x++) {
                setCellValue(x,y,"");
                setCellColor(x, y, Color.AZURE);
                player.setGameCellToOurFleetMap(new GameCell(), y, x);
                player.setGameCellToEnemyFleetMap(new GameCell(), y, x);
            }
        }
    }

   // public static void main(String[] args) { // Метод не актуален. Приложение стартует как Application
        //Tests.mainTest();
        //Player player1 = new Player("Warper");
        //Player playerCPU = new Player("CPU");
        //playerCPU.setCPU(true);
        //player1.shipsOnTheField();
        //playerCPU.shipsOnTheField();
        //player1.brushTheMap();
        //battle(player1, playerCPU);
   // }

    public static void battle(Player player1, Player player2) {
        while (player1.getNumberOfShip() > 0 && player2.getNumberOfShip() > 0) {
            player1.shoot(player2);
            player2.shoot(player1);
            player1.brushTheMap();
        }
        if (player1.getNumberOfShip()==0){
            System.out.println("Победил игрок " + player2.getName());
        }else{
            System.out.println("Победил игрок " + player1.getName());
        }
    }



}
