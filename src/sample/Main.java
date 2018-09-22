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
       ob.createTablePassword();

        if(ob.getRememberBool().equals("yes"))
        {
            ConnectToBase ob2 = new ConnectToBase();
            HomePageController.index = ob2.PasswordMatchChecker(ob.getRememberPassword());
            System.out.println("  PassRemember == "+ ob.getRememberPassword());
            System.out.println("  INDEX== "+ HomePageController.index);
        //    ButtonLogin.getScene().getWindow().hide();
            try {
                ob.NextWindow("user.fxml");
            } catch (Exception e) {
                System.out.println("Не тот пароль ");
            }
        }
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 853, 630));
            primaryStage.show();
        }
    }



    public static void main(String[] args) {

        launch(args);

    }
}
