package com.core.GameObjects;

import com.core.ImageName;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MapObject extends ImageView {
    /**
     * * shipLabel - Не смотря на то что игра имеет графический интерфейс, тем не менее бекэндная часть игры ориентируется
     *  * на эти простые символы, чтобы понимать состояние клетки: "B" = Линкор, L = Крейсер, M = Эсминец, S = Подлодкаж;
     *  * "+" = по клетке уже стреляли, "X" = Корабль поврежден\уничтожен, "0" = соседствующая с кораблем клетка.
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
            case LINKOR:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/linkor60x60.png"));
                break;
            case CRUISER:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/cruiser60x60.png"));
                break;
            case DESTROYER:
                this.setFitHeight(60);
                this.setFitWidth(60);
                super.setImage(new Image("/resources/destroyer60x60.png"));
                break;
            case SUBMARINE:
                this.setFitHeight(60);
                this.setFitWidth(60);
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

    public abstract boolean isShip();
    public abstract boolean spruting();
}
