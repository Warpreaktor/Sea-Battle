package com.core;

public class Game {

    private static int SIZE = 10;
    //static GameCell[][] gameField = new GameCell[SIZE][SIZE]; // Возможно тут будет общее игровое поле
    private static int totalShips = 0;

    public static void main(String[] args) {
        //Tests.mainTest();
        Player player1 = new Player("Warper");
        Player playerCPU = new Player("CPU");
        playerCPU.setCPU(true);
        player1.shipsOnTheField();
        playerCPU.shipsOnTheField();
        player1.brushTheMap();
        battle(player1, playerCPU);
    }

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

    public static void setSIZE(int SIZE) {
        Game.SIZE = SIZE;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static void setTotalShips(int ships) {
        totalShips = ships;
    }

    public static int getTotalShips() {
        return totalShips;
    }
}
