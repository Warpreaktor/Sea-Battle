package com.core.MapObjects;

import com.core.ImageName;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MapObject extends ImageView {
    /**
     * * shipLabel - Не смотря на то что игра имеет графический интерфейс, тем не менее бекэндная часть игры ориентируется
     *  * на эти простые символы, чтобы понимать состояние клетки: "B" = Линкор, L = Крейсер, M = Эсминец, S = Подлодкаж;
     *  * "+" = по клетке уже стреляли, "X" = Корабль поврежденный корабль.
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
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/redCRoss150x150.png"));
                setLabel('X');
                setName("Красный крест");
                break;
            case DOT:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/dot150x150.png"));
                setLabel('+');
                setName("Точка");
                break;
            case WAVE:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/waveminBoard.jpg"));
                setLabel('~');
                setName("Морская волна");
                break;
            case GREEN_DOT:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/dotGreen150x150.png"));
                setLabel('+');
                setName("Зеленая точка");
                break;
            case KRAKEN:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage( new Image("/resources/octopus60x60.png"));
                setLabel('+');
                setName("Кракен");
                break;
            case BATTLESHIP:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/battleship60x60.png"));
                setLabel('L');
                break;
            case GALLEON:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/galleon60x60.png"));
                setLabel('C');
                break;
            case FRIGATE:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/frigate60x60.png"));
                setLabel('D');
                break;
            case SCHOONER:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/schooner60x60.png"));
                setLabel('S');
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

    public abstract boolean isShip();
    public abstract boolean spruting();
}
