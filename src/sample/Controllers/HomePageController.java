package sample.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ConnectToBase;

import java.io.IOException;


public class HomePageController {

    public static int index;

    @FXML
    private PasswordField PassText;

    @FXML
    private ImageView ButtonLogin;

    @FXML
    void initialize() {


        ButtonLogin.setOnMouseClicked(event -> {

            if (PassText.getText().isEmpty() == false) {

                ConnectToBase ob = new ConnectToBase();
                index = ob.PasswordMatchChecker(PassText.getText());
                
                try {
                    NextWindow("sample.main.fxml");
                } catch (Exception e) {
                }

                if (ob.confirmation == true) {
                    ButtonLogin.getScene().getWindow().hide();// если пароль правильный
                    try {
                        NextWindow("sample.main.fxml");
                    } catch (Exception e) {
                    }
                }
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
