package com.core;

import front.App;
import front.Game;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.InputStream;

public class SeaBattleGame extends App {
    private Pane paneLeft = new Pane();
    private Pane paneRight = new Pane();
    private int width;
    private int height;
    private static int cellSize;
    private StackPane[][] cells;
    private boolean showGrid = true;
    private boolean showCoordinates = false;
    private boolean showTV = true;

    private static int SIZE = 10;
    private static int totalShips = 0;
    private static GameCell[][] gameField = new GameCell[SIZE][SIZE];

    public static int getSIZE() {
        return SIZE;
    }

    public static int getTotalShips() {
        return totalShips;
    }

    public static void setTotalShips(int ships) {
        totalShips = ships;
    }

    public SeaBattleGame() {
    }

    public static void main(String args[]) {
        launch(args);
    }




    //@Override
    public void initialize() {
        setScreenSize(SIZE, SIZE);
        Player player1 = new Player("Warper");
        Player playerCPU = new Player("CPU");
        playerCPU.setCPU(true);
        createBattleField(player1);
        //createBattleField(playerCPU);
        System.out.println("Initialize");
    }


    private void createBattleField(Player player) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                setCellValue(x, y, "fuck");
                gameField[y][x] = new GameCell();
                setCellColor(x, y, javafx.scene.paint.Color.RED);
                //player.setGameCellToOurFleetMap(new GameCell(), y, x);
                //player.setGameCellToEnemyFleetMap(new GameCell(), y, x);
            }
        }
    }


    public static void battle(Player player1, Player player2) {
        while (player1.getNumberOfShip() > 0 && player2.getNumberOfShip() > 0) {
            player1.shoot(player2);
            player2.shoot(player1);
            player1.brushTheMap();
        }
        if (player1.getNumberOfShip() == 0) {
            System.out.println("Победил игрок " + player2.getName());
        } else {
            System.out.println("Победил игрок " + player1.getName());
        }
    }

    private Parent createContent(Pane pane) {
        pane.setPrefSize((double) (this.width * cellSize + 250), (double) (this.height * cellSize + 110 + 140));

        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                ObservableList<Node> children = this.cells[y][x].getChildren();
                Rectangle cell;
                if (this.showGrid && children.size() > 0) {
                    cell = (Rectangle) children.get(0);
                    cell.setWidth((double) (cellSize - 1));
                    cell.setHeight((double) (cellSize - 1));
                    cell.setStroke(this.toFXColor(front.Color.BLACK));
                }

                if (this.showCoordinates && children.size() > 2) {
                    Text coordinate = (Text) children.get(2);
                    coordinate.setFont(Font.font((double) cellSize * 0.15D));
                    StackPane.setAlignment(coordinate, Pos.TOP_LEFT);
                    coordinate.setText(x + " - " + y);
                }

                if (children.size() > 0) {
                    cell = (Rectangle) children.get(0);
                    cell.setWidth((double) cellSize);
                    cell.setHeight((double) cellSize);
                    this.cells[y][x].setLayoutX((double) (x * cellSize + 125));
                    this.cells[y][x].setLayoutY((double) (y * cellSize + 110));
                    pane.getChildren().add(this.cells[y][x]);
                }
            }
        }
        return pane;
    }

    private javafx.scene.paint.Color toFXColor(front.Color color) {
        if (color == front.Color.NONE) {
            return javafx.scene.paint.Color.TRANSPARENT;
        } else {
            return color != null ? javafx.scene.paint.Color.valueOf(color.name()) : javafx.scene.paint.Color.BLACK;
        }
    }

    private void createBorderImage() {
        InputStream inputStream = Game.class.getResourceAsStream("/resources/sea.jpg");
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth((double) (this.width * cellSize + 250));
        imageView.setFitHeight((double) (this.height * cellSize + 110 + 140));
        this.paneLeft.getChildren().add(imageView);
        this.paneRight.getChildren().add(imageView);
    }

    public void setScreenSize(int width, int height) {
        this.width = width < 3 ? 3 : (Math.min(width, 100));
        this.height = height < 3 ? 3 : (Math.min(height, 100));
        cellSize = Math.min(800 / this.width, 600 / this.height);
        this.cells = new StackPane[this.height][this.width];

        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                this.cells[y][x] = new StackPane(new Node[]{new Rectangle(), new Text(), new Text()});
            }
        }

    }

    public void setCellValue(int x, int y, String value) {
        ObservableList<Node> children = this.cells[y][x].getChildren();
        if (children.size() > 1) {
            Text text = (Text) children.get(1);
            if (text.getText().equals(value)) {
                return;
            }

            if (value.length() <= 4) {
                double fontSize = (double) cellSize * 0.4D;
                text.setFont(Font.font(fontSize));
            } else {
                int fontSize = cellSize / value.length();
                text.setFont(Font.font((double) fontSize));
            }

            text.setText(value);
        }

    }

    public void setCellColor(int x, int y, javafx.scene.paint.Color color) {
        if (color != null) {
            ObservableList<Node> children = this.cells[y][x].getChildren();
            ((Rectangle) children.get(0)).setFill(javafx.scene.paint.Color.BLUE);

        }
    }

}
