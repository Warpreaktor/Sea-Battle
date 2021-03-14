package com.core;

import front.Game;
import front.Color;

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
        createGame();
    }
    private void createGame() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                //setCellValue(x,y,"");
                gameField[y][x].setCellLabel('R');
                boolean isMine = getRandomNumber(15) < 1;
                if (isMine) {
                    System.out.println("+минка");
                    totalShips++;
                }
                gameField[y][x] = new GameCell();
                setCellColor(x, y, Color.WHITESMOKE);
            }
        }
        //countMineNeighbors();
        //countFlags = countMinesOnField;
    }


    public static void main(String[] args) {

        //Tests.mainTest();
        //Player player1 = new Player("Warper");
        //Player playerCPU = new Player("CPU");
        //playerCPU.setCPU(true);
        //player1.shipsOnTheField();
        //playerCPU.shipsOnTheField();
        //player1.brushTheMap();
        //battle(player1, playerCPU);
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



}
