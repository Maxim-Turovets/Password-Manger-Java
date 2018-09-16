package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class UsController {

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private Button AddAccount;

    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

        AddAccount.setOnAction(event -> {


          //  AddAccount.getScene().getWindow().hide();

            ConnectToBase ob= new ConnectToBase();
          //  ob.CreateTable();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addaccount.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });


        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);
    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {
        usersData.add(new User(1, "Alex", "qwerty", "alex@mail.com"));
        usersData.add(new User(2, "Bob", "dsfsdfw", "bob@mail.com"));
        usersData.add(new User(3, "Jeck", "dsfdsfwe", "Jeck@mail.com"));
        usersData.add(new User(4, "Mike", "iueern", "mike@mail.com"));

    }

}