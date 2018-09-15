package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectToBase {
    public static Connection open()
    {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn =  DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Макс\\Documents\\GitHub\\Password-Manger-Java\\src\\sample\\DataBase.db");


            Statement state = conn.createStatement();
            ResultSet res;

            res = state.executeQuery("SELECT UserName From User ");
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
}
