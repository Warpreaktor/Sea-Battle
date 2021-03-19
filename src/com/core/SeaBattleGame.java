package com.core;

import front.App;
import front.Game;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.InputStream;

public class SeaBattleGame extends App {
    private Pane paneLeft = new Pane();
    private Pane paneRight = new Pane();
    private int width;
    private int height;
    private static int cellSize;
    private StackPane[][] cells;
    private boolean showGrid = true;
    private boolean showCoordinates = false;
    private boolean showTV = true;

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

    public SeaBattleGame() {
    }

    public static void main(String args[]) {
        launch(args);
    }




    //@Override
    public void initialize() {
        SIZE = 10;
        Player player1 = new Player("Warper");
        Player playerCPU = new Player("CPU");
        playerCPU.setCPU(true);
        createBattleField(player1);
        //createBattleField(playerCPU);
        System.out.println("Initialize");
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
