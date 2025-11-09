/** Name: Lotem Skaletzky
 * ID: 32251709
 */

/**
 * The {@code Line} class represents a line segment in a two-dimensional plane.
 * A line segment is defined by a start point and an end point.
 * The class provides methods to compute its length, middle point,
 * check for intersections with other line segments, and find an intersection point.
 */

public class Line {
   /** The starting point of this line segment. */
    Point Start;
    /** The ending point of this line segment. */
    Point End;
    /** First internal point used to represent this line (same as start). */
    Point Point1;
    /** Second internal point used to represent this line (same as end). */
    Point Point2;
    /** The x-coordinate of the middle point of this line (used internally). */
    double midX;
    /** The y-coordinate of the middle point of this line (used internally). */
    double midY;
    /** The length of this line (cached during length computation). */
    double length=0;
    /** The slope of this line (used in intersection calculations). */
    double m=0;
    /** The y-intercept of this line (used in intersection calculations). */
    double n=0;
    /** The x-coordinate of the last computed intersection point. */
    double InterX;
    /** The y-coordinate of the last computed intersection point. */
    double InterY;


    /**
     * Constructs a new {@code Line} from the given start and end points.
     *
     * @param start the starting point of the line segment
     * @param end   the ending point of the line segment
     */
    public Line(Point start, Point end) { 
        this.Start = start;
        this.End = end;
        this.Point1 = start;
        this.Point2 = end;
    }

     /**
     * Constructs a new {@code Line} from the given coordinates of the start and end points.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.Point1=new Point (x1,y1);
        this.Point2=new Point(x2,y2);
        this.Start = this.Point1;
        this.End   = this.Point2;
    }


    /**
     * Returns the length of this line segment.
     * The length is computed using the Euclidean distance formula
     * between the start and end points.
     *
     * @return the length of this line
     */
    public double length() {
        length= ((Start.getX()-End.getX())*(Start.getX()-End.getX())+ (Start.getY()-End.getY())*(Start.getY()-End.getY()) );
        length= Math.sqrt(length); 
        return length; 
   }

    /**
     * Returns the middle point of this line segment.
     * The middle point is the average of the x and y coordinates
     * of the start and end points.
     *
     * @return a {@code Point} representing the middle of this line
     */
    public Point middle() { 
        this.midX= ((this.Start.getX()+this.End.getX())/2);
        this.midY= ((this.Start.getY()+this.End.getY())/2);
        return new Point (midX,midY);
   }
   
    /**
     * Returns the starting point of this line segment.
     *
     * @return the start {@code Point} of this line
     */
    public Point start() {
    return this.Start; 
    }

    /**
     * Returns the ending point of this line segment.
     *
     * @return the end {@code Point} of this line
     */
    public Point end() {
    return this.End;
    }

    /**
     * Checks whether this line segment intersects with another line segment.
     * The method uses the slope-intercept form of each line to calculate
     * a potential intersection point, and then verifies that this point
     * lies within the bounds of both segments.
     *
     * @param other the other {@code Line} to check intersection with
     * @return {@code true} if the two line segments intersect, {@code false} otherwise
     */
    public boolean isIntersecting(Line other) {
        double dx1=this.End.getX() - this.Start.getX();
        double dy1= this.End.getY() - this.Start.getY();
        double dx2= other.End.getX() - other.Start.getX();
        double dy2= other.End.getY() -other.Start.getY();

        this.m= (dy1 / dx1);
        this.n=(this.Start.getY() - (this.m*this.Start.getX()));
        double otherM= (dy2/ dx2);
        double otherN= (other.Start.getY() - (otherM *other.Start.getX()));
        if  (this.m == otherM){
            return false;
        }else{
            this.InterX = (otherN - this.n) / (this.m - otherM);
            this.InterY = (this.m * this.InterX) + this.n;
            boolean onThisLine =
            this.InterX >= Math.min(this.Start.getX(), this.End.getX()) &&
            this.InterX <= Math.max(this.Start.getX(), this.End.getX()) &&
            this.InterY >= Math.min(this.Start.getY(), this.End.getY()) &&
            this.InterY <= Math.max(this.Start.getY(), this.End.getY());

            boolean onOtherLine =
            this.InterX >= Math.min(other.Start.getX(), other.End.getX()) &&
            this.InterX <= Math.max(other.Start.getX(), other.End.getX()) &&
            this.InterY >= Math.min(other.Start.getY(), other.End.getY()) &&
            this.InterY <= Math.max(other.Start.getY(), other.End.getY());

        return onThisLine && onOtherLine;
        }
    }

    /**
     * Checks whether this line segment intersects with both of the given line segments.
     * The method returns {@code true} only if this line intersects {@code other1}
     * and also intersects {@code other2}.
     *
     * @param other1 the first line segment to check
     * @param other2 the second line segment to check
     * @return {@code true} if this line intersects both given lines, {@code false} otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return (isIntersecting(other1) && isIntersecting (other2));  
    }
  
    /**
     * Returns the intersection point between this line segment and another line segment.
     * If the two lines do not intersect, this method returns {@code null}.
     * <p>
     * This method relies on the last intersection point calculated by
     * {@link #isIntersecting(Line)}, so it first checks intersection
     * and then returns a new {@code Point} based on the stored intersection coordinates.
     *
     * @param other the other {@code Line} to find the intersection with
     * @return the intersection {@code Point} if the lines intersect; {@code null} otherwise
     */
    public Point intersectionWith(Line other) { 
        if (!isIntersecting(other)){
            return null;
        }else{
            return (new Point (this.InterX,this.InterY));
        }
    }

    /**
     * Checks whether this line is equal to another line.
     * Two lines are considered equal if they connect the same two x-coordinates,
     * regardless of the order of their endpoints.
     * <p>
     * Note: this implementation compares only the x-coordinates of the endpoints.
     *
     * @param other the {@code Line} to compare with
     * @return {@code true} if the lines are considered equal, {@code false} otherwise
     */
    public boolean equals(Line other) {
        return (this.Start.getX()== other.Start.getX() &&
            this.End.getX()== other.End.getX() ||
            this.End.getX()== other.Start.getX() &&
            this.Start.getX()== other.End.getX());
    }

    // Test code (commented out) left here for debugging and experimentation.
    // It is not part of the Line API required by the assignment.

    // public static void main(String[] args) {

      
    //     Point p1 = new Point(0, 0);
    //     Point p2 = new Point(4, 4);
    //     Line line1 = new Line(p1, p2);

     
    //     Line line2 = new Line(new Point(0, 4), new Point(4, 0));  
    //     Line line3 = new Line(new Point(0, 5), new Point(5, 5));  

        
    //     System.out.println("Length of line1: " + line1.length());

     
    //     System.out.println("Middle of line1: " + line1.middle());

       
    //     System.out.println("Start of line1: " + line1.start());
    //     System.out.println("End of line1: " + line1.end());

       
    //     System.out.println("line1 intersects line2? " + line1.isIntersecting(line2));
    //     System.out.println("line1 intersects line3? " + line1.isIntersecting(line3));

      
    //     System.out.println("line1 intersects with both line2 and line3? " + line1.isIntersecting(line2, line3));

        
    //     Point intersection = line1.intersectionWith(line2);
    //     if (intersection != null) {
    //         System.out.println("Intersection point of line1 & line2: " + intersection);
    //     } else {
    //         System.out.println("line1 and line2 do not intersect");
    //     }

        
    //     Line line4 = new Line(new Point(0, 0), new Point(4, 4));
    //     System.out.println("line1 equals line4? " + line1.equals(line4));
    // }


}


