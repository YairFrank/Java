package sample;
import java.util.ArrayList;

public class ReversieGame {

    private boolean gameOver;
    private Player p1;
    private Player p2;
    private Board b;
    private GameLogic gl;
    private Displayer d;

    public ReversieGame(){
        this.gameOver = false;
        this.d = new ConsoleDisplayer();
        this.b = new Board(8,d);
        this.p1 = new Player('X', 8);
        this.p2 = new Player('O', 8);
        this.gl = new GameLogic();

    }

    public ReversieGame(Displayer d){
        this.gameOver = false;
        this.d = d;
        this.b = new Board(8,d);
        this.p1 = new Player('X', 8);
        this.p2 = new Player('O', 8);
        this.gl = new GameLogic();

    }


    public void play() {
        ArrayList<ArrayList<Character>> board = this.b.getBoard();
        Player current = p1;
        ArrayList<Coordinate> p1v = gl.getValidMoves(p1.getSign(), board);
        ArrayList<Coordinate> p2v = gl.getValidMoves(p2.getSign(), board);
        ArrayList<Coordinate> v = new ArrayList<Coordinate>();
        ArrayList<Coordinate> flips = new ArrayList<Coordinate>();
        char winner;
        boolean firstMove=true;
        //if exits while loop - neither players have moves. Game over.
        board = b.getBoard();
        while (b.hasFreeSpaces()
                && (gl.hasValidMoves(p1.getSign(), board)
                        || gl.hasValidMoves(p2.getSign(), board))) {

            if (p1v.size() != 0) {
                p1.setHasMoves(true);

            } else {
                System.out.println("no moves left for X");
                p1.setHasMoves(false);
            }
            if (p2v.size() != 0) {
                p2.setHasMoves(true);
            } else {
                System.out.println("no moves left for O");
                p2.setHasMoves(false);
            }
            if (firstMove){
                b.print();
            }
            System.out.println(current.getSign() + ": It's your move.");
            if (current.getHasMoves()) {
                if (current.getSign() == p1.getSign()) {
                    v = p1v;
                } else {
                    v = p2v;
                }
                System.out.print("Your possible moves: ");
                for (Coordinate g : v) {
                    g.printString();
                }
                System.out.println();
                Coordinate c = current.playTurn();

                //make sure valid move was entered.
                while (!this.checkValidInput(c, v)) {
                    System.out.println("please enter valid move");
                    c = current.playTurn();
                }

                //alter board
                b.enterMove(current.getSign(), c.x, c.y);
                flips = gl.flipTokens(current.getSign(), c.x - 1, c.y - 1,
                        board);
                for (Coordinate f : flips) {
                    b.enterMove(current.getSign(), f.x + 1, f.y + 1);
                }

                //print board after update
                b.print();
                System.out.println(current.getSign() + " played (" + c.x + ','
                        + c.y + ')');
                p1v.clear();
                p2v.clear();
                board = b.getBoard();
                } else {
                //player had no moves available
                    System.out.println("No possible moves. Play passes back to the other player");
            }
            //switch player
            if (current.getSign() == p1.getSign()) {
                current = p2;
            } else {
                current = p1;
            }
            p1v = gl.getValidMoves(p1.getSign(), board);
            p2v = gl.getValidMoves(p2.getSign(), board);
            firstMove=false;
        }
        // neither player has valid moves available. Game over.
        //announce winner

        winner = this.getWinner();
        if (winner == 't') {
            System.out.println("Game Over. You tied :-)");
        } else {
            System.out.println("Game Over. Winner is " + winner + " :)");
        }
    }

    public char getWinner() {
        return b.getMaxPoints(p1.getSign(), p2.getSign());
    }

    public boolean checkValidInput(Coordinate c, ArrayList<Coordinate> v) {
        for (int i = 0; i < v.size(); i++) {
            if ((v.get(i).x == c.x) && v.get(i).y == c.y) {
                return true;
            }
        }
        return false;
    }

}
