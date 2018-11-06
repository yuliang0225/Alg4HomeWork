import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    /**Dequeue. A double-ended queue or deque (pronounced “deck”) is
     * a generalization of a stack and a queue that supports adding
     * and removing items from either the front or the back of the data structure. */

    private int size;
    private int arraySize;
    private Item[] items;
    private int first;
    private int last;

    // construct an empty deque
    public Deque(){
        size = 0;
        arraySize = 20;
        first = 0;
        last = 1;
        items = (Item []) new Object[arraySize];
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    private boolean isFull(){
        return size == items.length-2;
    }

    // Todo: add the item to the front
    public void addFirst(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        items[first] = item;
        first = subOne(first);
        size += 1;
        if (isFull()){
            resize(items.length*5);
        }
    }

    // Todo: add the item to the end
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        items[last] = item;
        last = plusOne(last);
        size += 1;
        if (isFull()){
            resize(items.length*5);
        }
    }

    // Todo: remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        first = plusOne(first);
        Item temp = items[first];
        size -= 1;
        if (size > 20 && size < items.length*0.25){
            resize((int)(items.length*0.5));
        }
        return temp;
    }
    // Todo: remove and return the item from the end

    public Item removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        last = subOne(last);
        Item temp = items[last];
        size -= 1;
        if (size > 20 && size < items.length*0.25){
            resize((int)(items.length*0.5));
        }
        return temp;
    }

    // Todo: return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator(){
        return new dequeIterator();
    }

    // Todo: unit testing (optional)
    public static void main(String[] args){
        Deque<Integer> test = new Deque<>();
//        System.out.println(test.removeFirst());
        test.addFirst(50);
        test.addFirst(60);
        test.addLast(70);
        test.addLast(80);
        test.addLast(90);
        System.out.println(test.size());
        System.out.println(test.removeFirst());
        System.out.println(test.size());

        for (int s : test){
            System.out.println(s);
        }
    }

    private class dequeIterator implements Iterator<Item>{
        private int ptr;
        private int count;
        public dequeIterator(){
            ptr = plusOne(first);
            count = 0;
        }
        @Override
        public boolean hasNext(){
            return (count < size);
        }
        @Override
        public Item next(){
            if(count >= size){
                throw new NoSuchElementException();
            }
            Item returnItem = items[ptr];
            ptr = plusOne(ptr);
            count += 1;
            return returnItem;
        }
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }


    /** plus one for pointer */
    private int plusOne(int pos){
        if (pos + 1 > items.length-1){
            return 0;
        }
        return pos + 1;
    }
    /** sub one for pointer */
    private int subOne(int pos){
        if (pos - 1 < 0){
            return items.length-1;
        }
        return pos - 1;
    }

    private void resize(int newArraySize){
        Item[] items_new = (Item[]) new Object[newArraySize];
//        int i = 1;
//        while (isEmpty()){
//            items_new[i] = removeFirst();
//            i += 1;
//        }

        if (last > first){
            System.arraycopy(items,first+1,items_new,1,size);
        } else{
            System.arraycopy(items,first+1,items_new,1,items.length-1-first);
            System.arraycopy(items,0,items_new,items.length-first,last);
        }
        first = 0;
        last = size+1;
        items = items_new;
    }

}
