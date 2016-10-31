package GUI001_f.ErrorWindow001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by Даниил on 30.10.2016.
 */
public class Controller {
    @FXML
    Button button;
    @FXML
    Label label;
    private Stage currentStage;

    @FXML
    public void initialize() {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> currentStage.close());
    }

    public void setText(String text) {
        label.setText(text);
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
}
