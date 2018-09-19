package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;

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
    private Button BackButton;



    @FXML
    private void initialize() {
        AddAccount.setOnAction(event -> {

            AddInformationToTableAccount( AccountLogin.getText(),AccountPass.getText(),AccountEmail.getText());
            ClearText();
        });

        BackButton.setOnAction(event -> {
            BackButton.getScene().getWindow().hide();

            Controller controllerobject = new Controller();
            try{
                controllerobject.NextWindow("main.fxml");}
            catch (Exception e){}
        });

}

    public void AddInformationToTableAccount (String log , String pass ,String ema )
    {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser"+Controller.index+".db");
            Statement state = conn.createStatement();
            UsController ob = new UsController();

            if(ob.GetValue()<1) {
                String stringSQL = "INSERT INTO  UserTable ( id ,login,password,email) VALUES  ("+1+ ",'" + log + "','" + pass + "','" + ema + "')";
                state.executeUpdate(stringSQL);
            }
            else {
                String stringSQL = "INSERT INTO  UserTable ( id ,login,password,email) VALUES  ("+"'"+(ob.GetValue()+1)+ "','" + log + "','" + pass + "','" + ema + "')";
                state.executeUpdate(stringSQL);
            }
            conn.close();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private  void ClearText()
    {
        AccountLogin.setText("");
        AccountPass.setText("");
        AccountEmail.setText("");
    }


}
