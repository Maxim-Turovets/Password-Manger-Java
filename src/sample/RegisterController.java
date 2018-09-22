package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private PasswordField RegisterPass;



    @FXML
    private ImageView RegisterButton;

    @FXML
    void initialize() {

        RegisterButton.setOnMouseClicked(event -> {
            if (RegisterPass.getText() != "") {
                RegisterPass.getScene().getWindow().hide();
                if (RegisterPass.getText().trim() == "")
                    System.out.print(RegisterPass.getText());
                ConnectToBase ob = new ConnectToBase();



                HomePageController.index = ob.Register(RegisterPass.getText());

                if(HomePageController.index==0)
                {
                    HomePageController.index=1;
                }

                ob.CreateTable(HomePageController.index);


                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("user.fxml"));

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
        });


    }
}
