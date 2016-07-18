import edu.princeton.cs.algs4.StdOut;

public class HeapSort {

    public static void sort(Comparable[] pq)
    {
        int N = pq.length - 1;

        for (int k = N/2; k >= 1; k--)
            sink(pq, k, N);

        StdOut.println("After For Loop:");

        while (N > 1)
        {
            exch(pq,1, N);
            sink(pq, 1, --N);
        }
    }


    private static void sink(Comparable[] pq, int k, int N)
    {
        while (2*k <= N )
        {
            int j = 2*k;
            if (j < N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }

        printPQ(pq);
        StdOut.println();
    }

    private static boolean less(Comparable[] pq, int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static  void exch(Comparable[] pq, int i, int j)
    {
        Comparable temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private static void printPQ(Comparable[] pq)
    {
        int N = pq.length;
        StdOut.println();
        for (int i=1; i< N; i++)
        {
            StdOut.print(pq[i]+ " ");
        }
    }

    public static void main(String[] args)
    {
        HeapSort hs = new HeapSort();

        Comparable[] pq = new Comparable[]{null,56,43,82,41,78};

        hs.sort(pq);

        StdOut.println("Printing final sorted heap");
        hs.printPQ(pq);

    }
}
