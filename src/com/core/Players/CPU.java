package com.core.Players;

public class CPU extends Player{
    Difficult difficult = Difficult.EASY;
    public boolean isCPU() {
        return true;
    }
    public CPU(Difficult difficult) {
        super();
        this.difficult = difficult;
    }
}
