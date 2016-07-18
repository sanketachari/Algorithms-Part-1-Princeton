import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;


public class BST<Key extends Comparable<Key>, Value>
{
    private Node root;
    private int count;

    public BST() //create a symbol table
    {
        root = null;
    }
    private void put(Key key, Value val)   // put key-value pair into the table (remove key from table if value is null)
    {
       root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val)
    {
        if (x==null) return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        x.count = 1 + size(x.right) + size(x.left);
        return x;
    }

    private Value get(Key key) // value paired with key (null if key is absent)
    {
        Node x = root;

        while (x!=null)
        {
            int cmp = key.compareTo(x.key);

            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    private boolean contains(Key key) //is there a value paired with key?
    {
        return get(key) != null;
    }

    private boolean isEmpty() //is the table empty?
    {
        return root== null;
    }

    private int size() //number of key-value pairs in the table
    {
        return size(root);
    }

    private int size(Node x)
    {
        if (x == null) return 0;
        return x.count;
    }

    public Key floor(Key key)
    {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node floor(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key Ceiling(Key key)
    {
        Node x = Ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node Ceiling(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return Ceiling(x.right, key);

        Node t = Ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public int rank(Key key)
    { return rank(key, root); }

    private int rank(Key key, Node x)
    {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
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

    private int height(Node x)
    {
        if (x == null) return 0;

        int lheight = 1 + height(x.left);
        int rheight = 1+ height(x.right);

        if (lheight > rheight) return lheight;
        else return rheight;
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

    private void inorder(Node x, Queue<Key> q)
    {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    public void deleteMin()
    { root = deleteMin(root); }

    private Node deleteMin(Node x)
    {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key)
    { root = delete(root, key); }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x)
    {
        if (x == null) return null;
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node max(Node x)
    {
        if (x == null) return null;
        if (x.right == null) return x;
        return max(x.right);
    }


    private class Node
    {
        Key key;
        Value val;
        private int count;
        public Node left, right;

        public Node(Key key, Value val, int count)
        {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    public static void main (String[] args)
    {
        BST<Integer, Integer> myBST = new BST<>();

        myBST.put(83, 1);
        myBST.put(44, 2);
        myBST.put(94, 3);
        myBST.put(26, 4);
        myBST.put(60, 5);
        myBST.put(23, 6);
        myBST.put(39, 7);
        myBST.put(55, 8);
        myBST.put(77, 9);
        myBST.put(49, 10);
        myBST.put(56, 11);
        myBST.put(68, 12);

        //Queue<Integer> myQ = (Queue)myBST.keys();

        //StdOut.println("Element is " + myQ.peek());

        for (Integer item: myBST.keys())
        {
            StdOut.print(item + " ");
        }

        StdOut.println();
        StdOut.println("Deleting the elements");

        myBST.delete(49);
        myBST.delete(77);
        myBST.delete(44);

        for (Integer item: myBST.keys())
        {
            StdOut.print(item + " ");
        }

    }
}
