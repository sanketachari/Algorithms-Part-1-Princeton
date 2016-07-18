import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;*/

public class Percolation {

    private WeightedQuickUnionUF uf;
    private int N;
    private boolean [][] openSites;
    public Percolation(int N)
    {
        if (N <= 0)
            throw new IllegalArgumentException();

        uf = new WeightedQuickUnionUF(N*N + 2);
        this.N = N;

        openSites = new boolean[N][N];

        //Connect top row to virtual top site
        for (int i = 0; i < N; i++)
        {
            uf.union(N*N, i);
        }

    }

    public void open(int i, int j)
    {
        if ((i < 1 || i > N) || (j < 1 || j > N))
            throw new IndexOutOfBoundsException();

        if (!this.isOpen(i, j))
        {
            this.openSites[i-1][j-1] = true;
            connectAdjacentSites(i, j);

            if (this.isFull(i, j))
            {
                for (int index = 0; index < this.N; index++)
                {
                    if (uf.connected((i-1)*N + j-1, N*(N-1)+index))
                        uf.union(N*N + 1, N*(N-1)+index);
                }

            }
        }

    }

    public boolean isOpen(int i, int j)
    {
        if ((i < 1 || i > N) || (j < 1 || j > N))
            throw new IndexOutOfBoundsException();

        return this.openSites[i-1][j-1];
    }

    public boolean isFull(int i, int j)
    {
        if ((i < 1 || i > N) || (j < 1 || j > N))
            throw new IndexOutOfBoundsException();

        if (this.isOpen(i, j))
        {
            return uf.connected(N*(i-1)+j-1, N*N);
        }

        return false;
    }

    public boolean percolates()
    {
        return uf.connected(N*N, N*N+1);
    }

    private void connectAdjacentSites(int row, int col)
    {
        int tvi1, tvi2;

        tvi2 = (row-1)*this.N+col-1;

        // Case 1: Left site
        if (col-1 > 0)
        {
            if (this.isOpen(row, col-1))
            {
                tvi1 = tvi2-1;

                if (!uf.connected(tvi1, tvi2))
                    uf.union(tvi1, tvi2);
            }
        }

        // Case 2: Right site

        if (col+1 <= this.N)
        {
            if (this.isOpen(row, col+1))
            {
                tvi1 = tvi2 + 1;

                if (!uf.connected(tvi1, tvi2))
                    uf.union(tvi1, tvi2);
            }
        }

        // Case 3: Top site
        if (row-1 > 0)
        {
            if (this.isOpen(row-1, col)) {
                tvi1 = tvi2 - this.N;

                if (!uf.connected(tvi1, tvi2))
                    uf.union(tvi1, tvi2);
            }
        }


        // Case 4: Bottom site
        if (row+1 <= this.N)
        {
            if (this.isOpen(row+1, col))
            {

                tvi1 = tvi2 + this.N;

                if (!uf.connected(tvi1, tvi2))
                    uf.union(tvi1, tvi2);
            }
        }

    }


    public static void main(String[] args)// throws IOException
    {

        Percolation p = new Percolation(4);

        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);
        p.open(3, 3);
        p.open(4, 3);
        p.open(4, 1);

        System.out.println("Does it percolate? : " + p.percolates());
        System.out.println("Is Full site? : " + p.isFull(4, 3));

        /*BufferedReader br = new BufferedReader(new FileReader(
        "C:\\Sanket\\Learning\\DSA\\ProgrammingAssignment1\\percolation\\input10-no.txt"));

        int N = Integer.parseInt(br.readLine());
        Percolation p1 = new Percolation(N);

        String nLocation;
        String[] ij;

        while((nLocation = br.readLine())!= null)
        {
            if(!nLocation.isEmpty()) {
                ij = nLocation.split("\\s+");
                p1.open(Integer.parseInt(ij[1]), Integer.parseInt(ij[2]));
            }

        }

        System.out.println("Does it percolate? : " + p1.percolates());*/

    }
}
