package sample;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 *
 * @author leah
 *  this class depicts a player
 */
public class Player {
    private Character sign;
    private boolean hasMoves;
    private ArrayList<Coordinate> moves;
    private Color color;

    /**
     * default constructor
     */
    public Player() {
        this.sign = 'X';
        this.hasMoves = true;
        this.color = Color.BLACK;
        this.moves = new ArrayList<Coordinate>();
    }

    /**
     * constructor
     * @param x player sign
     * @param c player color
     */
    public Player(char x, Color c) {
        this.sign = x;
        this.color = c;
        this.hasMoves = true;
        this.moves = new ArrayList<Coordinate>();
    }

    /**
     * @return true if player has valid moves to make, false otherwise
     */
    public boolean hasOptions() {
        return hasMoves;
    }

    /**
     * sets member according to data
     * @param x boolean- wether player has valid moves or not
     */
    public void setHasMoves(boolean x) {
        this.hasMoves = x;
    }

    /**
     * @return if player has valid moves or not
     */
    public boolean getHasMoves() {
        return this.hasMoves;
    }


    /**
     * @return players sign
     */
    public char getSign() {
        return this.sign;
    }

    /**
     * @return players color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * sets players color
     * @param c color to assign to player
     */
    public void setColor(Color c) {
        this.color = c;
    }
}

