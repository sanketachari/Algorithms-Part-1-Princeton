import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class LinkedListDemo {

    private Node current, first, last, temp;
    private static int count = 0;

    public LinkedListDemo()
    {
        first = null;
        last = null;

    }

    public void addLast(int item)
    {
        temp = new Node();
        temp.item = item;

        if (last == null)
        {
            first = temp;
            last = first;
        }
        else
        {
            last.nxt = temp;
            temp.prev = last;
            last = temp;
        }

        count++;

    }

    public int removeFromLast ( int pos)
    {
        int item, N = pos;
        temp = last;

        if (pos < 0 || pos > count || last == null)
        {

            throw new NoSuchElementException();
        }

        if (count > 1)
        {
            while (N!=1 && temp!= null)
            {
                temp = temp.prev;
                N--;
            }

            item = temp.item;
        }
        else if ( pos==1)
        {
            item = first.item;
            first = null;
        }
        else
        {
            throw new NoSuchElementException();
        }

        count--;
        temp = null;
        return item;
    }

    public class Node
    {
        private int item;
        private Node nxt = null, prev = null;
    }


    public static void main(String[] args)
    {
        LinkedListDemo myList = new LinkedListDemo();


        myList.addLast(10);
        myList.addLast(20);
        myList.addLast(30);
        myList.addLast(40);
        myList.addLast(50);


        StdOut.println("Removing from last, item is : " + myList.removeFromLast(1));



    }
}
