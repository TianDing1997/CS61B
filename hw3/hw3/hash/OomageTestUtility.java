package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        List<List> a = new ArrayList();
        for(int i = 0; i<M; i++){
            List<Oomage> b = new ArrayList<Oomage>();
            a.add(b);
        }
        for(int i = 0; i< oomages.size(); i++){
            int number = (oomages.get(i).hashCode()& 0x7FFFFFFF) % M;
            a.get(number).add(oomages.get(i));
        }
        for(int i = 0; i<a.size();i++){
            if(a.get(i).size()<(N/50)||a.get(i).size()>N/2.5) return false;
        }
        return true;
    }
}