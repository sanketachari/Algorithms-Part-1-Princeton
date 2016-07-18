import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board
{
    private int [][] myBoard;
    private int size;

    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    {
        this.size = blocks.length;
        this.myBoard = new int[size][size];

        for (int i = 0; i < size; i++)
            for ( int j = 0; j < size; j++)
                this.myBoard[i][j] = blocks[i][j];
    }

    public int dimension()                 // board dimension N
    {
        return myBoard.length;
    }

    public int hamming()                   // number of blocks out of place
    {
        int[][] goalBoard = target();
        int N = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                if (this.myBoard[i][j] == 0) continue;
                if (goalBoard[i][j] != this.myBoard[i][j]) N++;
            }
        return N;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int[][] goalBoard = target();
        int N, d =0, mDistance = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                N = this.myBoard[i][j];
                if (N == 0) continue;

                for (int a = 0; a < size; a++)
                {
                    for (int b = 0; b < size; b++)
                    {
                         if (goalBoard[a][b] == N)
                        {
                            d =  Math.abs( (b - j) ) + Math.abs( (a - i) );
                            break;
                        }
                    }
                    if (d > 0)
                        break;
                }

                mDistance += d;
                d =0;
            }
        return mDistance;
    }

    public boolean isGoal()                // is this board the goal board?
    {
       return manhattan() == 0;

    }

    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        Board twinBoard = null;
        int[][] copy = copyOfArray();

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                if (copy[i][j] == 0) continue;

                twinBoard = this.swapLeft(copy, i ,j);

                if (twinBoard == null)
                    twinBoard = this.swapRight(copy, i, j);

                if (twinBoard != null)
                    return twinBoard;
            }
        return  twinBoard;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == this) {
            return true;
        }
        if (null == y) {
            return false;
        }
        if (!this.getClass().equals(y.getClass())) {
            return false;
        }

        Board thatBoard = (Board) y;

        if (this.dimension() != thatBoard.dimension()) {
            return false;
        }
        if (this.manhattan() != thatBoard.manhattan()) {
            return false;
        }

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (this.myBoard[i][j] != thatBoard.myBoard[i][j])
                    return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Queue<Board> q = new Queue<>();
        Board[] neighbour = new Board[4];
        int[][] copy;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                copy = copyOfArray();

                if (copy[i][j] == 0)
                {
                    neighbour[0] = this.swapTop( copy, i, j);
                    if (neighbour[0] != null)
                    {
                        q.enqueue(neighbour[0]);
                        copy = copyOfArray();
                    }

                    neighbour[1] = this.swapDown(copy, i, j);
                    if (neighbour[1] != null)
                    {
                        q.enqueue(neighbour[1]);
                        copy = copyOfArray();
                    }


                    neighbour[2] = this.swapRight(copy, i, j);
                    if (neighbour[2] != null)
                    {
                        q.enqueue(neighbour[2]);
                        copy = copyOfArray();
                    }

                    neighbour[3] = this.swapLeft(copy, i, j);
                    if (neighbour[3] != null)
                        q.enqueue(neighbour[3]);

                    return q;
                }
            }
        }
        return null;
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", myBoard[i][j]));
            }
            s.append("\n");
        }
        return s.toString();

    }

    private int[][] target()
    {
        int N = 1;
        int[][] goalBoard = new int[size][size];
        for(int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                goalBoard[i][j] = N++;
            }
        }
        goalBoard[size-1][size-1] = 0;
        return goalBoard;
    }

    private int[][] copyOfArray()
    {
        int[][] copy = new int[size][size];

        for(int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                copy[i][j] = myBoard[i][j];
            }
        }
        return copy;
    }

    private Board swapTop(int[][] blocks,int i, int j)
    {
        if ( i == 0 ) return null;
        if ( blocks[i-1][j] == 0 ) return null;

        Board tBoard = new Board(blocks);
        int temp = tBoard.myBoard[i][j];

        tBoard.myBoard[i][j] = tBoard.myBoard[i-1][j];
        tBoard.myBoard[i-1][j] = temp;

        return tBoard;
    }

    private Board swapDown(int[][] blocks,int i, int j)
    {
        if ( i == size - 1 ) return null;
        if ( blocks[i+1][j] == 0 ) return null;


        Board dBoard = new Board(blocks);
        int temp = dBoard.myBoard[i][j];

        dBoard.myBoard[i][j] = dBoard.myBoard[i+1][j];
        dBoard.myBoard[i+1][j] = temp;

        return dBoard;
    }

    private Board swapLeft(int[][] blocks,int i, int j)
    {
        if ( j == 0 ) return null;
        if ( blocks[i][j-1] == 0 ) return null;

        Board lBoard = new Board(blocks);
        int temp = lBoard.myBoard[i][j];

        lBoard.myBoard[i][j] = lBoard.myBoard[i][j-1];
        lBoard.myBoard[i][j-1] = temp;

        return lBoard;
    }

    private Board swapRight(int[][] blocks,int i, int j)
    {
        if ( j == size - 1 ) return null;
        if ( blocks[i][j+1] == 0 ) return null;

        Board rBoard = new Board(blocks);
        int temp = rBoard.myBoard[i][j];

        rBoard.myBoard[i][j] = rBoard.myBoard[i][j+1];
        rBoard.myBoard[i][j+1] = temp;

        return rBoard;
    }


    public static void main(String[] args) // unit tests (not graded)
    {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();

        Board initial = new Board(blocks);

        StdOut.println("Initial Board");
        StdOut.println( initial.toString() );
        StdOut.println("Manhattan Distance : " + initial.manhattan() );
        StdOut.println("Hamming Distance : " + initial.hamming() );

        StdOut.println("Twin Board");
        Board twinBoard = initial.twin();
        StdOut.println( twinBoard.toString() );

        for (Board current: initial.neighbors())
        {
            StdOut.println("Neighbour: ");
            StdOut.println(current.toString());
        }




    }
}
