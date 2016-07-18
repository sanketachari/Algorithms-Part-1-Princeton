import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by acharis on 4/12/2016.
 */
public class AVLBST<Key extends Comparable<Key>, Value> {

    private class Node
    {
        Node left, right = null;
        Key key;
        Value val;
        int height;

        Node(Key key, Value val)
        {
            this.key = key;
            this.val = val;
            this.height = 1;
        }
    }

    Node root = null;

    public int max(int h1, int h2)
    {
        if(h1 > h2)
            return h1;
        else
            return h2;
    }

    public int height(Node current)
    {
        if (current == null)
            return 0;
        else
            return current.height;
    }

    public int balance(Node current)
    {
        return height(current.left) - height(current.right);
    }

    public Node rotateLeft(Node current)
    {
        Node x = current.right;
        current.right = x.left;
        x.left = current;
        return x;
    }

    public Node rotateRight(Node current)
    {
        Node x = current.left;
        current.left = x.right;
        x.right = current;
        return x;
    }

    public void inOrder(Node current, Queue<Key> q)
    {
        if (current == null) return;
        inOrder(current.left, q);
        q.add(current.key);
        inOrder(current.right, q);
    }

    public void preOrder(Node current, Queue<Key> q)
    {
        if (current == null) return;
        q.add(current.key);
        preOrder(current.left, q);
        preOrder(current.right, q);

    }

    public void postOrder(Node current, Queue<Key> q)
    {
        if (current == null) return;
        postOrder(current.left, q);
        postOrder(current.right, q);
        q.add(current.key);
    }

    private Iterable<Key> keys()
    {
        Queue<Key> q = new LinkedList<Key>();
        postOrder(root,q);
        return q;
    }

    public void put( Key key, Value val )
    {
        root = put(root, key, val);
    }

    public Node put(Node h, Key key, Value val)
    {
        if (h == null) return new Node(key, val);

        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, val);
        else if(cmp > 0)
            h.right = put(h.right, key, val);
        else
            h.val = val;

        h.height = max(height(h.left), height(h.right)) + 1;
        int balance = balance(h);

        // Right Right
        if (balance < -1 && key.compareTo(h.right.key) > 0)
            return rotateLeft(h);
        // Right Left
        else if (balance < -1 && key.compareTo(h.right.key) < 0)
        {
            h.right = rotateRight(h.right);
            return rotateLeft(h);
        }
        // Left Left
        else if (balance > 1 && key.compareTo(h.left.key) < 0)
            return rotateRight(h);
        // Left Right
        else if (balance > 1 && key.compareTo(h.left.key) > 0)
        {
            h.left = rotateLeft(h.left);
            return rotateRight(h);
        }

        return h;
    }

    public Value get (Key key)
    {
        Node x = root;

        if (x != null)
        {
            int cmp = key.compareTo(key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x =x.right;
            else
                return x.val;
        }

        return null;
    }

    public Node minValueNode(Node current) {
         /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }
    public  Node deleteNode(Node current, Key key) {

        // STEP 1: PERFORM STANDARD BST DELETE
        if (current == null) {
            return current;
        }

        // If the key to be deleted is smaller than the root's key,
        // then it lies in left subtree
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            root.left = deleteNode(root.left, key);
        }

        // If the key to be deleted is greater than the root's key,
        // then it lies in right subtree
        else if (cmp > 0) {
            current.right = deleteNode(current.right, key);
        }

        // if key is same as root's key, then this is the node
        // to be deleted
        else {

            // node with only one child or no child
            if ((current.left == null) || (current.right == null)) {
                Node temp = null;
                if (temp == current.left) {
                    temp = current.right;
                } else {
                    temp = current.left;
                }

                // No child case
                if (temp == null) {
                    //temp = current;
                    current = null;
                } else // One child case
                {
                    current = temp; // Copy the contents of the non-empty child
                }
            } else {

                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                Node temp = minValueNode(current.right);

                // Copy the inorder successor's data to this node
                current.key = temp.key;

                // Delete the inorder successor
                current.right = deleteNode(current.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (current == null) {
            return current;
        }

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        current.height = max(height(current.left), height(current.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = balance(current);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && balance(current.left) >= 0) {
            return rotateRight(current);
        }

        // Left Right Case
        if (balance > 1 && balance(current.left) < 0) {
            root.left = rotateLeft(current.left);
            return rotateRight(current);
        }

        // Right Right Case
        if (balance < -1 && balance(current.right) <= 0) {
            return rotateLeft(current);
        }

        // Right Left Case
        if (balance < -1 && balance(current.right) > 0) {
            current.right = rotateRight(current.right);
            return rotateLeft(current);
        }

        return current;
    }

    public static void main(String[] args)
    {
        AVLBST<Integer, Character> avl = new AVLBST<Integer, Character>();
        
        avl.put(40, 'J');
        avl.put(27, 'I');
        avl.put(85, 'A');
        avl.put(13, 'F');
        avl.put(36, 'G');
        avl.put(54, 'B');
        avl.put(90, 'C');
        avl.put(45, 'H');
        avl.put(83, 'D');
        avl.put(55, 'E');

        System.out.println();

        for (Integer i: avl.keys())
            System.out.print(i + " ");

        System.out.println();

    }
}
