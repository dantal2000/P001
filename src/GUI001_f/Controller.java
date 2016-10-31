package GUI001_f;

import GUI001_f.ErrorWindow001.CallError;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private ChoiceBox<String> chooser;
    @FXML
    private TextArea urlArea, cookieArea;
    @FXML
    private Button clearUrlButton, clearCookieButton, doSome;

    private Model model;
    private Object monitor = new Object();

    @FXML
    public void initialize() {
        model = new Model(this);
        clearUrlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> urlArea.setText(""));
        clearCookieButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> cookieArea.setText(""));

        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(Data_Strings.a, Data_Strings.b, Data_Strings.c);
        chooser.setItems(items);
        doSome.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> analyzeHandle(chooser.getValue()));
        chooser.setValue(Data_Strings.b);
    }

    private String getURL() throws URLError {
        String url = urlArea.getText();
        if (url.equals("")) throw new URLError();
        return url;
    }

    private String getCookies() {
        return cookieArea.getText();
    }

    private void analyzeHandle(String handle) {
        Thread thread1 = new Thread(() -> {
            /*URL url = getClass().getResource("img/1.gif");
            File a = new File(url.toURI());*/
            Image image = new Image(getClass().getResourceAsStream("img/1.gif"));

            ImageView view = new ImageView(image);
            final Stage[] waiting = new Stage[1];

            Platform.runLater(() -> {
                waiting[0] = new Stage();
                AnchorPane root = new AnchorPane(view);
                Scene scene = new Scene(root);
                waiting[0].setScene(scene);
                waiting[0].show();
            });
            new Thread(() -> {
                try {
                    synchronized (getMonitor()) {
                        getMonitor().wait();
                        Platform.runLater(waiting[0]::close);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() ->
        {
            try {
                switch (handle) {
                    case Data_Strings.a: {
                        String cookies = model.receiveCookies(getURL(), getCookies());
                        if (!cookies.equals("")) Platform.runLater(() -> cookieArea.setText(cookies));
                        synchronized (getMonitor()) {
                            getMonitor().notifyAll();
                        }
                        break;
                    }
                    case Data_Strings.b: {
                        model.handleOpenURL(getURL(), getCookies());
                        break;
                    }
                    case Data_Strings.c: {
                        model.handleShowHeaders(getURL(), getCookies());
                        break;
                    }
                }
            } catch (URLError urlError) {
                synchronized (getMonitor()) {
                    getMonitor().notifyAll();
                }
                CallError.CallUrlException();
            }
        }).start();

    }

    Object getMonitor() {
        return monitor;
    }
}
