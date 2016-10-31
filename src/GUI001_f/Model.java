package GUI001_f;

import GUI001_f.ErrorWindow001.CallError;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

class Model {
    private final Controller controller;

    Model(Controller controller) {
        this.controller = controller;
    }

    void handleOpenURL(String url, String cookies) {
        try {
            Connector_2 connector = new Connector_2(url, cookies);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result001_f/View.fxml"));
            String result = connector.getResult();
            synchronized (controller.getMonitor()) {
                controller.getMonitor().notifyAll();
            }

            Platform.runLater(() -> {
                try {
                    Stage stage = new Stage();
                    stage.setTitle(url);
                    Scene scene = new Scene(loader.load());
                    GUI001_f.Result001_f.Controller controller = loader.getController();
                    stage.setScene(scene);
                    controller.printArea(result);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (MalformedURLException e) {
            synchronized (controller.getMonitor()) {
                controller.getMonitor().notifyAll();
            }
            CallError.CallUrlException();
        } catch (IOException e) {
            synchronized (controller.getMonitor()) {
                controller.getMonitor().notifyAll();
            }
            CallError.Call(e.getMessage());
        }
    }

    void handleShowHeaders(String url, String cookies) {
        try {
            Connector_2 connector = new Connector_2(url, cookies);
            Map<String, String> map = connector.getResponseParams();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result002_f/View.fxml"));
            synchronized (controller.getMonitor()) {
                controller.getMonitor().notifyAll();
            }

            Platform.runLater(() -> {
                try {
                    Stage stage = new Stage();
                    Scene scene = new Scene(loader.load());
                    GUI001_f.Result002_f.Controller controller = loader.getController();
                    map.forEach(controller::addData);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (MalformedURLException e) {
            synchronized (controller.getMonitor()) {
                controller.getMonitor().notifyAll();
            }
            CallError.CallUrlException();
        } catch (IOException e) {
            synchronized (controller.getMonitor()) {
                controller.getMonitor().notifyAll();
            }
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
