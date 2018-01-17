package sample;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import sample.BoardController;
import sample.GameInfo;

/**
 *
 * @author leah
 * this class manages the game flow, basically runs the game
 */
public class GameFlow {

    private GameInfo gameInfo;
    private Player p1;
    private Player p2;
    private Player current;
    private Board b;
    private GameLogic gl;
    private ArrayList<String> buttonIds = new ArrayList<String>();
    private Coordinate chosen = new Coordinate(-1, -1);
    private BoardController gameBoard;

    public GameFlow(Player p1, Player p2, Board b, GameInfo gi) {
        this.gameInfo = gi;
        this.b = b;
        this.p1 = p1;
        this.p2 = p2;
        this.current = p1;
        this.gl = new GameLogic();
        this.gameBoard = (BoardController)this.b.getDisplayer();
        gameBoard.setPrefWidth(400);
        gameBoard.setPrefHeight(400);
    }

    /**
     * plays the game
     */
    public void play() {
        ArrayList<ArrayList<Character>> board = this.b.getBoard();
        ArrayList<Coordinate> p1v = gl.getValidMoves(p1.getSign(), board);
        ArrayList<Coordinate> p2v = gl.getValidMoves(p2.getSign(), board);
        Player other = p2;
        ArrayList<Coordinate> v = new ArrayList<Coordinate>();
        ArrayList<Coordinate> flips = new ArrayList<Coordinate>();
        char winner;
        board = b.getBoard();
        // if not first turn
        if (chosen.x != -1) {
            //if this is the first move, there were no previous moves, so no need to update the board
            if (current.getSign() == p1.getSign()) {
                other = p2;
            } else {
                other = p1;
            }
            b.enterMove(other.getSign(), chosen.x + 1, chosen.y + 1);
            //flip the opponents tokens which were taken
            flips = gl.flipTokens(other.getSign(), chosen.x, chosen.y, board);
            for (Coordinate f : flips) {
                b.enterMove(other.getSign(), f.x + 1, f.y + 1);
            }

        }
        //update the bar on the side of the screen with current player, and players scores.
        this.gameInfo.setData(current.getSign(), this.b.getScore(this.p1.getSign()),
                this.b.getScore(this.p2.getSign()));

        //check which of the players have valid moves
        p1v = gl.getValidMoves(p1.getSign(), board);
        p2v = gl.getValidMoves(p2.getSign(), board);
        if (p1v.size() != 0) {
            p1.setHasMoves(true);

        } else {
            p1.setHasMoves(false);
        }
        if (p2v.size() != 0) {
            p2.setHasMoves(true);
        } else {
            p2.setHasMoves(false);
        }

        if (current.getHasMoves()) {
            //if current player has valid moves, mark his possible moves on the board
            if (current.getSign() == p1.getSign()) {
                v = p1v;
            } else {
                v = p2v;
            }
            this.buttonIds.clear();
            for (Coordinate g : v) {
                String index = String.valueOf(g.x - 1);
                String y = String.valueOf(g.y - 1);
                index += y;
                index += (Character.toString(current.getSign()));
                this.buttonIds.add(index);
            }
            v.clear();

            /*
             * this should draw the board with possible moves as buttons, when
             * player selects a button, it will pass the coordinate back to the
             * game flow and update the member "chosen" to be the coordinate
             * chosen by player.
             */
            this.gameBoard.draw(p1.getColor(), p2.getColor(), this.b.getBoard());
            this.gameBoard.drawPossibleMovesButtons(buttonIds, this.b.getBoard(), this);
            board = b.getBoard();
        } else if (other.getHasMoves()) {
            // player had no moves available
            //set the info bar on the side of the screen that opponent gets another turn
            this.gameInfo.setData(other.getSign(), this.b.getScore(this.p1.getSign()),
                    this.b.getScore(this.p2.getSign()));
            v = gl.getValidMoves(other.getSign(), board);
            //set the possible moves of opponent on the screen
            this.buttonIds.clear();
            for (Coordinate g : v) {
                String index = String.valueOf(g.x - 1);
                String y = String.valueOf(g.y - 1);
                index += y;
                index += (Character.toString(other.getSign()));
                this.buttonIds.add(index);
            }
            v.clear();
            this.gameBoard.draw(p1.getColor(), p2.getColor(), this.b.getBoard());
            this.gameBoard.drawPossibleMovesButtons(buttonIds, this.b.getBoard(), this);

        } else {
            //neither player has moves left, or board is full
            if (gl.gameOver(b, p1.getSign(), p2.getSign())) {
                //Game over. announce winner and set board.
                winner = this.getWinner();
                this.finishGame(winner);
            }
        }
    }

    public void finishGame(char winner) {
        //draw the tokens on the board, with no "possible moves" buttons
        this.gameBoard.draw(p1.getColor(), p2.getColor(), this.b.getBoard());
        //give and alert announcing winner
        if (winner == 't') {
            JOptionPane.showMessageDialog(null, "you tied :)");
            return;
        }
        JOptionPane.showMessageDialog(null,"Game Over. Winner is " + winner + " :)");
    }

    /**
     * @return array of "possible moves" buttons id's
     */
    public ArrayList<String> getButtonIds() {
        return this.buttonIds;
    }

    /**
     * set the member "chosen" to be move selected by player
     * @param c coordinate chosen as move by player
     */
    public void setChosen(Coordinate c) {
        this.chosen = c;
    }

    /**
     * @return the winner of the game
     */
    public char getWinner() {
        return b.getMaxPoints(p1.getSign(), p2.getSign());
    }

    /**
     * @return the board controller.
     */
    public BoardController getBoardController() {
        return this.gameBoard;
    }

    /**
     * @param a sets current player to be the opponent of player a
     */
    public void setCurrent(char a) {
        if (a == 'X') {
            this.current = p2;
            return;
        }
        this.current = p1;
    }

}
