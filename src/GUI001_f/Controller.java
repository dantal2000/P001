package GUI001_f;

import GUI001_f.ErrorWindow001.CallError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    public ChoiceBox<String> chooser;
    @FXML
    private TextArea urlArea, cookieArea;
    @FXML
    private Button clearUrlButton, clearCookieButton, doSome;

    private Model model;

    @FXML
    public void initialize() {
        model = new Model();
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
        try {
            switch (handle) {
                case Data_Strings.a: {
                    String cookies = model.receiveCookies(getURL(), getCookies());
                    if (!cookies.equals("")) cookieArea.setText(cookies);
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
            CallError.CallUrlException();
        }
    }
}
