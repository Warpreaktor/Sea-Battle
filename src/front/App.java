package front;

import com.core.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

public class App extends Application {
    public static final App APP = new App();
    public static Stage STAGE;
    public static Image[] allPortraits;
    public static SeaBattleGame SEA_BATTLE_GAME;
    public static BattleFieldController BATTLE_FIELD_CONTROLLER;
    public static ShipSettingController SHIP_SETTING_CONTROLLER;
    private static boolean isHumanTurn = true;
    public static MyMediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    public static App getAPP() {
        return APP;
    }

    @Override
    public void start(Stage stage) throws Exception {
       gameInit(stage);
       brushTheVictroryScreen();//удалить
       //brushStartMenu(); //вернуть
    }

    private final void gameInit(Stage stage){
        this.STAGE = stage;
        this.STAGE.setResizable(false);
        portraitsInit();
        this.SEA_BATTLE_GAME = new SeaBattleGame();
        mediaPlayer = new MyMediaPlayer(new File("src/resources/music/SeaBattle_Main_Theme.wav"));
        mediaPlayer.play();
    }

    public final void resetGame() throws IOException {
        this.SEA_BATTLE_GAME = new SeaBattleGame();
        mediaPlayer.stopPlaying();
        mediaPlayer = new MyMediaPlayer(new File("src/resources/music/SeaBattle_Main_Theme.wav"));
        mediaPlayer.play();
        brushStartMenu();
    }

    public static Image[] getAllPortraits() {
        return allPortraits;
    }

    public void portraitsInit(){
        allPortraits = new Image[10];
        String portraitPath = "/resources/persons/pirate";
        for (int i = 0; i < allPortraits.length; i++) {
            allPortraits[i] = new Image(portraitPath + (i) + ".png");
        }
    }

    public final void brushStartMenu() throws IOException {
        SHIP_SETTING_CONTROLLER= new ShipSettingController();
        try {
            STAGE.setX(300);
            STAGE.setY(100);
            Parent root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
            Group group = new Group(root);
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            STAGE.setScene(scene);
            STAGE.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static void brushShipSettingMenu() {
        STAGE.setX(300);
        STAGE.setY(0);
        Scene scene = new Scene(SHIP_SETTING_CONTROLLER.getMainPanel());
        STAGE.setScene(scene);
        STAGE.show();
    }

    /**
     * В этом методе отображается вся динамическая информация и вьюшки на поле.
     */
    public final static void brushTheBattleField() {
        BATTLE_FIELD_CONTROLLER = new BattleFieldController();
        STAGE.setX(300);
        STAGE.setY(0);
        //Отрисовка поля с вражеским флотом

        Scene scene = new Scene(BATTLE_FIELD_CONTROLLER.anchorPane);
        STAGE.setScene(scene);
        STAGE.show();
    }

    public final void brushTheVictroryScreen() {
        VictoryScreenController victoryScreenController = new VictoryScreenController();
        try {
            STAGE.setX(600);
            STAGE.setY(200);
            Parent root = FXMLLoader.load(getClass().getResource("VictoryScreen.fxml"));
            Group group = new Group(root);
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            STAGE.setScene(scene);
            STAGE.show();
        } catch (IOException e) {
            e.printStackTrace();
            brushTheErrorMessage("Файл \"Victory.fxml\" поврежден или отсутствует");
        }
    }

    public static void brushTheErrorMessage(String message) {
        Stage stage = new Stage();
        TextArea error = new TextArea();
        error.setText(message);
        Button button = new Button();
        button.setText("OK");
        button.setLayoutX(200);
        button.setLayoutY(100);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        Group group = new Group(error, button);
        stage.setScene(new Scene(group));
        stage.show();
    }

    public static void brushTheVictoryMessage(String message) {
        Stage stage = new Stage();
        TextArea error = new TextArea();
        error.setText(message);
        Button button = new Button();
        button.setText("OK");
        button.setLayoutX(200);
        button.setLayoutY(100);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        Group group = new Group(error, button);
        stage.setScene(new Scene(group));
        stage.show();
    }

    public static boolean getIsHumanTurn() {
        return isHumanTurn;
    }

    public static void setNexTurn() {
        App.isHumanTurn = !isHumanTurn;
    }

}
