package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ConnectToBase {

    public boolean confirmation;

    public int PasswordMatchChecker(String pass) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");

            Statement state = connection.createStatement();
            ResultSet res;

            String stringSQL = "SELECT ID FROM PasswordTable WHERE Password = '" + pass + "';";


            res = state.executeQuery(stringSQL);
            int id = res.getInt(1);
            this.confirmation = true;
            connection.close();
            return id;
        } catch (Exception e) {
            this.confirmation = false;
            System.out.print("ERROR");
            return 0;
        }

    }

    public int Register(String RegisterPass) {
        if (RegisterPass.isEmpty() == false) {
            try {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");

                Statement state = connection.createStatement();

                String stringSQL = "INSERT INTO PasswordTable (Password) VALUES ('" + RegisterPass + "')";

                state.executeUpdate(stringSQL);

                System.out.println(HomePageController.index);



                state.executeQuery(stringSQL);
                connection.close();

            } catch (Exception e) {
                System.out.print("!!!"+e.getMessage());
            }
        }
        return PasswordMatchChecker(RegisterPass);
    }

    public void CreateTable(int index) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser" + index + ".db");
            Statement state = conn.createStatement();
            String stringSQL = "CREATE TABLE UserTable ( id INTEGER PRIMARY KEY AUTOINCREMENT ,login Varchar (30),   password Varchar (30),   email Varchar (30))";

            state.executeUpdate(stringSQL);
            conn.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

}
