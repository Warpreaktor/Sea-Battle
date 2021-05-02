package front;

import com.core.*;
import com.core.Players.Difficult;
import com.core.Players.Human;
import com.core.Players.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
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
    public LooseScreenController looseScreenController;
    private static boolean isHumanTurn = true;
    public static MusicPlayer musicPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    public static App getAPP() {
        return APP;
    }

    @Override
    public void start(Stage stage) throws Exception {
       gameInit(stage);
       //brushTheLooseScreen(new CPU(Difficult.EASY)); //удалить
       brushTheVictroryScreen();//удалить
       //brushStartMenu(); //вернуть
    }

    private final void gameInit(Stage stage){
        Player human = new Human();
        portraitsInit();
        SEA_BATTLE_GAME = new SeaBattleGame(Difficult.NORMAL, human);
        STAGE = stage;
        STAGE.setResizable(false);
//        if (musicPlayer != null && musicPlayer.isPlaying()) musicPlayer.stopPlaying();
        musicPlayer = new MusicPlayer();
        musicPlayer.play(musicPlayer.getMusicTracks()[0]);
    }

    public final void restartGame() throws IOException {
        Player human = new Human();
        SEA_BATTLE_GAME = new SeaBattleGame(Difficult.NORMAL, human);
        brushStartMenu();
    }

    /**
     * Перезапуск игры после победы игрока. Игрок остается прежний, то же имя и картинка,
     * но верфь наполняется новыми кораблями.
     * @param dif
     * @throws IOException
     */
    public final void restartGame(Difficult dif) throws IOException {
        Player human = new Human();
        human.setName(App.SEA_BATTLE_GAME.getHuman().getName());
        human.setPortrait(App.SEA_BATTLE_GAME.getHuman().getPortrait());
        SEA_BATTLE_GAME = new SeaBattleGame(dif, human);
        brushShipSettingMenu();
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
        try {
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
        SHIP_SETTING_CONTROLLER = new ShipSettingController();
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
            brushTheErrorMessage("При попытке отрисовать экран победы, произошло какое-то дерьмо");
        }
    }

    public static void brushTheErrorMessage(String message) {
        Stage stage = new Stage();
        TextArea error = new TextArea();
        error.setFont(new Font(16));
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

    public void brushTheLooseScreen(Player winner) {
        LooseScreenController looseScreenController = new LooseScreenController();
        try {
            STAGE.setX(600);
            STAGE.setY(200);
            Parent root = FXMLLoader.load(getClass().getResource("LooseScreen.fxml"));
            looseScreenController.setText("Вас победил " + winner.getName());
            Group group = new Group(root, looseScreenController.getText());
            Scene scene = new Scene(group);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            STAGE.setScene(scene);
            STAGE.show();
        } catch (IOException e) {
            e.printStackTrace();
            brushTheErrorMessage("При попытке отрисовать экран поражения, произошло какое-то дерьмо");
        }
    }

    public static boolean getIsHumanTurn() {
        return isHumanTurn;
    }

    public static void setNexTurn() {
        App.isHumanTurn = !isHumanTurn;
    }

}
