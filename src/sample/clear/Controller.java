package sample;

import java.awt.desktop.SystemSleepEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;


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

            if (PassText.getText().isEmpty() == false) {

                ConnectToBase ob = new ConnectToBase();
                index = ob.PasswordMatchChecker(PassText.getText());

                if (ob.confirmation == true) {
                    ButtonLogin.getScene().getWindow().hide();// если пароль правильный
                    try {
                        NextWindow("main.fxml");
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
            }
        });
    }


    public void NextWindow(String paht) throws IOException {
        Stage stage = new Stage();
        // stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource(paht));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

