package es.datastructur.synthesizer;
import java.nio.Buffer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
       if(!isFull()){
           rb[last] = x;
           fillCount++;
           last++;
           if(this.last == rb.length){
               this.last = 0;
           }
       }
       else{
           throw new RuntimeException("Ring buffer overflow");
       }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.

        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T a = rb[first];
        rb[first] = null;
        fillCount = fillCount -1;
        first = first + 1;
        if(this.first == rb.length){
            this.first = 0;
        }

        return a;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T a = rb[first];
        return a;
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements  Iterator<T>{
        private int wizPos;
        public BufferIterator(){
            wizPos = first;
        }
        @Override
        public boolean hasNext(){
            if(wizPos == 0){
                return wizPos < fillCount;
            }
            else{
                return wizPos <= fillCount;
            }
        }

        @Override
        public T next() {
            T returnItem = rb[wizPos];
            wizPos++;
            if(wizPos == rb.length){
                wizPos = 0;
            }
            return returnItem;
        }

    }

    @Override
    public boolean equals(Object obj) {
        int i = first;
        boolean equal = true;
        if(!(obj instanceof  ArrayRingBuffer)){
            equal = false;
        }
        else if(((ArrayRingBuffer) obj).first !=this.first || ((ArrayRingBuffer) obj).last != this.last){
            equal = false;
        }
        else if(obj instanceof  ArrayRingBuffer){
            while(true){
                if(!(((ArrayRingBuffer) obj).rb[i].equals(this.rb[i]))){
                    equal = false;
                }
                i++;
                if(i == rb.length){
                    i = 0;
                }
                if(i == last-1){
                    break;
                }
            }

        }
        return equal;
    }
}

