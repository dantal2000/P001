import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI001 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("GUI001_f/View.fxml")),
                    primaryStage.getWidth(), primaryStage.getHeight());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Узнать результат запроса");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
