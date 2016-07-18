import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

    public static void sort( Comparable[] a )
    {
        /*StdRandom.shuffle(a);

        StdOut.println("After Shuffling");

        for( int i=0; i< a.length; i++)
        {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();*/

        sort( a, 0, a.length - 1);
    }

    public static void sort( Comparable[] a, int lo, int hi )
    {
        if( hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static int partition( Comparable[] a, int lo, int hi )
    {
        StdOut.print("low:= " + lo + " hi:= " + hi);
        int i = lo, j = hi + 1;

        while(true)
        {
            while( less( a[++i], a[lo] ) )
                if( i==hi ) break;

            while( less( a[lo], a[--j] ) )
                if( j==lo ) break;

            // in case of duplicate keys uncomment followin condition
            //if(!less(a[j], a[i]) && j>i) continue;
            if( j>i ) exch( a, i, j );
            else break;
        }

        exch( a, lo, j);
        StdOut.println(" j:= " + j);

        for( int k=0; k< a.length; k++)
        {
            StdOut.print(a[k] + " ");
        }
        StdOut.println();

        return j;

    }

    public static boolean less( Comparable x, Comparable y )
    {
        return x.compareTo(y) < 0;
    }

    public static void exch( Comparable[] a, int i, int j )
    {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main( String[] args )
    {
        Comparable[] a = {38, 55, 32, 35, 37, 43, 79, 30, 87, 83, 94, 14};
        //Comparable[] a = {'A', 'A', 'A', 'A', 'B', 'B', 'A', 'B', 'A', 'A', 'A', 'A' };

        StdOut.println("Before Sort");

        for( int i=0; i< a.length; i++)
        {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
        sort(a);

        StdOut.println("After Sort");

        for( int i=0; i< a.length; i++)
        {
            StdOut.print(a[i] + " ");
        }

    }
}