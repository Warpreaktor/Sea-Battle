package com.core;

import com.core.Players.CPU;
import com.core.Players.Player;
import com.core.Ships.Ship;
import front.App;

import java.io.IOException;

import static com.core.Tools.getRandomNumber;

public class Tests {
    /**
     * Набор тестов для класса Player
     */
    //Написать тест

    /**
     * Тест запускает игру создав двух компьютерных игроков и рубиться сам с собой проверяя игру.
     */
    //Написать тест

    /**
     * Стреляет сразу по всем клеткам.
     */
    public static void killThemAll() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (App.SEA_BATTLE_GAME.getHuman().shoot(i, j)) continue;
            }
        }
    }


    public static void main(String[] args) throws IOException {

    }
}