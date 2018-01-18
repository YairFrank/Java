package javaFX;

import java.io.IOException;
import java.util.ArrayList;

import logic.Coordinate;
import logic.Displayer;
import logic.GameFlow;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author leah
 *  class depicting a board controller
 */
public class BoardController extends GridPane implements Displayer {


    /**
     * constructor
     */
    public BoardController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void draw(char first, Color p1Color, Color p2Color, ArrayList<ArrayList<Character>> board) {
        char second;
        if(first=='X'){
            second='O';
        }else{
            second='X';
        }
        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int radius;
        int cellHeight = height / board.size();
        int cellWidth = width / board.size();
        if (cellWidth > cellHeight) {
            radius = cellHeight / 3;
        } else {
            radius = cellWidth / 3;
        }

        // draw grid
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                Rectangle back = new Rectangle(cellWidth, cellHeight, Color.BLUEVIOLET);
                back.setStroke(Color.BLACK);
                this.add(back, j, i);
            }
        }

        // draw player tokens on grid
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == first) {
                    Circle black = new Circle(3, 3, radius, p1Color);
                    black.setCenterX(i + (width / 2));
                    black.setCenterY(j + (height / 2));
                    GridPane.setHalignment(black, HPos.CENTER);
                    this.add(black, j, i);
                } else if (board.get(i).get(j) == second) {
                    Circle white = new Circle(3, 3, radius, p2Color);
                    white.setCenterX(i + (width / 2));
                    white.setCenterY(j + (height / 2));
                    GridPane.setHalignment(white, HPos.CENTER);
                    this.add(white, j, i);
                }
            }
        }
    }

    /**
     * add buttons on grid for player to select possible (valid) move
     * @param buttonId a list of possible move coordinate, to be button location on game board
     * @param board game board
     * @param gf game flow, in order to update the game flow on which move was made and to continue the game accordingly
     */
    public void drawPossibleMovesButtons(ArrayList<String> buttonId, ArrayList<ArrayList<Character>> board, GameFlow gf) {
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / board.size();
        int cellWidth = width / board.size();
        //for every location (given as string) make a button placed accordingly on board. its location will be its id
        for (String bid: buttonId) {

            Button move = new Button();
            move.setStyle("-fx-background-color: #0abbb3");
            move.setPrefHeight(cellHeight);
            move.setPrefWidth(cellWidth);
            move.setId(bid);
            //System.out.println(bid);
            char [] coord = bid.toCharArray();
            int xCoord = Character.getNumericValue(coord[0]);
            int yCoord = Character.getNumericValue(coord[1]);
            this.add(move, yCoord, xCoord);
            move.setOnMousePressed(event -> {
                //when button is pressed, interpret location by the buttons id, and send location to game flow
                String s = ((Control)event.getSource()).getId();
                char [] chosenCell = s.toCharArray();
                int chosenCellxCoord = Character.getNumericValue(chosenCell[0]);
                int chosenCellyCoord = Character.getNumericValue(chosenCell[1]);
                Coordinate c = new Coordinate(chosenCellxCoord, chosenCellyCoord);
                gf.setChosen(c);
                System.out.println("chosen : ");
                c.printString();
                System.out.println("current board: " + chosenCell[2]);
                gf.play();
            });
        }
    }
}

