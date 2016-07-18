import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by acharis on 2/14/2016.
 */
public class Subset {

    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty())
        {
            rq.enqueue(StdIn.readString());

        }

        int k = Integer.parseInt(args[0]);

        while ( k > 0 )
        {
            StdOut.println(rq.dequeue());
            k--;
        }
    }
}
