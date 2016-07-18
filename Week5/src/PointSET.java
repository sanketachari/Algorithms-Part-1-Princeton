import edu.princeton.cs.algs4.*;
import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> set;

    public PointSET()                               // construct an empty set of points
    {
        set = new TreeSet<Point2D>();
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return set.isEmpty();
    }

    public int size()                         // number of points in the set
    {
        return set.size();
    }

    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if ( p == null)
            throw new NullPointerException("Insertion cannot be null");
        set.add(p);
    }

    public boolean contains(Point2D p)            // does the set contain point p?
    {
        if ( p == null )
            throw new NullPointerException("Presence of null pouit cannot be checked in set");
        return set.contains(p);
    }

    public void draw()                         // draw all points to standard draw
    {
        if (this.isEmpty()) return;
        for (Point2D p: set)
            StdDraw.point(p.x(), p.y());
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle
    {
        if ( rect == null )
            throw new NullPointerException("Rectangle cannot be empty");

        Queue<Point2D> q = new Queue<>();

        for (Point2D p: set)
            if ( rect.contains(p) )
                q.enqueue(p);

        return q;
    }

    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if ( p == null )
            throw new NullPointerException("Point whose nearest point is to be determined canot be empty");

        if ( this.isEmpty() ) return null;
        Point2D nearestPoint = null;
        double minDistance = Double.MAX_VALUE, d ;

        for ( Point2D that: set)
        {
            if ( p.equals(that) )
                continue;
            d =  p.distanceSquaredTo(that);
            if (d < minDistance)
            {
                minDistance = d;
                nearestPoint = that;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
        PointSET pset = new PointSET();
        Point2D p = new Point2D(0.2,0.3);
        pset.insert(p);
        RectHV rect = new RectHV(0.2, 0.2, 0.7, 0.7);

        for ( int i = 0; i < 100; i++)
            pset.insert(new Point2D(StdRandom.uniform(),StdRandom.uniform()));


        rect.draw();
        StdDraw.circle(p.x(),p.y(), p.distanceTo(pset.nearest(p)));
        pset.draw();

        StdDraw.setPenRadius(0.01);
        StdDraw.point(p.x(),p.y());
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(pset.nearest(p).x(), pset.nearest(p).y());
        StdDraw.show(0);

        StdOut.println("Nearest to " + p.toString() + " = " + pset.nearest(p));
        for (Point2D point : pset.range(rect))
            StdOut.println("In Range: " + point.toString());



    }
}
