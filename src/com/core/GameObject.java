package com.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject extends ImageView {
    /**
     * label: s - объект Ship часть корабля.
     */
    private char label; //Какой символ отображать на консольной карте поля боя.

    public char getLabel(){
        return this.label;
    }
    public void setLabel(char x){
        this.label = x;
    }

    public abstract String getName();
    public void setRedCross(){

        Image redCross = new Image("/resources/redCRoss150x150.png");
        super.setImage(redCross);
        setLabel('X');
    }

    public void setWave(){
        Image wave = new Image("/resources/waveminBoard.jpg");
        super.setImage(wave);
        setLabel('~');
    }
    public void setDot(){
        Image dot = new Image("/resources/dot150x150.png");
        super.setImage(dot);
        setLabel('+');
    }

    public void setImage(ImageName imageName){
        switch (imageName){
            case RED_CROSS:
                setRedCross();
                break;
            case DOT:
                setDot();
                break;
            case WAVE:
                setWave();
                break;
            case GREEN_DOT:
                Image greenDot = new Image("/resources/dotGreen150x150.png");
                super.setImage(greenDot);
                setLabel('+');
                break;
            case LINKOR:
                super.setImage(new Image("/resources/linkor60x60.png"));
                break;
            case CRUISER:
                super.setImage(new Image("/resources/cruiser60x60.png"));
                break;
            case DESTROYER:
                super.setImage(new Image("/resources/destroyer60x60.png"));
                break;
            case SUBMARINE:
                super.setImage(new Image("/resources/submarine60x60.png"));
                break;
        }
    }

}
