package sample;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class ConsoleDisplayer implements Displayer{

    public ConsoleDisplayer(){}

    @Override
    public void draw(Color p1Color, Color p2Color, ArrayList<ArrayList<Character>> board) {
        int k = 1;
        int l = 0, i = 0;
        System.out.print(" |");
        while (k < board.size()) {
            System.out.print(" " +k + " |");
            k++;
        }
        System.out.println();
        System.out.println("----------------------------------");
        k = 1;
        while (k < board.size()) {
            System.out.print(k + "|");
            while (l < board.size()) {

                System.out.print( " " + board.get(i).get(l));
                if (l!= board.size() - 1) {
                    System.out.print(" |");
                }
                l++;
            }
            l = 0;
            System.out.println();
            System.out.println("----------------------------------");
            k++;
            i++;
        }

    }


}

