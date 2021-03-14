package com.core;

public class Ship {
    private final String name;
    private int shipSize;        //Количество палуб у корабля
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

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    public int getShipSize() {
        return shipSize;
    }

    public void setShipLabel(char shipLabel) {
        this.shipLabel = shipLabel;
    }

    public char getShipLabel() {
        return shipLabel;
    }

    public Ship(int decks, Player owner) {
        this.hp = decks;
        this.shipSize = decks;
        this.coordinates = new int[decks][2]; //Создаем пустой массив для хранения в нем таблицы координат [y][x]
        switch (decks){
            case(1): this.shipLabel = 'S';break;
            case(2): this.shipLabel = 'M';break;
            case(3): this.shipLabel = 'L';break;
            case(4): this.shipLabel = 'B';break;
        }
        this.owner = owner;
        this.name = Tools.adjectivesBook[(int)(Math.random()*(Tools.adjectivesBook.length-1))] +
                " " + Tools.nounsBook[(int)(Math.random()*(Tools.nounsBook.length-1))];
    }
    public void shipsOnTheSea(int index, int y, int x){
        /**
         * @param index - Это индекс строки в двумерном массиве координат корабля.
         */
        this.setCoordinates(index, 0, y);
        this.setCoordinates(index, 1, x);
        this.getOwner().getOurFleetMap()[y][x].setShip(true);
        this.getOwner().getOurFleetMap()[y][x].setCellLabel(this.getShipLabel());
        this.getOwner().getOurFleetMap()[y][x].setShipRef(this);
        this.getOwner().setNumberOfShip(this.getOwner().getNumberOfShip()+1);
    }
}
