package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public  static  boolean open = false;
    @Override
    public void start(Stage primaryStage)
            throws Exception{
        HomePageController ob= new HomePageController();
       ob.createTableRemember();
  //     ob.createTablePassword();
/*
        if(ob.getRememberBool().equals("yes"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("user.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 853, 630));
            primaryStage.show();
        }
        else
        {*/
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 853, 630));
            primaryStage.show();
     //   }
    }



    public static void main(String[] args) {

        launch(args);

    }
}
