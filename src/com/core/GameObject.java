package com.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject extends ImageView {
    /**
     * label: s - объект Ship часть корабля.
     */
    private char label; //Какой символ отображать на консольной карте поля боя.
    private String name;
    private int coordinateY;
    private int coordinateX;

    public char getLabel(){
        return this.label;
    }
    public void setLabel(char x){
        this.label = x;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setImage(ImageName imageName){
        switch (imageName){
            case NULL:
                super.setImage(null);
                break;
            case RED_CROSS:
                Image redCross = new Image("/resources/redCRoss150x150.png");
                super.setImage(redCross);
                setLabel('X');
                setName("Красный крест");
                break;
            case DOT:
                Image dot = new Image("/resources/dot150x150.png");
                super.setImage(dot);
                setLabel('+');
                setName("Точка");
                break;
            case WAVE:
                Image wave = new Image("/resources/waveminBoard.jpg");
                super.setImage(wave);
                setLabel('~');
                setName("Морская волна");
                break;
            case GREEN_DOT:
                Image greenDot = new Image("/resources/dotGreen150x150.png");
                super.setImage(greenDot);
                setLabel('+');
                setName("Зеленая точка");
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

    public int getCoordinateY(){
        return coordinateY;
    }
    public int getCoordinateX(){
        return coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }
}
