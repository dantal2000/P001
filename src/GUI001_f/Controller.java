package GUI001_f;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.util.ResourceBundle;

public class Controller {
    @FXML
    public ChoiceBox<String> chooser;
    @FXML
    private TextArea urlArea, cookieArea;
    @FXML
    private Button openButton, clearUrlButton, clearCookieButton, showResponceHeaders, recieveCookies, doSome;

    private Model model;
    private ResourceBundle bundle;

    @FXML
    public void initialize() {
        model = new Model(this);
        /*openButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> model.handleOpenURL());
        clearUrlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> urlArea.setText(""));
        clearCookieButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> cookieArea.setText(""));
        showResponceHeaders.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> model.handleShowHeaders());
        recieveCookies.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String cookies = model.recieveCookies();
            if (!cookies.equals("")) cookieArea.setText(cookies);
        });*/
        clearUrlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> urlArea.setText(""));
        clearCookieButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> cookieArea.setText(""));

        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(Data_Strings.a, Data_Strings.b, Data_Strings.c);
        chooser.setItems(items);
        doSome.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> analyzeHandle(chooser.getValue()));
        chooser.setValue(Data_Strings.b);
    }

    public String getURL() {
        return urlArea.getText();
    }

    public String getCookies() {
        return cookieArea.getText();
    }

    private void analyzeHandle(String handle) {
        switch (handle) {
            case Data_Strings.a: {
                String cookies = model.recieveCookies();
                if (!cookies.equals("")) cookieArea.setText(cookies);
                break;
            }
            case Data_Strings.b: {
                model.handleOpenURL();
                break;
            }
            case Data_Strings.c: {
                model.handleShowHeaders();
                break;
            }
        }
    }
}
