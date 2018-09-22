package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public  static  boolean open = false;
    @Override
    public void start(Stage primaryStage)
            throws Exception{
        HomePageController ob= new HomePageController();

       ob.createTableRemember();
       ob.createTablePassword();

        if(ob.getRememberBool().equals("yes"))
        {
            ConnectToBase ob2 = new ConnectToBase();
            HomePageController.index = ob2.PasswordMatchChecker(ob.getRememberPassword());
            try {
                ob.NextWindow("user.fxml");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            primaryStage.setTitle("Hello World");
            Scene scene = new Scene(root, 853, 630);
            primaryStage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        }
    }



    public static void main(String[] args) {

        launch(args);

    }
}
