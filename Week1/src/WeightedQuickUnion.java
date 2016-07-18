import java.util.Scanner;
import java.util.Random;


/**
 * Created by acharis on 1/27/2016.
 */
public class WeightedQuickUnion {

   private int[] id,sz;

    public WeightedQuickUnion(int N)
    {
        id = new int[N];
        sz = new int[N];
        for(int i=0; i<N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int root(int p)
    {
        int i = id[p];
        while(i != id[i]) i = id[i];
        return i;
    }

/*        public boolean IsConnected(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        return i == j;
    }*/

    public void Union(int q, int p)
    {
        int i = root(p);
        int j = root(q);

        if(sz[i] > sz[j]) {
            id[j] = i;
            sz[i] += sz[j];
        }
        else
        {
            id[i] = j;
            sz[j] += sz[i];
        }

    }

    public void FinalQuickFind() {
        for(int i=0; i<id.length; i++)
        {
            System.out.print(id[i] + " ");
        }

    }

    public void FinalSizeQuickFind() {
        for(int i=0; i<sz.length; i++)
        {
            System.out.print(sz[i] + " ");
        }

    }

    public static void main(String[] args) {
    /* TODO Auto-generated method stub */

        Random randnum = new Random();
        randnum.setSeed(593165);

        System.out.print("Enter number of elements: ");
        Scanner scan = new Scanner(System.in);

        WeightedQuickUnion uf = new WeightedQuickUnion(scan.nextInt());

        uf.Union(1, 6);
        uf.Union(5, 9);
        uf.Union(6, 0);
        uf.Union(8, 2);
        uf.Union(7, 6);
        uf.Union(2, 5);
        uf.Union(4, 0);
        uf.Union(2, 6);
        uf.Union(5, 3);

        System.out.print("Final Array is : ");

        uf.FinalQuickFind();

        System.out.println();
        System.out.print("Final Size Array is : ");
        uf.FinalSizeQuickFind();


        /*System.out.println("Check whether '4' & '5' are connected : "+ uf.IsConnected(4, 5));
        System.out.println("Check whether 1 & 5 are connected : "+ uf.IsConnected(1, 5));*/

    }

}
