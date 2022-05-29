package mobility;


/**
 * class Point
 * @version 3.0
 * @author Tal vazana
 */
public class Point {
    private static final int MAX_X_VAL = 800;
    private static final int MAX_Y_VAL = 600;

    private int x;
    private int y;

    /**
     * Point constructor.
     * @param x - int value of x coordinate.
     * @param y - int value of y coordinate.
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Copy constructor
     * @param other - Point object to copy.
     */
    public Point(Point other) {
            this.x = other.getX();
            this.y = other.getY();
    }

    //API
    /**
     * checks if the point is within valid boundaries.
     * @param p Point object.
     * @return true if the values are between the boundaries.
     */
    public static boolean checkBoundaries(Point p){
        boolean isSuccess =  p.x <= MAX_X_VAL && p.x >= 0 && p.y <= MAX_Y_VAL && p.y >= 0;
        return isSuccess;
    }


    //getters
    /**
     * getter for x coordinate
     * @return int value of x coordinate.
     */
    public int getX(){return this.x;}
    /**
     * getter for y coordinate
     * @return int value of y coordinate.
     */
    public int getY(){return this.y;}

    /**
     * toString implementation of Point object.
     * @return String that represents the object.
     */
    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ") " ;
    }

    public static int getMaxXVal() {
        return MAX_X_VAL;
    }

//    public static int getMinXVal() {
//        return MIN_X_VAL;
//    }
//
//    public static int getMinYVal() {
//        return MIN_Y_VAL;
//    }

    public static int getMaxYVal() {
        return MAX_Y_VAL;
    }
}

