package GUI001_f.Result002_f;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Даниил on 30.10.2016.
 */
public class Controller {
    @FXML
    private TableColumn<TableRow, String> option, value;
    @FXML
    private TableView<TableRow> table;

    private ObservableList<TableRow> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        option.setCellValueFactory(new PropertyValueFactory<TableRow, String>("option"));
        value.setCellValueFactory(new PropertyValueFactory<TableRow, String>("value"));
        table.setItems(data);
    }

    public void addData(String option, String value) {
        data.add(new TableRow(option, value));
    }
}
