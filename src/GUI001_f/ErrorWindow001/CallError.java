package GUI001_f.ErrorWindow001;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Даниил on 30.10.2016.
 */
public class CallError {
    public static void Call(String message) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(CallError.class.getResource("View.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(loader.load());
                Controller controller = loader.getController();

                String title = "Error";
                stage.setTitle(title);

                controller.setText(message);
                controller.setCurrentStage(stage);

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void CallUrlException() {
        Call("Неверный URL!!!");
    }
}
