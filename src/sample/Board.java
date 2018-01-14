package sample;
import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();
    private Displayer displayer;
    private int size;

    public Board() {
        this.size = 8;
        this.board = new ArrayList<ArrayList<Character>>();
        this.displayer = new ConsoleDisplayer();
        ArrayList<Character> inner = new ArrayList<Character>(9);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                inner.add(' ');
            }
            board.add(inner);
            inner = new ArrayList<Character>(size + 1);
        }
        board.get(3).set(4, 'X');
        board.get(4).set(3, 'X');
        board.get(3).set(4, 'O');
        board.get(4).set(4, 'O');
    }

    public Board(int size, Displayer d) {
        this.displayer = d;
        this.size = size;
        ArrayList<Character> inner = new ArrayList<Character>(size + 1);
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
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

    public Board(Board b) {
        this.board = b.getBoard();
        this.displayer = b.getDisplayer();
    }

    public void print() {
        this.displayer.display(board);
    }

    public void enterMove(char x, int i, int j) {
        this.board.get(i - 1).set(j - 1, x);
    }

    public boolean hasFreeSpaces() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.get(i).get(j) == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

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
     * @return the board
     */
    public ArrayList<ArrayList<Character>> getBoard() {
        return board;
    }

    /**
     * @return the displayer
     */
    public Displayer getDisplayer() {
        return displayer;
    }

    public int getSize() {
        return this.size;
    }




}
