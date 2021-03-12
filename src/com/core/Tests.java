package com.core;

public class Tests {
    //Тест на
    public static void test1(){
        Player player1 = new Player(Game.getShipsInShipyard());
        Player player2CPU = new Player(Game.getShipsInShipyard());
        Game.initializeGameField();
        Game.shipsOnTheField(player2CPU);
        Tools.brushFullMap();
    }
}
