package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReversieGameController implements Initializable {
    @FXML
    private HBox root;
    private ArrayList<ArrayList<Character>> board;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BoardController mazeBoard = new BoardController(board);
        mazeBoard.setPrefWidth(400);
        mazeBoard.setPrefHeight(400);
        root.getChildren().add(0, mazeBoard);
        mazeBoard.display(board);
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            mazeBoard.setPrefWidth(boardNewWidth);
            mazeBoard.display(board);
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            mazeBoard.setPrefHeight(newValue.doubleValue());
            mazeBoard.display(board);
        });
    }
}