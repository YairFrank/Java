package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import sample.Board;
import sample.GameFlow;
import sample.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ReversiGameController implements Initializable{
    @FXML
    private HBox root;
    private ArrayList<String> settings = new ArrayList<String>();
    private int boardSize;
    private Player p1;
    private Player p2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.getSettings();
        GameInfo gameInfo = new GameInfo();
        GameFlow gf = new GameFlow(this.p1,this.p2,new Board(boardSize, new BoardController()), gameInfo);
        root.getChildren().add(0, gf.getBoardController());
        root.getChildren().add(1, gameInfo);
        gf.play();
    }

    /**
     * set the game setting according to setting file (if exists)
     */
    public void getSettings() {
        // The name of the file to open.
        String fileName = "settings.txt";

        // This will reference one line at a time
        String line = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                new FileReader(fileName);

            //wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                settings.add(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            this.setAll();
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + fileName + "'");
        }

        //set the game parameters according to file
        this.setAll();
    }

    /**
     * sets the game parameters according to file
     */
    public void setAll() {
        if (this.settings.size() == 0) {
            //choose default settings
            this.boardSize = 8;
            this.p1 = new Player('X',Color.BLACK);
            this.p2 = new Player('O',Color.WHITE);
            return;

        }
        this.boardSize = Integer.parseInt(this.settings.get(0));
        if (this.settings.get(3).charAt(0) == 'x') {
            this.p1 = new Player('X',Color.BLACK);
            p1.setColor(Color.valueOf(this.settings.get(1)));
            this.p2 = new Player('O',Color.WHITE);
            p2.setColor(Color.valueOf(this.settings.get(2)));
        } else {
            this.p1 = new Player('O',Color.BLACK);
            p1.setColor(Color.valueOf(this.settings.get(2)));
            this.p2 = new Player('X',Color.WHITE);
            p2.setColor(Color.valueOf(this.settings.get(1)));
        }
    }
}