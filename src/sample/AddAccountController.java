package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Button BackButton;



    @FXML
    private void initialize() {
        AddAccount.setOnAction(event -> {

            AddInformationToTableAccount( AccountLogin.getText(),AccountPass.getText(),AccountEmail.getText());
        });

        BackButton.setOnAction(event -> {
            BackButton.getScene().getWindow().hide();


            Stage stage = new Stage();

            try{
                FXMLDocumentController(stage);}
            catch (Exception e){}
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

            if(ob.GetValue()<1) {
                String stringSQL = "INSERT INTO  UserTable ( id ,login,password,email) VALUES  ("+1+ ",'" + log + "','" + pass + "','" + ema + "')";
                state22.executeUpdate(stringSQL);
            }
            else {
                String stringSQL = "INSERT INTO  UserTable ( id ,login,password,email) VALUES  ("+"'"+(ob.GetValue()+1)+ "','" + log + "','" + pass + "','" + ema + "')";
                state22.executeUpdate(stringSQL);
            }
            conn.close();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void FXMLDocumentController(Stage stage) throws IOException {
        //Загрузили ресурс файла
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
