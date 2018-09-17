package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Controller {


    public static int index;

    @FXML
    private Button ButtonRegister;

    @FXML
    private PasswordField PassText;

    @FXML
    private Button ButtonLogin;




    @FXML
    void initialize() {

           ButtonLogin.setOnAction(event -> {
               if(PassText.getText().isEmpty()==false) {
                 //  ButtonLogin.getScene().getWindow().hide();// если строка не пустая
                   ConnectToBase ob = new ConnectToBase();
                   index=ob.AddPass(PassText.getText());


                   if(ob.confirmation==true) {            // если пароль правильный

                       FXMLLoader loader = new FXMLLoader();
                       loader.setLocation(getClass().getResource("main.fxml"));

                       try {
                           loader.load();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }

                       Parent root = loader.getRoot();
                       Stage stage = new Stage();
                       stage.setScene(new Scene(root));
                       stage.showAndWait();
                   }
               }
           });


        ButtonRegister.setOnAction(event -> {

                ButtonRegister.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("register.fxml"));

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


    }


}

