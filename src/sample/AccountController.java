package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class AccountController {

    @FXML
    private ComboBox<String> AccountList;



    @FXML
    void initialize(){


        Scene scene = new Scene(new Group(), 450, 250);
        scene= AccountList.getScene();
        Stage stage = new Stage();

        AccountList =   new ComboBox<>();
        AccountList.getItems().addAll("A","B","C","D","E");


    }
}
