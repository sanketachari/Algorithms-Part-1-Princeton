import edu.princeton.cs.algs4.*;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private Node firstNode;
    private Node  lastNode, tempNode = null;
    private int count = 0;

    public Deque(){

        firstNode = null;
    }

    public boolean isEmpty(){

        return firstNode == null;
    }

    public int size(){

        return count;
    }

    public void addFirst(Item item){

        if(item == null){
            throw new java.lang.NullPointerException();
        }

        tempNode = new Node();
        tempNode.item = item;

        if(isEmpty()){
            firstNode = tempNode;
            lastNode = firstNode;
        }
        else
        {
            tempNode.next = firstNode;
            firstNode.prev = tempNode;
            firstNode = tempNode;
        }

        count++;
    }

    public void addLast(Item item){

        if(item == null){
            throw new java.lang.NullPointerException();
        }

        tempNode = new Node();
        tempNode.item = item;

        if(isEmpty()){
            firstNode = tempNode;
            lastNode = firstNode;

        }
        else
        {
            lastNode.next = tempNode;
            tempNode.prev = lastNode;
            lastNode = tempNode;
        }

        count++;
    }

    public Item removeFirst(){

        if(isEmpty()){
            throw new NoSuchElementException();
        }

        tempNode = firstNode;
        firstNode = firstNode.next;
        firstNode.prev = null;
        count--;

        Item item = (Item) tempNode.item;
        tempNode = null;
        return item;
    }

    public Item removeLast(){

        Item item;

        if(isEmpty()){
            throw new NoSuchElementException();
        }
        else if(firstNode == lastNode){

            item = (Item) firstNode.item;
            firstNode = null;
        }
        else
        {
            item = (Item) lastNode.item;
            lastNode.prev.next= null;

            tempNode = lastNode;
            lastNode = lastNode.prev;
            tempNode = null;
        }

        count--;
        return item;
    }

    public Iterator<Item> iterator(){
        return new itemIterator();
    }

    private class itemIterator implements Iterator<Item>{


        public boolean hasNext(){
            return firstNode != null;
        }

        public Item next(){
            if(firstNode == null){
                throw new java.util.NoSuchElementException();
            }

            Item item = (Item) firstNode.item;
            if(firstNode.next == null){
                firstNode = null;
            }
            else
            {
                firstNode = firstNode.next;
            }


            return item;
        }

        public void remove(){
            throw new java.lang.UnsupportedOperationException();
        }

    }

    private class Node<Item>{
        public Item item;
        public Node next = null;
        public Node prev = null;
    }


    public static void main(String []args){

        Deque<Integer> dq = new Deque<Integer>();

        Iterator<Integer> i = dq.iterator();

        dq.addFirst(2);
        dq.addFirst(3);
        dq.addFirst(4);
        dq.addFirst(5);
        dq.addFirst(6);

        dq.addLast(0);
        dq.addLast(1);

        dq.removeFirst();
        dq.removeLast();

        while(i.hasNext()){
            Integer item = i.next();
            StdOut.print (item + " " );
        }

        StdOut.println();
        StdOut.println("Total elements present are: "+ dq.size() );

    }


}
