package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EditAccountController {
    
    @FXML
    private Button SaveButton;

    @FXML
    private TextField AccountLogin;

    @FXML
    private TextField AccountPass;

    @FXML
    private TextField AccountEmail;

    @FXML
    private Button BackButton;

    @FXML
    private  void initialize()
    {

      //  SaveButton.setOnAction(event -> {
        //    AddInformationToTableAccount(AccountLogin.getText(),AccountPass.getText(),AccountEmail.getText());
      //      System.out.print("12345");
     //  });



        BackButton.setOnAction(event -> {

            Controller controllerobject = new Controller();
            try{
                controllerobject.NextWindow("main.fxml");}
            catch (Exception e){}
        });

        SetValueTable();


    }



    public void SetValueTable() {

            UsController uscontrollerobject = new UsController();
            AccountLogin.setText(uscontrollerobject.getLogin(UsController.numberCol)) ;
            AccountPass.setText(uscontrollerobject.getPass(UsController.numberCol));
            AccountEmail.setText(uscontrollerobject.getEmail(UsController.numberCol));


    }

    public void AddInformationToTableAccount (String log , String pass ,String ema )
    {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser"+Controller.index+".db");
            Statement state = conn.createStatement();
            UsController ob = new UsController();


                String stringSQL = "UPDATE  UserTable  SET login="+log+", password="+pass+",email="+ema+";";
                state.executeUpdate(stringSQL);
                System.out.print(stringSQL);
            conn.close();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
