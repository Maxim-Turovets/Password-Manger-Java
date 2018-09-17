package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

            AddInformationToTableAccount( AccountLogin.getText(),AccountPass.getText(),AccountEmail.getText());
        });
    }

    public void AddInformationToTableAccount (String log , String pass ,String ema )
    {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser"+Controller.index+".db");
            Statement state = conn.createStatement();
            Statement state22 = conn.createStatement();
            UsController ob = new UsController();

            String stringSQL = "INSERT INTO  UserTable ( login,password,email) VALUES  ("+"'" +log+"','"+pass+"','"+ema+"')";
            state22.executeUpdate(stringSQL);
         
            state.executeUpdate(stringSQL);

          //  conn.close();
        } catch (Exception e) {
            System.out.print("1");
        }
    }
}
