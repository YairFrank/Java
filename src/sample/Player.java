package sample;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private char sign;
    private boolean hasMoves;
    private ArrayList<Coordinate> moves;
    int boardSize;

    public Player() {
        this.sign = 'X';
        this.hasMoves = true;
        this.moves = new ArrayList<Coordinate>();
        this.boardSize = 8;
    }

    public Player(char x, int size) {
        this.sign = x;
        this.hasMoves = true;
        this.moves = new ArrayList<Coordinate>();
        this.boardSize = size;
    }

    public boolean hasOptions() {
        return hasMoves;
    }

    public Coordinate playTurn() {
        int x,y;
        char c;

        System.out.println("Please enter your move row <space> column:");

        while(true) {
            Scanner sc=new Scanner(System.in);
            while(!sc.hasNextInt()){
                System.out.println("please enter a number");
                sc=new Scanner(System.in);
                if(sc.hasNextInt()) {
                    break;
                }
            }
            x = sc.nextInt();
            while(!sc.hasNextInt()){
                System.out.println("please enter a number");
                sc=new Scanner(System.in);
                if(sc.hasNextInt()) {
                    break;
                }
            }
            y = sc.nextInt();
            if (x < 1 || x >  8 || y < 1 || y > 8) {
                System.out.println("coordinates out of bounds. please submit your move again");
                continue;
            }
            break;
        }
        return new Coordinate(x,y);
    }
    public void setHasMoves(boolean x) {
        this.hasMoves = x;
    }

    public boolean getHasMoves() {
        return this.hasMoves;
    }

    public ArrayList<Coordinate> getMoves() {
        return this.moves;
    }

    public char getSign() {
        return this.sign;
    }
}
