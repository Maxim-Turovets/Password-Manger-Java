package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EditAccountController {


    @FXML
    private ImageView EditSaveButton;

    @FXML
    private TextField EditLogin;

    @FXML
    private TextField EditPass;

    @FXML
    private TextField EditEmail;

    @FXML
    private ImageView EditBackButton;

    @FXML
    private void initialize() {

        EditSaveButton.setOnMouseClicked(event -> {
            AddInformationToTableAccount(EditLogin.getText(), EditPass.getText(), EditEmail.getText());

        });


        EditBackButton.setOnMouseClicked(event -> {

            HomePageController controllerobject = new HomePageController();
            EditBackButton.getScene().getWindow().hide();
            try {
                controllerobject.NextWindow("./fxmlfiles/user.fxml");
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        });

        SetValueTable();


    }


    public void SetValueTable() {

        UsController uscontrollerobject = new UsController();
        EditLogin.setText(uscontrollerobject.getLogin(UsController.numberCol));
        EditPass.setText(uscontrollerobject.getPass(UsController.numberCol));
        EditEmail.setText(uscontrollerobject.getEmail(UsController.numberCol));


    }

    public void AddInformationToTableAccount(String log, String pass, String ema) {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser" + HomePageController.index + ".db");
            Statement state = conn.createStatement();
            UsController ob = new UsController();


            String stringSQL = "UPDATE  UserTable  SET login='" + log + "', password='" + pass + "',email='" + ema + "' Where id=" + UsController.numberCol + ";";
            state.executeUpdate(stringSQL);
            System.out.print(stringSQL);
            conn.close();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
