/*

public class ST<Key, Value>
{
    private Node first;
    private int count;

    public ST() //create a symbol table
    {
        first = null;
    }
    private void put(Key key, Value val)   // put key-value pair into the table (remove key from table if value is null)
    {
        if (val == null)
        {
            //delete(key);
        }

        Node temp = traverse(key);

        //key is not present so create new node at begining
        if (temp == null)
        {
            temp = new Node();
            temp.key = key;
            temp.val = val;

            temp.nxt = first;
            first = temp;
            count ++;

            return;
        }

        // key is already present, update the value of the node
        temp.val = val;
    }

    private Value get(Key key) // value paired with key (null if key is absent)
    {
        Node temp = traverse(key);

        if (temp == null) return null;

        return temp.val;
    }

    private void delete(Key key) //remove key(and its value) from table
    {
        put(key, null);
    }
    private boolean contains(Key key) //is there a value paired with key?
    {
        return get(key) != null;
    }

    private boolean isEmpty() //is the table empty?
    {
        return first== null;
    }

    private int size() //number of key-value pairs in the table
    {
        return count;
    }

    private Iterable<Key> keys() //all the keys in the table
    {

    }

    private Node traverse(Key key)
    {
        Node current = first;

        while (current != null)
        {
            if (key == current.key)
                break;

            current = current.nxt;
        }

        // Return null if key is not present
        if (current == null)
        {
            return null;
        }

        return current;
    }

    private class Node
    {
        Key key;
        Value val;
        public Node nxt = null;
    }
}
*/
