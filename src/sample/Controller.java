package sample;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;


public class Controller {

    @FXML
    private Button ButtonRegister;

    @FXML
    private PasswordField PassText;

    @FXML
    private Button ButtonLogin;

    @FXML
    void initialize() {
  ButtonLogin.setOnAction(event -> {
  //    System.out.print(PassText.getText());

      ConnectToBase ob= new ConnectToBase();
   //   System.out.print(PassText.getText());

      ob.AddPass(PassText.getText());
  });
    }

}

