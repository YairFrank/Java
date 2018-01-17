package sample;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public interface Displayer {

    /**
     * display the board
     * @param p1Color color of first player
     * @param p2Color color of second player
     * @param board game board
     */
    public void draw(Color p1Color, Color p2Color, ArrayList<ArrayList<Character>> board);
}
