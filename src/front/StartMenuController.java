package front;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {
    @FXML
    private ComboBox chooseMap;

    ObservableList<String> list  = FXCollections.observableArrayList("10 by 10","20 by 20");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseMap.setItems(list);
        chooseMap.setValue(list.get(0));
    }

    public final void toMainGameUI() throws Exception {
        App.app.brushMainGameUI(App.stage);
    }
}
