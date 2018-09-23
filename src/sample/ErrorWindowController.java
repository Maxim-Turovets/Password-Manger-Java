package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ErrorWindowController {

    public  static  String errorText;
    @FXML
    private Label errortext;

    @FXML
    private ImageView closebutton;

    @FXML
    private   void initialize() {
        closebutton.setOnMouseClicked(event -> {
            closebutton.getScene().getWindow().hide();
            ;
        });

        errortext.setText(errorText);




    }


}
