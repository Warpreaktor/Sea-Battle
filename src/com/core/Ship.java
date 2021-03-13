package com.core;

public class Ship {
    private final String name;
    private int shipType;        //Количество палуб у корабля
    private char shipLabel;
    private int[][] coordinates; //[y][x]
    private Player owner;
    private int hp;

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public Player getOwner() {
        return owner;
    }


    public void setCoordinates(int string, int column, int coordinate) {
        coordinates[string][column] = coordinate;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipLabel(char shipLabel) {
        this.shipLabel = shipLabel;
    }

    public char getShipLabel() {
        return shipLabel;
    }

    public Ship(int decks, Player owner) {
        this.hp = decks;
        this.shipType = decks;
        this.coordinates = new int[decks][2]; //Создаем пустой массив для хранения в нем таблицы координат [y][x]
        switch (decks){
            case(1): this.shipLabel = 'S';break;
            case(2): this.shipLabel = 'M';break;
            case(3): this.shipLabel = 'L';break;
            case(4): this.shipLabel = 'B';break;
        }
        this.owner = owner;
        this.name = Tools.nounsBook[(int)(Math.random()*(Tools.nounsBook.length-1))] +
                " " + Tools.adjectivesBook[(int)(Math.random()*(Tools.adjectivesBook.length-1))];
    }
}
