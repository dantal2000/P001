package GUI001_f;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    private TextArea urlArea, cookieArea;
    @FXML
    private Button openButton, clearUrlButton, clearCookieButton, showResponceHeaders, recieveCookies;
    private Model model;

    @FXML
    public void initialize() {
        model = new Model(this);
        openButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> model.handleOpenURL());
        clearUrlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> urlArea.setText(""));
        clearCookieButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> cookieArea.setText(""));
        showResponceHeaders.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> model.handleShowHeaders());
        recieveCookies.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String cookies = model.recieveCookies();
            if (!cookies.equals("")) cookieArea.setText(cookies);
        });
    }

    public String getURL() {
        return urlArea.getText();
    }

    public String getCookies() {
        return cookieArea.getText();
    }
}
