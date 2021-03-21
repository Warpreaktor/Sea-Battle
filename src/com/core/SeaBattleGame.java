package com.core;


public class SeaBattleGame {
    private static int SIZE;
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

    public SeaBattleGame(String size) {
        if (size.equals("10 на 10")){
            SIZE = 10;
        }else {
            SIZE = 20;
        }
        Player playerCPU = new Player("CPU");
        playerCPU.setCPU(true);
        //createBattleField(player1);
        //createBattleField(playerCPU);
    }

    private void createBattleField(Player player) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                gameField[y][x] = new GameCell();
                player.setGameCellToOurFleetMap(new GameCell(), y, x);
                player.setGameCellToEnemyFleetMap(new GameCell(), y, x);
            }
        }
    }
    public static void battle(Player player1, Player player2) {
        while (player1.getNumberOfShip() > 0 && player2.getNumberOfShip() > 0) {
            player1.shoot(player2);
            player2.shoot(player1);
            player1.brushTheMap();
        }
        if (player1.getNumberOfShip() == 0) {
            System.out.println("Победил игрок " + player2.getName());
        } else {
            System.out.println("Победил игрок " + player1.getName());
        }
    }
}
