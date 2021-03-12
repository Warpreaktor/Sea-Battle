package com.core;

import java.util.Scanner;

public class Player {
    private int countOfTurns = 0;   //Счетчик ходов сделанных игроком.
    private int numberOfShip = 0;   //Итоговое количество кораблей игрока, которое уменьшается по ходу их уничтожения.
    private Ship[] shipyard;           //Массив со списком доступных в начале типов кораблей.

    public void setNumberOfShip(int numberOfShip) {
        this.numberOfShip = numberOfShip;
    }

    public void setShipyard(Ship[] shipyard) {
        this.shipyard = shipyard;
        this.numberOfShip = shipyard.length+1;
    }

    public int getNumberOfShip() {
        return numberOfShip;
    }

    public Ship[] getShipyard() {
        return shipyard;
    }

    public Player(int shipsInShipyard) {
        shipyard = new Ship[shipsInShipyard];

    }

    public void shoot() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите координату X от 0 до " + (Game.getSIZE()-1) + ": ");
            int x = scanner.nextInt();
            System.out.print("Введите координату Y от 0 до " + (Game.getSIZE()-1) + ": ");
            int y = scanner.nextInt();
            if (Game.gameField[y][x].isShip()) {
                System.out.println("УБИИИЛ!");
                Game.gameField[y][x].setCellLabel('X');
                Game.setTotalShips(Game.getTotalShips() - 1);
                countOfTurns++;
            } else {
                System.out.println("Промах");
                Game.gameField[y][x].setCellLabel('+');
                countOfTurns++;
            }
        }
        catch (Exception e){
            System.out.println("Некорректный ввод, попробуйте еще раз");
        }
    }
    public void setCountOfTurns(int countOfTurns) {
        this.countOfTurns = countOfTurns;
    }

    public int getCountOfTurns() {
        return countOfTurns;
    }
}
