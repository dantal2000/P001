package GUI001_f.Result001_f;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    TextArea area;

    @FXML
    public void initialize() {
    }

    public void printArea(String message) {
        area.setText(message);
    }
}
