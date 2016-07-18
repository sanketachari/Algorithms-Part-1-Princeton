import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    //private LineSegment[] segments;
    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
            throw new NullPointerException();

        for (int i = 0; i< points.length - 1; i++)
            for (int j = i+1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();


        Point[] pointsCopy = Arrays.copyOf(points,points.length);
        Arrays.sort(pointsCopy, points[0].SLOPE_ORDER);
        int numberofPoints = pointsCopy.length;

        for (int p=0; p < numberofPoints - 3; p++)
            for (int q=p+1; q < numberofPoints - 2; q++)
                for (int r=q+1; r < numberofPoints - 1; r++)
                {
                    if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[q].slopeTo(pointsCopy[r]))
                    {
                        for (int s= r+1; s< numberofPoints; s++)
                            if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[r].slopeTo(pointsCopy[s]))
                            {
                                LineSegment ls = new LineSegment(pointsCopy[p],pointsCopy[s]);
                                segments.add(ls);
                            }
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
