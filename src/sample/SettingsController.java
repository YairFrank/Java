package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.*;

public class SettingsController implements Initializable {
    @FXML
    private RadioButton xchoise;
    @FXML
    private RadioButton ochoise;
    @FXML
    private ColorPicker xColor;
    @FXML
    private ColorPicker oColor;
    @FXML
    private Slider slider;
    public void initialize(URL location, ResourceBundle resources) {
        //writing();
    }
    public void writeToFile(javafx.event.ActionEvent event) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("settings.txt"), "utf-8"))) {
            int v=(int)slider.getValue();
            String s = String.valueOf(v);
            writer.write(s);
            writer.write("\n");
            Color x=xColor.getValue();
            writer.write(x.toString());
            writer.write("\n");
            Color o=oColor.getValue();
            writer.write(o.toString());
            writer.write("\n");
            if (xchoise.isSelected()){
                writer.write("x");
            }
            if (ochoise.isSelected()){
                writer.write("o");
            }
        }
        //PrintWriter writer = new PrintWriter("settings.txt", "UTF-8");
        //writer.println("The first line");
        //writer.println("The second line");
        //FileWriter f = new FileWriter("settings.txt");
        //BufferedWriter b = new BufferedWriter(f);

        //seconderyStage.close();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root,520,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        window.setTitle("Reversie game");
        window.setScene(scene);
        window.show();
    }
    /*public void writing(){
        System.out.println("gerbrbsrb");
        try (Writer writer = new BufferedWriter(new FileWriter("settings.txt"))) {
            writer.write("aString");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}