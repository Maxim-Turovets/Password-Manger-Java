package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private ImageView closebutton;

    @FXML
    private ImageView collapsebutton;

    @FXML
    private ImageView resetbutton;

    @FXML
    void initialize() {





        resetbutton.setOnMouseClicked(event -> {
            resetbutton.getScene().getWindow().hide();
            HomePageController homepagecontrollerobject = new HomePageController();
            homepagecontrollerobject.setRememberBool("no");
            try {
                homepagecontrollerobject.NextWindow("fxmlfiles/uhomepage.fxml");
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        });

        collapsebutton.setOnMouseClicked(event -> {
            Stage stage = (Stage) collapsebutton.getScene().getWindow();
            stage.setIconified(true);

        });

        closebutton.setOnMouseClicked(event -> {
            closebutton.getScene().getWindow().hide();;
        });

        RegisterButton.setOnMouseClicked(event -> {
            if (RegisterPass.getText() != "") {
                if(RegisterPass.getText().length()>4) {
                    RegisterPass.getScene().getWindow().hide();
                    if (RegisterPass.getText().trim() == "")
                        System.out.print(RegisterPass.getText());
                    ConnectToBase ob = new ConnectToBase();


                    HomePageController.index = ob.Register(RegisterPass.getText());

                    if (HomePageController.index == 0) {
                        HomePageController.index = 1;
                    }

                    ob.CreateTable(HomePageController.index);


                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("fxmlfiles/user.fxml"));

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
               else {
                    HomePageController object = new HomePageController();
                    ErrorWindowController.errorText= "  Password must be longer \nthan 4 characters. Try again";
//                    object2.setTextError("sssssss");
                    try {
                        object.NextWindow("fxmlfiles/errorwindow.fxml");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });


    }
}
