import edu.princeton.cs.algs4.*;

public class Solver
{
    private Node lastNode;

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        MinPQ<Node> minPQ = new MinPQ<Node>();
        MinPQ<Node> twinPQ = new MinPQ<>();

        lastNode = new Node(initial);

        minPQ.insert(lastNode);
        twinPQ.insert(new Node(initial.twin()));

        while (!minPQ.isEmpty() && !twinPQ.isEmpty())
        {
            if (null != solve(minPQ)) return;

            if (null != solve(twinPQ))
            {
                lastNode = null;
                return;
            }
        }
    }

    private Node solve(MinPQ<Node> myPQ)
    {
        lastNode = myPQ.delMin();
        if ( lastNode.manhattanDistance == 0) return lastNode;

        Node tempNode;
        
        for ( Board myNeighbor: lastNode.currentBoard.neighbors())
        {
            if (!isNodePresentInAllParentNodes(myNeighbor, lastNode))
            {
                tempNode = new Node(myNeighbor);
                tempNode.prev = lastNode;
                tempNode.moves = lastNode.moves + 1;

                myPQ.insert(tempNode);
            }
        }
        return null;
    }

    private boolean isNodePresentInAllParentNodes(Board myNeighbor, Node parent)
    {
        if (parent != null)
        {
            parent = parent.prev;

            while (parent != null)
            {
                if (myNeighbor.equals(parent.currentBoard))
                    return true;

                parent = parent.prev;
            }
        }

        return false;
    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        return lastNode !=null;
    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if (lastNode!=null)  return lastNode.moves;
        return -1;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (!isSolvable()) return null;

        Stack<Board> myStack = new Stack<>();
        Node tempNode = lastNode;

        while (tempNode != null)
        {
            myStack.push(tempNode.currentBoard);
            tempNode = tempNode.prev;
        }
        return myStack;
    }

    private class Node implements Comparable<Node>
    {
        Board currentBoard;
        int moves;
        int manhattanDistance;
        Node prev = null;

        public Node(Board myBoard)
        {
            this.manhattanDistance = myBoard.manhattan();
            this.currentBoard = myBoard;
            this.moves = 0;
        }

        @Override
        public int compareTo(Node that) {

            int thisDistance = this.manhattanDistance + this.moves;
            int thatDistance = that.manhattanDistance + that.moves;

            return (thisDistance < thatDistance) ? -1 : 1;
        }
    }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
