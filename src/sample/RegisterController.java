package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private PasswordField RegisterPass;

    @FXML
    private Button RegisterButton;

    @FXML
    void initialize() {

        RegisterButton.setOnAction(event -> {
            if(RegisterPass.getText()!="") {

                if(RegisterPass.getText().trim() =="")
                System.out.print("aaa");

                System.out.print(RegisterPass.getText());

                ConnectToBase ob = new ConnectToBase();
                ob.Register(RegisterPass.getText());

                RegisterPass.getScene().getWindow().hide();

               /* FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("sample.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();*/
            }
        });


    }
}
