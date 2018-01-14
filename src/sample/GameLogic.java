package sample;
import java.util.ArrayList;

public class GameLogic {

    public GameLogic() {}

    public ArrayList<Coordinate> getValidMoves(char sign, ArrayList<ArrayList<Character>> board) {
        ArrayList<Coordinate> v = new ArrayList<Coordinate>();
        int x, y, z = 0,xStart,yStart;
        int outOfBounds = board.size() - 1;
        Coordinate  move = new Coordinate(0, 0);
        Coordinate  temp = new Coordinate(0, 0);
        ArrayList<Coordinate> playerPos, directions;
        //ArrayList<Coordinate>::iterator i,i2,i3;

        //get vector of player positions on the board.
        playerPos = this.getPlayersCoords(sign, board);
        //get vector of directions by which we will probe spaces around a cell.
        directions = this.getDirections();
        //for every piece of that player on the board
        for (Coordinate coord : playerPos) {
            //original coordinate of player-occiupied slot.
            xStart = coord.x;
            yStart = coord.y;
            x = xStart;
            y = yStart;
            //for every direction around the cell
            for (Coordinate dir : directions) {
                x += dir.x;
                y += dir.y;
                //check boundary stipulation
                while ((x >= 0) && (x < outOfBounds) && (y >= 0) && (y < outOfBounds)) {
                    //if reached a cell already occupied by himself - not valid. Keep looking.
                    if (board.get(x).get(y) == sign) {
                        //return to original starting location to probe in a new direction.
                        x = xStart;
                        y = yStart;
                        break;
                    } else if (board.get(x).get(y) == ' '){
                        //if reached and empty slot
                        if (z == 0) {
                            //reached empty square, without passing opponent first.Not a valid move.
                            x = xStart;
                            y = yStart;
                            break;
                        } else {
                            //reached empty slot after passing opponent-occupied spaces. Potentially valid move.
                            move.setXY(x + 1, y + 1);
                            //add the slot, while avoiding duplicates.
                            if (!this.checkForDuplicates(v,move)) {
                                v.add(move);
                                move = new Coordinate(0,0);
                            }
                            x = xStart;
                            y = yStart;
                            break;
                        }
                    } else {
                        //reached opponent occupied square
                        z++;    //flag
                        //continue moving in the same direction.
                        x += dir.x;
                        y += dir.y;
                        continue;
                    }
                }
                z = 0;
                x = xStart;
                y = yStart;

            }
        }
        return v;
    }

    private ArrayList<Coordinate> getPlayersCoords(char s, ArrayList<ArrayList<Character>> board) {
        ArrayList<Coordinate> vec = new ArrayList<Coordinate>();
        Coordinate c = new Coordinate(0,0);
        //scan board and find coordinates occupied by player.
        for (int i = 0; i < board.size(); i ++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == s) {
                    c.x = i;
                    c.y = j;
                    vec.add(c);
                    c = new Coordinate(0,0);
                }
            }
        }
        return vec;
    }

    public ArrayList<Coordinate> flipTokens(char s, int x, int y, ArrayList<ArrayList<Character>>board) {
        int outOfBounds = board.size() - 1;
        int xStart, yStart,z = 0;
        ArrayList<Coordinate> directions = new ArrayList<Coordinate>();
        ArrayList<Coordinate> flips = new ArrayList<Coordinate>();
        ArrayList<Coordinate> mayFlip = new ArrayList<Coordinate>();
        Coordinate op = new Coordinate(0,0);

        //pretty much the same logic of getting valid moves, with obvious changes.
        directions = this.getDirections();
        /*start at the coordinate of newly-positioned piece. probe all directions searching for
        a line of opponent pieces blocked by player's. */
        xStart = x;
        yStart = y;
        for (Coordinate dir : directions) {
                    x += dir.x;
                    y += dir.y;
                    //check boundary stipulation
                    while ((x >= 0) && (x < outOfBounds) && (y >= 0) && (y < outOfBounds)) {
                        if (board.get(x).get(y) == ' ') {
                            //if reached empty slot, opponent's pieces are not blocked by player's.Realese them.
                            mayFlip.clear();
                            //return to original starting location to probe in a new direction.
                            x = xStart;
                            y = yStart;
                            break;
                        } else if (board.get(x).get(y) == s){
                            if (z == 0) {
                                //reached self-occupied square, without passing opponent first.No tokens to flip.
                                //return to original starting location to probe in a new direction.
                                x = xStart;
                                y = yStart;
                                break;
                            } else {
                                /*passed one opponent at least, now known to be blocked by another
                                 * of player's pieces. Add all opponent tokens passed until now to array of
                                 * tokens to be flipped.
                                 */
                                for (int j = 0; j < mayFlip.size(); j++) {
                                    flips.add(mayFlip.get(j));
                                }
                                //clear array of possible tokens to flip.
                                mayFlip.clear();
                                x = xStart;
                                y = yStart;
                                break;
                            }
                        } else {
                            //reached opponent occupied square
                            z++;    //flag
                            op.setXY(x, y);
                            /*add coordinate to array of tokens which may be flipped, depending
                             * on whether they turn out to be blocked by another of player's pieces.
                             */
                            mayFlip.add(op);
                            op = new Coordinate(0,0);
                            x += dir.x;
                            y += dir.y;
                            continue;
                        }
                    }
                    z = 0;
                    x = xStart;
                    y = yStart;
                }
        return flips;
        }


    private ArrayList<Coordinate> getDirections() {
        /*make an array of directions- values by which x and y will be altered in order to
         * scan the board around a specific coordinate.
         */

        Coordinate dir = new Coordinate(0,0);
        ArrayList<Coordinate> directions = new ArrayList<Coordinate>();
        for (int b = -1; b < 2; b++) {
            for (int c = -1; c < 2; c++) {
                if (!(b == 0 && c== 0)) {
                    dir.x = b;
                    dir.y = c;
                    directions.add(dir);
                    dir = new Coordinate(0,0);
                }
            }
        }
        return directions;
    }

    private boolean checkForDuplicates(ArrayList<Coordinate> v, Coordinate c) {
        for (Coordinate temp : v) {
            if ((c.x == temp.x) && (c.y == temp.y)) {
                return true;
            }
        }
        return false;
    }



    public boolean hasValidMoves(char sign, ArrayList<ArrayList<Character>> board) {
        ArrayList<Coordinate> v = this.getValidMoves(sign,board);
        //check if there are no moves available, check syntax
        //if array of valid moves is empty, there are no moves available.
        if (v.size() == 0) {
            return false;
        }
        return true;
    }
}
