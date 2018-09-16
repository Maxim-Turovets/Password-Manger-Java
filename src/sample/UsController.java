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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

           // ConnectToBase ob= new ConnectToBase();
        //    ob.AddInformationToTableAccount(1,"aaaa","aaaa","asdfdf");
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

        ConnectToBase ob = new ConnectToBase();
        ConnectToBase obw = new ConnectToBase();
        obw.GetValue();
        //System.out.print("====   "+ConnectToBase.value+"     =========");

         for (int i=0;i<ConnectToBase.value;i++)
             usersData.add(new User(i+1, ob.getLogin(i+1), ob.getPass(i+1), ob.getEmail(i+1)));

    }


}