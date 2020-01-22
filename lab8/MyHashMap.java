import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class MyHashMap<K,V> implements Map61B<K,V> {
    private class Entry{
        K key;
        V value;
        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private HashSet<K> keyset;
    private List<List<Entry>> buckets;
    private int size;
    double loadFactor;
    private int numberOfbuckets;

    public MyHashMap(){
        this(16);
    }

    public MyHashMap(int initialSize){
        this(initialSize,0.75);
    }

    public MyHashMap(int initialSize, double loadFactor){
        this.buckets = new ArrayList<>();
        this.keyset = new HashSet<K>();
        this.numberOfbuckets = initialSize;
        this.size = initialSize;
        this.loadFactor = loadFactor;
        for (int i = 0; i < numberOfbuckets; i++) {
            buckets.add(new ArrayList<Entry>());
        }
    }

    @Override
    public void clear() {
        this.keyset = new HashSet<K>();
        this.buckets = new ArrayList<>();
        this.numberOfbuckets = 0;
        this.size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return keyset.contains(key);
    }

    @Override
    public V get(Object key) {
        V value = null;
        if(containsKey(key)){
            int index = Math.floorMod(key.hashCode(), numberOfbuckets);
            int bucketSize = buckets.get(index).size();
            for(int i = 0; i< bucketSize; i++){
                if(buckets.get(index).get(i).key.equals(key)){
                    value = buckets.get(index).get(i).value;
                    break;
                }
            }
        }

        return value;
    }

    @Override
    public int size() {
        return keyset.size();
    }

    public void resize(int newsize){
        List<List<Entry>> e = new ArrayList<>();
        for(int i = 0; i<newsize; i++){
            e.add(new ArrayList<Entry>());
        }
        for(int i = 0; i<numberOfbuckets; i++){
           for(int j = 0; j<buckets.get(i).size(); j++){
               int index = buckets.get(i).get(j).key.hashCode() % newsize;
               e.get(index).add(buckets.get(i).get(j));
           }
        }
        this.buckets = e;
        this.numberOfbuckets = newsize;
    }

    @Override
    public void put(Object key, Object value) {
        if(keyset.size() >= this.loadFactor * numberOfbuckets ){
            resize(numberOfbuckets*2);
        }
        int index = Math.floorMod(key.hashCode(), numberOfbuckets);
        if(containsKey(key)){
            int bucketSize = buckets.get(index).size();
            for(int i = 0; i<bucketSize; i++){
                if(buckets.get(index).get(i).key == key){
                    buckets.get(index).get(i).value = (V)value;
                }
            }
        }
        else{
            Entry e = new Entry((K)key, (V)value);
            buckets.get(index).add(e);
            keyset.add((K) key);

        }
    }

    @Override
    public Set keySet() {
        return keyset;
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return keyset.iterator();
    }
}
