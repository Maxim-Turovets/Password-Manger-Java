package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.sql.*;


public class UsController {

    public  static  int numberCol;

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private ImageView EditButton;

    @FXML
    private ImageView AddAccount;

    @FXML
    private ImageView DeleteAccount;


    @FXML
    private ImageView closebutton;

    @FXML
    private ImageView collapsebutton;


    @FXML
    private ImageView resetbutton;

    @FXML
    private Label usernametext;


    @FXML
    private void initialize() {

        usernametext.setText(HomePageController.usernametext);

        System.out.print(HomePageController.usernametext);

        resetbutton.setOnMouseClicked(event -> {
            resetbutton.getScene().getWindow().hide();
            HomePageController homepagecontrollerobject = new HomePageController();
            homepagecontrollerobject.setRememberBool("no");
            try {
                homepagecontrollerobject.NextWindow("./fxmlfiles/homepage.fxml");
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        });

        collapsebutton.setOnMouseClicked(event -> {
            Stage stage = (Stage) collapsebutton.getScene().getWindow();
            stage.setIconified(true);
        });

       closebutton.setOnMouseClicked(event -> {
           closebutton.getScene().getWindow().hide();;
       });


        EditButton.setOnMouseClicked(event -> {
            int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex()+1;
           // System.out.print(selectedIndex);
            numberCol = selectedIndex;
            HomePageController controllerobject = new HomePageController();
            EditButton.getScene().getWindow().hide();

            try {
                controllerobject.NextWindow("./fxmlfiles/edit_account.fxml");
           } catch (Exception e) {
                System.out.print(e.getMessage());
            }
         //   System.out.println(Paths.get("editaccount.fxml").toAbsolutePath());


        });


        DeleteAccount.setOnMouseClicked(event -> {
            int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
            tableUsers.getItems().remove(selectedIndex);
            int a = DeleteRow(selectedIndex + 1);
            Sort(a);
        });


        AddAccount.setOnMouseClicked(event -> {
            HomePageController controllerobject = new HomePageController();
            AddAccount.getScene().getWindow().hide();
            try {
                controllerobject.NextWindow("./fxmlfiles/addaccount.fxml");
            } catch (Exception e) {
            }
        });

        initData();


        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));


        tableUsers.setItems(usersData);


    }


    private void initData() {

        for (int i = 1; i < GetValue() + 1; i++)
            usersData.add(new User(i, getLogin(i), getPass(i), getEmail(i)));

    }


    public String getLogin(int index) {
        String LOgin = "";
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser" + HomePageController.index + ".db");


            Statement state = conn.createStatement();


            String stringSQL = "Select  Login  From UserTable  WHERE id=" + index;

            state.executeUpdate(stringSQL);
            try {
                LOgin = state.executeQuery(stringSQL).getString(1);
            } catch (Exception e) {
            }

            conn.close();

        } catch (Exception e) {
            System.out.print("121212");
        }
        return LOgin;
    }

    public String getPass(int index) {
        String PAss = "";
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser" + HomePageController.index + ".db");


            Statement state = conn.createStatement();

            String stringSQL = "Select  password  From UserTable  WHERE id=" + index;
            // System.out.print(stringSQL);
            state.executeUpdate(stringSQL);
            try {
                PAss = state.executeQuery(stringSQL).getString(1);
            } catch (Exception e) {
            }
            conn.close();


        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return PAss;
    }

    public String getEmail(int index) {
        String EMail = "";
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser" + HomePageController.index + ".db");


            Statement state = conn.createStatement();

            String stringSQL = "Select  email  From UserTable  WHERE id=" + index;
            state.executeUpdate(stringSQL);
            try {
                EMail = state.executeQuery(stringSQL).getString(1);
            } catch (Exception e) {
            }

            conn.close();


        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return EMail;
    }

    public int GetValue() {
        int count = 0;
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser" + HomePageController.index + ".db");
            ResultSet res;

            Statement state = conn.createStatement();
            Statement state2 = conn.createStatement();


            String stringSQL = "SELECT COUNT() FROM UserTable";
            res = state2.executeQuery(stringSQL);


            res.next();

            count = res.getInt(1);
            conn.close();


        } catch (Exception e) {
            System.out.print("er");
        }
        return count;
    }

    public int DeleteRow(int iD) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser" + HomePageController.index + ".db");
            PreparedStatement delete = conn.prepareStatement("DELETE  FROM UserTable WHERE id = " + iD + ";");
            delete.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return iD;
    }

    public void Sort(int index) {
        try {
            Class.forName("org.sqlite.JDBC");


            for (int i = index; i < GetValue() + 1; i++) {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:DataBaseUser" + HomePageController.index + ".db");
                String stringSQL = "UPDATE UserTable SET id=" + i + " WHERE id=" + (i + 1) + ";";
                Statement state = conn.createStatement();
                state.executeUpdate(stringSQL);
                System.out.print(stringSQL + "\n");
                conn.close();
            }


        } catch (Exception e) {
            System.out.print("aaaaa");
        }

    }



}

