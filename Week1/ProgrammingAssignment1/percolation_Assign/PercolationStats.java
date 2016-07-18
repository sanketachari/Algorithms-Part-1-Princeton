import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {

    private int N, T, row, col;
    private double []percolationThreshold;

    public PercolationStats(int N, int T)
    {
        this.N = N;
        this.T = T;

        Percolation []p = new Percolation[T];
        this.percolationThreshold = new double[T];
        if (N <= 0 || T <= 0)
        {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < T; i++)
        {
            int openedSites = 0;

            p[i] = new Percolation(N);

            while (!p[i].percolates())
            {
                //open sites
                row = StdRandom.uniform(1, this.N+1);
                col = StdRandom.uniform(1, this.N+1);

                if (!p[i].isOpen(row, col))
                {
                    p[i].open(row, col);
                    openedSites++;
                }

            }

            this.percolationThreshold[i] = (double) openedSites/(N*N);
        }

    }

    public double mean()
    {
        return StdStats.mean(this.percolationThreshold);
    }

    public double stddev()
    {
        if (this.T == 1)
            return Double.NaN;

        return StdStats.stddev(this.percolationThreshold);
    }

    public double confidenceLo()
    {
        return this.mean() - (1.96*this.stddev()/Math.sqrt(this.T));

    }

    public double confidenceHi()
    {
        return this.mean() + (1.96*this.stddev()/Math.sqrt(this.T));
    }


    public static void main(String []args)
    {
        int N, T;

        StdOut.print("Enter the number of sites: ");
        N = StdIn.readInt();

        StdOut.println();

        StdOut.print("Enter number of times percolation has to be performed: ");
        T = StdIn.readInt();

        PercolationStats ps = new PercolationStats(N, T);


        StdOut.println("mean                     = " + ps.mean());
        StdOut.println("stddev                   = " + ps.stddev());
        StdOut.println("95% confidence interval  = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
