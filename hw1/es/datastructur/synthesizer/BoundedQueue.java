package es.datastructur.synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();     // return size of the buffer
    int fillCount();    // return number of items currently in the buffer
    void enqueue(T x);  // add item x to the end
    T dequeue();        // delete and return item from the front
    T peek();           // return (but do not delete) item from the front

    default  boolean isEmpty(){
        boolean a = false;
        if(fillCount() == 0){
            a = true;
        }
        return a;
    }
    default boolean isFull(){
        boolean b  = false;
        if(fillCount() == capacity()){
            b = true;
        }
        return b;
    }

}
