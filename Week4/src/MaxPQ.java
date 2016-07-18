import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key extends Comparable<Key>>
{
    private int N;
    private Key[] pq;


    public MaxPQ(int capacity)
    {
        pq = (Key[])new Comparable[capacity + 1];
        //pq = (Key[])new Comparable[]{null, 94, 74, 66, 61, 43, 29, 57, 33, 44, 26};//, null, null, null};
        //N = 10;
    }

    public void insert(Key x)
    {
        pq[++N] = x;
        swim(N);
    }

    public Key delMax()
    {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;
        return max;
    }

    private boolean isEmpty()
    {
        return N==0;
    }

    private void sink(int k)
    {
        while (2*k <= N )
        {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k)
    {
        while (k > 1 && less(k/2, k))
        {
            exch(k, k/2);
            k = k/2;
        }
    }

    private boolean less(int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j)
    {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void printPQ()
    {
        StdOut.println();
        for (int i=1; i<=N; i++)
        {
            StdOut.print(pq[i]+ " ");
        }
    }

    public static void main(String[] args)
    {
        MaxPQ myPQ = new MaxPQ(13);

        /*myPQ.insert(93);
        myPQ.insert(77);
        myPQ.insert(38);
        myPQ.insert(75);
        myPQ.insert(74);
        myPQ.insert(22);
        myPQ.insert(27);
        myPQ.insert(28);
        myPQ.insert(57);
        myPQ.insert(52);*/

        StdOut.println("After Insertion");
        //myPQ.printPQ();
        StdOut.println();

        /*myPQ.insert(70);
        myPQ.insert(15);
        myPQ.insert(34);

        StdOut.println("After inserting 3 new integers");*/

        myPQ.delMax();
        myPQ.delMax();
        myPQ.delMax();

        StdOut.println("After deleting 3 items: ");
        myPQ.printPQ();
    }


}
