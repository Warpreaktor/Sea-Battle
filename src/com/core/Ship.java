package com.core;

import javafx.scene.image.ImageView;

/**
 * shipSize - Количество занимаемых на поле ячеек кораблеём. 4 - Линкор, 3 - Крейсер(), 2 - Эсминец, 1 - Подлодка
 * shipLabel - Не смотря на то что игра имеет графический интерфейс, тем не менее бекэндная часть игры ориентируется
 * на эти простые символы, чтобы понимать состояние клетки: "B" = Линкор, L = Крейсер, M = Эсминец, S = Подлодкаж;
 * "+" = по клетке уже стреляли, "X" = Корабль поврежден\уничтожен, "0" = соседствующая с кораблем клетка.
 */
public class Ship extends GameCell {
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
        this.name = naming();
    }
    /**
     * @param index - Это номер палубы коробля. Например 3 палубному кораблю нужно трижды вызвать этот метод и передать
     *              в него индекс от 0 до 2 где 0 это начало корабля, а 2 его конец.
     */
    public void shipsOnTheSea(int index, int y, int x){
        this.setCoordinates(index, 0, y);
        this.setCoordinates(index, 1, x);
        this.getOwner().getOurFleetMap()[y][x].setIsShip(true);
        this.getOwner().getOurFleetMap()[y][x].setCellLabel(this.getShipLabel());
        this.getOwner().getOurFleetMap()[y][x] = this;
    }
    public String naming(){
        String name = "Безымянный";
        int randomNum = 1 + (int)(Math.random()*6);
        switch (randomNum) {
            case (1)://сущ муж + прил муж
            name = Tools.adjectivesBookMan[(int) (Math.random() * (Tools.adjectivesBookMan.length - 1))] +
                    " " + Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))];
            break;
            case (2)://сущ жен + прил жен
                name = Tools.adjectivesBookWoman[(int) (Math.random() * (Tools.adjectivesBookWoman.length - 1))] +
                        " " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
            case (3)://сущ сред + спецэффект
                name = Tools.nounsBookIt[(int) (Math.random() * (Tools.nounsBookIt.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (4)://сущ муж + спецэффект
                name = Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (5)://сущ муж + спецэффект
                name = Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))] +
                        " " + Tools.whomBook[(int) (Math.random() * (Tools.whomBook.length - 1))];
                break;
            case (6)://сущ муж + сущ жен
                name = Tools.nounsBookMan[(int) (Math.random() * (Tools.nounsBookMan.length - 1))] +
                        " и " + Tools.nounsBookWoman[(int) (Math.random() * (Tools.nounsBookWoman.length - 1))];
                break;
        }
        return name;
    }
}
