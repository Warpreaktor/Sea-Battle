package com.core;

import com.core.Players.Player;
import front.App;

import java.io.IOException;

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
     *Стреляет сразу по всем клеткам. Предварительно необходимо отключить условие победы.
     */
    public static void killThemAll(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (App.SEA_BATTLE_GAME.getHuman().shoot(i,j)) continue;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
            //System.out.println(Tools.getRandomNumber(-1,10));
            //System.out.println(1 + (int) (Math.random() * 10));
            System.out.println(Tools.getRandomNumber(0,10));
        }
    }

}
