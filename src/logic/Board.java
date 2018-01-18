package logic;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();
    private Displayer displayer;
    private int size;

    /**
     * default constructor
     */
    public Board() {
        this.size = 8;
        this.displayer = new ConsoleDisplayer();
        this.board = new ArrayList<ArrayList<Character>>();
        ArrayList<Character> inner = new ArrayList<Character>(8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                inner.add(' ');
            }
            board.add(inner);
            inner = new ArrayList<Character>(size + 1);
        }
        board.get(3).set(4, 'X');
        board.get(4).set(3, 'X');
        board.get(3).set(3, 'O');
        board.get(4).set(4, 'O');
    }

    /**
     * constructor
     * @param size board size
     * @param d displayer
     */
    public Board(int size, Displayer d) {
        this.size = size;
        this.displayer = d;
        ArrayList<Character> inner = new ArrayList<Character>(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inner.add(' ');
            }
            board.add(inner);
            inner = new ArrayList<Character>(size + 1);
        }
        board.get((size / 2) - 1).set(size / 2, 'X');
        board.get(size / 2).set((size / 2) - 1, 'X');
        board.get((size / 2) - 1).set((size / 2) - 1, 'O');
        board.get(size / 2).set(size / 2, 'O');
    }

    /**
     * copy constructor
     * @param b board
     */
    public Board(Board b) {
        this.board = b.getBoard();
        this.displayer = b.getDisplayer();
    }

    /**
     * @return the boards displayer.
     */
    public Displayer getDisplayer() {
        return this.displayer;
    }

    /**
     * enter move on the board
     * @param x players sign
     * @param i x coordinate
     * @param j y coordinate
     */
    public void enterMove(char x, int i, int j) {
        this.board.get(i).set(j, x);
    }

    /**
     * check if board has free spaces
     * @return true if it has, false otherwise.
     */
    public boolean hasFreeSpaces() {
        for (int i = 0; i < board.size() - 1; i++) {
            for (int j = 0; j < board.size() - 1; j++) {
                if (board.get(i).get(j) == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check which player has more tokens on the board-who is the winner
     * @param a first player token
     * @param b second player token
     * @return the winner at given board stage
     */
    public char getMaxPoints(char a, char b) {
        int pointsA = 0;
        int pointsB = 0;

        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board.get(i).size(); j++)
            {
                if (board.get(i).get(j) == a)
                {
                    pointsA++;
                }
                else if(board.get(i).get(j) == b)
                {
                    pointsB++;
                }
            }
        }

        if (pointsA > pointsB) {
            return a;
        } else if (pointsA < pointsB) {
            return b;
        } else {
            //players tied.
            return 't';
        }
    }

    /**
     * @param a player sign
     * @return current score of player a
     */
    public int getScore(char a) {
        Integer score = 0;
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == a) {
                    score++;
                }
            }
        }
        return score;
    }


    /**
     * @return the board
     */
    public ArrayList<ArrayList<Character>> getBoard() {
        return board;
    }

    /**
     * @param b set the board
     */
    public void setBoard(ArrayList<ArrayList<Character>> b) {
        this.board = b;
    }

    /**
     * @return board size
     */
    public int getSize() {
        return this.size;
    }
}

