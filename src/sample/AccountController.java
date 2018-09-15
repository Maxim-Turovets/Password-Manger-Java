package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AccountController {


    @FXML
    private AnchorPane Grid;

    @FXML
    private TableView<?> AccountTable;

    @FXML
    private TableColumn<?, ?> AccountRow1;

    @FXML
    private TableColumn<?, ?> AccountRow2;


    @FXML
    void initialize() {

    }
}
