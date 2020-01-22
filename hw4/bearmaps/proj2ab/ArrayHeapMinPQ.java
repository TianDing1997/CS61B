package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private List<Node>  P;
    private HashMap<T, Integer> items;



    public ArrayHeapMinPQ(){
        P = new ArrayList<>();
        items = new HashMap<>();
    }

    public void add(T item, double priority) {
        if(!contains(item)){
            Node newnode = new Node(item, priority);
            P.add(newnode);
            items.put(item, size()-1);
            swim(size()-1);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean contains(T item) {
        return items.containsKey(item);
    }


    @Override
    public T getSmallest() {
        return P.get(0).item;
    }

    @Override
    public T removeSmallest() {
        T smallest = getSmallest();
        Node temper = P.get(size()-1);
        P.remove(size()-1);
        if(P.size() > 0){
            P.set(0, temper);
            sink(0);
        }
        return smallest;
    }

    @Override
    public int size() {
        return this.P.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if(!contains(item)){
            throw new NoSuchElementException();
        }else{
            int index = items.get(item);
            P.get(index).setPriority(priority);
            swim(index);
            sink(index);
        }
    }

    private int parent(int k){
        return (k-1) / 2;
    }

    private int leftchild(int k){
        return k*2 + 1;
    }

    private int rightchild(int k){
        return k*2 + 2;
    }

    private void swap(int i, int j){
        Node temper = P.get(i);
        P.set(i, P.get(j));
        P.set(j, temper);
        items.put(P.get(i).item, i);
        items.put(P.get(j).item, j);

    }

    private void swim(int k){
        if( k > 0 ){
            if(P.get(parent(k)).priority > P.get(k).priority){
                swap(k, parent(k));
                swim(parent(k));
            }
        }
    }

    private void sink(int k){
        int halfsize = (size() - 1)/2;
        if(k <= halfsize){
            if(P.get(leftchild(k)).priority < P.get(k).priority){
                swap(k, leftchild(k));
                sink(leftchild(k));
            }
            else if(P.get(rightchild(k)).priority < P.get(k).priority){
                swap(k,rightchild(k));
                sink(rightchild(k));
            }
        }
    }

    private class Node{
        private T item;
        private double priority;

        public Node(T item, double priority){
            this.item = item;
            this.priority = priority;
        }

        public void setPriority(double priority){
            this.priority = priority;
        }
    }
}
