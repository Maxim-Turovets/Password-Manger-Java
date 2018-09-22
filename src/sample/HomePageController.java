package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class HomePageController {

    public static int index = 1;

    @FXML
    private PasswordField PassText;

    @FXML
    private ImageView ButtonLogin;

    @FXML
    private Button ButtonRegister;

    @FXML
    private CheckBox checremember;

    @FXML
    void initialize() {
      //  createTablePassword();
        createTableRemember();
        setRememberTable("yes");

        if(getRememberTable()==true)
           System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqq");

        if (checremember.isSelected())
        {
            ButtonLogin.getScene().getWindow().hide();// если пароль правильный
            ConnectToBase ob = new ConnectToBase();
            HomePageController.index = ob.PasswordMatchChecker(getRememberPassword());
            try {
                NextWindow("user.fxml");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        else
            {
            ButtonLogin.setOnMouseClicked(event -> {
                if (PassText.getText().isEmpty() == false) {

                    ConnectToBase ob = new ConnectToBase();
                    HomePageController.index = ob.PasswordMatchChecker(PassText.getText());


                    if (ob.confirmation == true) {
                        ButtonLogin.getScene().getWindow().hide();// если пароль правильный
                        try {
                            NextWindow("user.fxml");
                        } catch (Exception e) {
                        }
                    }
                }
            });

            ButtonRegister.setOnAction(event -> {
                ButtonRegister.getScene().getWindow().hide();
                try {
                    NextWindow("Registr.fxml");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }

    public void NextWindow(String paht) throws IOException {
        Stage stage = new Stage();
        // stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource(paht));
        Scene scene = new Scene(root);
        //   scene.setFill(Color.TRANSPARENT);
        //  stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    private void createTablePassword() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
            Statement state = connection.createStatement();
            String stringSQL = "CREATE TABLE PasswordTable ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Password Varchar (30))";
            state.executeUpdate(stringSQL);
            connection.close();

        } catch (Exception e) {
            System.out.print(e.getMessage() + "  1212");
        }
    }

    private void createTableRemember() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
            Statement state = connection.createStatement();
            String stringSQL = "CREATE TABLE RememberTable ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Password Varchar (30),Remember VARCHAR (3))";
            state.executeUpdate(stringSQL);
            connection.close();

        } catch (Exception e) {
            System.out.print(e.getMessage() );
        }
    }

    private String getRememberPassword() {
        String pass = "";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBase.db");

            Statement state = conn.createStatement();


            String stringSQL = "Select  password  From  RememberTable   WHERE id=" + 1;

            state.executeUpdate(stringSQL);
            try {
                pass = state.executeQuery(stringSQL).getString(1);
            } catch (Exception e) {
            }

            conn.close();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return pass;
    }

    private void setRememberTable (String str )
    {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
            Statement state = conn.createStatement();
            UsController ob = new UsController();


                String stringSQL = "INSERT INTO  RememberTable ( Remember ) VALUES ('"+str+"')";
                state.executeUpdate(stringSQL);

            conn.close();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private boolean getRememberTable() {
        boolean rem= true;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
            Statement state = conn.createStatement();


            String stringSQL = "Select  Remember  From  RememberTable   WHERE id="+1;
        //    System.out.println(stringSQL);
            state.executeUpdate(stringSQL);

            String sql = state.executeQuery(stringSQL).getString(1);

                if(sql.equals("yes"))
                {
                    rem = true;
                    System.out.println("HHHHHH");
                }
                else {
                    rem = false;
                    System.out.println("AAAAAAAA");
                }

            conn.close();

        } catch (Exception e) {
            System.out.print(e.getMessage()+22);
        }
        return rem;
    }

}
