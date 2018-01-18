package javaFX;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class MenuController implements Initializable {

    Scene scene1, scene2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void startGame(javafx.event.ActionEvent event) throws IOException{
        HBox root1 = (HBox) FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1,520,400);
        window.setScene(scene1);
        window.show();
    }
    public void settings (javafx.event.ActionEvent event) throws IOException{
        AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root2,520,400);
        window.setScene(scene2);
        window.show();
    }
    public void end (){
        exit(0);
    }
}