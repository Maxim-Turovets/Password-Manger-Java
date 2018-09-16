package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectToBase {

    public boolean confirmation;

    public static Connection open()
    {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn =  DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Макс\\Documents\\GitHub\\Password-Manger-Java\\src\\sample\\DataBase.db");


            Statement state = conn.createStatement();
            ResultSet res;

            res = state.executeQuery("SELECT Password From Password ");
            String names;
            names =  res.getString(1);


            String age = state.executeQuery("SELECT UserPatronymic From User ").getString(1);

            System.out.print(names+ "   ");
            System.out.print(age);
            return conn;
        }
        catch (Exception e)
        {
            System.out.print("ERROR");
            return  null;
        }
    }

    public   int  AddPass(String pass){
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn =  DriverManager.getConnection("jdbc:sqlite:../Password-Manger-Java/src/sample/DataBase.db");


            Statement state = conn.createStatement();
            ResultSet res;


            String stringSQL = "SELECT ID FROM PasswordTable WHERE Password = '" + pass +"';" ;


            res = state.executeQuery(stringSQL);
            int id = res.getInt(1);
            this.confirmation=true;

            System.out.print(id + " Enter Login");

            return id;
        }
        catch (Exception e)
        {
            this.confirmation=false;
            System.out.print("ERROR");
            return  0;
        }
    }

    public void Register (String  RegisterPass)
    {
        if(RegisterPass.isEmpty()==false) {
            try {

                Class.forName("org.sqlite.JDBC");

                Connection conn = DriverManager.getConnection("jdbc:sqlite:../Password-Manger-Java/src/sample/DataBase.db");


                Statement state = conn.createStatement();

                String stringSQL = "INSERT INTO PasswordTable (Password) VALUES ('" + RegisterPass + "')";


                ResultSet res = state.executeQuery(stringSQL);


            } catch (Exception e) {
                System.out.print("ERROR");
            }
        }
    }
}
