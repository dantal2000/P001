package GUI001_f;

import GUI001_f.ErrorWindow001.CallError;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;

class Model {
    private Controller controller;

    Model(Controller controller) {
        this.controller = controller;
    }

    void handleOpenURL() {
        String url = controller.getURL();
        String cookies = controller.getCookies();

        Connector_2 connector;

        if (Objects.equals(url, "")) {
            CallError.CallUrlException();
        } else {
            try {
                connector = new Connector_2(url, cookies);
                Stage stage = new Stage();
                stage.setTitle(url);
                Scene scene;
                GUI001_f.Result001_f.Controller controller;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Result001_f/View.fxml"));
                    scene = new Scene(loader.load());
                    controller = loader.getController();
                    stage.setScene(scene);
                    controller.printArea(connector.getResult());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                CallError.CallUrlException();
            }
        }
    }

    void handleShowHeaders() {
        String url = controller.getURL();
        if (url.equals("")) CallError.CallUrlException();
        else {
            String cookies = controller.getCookies();
            try {
                Connector_2 connector = new Connector_2(url, cookies);
                Stage stage = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Result002_f/View.fxml"));
                    Scene scene = new Scene(loader.load());

                    GUI001_f.Result002_f.Controller controller = loader.getController();
                    connector.getResponseParams().forEach(controller::addData);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                CallError.CallUrlException();
            }
        }
    }

    String recieveCookies() {
        String url = controller.getURL();
        String cookies = controller.getCookies();
        try {
            Connector_2 connector = new Connector_2(url, cookies);
            return connector.recieveCookies();
        } catch (MalformedURLException e) {
            CallError.CallUrlException();
        }
        return "";
    }
}
