package GUI001_f;

import GUI001_f.ErrorWindow001.CallError;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

class Model {
    void handleOpenURL(String url, String cookies) {
        try {
            Connector_2 connector = new Connector_2(url, cookies);
            Stage stage = new Stage();
            stage.setTitle(url);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result001_f/View.fxml"));
            Scene scene = new Scene(loader.load());
            GUI001_f.Result001_f.Controller controller = loader.getController();
            stage.setScene(scene);
            controller.printArea(connector.getResult());
            stage.show();
        } catch (MalformedURLException e) {
            CallError.CallUrlException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleShowHeaders(String url, String cookies) {
        try {
            Connector_2 connector = new Connector_2(url, cookies);
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result002_f/View.fxml"));
            Scene scene = new Scene(loader.load());

            GUI001_f.Result002_f.Controller controller = loader.getController();
            connector.getResponseParams().forEach(controller::addData);

            stage.setScene(scene);
            stage.show();
        } catch (MalformedURLException e) {
            CallError.CallUrlException();
        } catch (IOException e) {
            CallError.Call(e.getMessage());
        }
    }


    String receiveCookies(String url, String cookies) throws URLError {
        try {
            return new Connector_2(url, cookies).recieveCookies();
        } catch (MalformedURLException e) {
            throw new URLError();
        }
    }
}
