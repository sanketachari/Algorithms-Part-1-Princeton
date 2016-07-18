import edu.princeton.cs.algs4.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int count = 0;
    private Node firstNode;
    private Node lastNode, tempNode, oldTempNode;

    public RandomizedQueue(){
        firstNode = null;
    }

    public boolean isEmpty(){
        return firstNode == null;
    }

    public int size(){
        return count;
    }

    public void enqueue( Item item ){

        if(item == null){
            throw new java.lang.NullPointerException();
        }

        if(isEmpty()){
            firstNode = new Node();
            firstNode.item = item;
            lastNode = firstNode;
        }
        else
        {
            tempNode = new Node();
            tempNode.item = item;
            lastNode.next = tempNode;
            lastNode = tempNode;
        }

        count++;
    }

    public Item dequeue()
    {
        if( isEmpty() ){
            throw new NoSuchElementException();
        }

        int itemIndex;
        Item item;

        if( count > 1){

            itemIndex = StdRandom.uniform(0, count);

            if ( itemIndex == 0 ){
                tempNode = firstNode;
                firstNode = firstNode.next;
            }
            else
            {
                tempNode = firstNode;
                oldTempNode = tempNode;

                while(itemIndex != 0){
                    oldTempNode = tempNode;
                    tempNode = tempNode.next;
                    itemIndex--;
                }
                if(tempNode.next == null){
                    oldTempNode.next = null;
                }
                else{
                    oldTempNode.next = tempNode.next;
                }
            }

            item = (Item) tempNode.item;
            tempNode = null;
        }
        else
        {
            item = (Item) firstNode.item;
            firstNode = null;
        }

        count--;
        return item;

    }

    public Item sample(){

        if( isEmpty() ){
            throw new NoSuchElementException();
        }

        Item item;
        int itemIndex = StdRandom.uniform(0, count);

        tempNode = firstNode;

        while(itemIndex != 0){
            tempNode = tempNode.next;
            itemIndex--;
        }

        item = (Item) tempNode.item;
        return item;


    }


    public Iterator<Item> iterator() {
        return new randomizedIterator();
    }

    private class randomizedIterator implements Iterator{

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
        Item item;
        private Node next = null;
    }


    public static void main(String []args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        Iterator<Integer> i = rq.iterator();

        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);

        StdOut.println("Sample Item: " + rq.sample() + " " + rq.sample());

        StdOut.println("Dequeued Item: " + rq.dequeue() + " " + rq.dequeue() + " " + rq.dequeue());


        while(i.hasNext()){
            Integer item = i.next();
            StdOut.print (item + " " );
        }

        StdOut.println();
        StdOut.println("Total elements present are: "+ rq.size() );

    }

}
