import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    // construct an empty randomized queue
    private int size;
    private Item[] queue;
    private int arraySize;
    private int count = 0;


    public RandomizedQueue(){
        size = 0;
        arraySize = 50;
        queue = (Item[]) new Object[arraySize];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        queue[size] = item;
        size += 1;
        resize();
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int returnIndex = StdRandom.uniform(size);
        Item returnItem = queue[returnIndex];
        queue[returnIndex] = queue[size-1];
        size -= 1;
        count += 1;
        resize();
        return returnItem;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int returnIndex = StdRandom.uniform(size);
        return queue[returnIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new randomizedQueueIterator();
    }

    private class randomizedQueueIterator implements Iterator<Item> {
        private int ptr;
        private int[] randomIndex;
        public randomizedQueueIterator(){
            ptr = 0;
            randomIndex = StdRandom.permutation(size);
        }
        @Override
        public boolean hasNext(){
            return (ptr != size);
        }
        @Override
        public Item next(){
            if(ptr >= size){
                throw new NoSuchElementException();
            }
            Item returnItem = queue[randomIndex[ptr]];
            ptr += 1;
            return returnItem;
        }
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (optional)
    public static void main(String[] args){
        RandomizedQueue<Integer> test = new RandomizedQueue<>();
        System.out.println(test.isEmpty());

        for (int i = 0; i < 100; i += 1){
            test.enqueue(i);
        }
        System.out.println(test.size());
        System.out.println(test.dequeue());
        System.out.println(test.size());
        System.out.println(test.sample());
        System.out.println(test.size());
        System.out.println("------");
        for (int s : test){
            System.out.println(s);
        }

    }

    private boolean isFull(){
        return size == queue.length;
    }

    private void resize(){
        if (isFull()) {
            Item[] items_new = (Item[]) new Object[queue.length*5];
            System.arraycopy(queue,0,items_new,0,size);
            queue = items_new;
        }
        if (count > 50 && size < queue.length/4){
            Item[] items_new = (Item[]) new Object[(int)(queue.length*0.5)];
            System.arraycopy(queue,0,items_new,0,size);
            queue = items_new;
            count = 0;
        }
        return;
    }

}
