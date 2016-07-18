import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private double[] slopes;

    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null)
            throw new NullPointerException();

        for (int i = 0; i< points.length - 1; i++)
            for (int j = i+1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();


        for (int i=0; i< points.length - 3; i++ )
        {
            Arrays.sort(points, i, points.length - 1, points[i].SLOPE_ORDER);

            Point tempPoint = null;
            int count =0;
            for (int j = i+1; j < points.length - 1; j++)
            {
                if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[j+1]))
                {
                    tempPoint = points[j];
                    count++;
                }
            }
            if (count > 3)
            {
                LineSegment ls = new LineSegment(points[i+1],tempPoint);
                segments.add(ls);
            }

        }

    }
    public int numberOfSegments()        // the number of line segments
    {
        return segments.size();
    }
    public LineSegment[] segments()                // the line segments
    {
        return segments.toArray(new LineSegment[segments.size()]);

    }

    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
