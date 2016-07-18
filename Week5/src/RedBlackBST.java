import edu.princeton.cs.algs4.*;


public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    Node root;

    public void put(Key key, Value val)
    {
        root = put( root, key, val);
    }
    private Node put(Node h, Key key, Value val)
    {
        if (h == null) return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);   // Lean left
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);   // Balance 4 node
        if (isRed(h.left) && isRed(h.right)) flipColors(h); // split 4- node

        return h;
    }

    public Value get(Key key)
    {
        Node x = root;
        while ( x!= null )
        {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    private Iterable<Key> keys() //all the keys in the table
    {
        /*Queue<Key> q = new Queue<Key>();
        inorder(root, q);*/

        Queue<Key> q = new Queue<Key>();
        int height = height(root);
        StdOut.println("Height of BST is " + height);

        for (int i = 0; i < height; i++)
        {
            levelOrder(root, i, q);
        }
        return q;
    }

    private void inorder(Node x, Queue<Key> q)
    {
        if (x == null) return;
        inorder(x.left, q);
        if (isRed(x) && x != root)
            q.enqueue(x.key);

        inorder(x.right, q);
    }

    private void levelOrder(Node x, int level, Queue<Key> q)
    {
        if (x == null) return;

        if (level == 0)
        {
            q.enqueue(x.key);
            return;
        }

        levelOrder(x.left, level - 1, q);
        levelOrder(x.right, level - 1, q);
    }

    private int height(Node x)
    {
        if (x == null) return 0;

        int lheight = 1 + height(x.left);
        int rheight = 1+ height(x.right);

        if (lheight > rheight) return lheight;
        else return rheight;
    }

    private boolean isRed(Node x)
    {
        if ( x == null ) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h)
    {
        Node x =  h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h)
    {
        Node x =  h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h)
    {
        assert !isRed(h);
        assert isRed(h.right);
        assert isRed(h.left);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private class Node
    {
        Key key;
        Value val;
        public Node left, right;
        boolean color;

        public Node( Key key, Value val, boolean color)
        {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    public static void main(String[] args)
    {
        RedBlackBST<Integer, Integer> rbTree = new RedBlackBST();

        /*rbTree.put(24, 1);
        rbTree.put(18, 2);
        rbTree.put(64, 3);
        rbTree.put(17, 4);
        rbTree.put(22, 5);
        rbTree.put(55, 6);
        rbTree.put(98, 7);
        rbTree.put(13, 8);
        rbTree.put(21, 9);
        rbTree.put(46, 10);
        rbTree.put(59, 11);
        rbTree.put(72, 12);
        rbTree.put(26, 13);*/


        rbTree.put(40, 1);
        rbTree.put(27, 2);
        rbTree.put(85, 3);
        rbTree.put(13, 4);
        rbTree.put(36, 5);
        rbTree.put(54, 6);
        rbTree.put(90, 7);
        rbTree.put(45, 8);
        rbTree.put(83, 9);
        rbTree.put(55, 10);

        rbTree.put(11, 11);
        rbTree.put(52, 12);
        rbTree.put(41, 13);

        for (Integer item: rbTree.keys())
        {
            StdOut.print(" " + item);

        }
    }
}
