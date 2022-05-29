package mobility;

/**
 * Mobile Class, An abstract class describes the mobility of an object
 * implementing Ilocatable
 * @version 2.0
 * @author Tal vazana
 */
public class Mobile implements ILocatable {

    protected Point location;
    private double totalDistance;

    /**
     * Mobile constructor.
     * @param location - Point object, represents the location
     */
    public Mobile(Point location){
        this.setLocation(location);
        this.totalDistance = 0;

    }
    //API
    /**
     * this method adding the distance parameter to the total distance attribute
     * @param distance - double value to add.
     */
    public void addTotalDistance(double distance){
        if(distance > 0) {
            this.totalDistance += distance;
        }
    }
    /**
     * Distance calculation method, uses the Point parameter for setting a new location
     * @param newLocation
     * @return new location
     */
    public double calcDistance(Point newLocation){
        double X = newLocation.getX() - this.location.getX();
        double Y = newLocation.getY() - this.location.getY();
        return Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
    }

    /**
     * this method describes the movement of an object by calculate the distance it made by using
     * the point parameter
     * @param newLocation
     * @return 0 or distanceTraveled
     */
    public double move(Point newLocation){
        if(Point.checkBoundaries(newLocation)){
            double distanceTraveled = this.calcDistance(newLocation);
            this.addTotalDistance(distanceTraveled);
            this.setLocation(newLocation);
            return distanceTraveled;
        }
        return 0;
    }

    //setters
    /**
     * Setter for location attribute, checks if the point parameter is in the system boundaries.
     * @param point
     * @return true or false
     */
    public boolean setLocation(Point point) {
        boolean isSuccess = Point.checkBoundaries(point);
        if (isSuccess) {
            this.location = new Point(point);
        }
        return isSuccess;
    }
    //getters
    /**
     * Getter for location attribute
     * @return location
     */
    public Point getLocation() {
        return this.location;
    }
    /**
     * Getter for the totalDistance attribute
     * @return totalDistance
     */
    public double getTotalDistance() {
        return this.totalDistance;
    }
    /**
     * an override method for String representation for the Mobile class.
     * @return String representation
     */
    public String toString() {
        return "[!]" + this.getClass().getSimpleName() + ":  \t" + "distance:["+ this.getTotalDistance()+"]";
    }
}
