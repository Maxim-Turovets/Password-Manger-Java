package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class HomePageController {

    public static int index=1;

    @FXML
    private PasswordField PassText;

    @FXML
    private ImageView ButtonLogin;

    @FXML
    private Button ButtonRegister;

    @FXML
    void initialize() {
       createTablePassword();

        ButtonLogin.setOnMouseClicked(event -> {

            if (PassText.getText().isEmpty() == false) {

                ConnectToBase ob = new ConnectToBase();
                HomePageController.index = ob.PasswordMatchChecker(PassText.getText());



                if (ob.confirmation == true) {
                    ButtonLogin.getScene().getWindow().hide();// если пароль правильный
                    try {
                        NextWindow("user.fxml");
                    } catch (Exception e) {
                    }
                }
            }
        });

        ButtonRegister.setOnAction(event -> {

            ButtonRegister.getScene().getWindow().hide();
            try {
                NextWindow("register.fxml");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void NextWindow(String paht) throws IOException {
        Stage stage = new Stage();
        // stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource(paht));
        Scene scene = new Scene(root);
     //   scene.setFill(Color.TRANSPARENT);
      //  stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    private void createTablePassword()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
            Statement state = connection.createStatement();
            String stringSQL = "CREATE TABLE PasswordTable ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Password Varchar (30))";
            state.executeUpdate(stringSQL);
            connection.close();

        } catch (Exception e) {
            System.out.print(e.getMessage()+"  1212");
        }
    }
}
