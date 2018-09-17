package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class ConnectToBase {




    public boolean confirmation;
    public  static  int value;

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

            Connection conn =  DriverManager.getConnection("jdbc:sqlite:DataBase.db");


            Statement state = conn.createStatement();
            ResultSet res;


            String stringSQL = "SELECT ID FROM PasswordTable WHERE Password = '" + pass +"';" ;


            res = state.executeQuery(stringSQL);
            int id = res.getInt(1);
            this.confirmation=true;

           // System.out.print(id + " Enter Login");

            return id;
        }
        catch (Exception e)
        {
            this.confirmation=false;
            System.out.print("ERROR");
            return  0;
        }
    }

    public int Register (String  RegisterPass)
    {
        if(RegisterPass.isEmpty()==false) {
            try {

                Class.forName("org.sqlite.JDBC");

                Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBase.db");


                Statement state = conn.createStatement();

                String stringSQL = "INSERT INTO PasswordTable (Password) VALUES ('" + RegisterPass + "')";
                state.executeQuery(stringSQL);




            } catch (Exception e) {
              //  System.out.print(e.getMessage());
            }
           // System.out.print("===="+AddPass(RegisterPass)+"=======\n");

        }
        return  AddPass(RegisterPass);
    }

    public void CreateTable ( int index )
    {

        try {
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser"+index+".db");
                 Statement state = conn.createStatement();
               String stringSQL = "CREATE TABLE UserTable ( id INTEGER PRIMARY KEY AUTOINCREMENT ,login Varchar (30),   password Varchar (30),   email Varchar (30))";
                //ResultSet res = state.executeQuery(stringSQL);
                   state.executeUpdate(stringSQL);

            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

    }

    public void AddInformationToTableAccount (int index,String log , String pass ,String ema )
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser1.db");
            Statement state = conn.createStatement();
         //   String stringSQL = "INSERT INTO  UserTable ( login,password,email) VALUES  ("+"'" +log+"','"+pass+"','"+ema+"')";
            String stringSQL = "INSERT INTO  UserTable ( login,password,email) VALUES  ('123','123','123')";
            ResultSet res = state.executeQuery(stringSQL);

        } catch (Exception e) {
            System.out.print("er Create Table");
        }
    }


}
