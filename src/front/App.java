package front;

import com.core.SeaBattleGame;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        //Подгружаем файл сгенерированный SceneBuilder
        Parent root = FXMLLoader.load(getClass().getResource("BattleField.fxml"));
        Scene scene = new Scene(root);

        for (int i = 0; i < SeaBattleGame.getSIZE(); i++) {
            Rectangle rectangle = new Rectangle();

        }

        //подгружаем файл со стилями
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        //Размещение сцены на главной стейдже
        stage.setScene(scene);
        stage.show();
    }
}
