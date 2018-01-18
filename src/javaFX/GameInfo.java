package javaFX;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameInfo extends VBox{

    /**
     * default constructor
     */
    public GameInfo(){}

    /**
     * sets the data on the side bar of the screen
     * @param nextPlayer current player sign
     * @param scoreX score of first player
     * @param scoreO second player score
     */
    public void setData(char nextPlayer, Integer scoreX, Integer scoreO) {
        this.getChildren().clear();
        String turnText = "Current player: ";
        turnText += nextPlayer;
        String bScoreText = "first player score: ";
        bScoreText += Integer.toString(scoreX);
        String wScoreText = "second player score: ";
        wScoreText += Integer.toString(scoreO);
        Label turn = new Label(turnText);
        Label bScore = new Label(bScoreText);
        Label wScore = new Label(wScoreText);
        this.getChildren().add(turn);
        this.getChildren().add(bScore);
        this.getChildren().add(wScore);
    }

}
