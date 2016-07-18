import edu.princeton.cs.algs4.StdOut;

public class MergeSort
{
    private static int count = 0;
    private static Comparable[] aux;

    public static void sort( Comparable[] a)
    {
        Comparable[] aux = new Comparable[a.length];
        sort( a, aux, 0, a.length - 1);
        StdOut.println();
        StdOut.println("Count is "+ count + " & number of elements are "+ a.length);
    }

    public static void buSort(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];

        for (int sz = 1; sz < N; sz=2*sz)
            for (int lo=0; lo < N-sz; lo += 2*sz)
            {
                merge (a, aux, lo, lo+sz-1, Math.min(lo+2*sz-1, N-1));

                if (count == 7)
                {
                    StdOut.println("After 7th call to merge");

                    for ( int i=0; i < a.length; i++ )
                    {
                        StdOut.print ( a[i] + " ");
                    }
                }

            }
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if( hi <= lo) return;
        int mid = lo + ( hi - lo )/2;

        sort( a, aux, lo, mid );
        sort( a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);

        if (count == 7)
        {
            StdOut.println("After 7th call to merge");

            for ( int i=0; i < a.length; i++ )
            {
                StdOut.print ( a[i] + " ");
            }
        }

    }

    public static void merge( Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        int i = lo, j = mid + 1;

        for ( int k = lo; k <= hi; k++)
        {
            aux[k] = a[k];
        }

        for ( int k = lo; k <= hi; k++)
        {
            if(i > mid) a[k] = aux[j++];
            else if( j > hi) a[k] = aux[i++];
            else if ( isLess( aux[j], aux[i])) a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        count++;
    }

    public static boolean isLess( Comparable x, Comparable y)
    {
        return x.compareTo(y) < 0;

    }

    public static void main( String[] args)
    {
        Comparable[] a ={89, 75, 58, 79, 66, 93, 70, 32, 48, 96};

        // Bottom up Merging
        buSort(a);

        // Top down Merging
        //sort(a);

        StdOut.println();
        StdOut.println("Sorted array");

        for ( int i=0; i < a.length; i++ )
        {
            StdOut.print ( a[i] + " ");
        }
    }

}
