import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;

public class Point implements Comparable<Point>
{
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public final Comparator<Point> SLOPE_ORDER = new slopeOrder();

    public Point(int x, int y)                         // constructs the point (x, y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw()                               // draws this point
    {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that)                   // draws the line segment from this point to that point
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString()                           // string representation
    {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
    {
        if (this.y < that.y || this.x < that.x)
            return -1;
        else if (this.y == that.y && this.x == that.x)
            return 0;

        return 1;
    }

    public double slopeTo(Point that)       // the slope between this point and that point
    {
        double deltaX = this.x - that.x;
        double deltaY = this.y - that.y;

        if (deltaX == 0 && deltaY == 0)
            return Double.NEGATIVE_INFINITY;
        if (deltaX == 0)
            return Double.POSITIVE_INFINITY;
        if (deltaY == 0)
            return 0.0;

        return deltaY/deltaX;
    }

    public class slopeOrder implements Comparator<Point>
    {
        public int compare(Point A, Point B)
        {
            double slopeA = slopeTo(A);
            double slopeB = slopeTo(B);

            if (slopeA > slopeB)
                return 1;
            else if (slopeB >slopeA)
                return -1;
            return 0;

        }
    }

    /*public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point point1, Point point2) {
                double slopeDiff = slopeTo(point1) - slopeTo(point2);
                if (slopeDiff > 0) {
                    return 1;
                } else if (slopeDiff < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
    }*/

    public static void main(String[] args)
    {
         /* YOUR CODE HERE */
        Point p = new Point(0, 0);
        Point q1 = new Point(0, 2);
        Point q2 = new Point(2, 0);
        Point q3 = new Point(3, 4);
        Point q4 = new Point(4, 3);

        StdOut.println(p.slopeTo(q1));
        StdOut.println(p.slopeTo(q2));
        StdOut.println(p.slopeTo(q3));
        StdOut.println(p.slopeTo(q4));
        StdOut.println(p.slopeTo(p));
        StdOut.println("==================");
        StdOut.println(p.compareTo(q1));
        StdOut.println(p.compareTo(q2));
        StdOut.println(p.compareTo(q3));
        StdOut.println(p.compareTo(q4));

    }
}
