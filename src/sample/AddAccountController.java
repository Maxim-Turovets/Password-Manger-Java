package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddAccountController {

    @FXML
    private TextField AccountLogin;

    @FXML
    private TextField AccountPass;

    @FXML
    private TextField AccountEmail;

    @FXML
    private Button AddAccount;

    @FXML
    private void initialize() {
        AddAccount.setOnAction(event -> {
            ConnectToBase ob= new ConnectToBase();
            //System.out.print("Text Login - " + AccountLogin.getText());
            ob.AddInformationToTableAccount(AccountLogin.getText(),AccountPass.getText(),AccountEmail.getText());
        });
    }

}
