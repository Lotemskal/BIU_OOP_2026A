/** Name: Lotem Skaletzky
 * ID: 32251709
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 * The {@code AbstractArtDrawing} class is responsible for creating
 * and displaying an abstract drawing composed of random line segments.
 * 
 * The program:
 * 
 *   Generates random lines.
 *   Draws each line and its middle point.
 *   Marks intersection points between lines.
 *   Draws green segments between intersection points of line triples
 *   that intersect.
 * 
 */
public class AbstractArtDrawing {

    /**
     * Generates a random line segment within a fixed drawing area.
     * 
     * The x-coordinates are chosen in the range [0, 400),
     * and the y-coordinates in the range [0, 300).
     *
     * @return a new {@code Line} with random start and end coordinates
     */
    public static Line generateRandomLine() {
        Random rand = new Random();
        int x1 = rand.nextInt(400);
        int y1 = rand.nextInt(300);
        int x2 = rand.nextInt(400);
        int y2 = rand.nextInt(300);
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Draws the given line segment on the provided drawing surface in black.
     *
     * @param l the {@code Line} to draw
     * @param d the {@code DrawSurface} on which the line is drawn
     */ 
    public static void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine(
            (int) l.start().getX(),
            (int) l.start().getY(),
            (int) l.end().getX(),
            (int) l.end().getY()
        );
    }

    /**
     * Draws a small filled circle to represent a point on the drawing surface.
     *
     * @param mid the {@code Point} to be drawn
     * @param d   the {@code DrawSurface} on which the point is drawn
     * @param c   the {@code Color} used to draw the point
     */
    public static void drawPoint(Point mid, DrawSurface d, Color c) {
        d.setColor(c);
        d.fillCircle((int)mid.getX(), (int)mid.getY(), 3);
    }

    /**
     * Draws a green line segment between two given points on the drawing surface.
     *
     * @param a the first {@code Point} (start of the segment)
     * @param b the second {@code Point} (end of the segment)
     * @param d the {@code DrawSurface} on which the segment is drawn
     */
    public static void drawSegment(Point a, Point b, DrawSurface d) {
        d.setColor(Color.GREEN);
        d.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
    }

    /**
     * The entry point of the program.
     * 
     * This method:
     * 
     *   Creates a GUI window.
     *   Generates 10 random lines and draws them in black.
     *   Draws the middle point of each line in blue.
     *   Marks intersections between each pair of lines in red.
     *   For each triple of lines that intersect this way, draws green segments
     *   between their intersection points, forming triangles.
     * 
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[10];

        // Generate random lines, draw them, and mark their middle points in blue
        for (int i = 0; i < 10; i++) {
            lines[i] = generateRandomLine();
            Point mid= lines[i].middle();
            drawLine(lines [i], d);
            drawPoint(mid ,d,Color.BLUE );
        }

         // Mark intersection points between each pair of lines in red
        for (int i = 0; i < 10; i++) {
            for (int j= i+1; j < 10; j++){
                Point inter = lines[i].intersectionWith(lines[j]);
                if (inter !=null){
                    drawPoint(inter ,d,Color.RED );
                }
            }
        }

        // For each triple of lines that all intersect pairwise with the first,
        // draw green segments between their intersection points to form triangles
        for (int i = 0; i < 10; i++) {
            for (int j= i+1; j < 10; j++) {
                for (int h=j+1; h<10; h++){
                    if (lines [i].isIntersecting(lines[j],lines[h])){
                       Point a= lines[i].intersectionWith(lines[j]);
                       Point b= lines[i].intersectionWith(lines[h]);
                       Point c=lines[j].intersectionWith(lines[h]);
                       if (a == null || b == null || c== null){
                        continue;
                       }
                       drawSegment(a, b, d);
                       drawSegment(a, c, d);
                       drawSegment(b, c, d);
                    }
                }    
            }
        }
        gui.show(d);
    }
}
