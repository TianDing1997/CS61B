import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K> ,V> implements Map61B<K,V> {
    private Node root;

    private class Node{
        private K key;
        private V value;
        private Node leftchild;
        private Node rightchild;
        private int size;

        public Node(K key, V value, int size ){
            root = null;
            this.key = key;
            this.value = value;
            this.size = size;
            this.leftchild = null;
            this.rightchild = null;
        }
    }
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    //new get
    public V get(Node x, K key){
        V value = null;
        if(x == null){
            return null;
        }
        int cmp = x.key.compareTo(key);
        if(cmp == 0){
            value = x.value;
        }
        else if(cmp < 0){
            get(x.leftchild, key);
        }
        else{
            get(x.rightchild, key);
        }
        return value;
    }

    @Override
    public int size() {
        return size(root);
    }

    //new size
    public int size(Node x){
        if (x == null) return 0;
        return x.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    //new put method
    public Node put(Node x, K key, V value){
        if(x == null ){
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.leftchild = put(x.leftchild, key, value);
        }
        else if(cmp > 0){
            x.rightchild = put(x.rightchild, key, value);
        }
        else{
            x.value = value;
        }
        x.size = 1 + x.leftchild.size + x.rightchild.size;
        return x;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
