package sample;
class Coordinate {
    public int x;    // x-coordinate
    public int y;    // y-coordinate

    // point initialized from parameters
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    // return a string representation of this point
    public void printString() {
        System.out.print("(" + x + "," + y + ")");
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
