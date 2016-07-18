import java.util.Scanner;
import java.util.Random;

public class QuickFind {

    private int[] a;

    public QuickFind(int N)
    {
        a = new int[N];
        for(int i=0; i < N; i++ )
        {
            a[i] = i;
        }
    }

    // Union Operation
    public void Union(int p, int q)
    {
        int pid = a[p];
        int qid = a[q];

        for(int i=0; i<a.length; i++)
        {
            if(a[i] == pid)
                a[i] = qid;
        }
    }

    // Find Operation
    /*public boolean Isconnected(int p, int q)
    {
        return a[p] == a[q];
    }*/

    //Print array
    public void FinalQuickFind()
    {
        for(int i=0; i < a.length; i++ )
        {
            System.out.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Random randnum = new Random();
        randnum.setSeed(395624);

        System.out.print("Enter number of elements: ");
        Scanner scan = new Scanner(System.in);

        QuickFind uf = new QuickFind(scan.nextInt());


        uf.Union(7, 3);
        uf.Union(9, 0);
        uf.Union(0, 1);
        uf.Union(5, 4);
        uf.Union(2, 5);
        uf.Union(1, 4);

        //System.out.println("Check whether 4 & 5 are connected : "+ uf.Isconnected(4, 5));
        //System.out.println("Check whether 1 & 5 are connected : "+ uf.Isconnected(1, 5));

        uf.FinalQuickFind();

    }

}
