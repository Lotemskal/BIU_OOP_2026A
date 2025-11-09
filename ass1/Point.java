/** Name: Lotem Skaletzky
 * ID: 32251709
 */

/**
 * The {@code Point} class represents a point in a two-dimensional plane.
 * Each point is defined by its x and y coordinates (NumberX and NumberY).
 * It provides methods for calculating the distance between points,
 * comparing equality, and retrieving coordinate values.
 */

public class Point {
   /** The x-coordinate of this point. */
    double NumberX;

    /** The y-coordinate of this point. */
    double NumberY;

     /**
     * Constructs a new {@code Point} object with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.NumberX=x;
        this.NumberY=y;
    }
 
     /**
     * Calculates the  distance between this point and another given point.
     *
     * @param other the other {@code Point} to measure distance to
     * @return the distance between this point and the specified point
     */
    public double distance(Point other) {
        double Distance=0;
        Distance = ((NumberX-other.getX())*(NumberX-other.getX())+ (NumberY-other.getY())*(NumberY-other.getY()) );
        Distance= Math.sqrt(Distance); 
        return Distance; 
    }
   
    /**
     * Checks whether this point is equal to another point.
     * Two points are considered equal if they have the same x and y values.
     *
     * @param other the {@code Point} to compare with
     * @return {@code true} if the points have the same coordinates, {@code false} otherwise
     */
    public boolean equals(Point other) {
        return NumberX==other.getX() && NumberY==other.getY();
    }

   /**
     * Returns the x-coordinate of this point.
     *
     * @return the x value
     */
    public double getX() {
        return NumberX;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y value
     */
    public double getY() {
        return NumberY;
    }
    
    /**
     * Returns a string representation of this point in the format (x, y).
     *
     * @return a string containing the coordinates of the point
     */
    @Override

     public String toString() {
        return "(" + this.NumberX + ", " + this.NumberY + ")";
    }


    // Example usage (for testing purposes only):
    // public static void main(String[] args) {
    //     Point ex=new Point(5,7); 
    //     Point old = new Point(1,1);
    //     double dist = ex.distance(old);
    //     System.out.println(dist);
    // }
}

