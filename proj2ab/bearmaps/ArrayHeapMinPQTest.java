package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayHeapMinPQTest {
    private ArrayHeapMinPQ P;
    private NaiveMinPQ NP;
    public ArrayHeapMinPQTest(){
        P = new ArrayHeapMinPQ();
        NP = new NaiveMinPQ();
    }
    // add testing
    public void testAdd(){
        P.add("a", 10);
        P.add("c", 8);
        P.add("d", 11);
        if(P.contains("a") && P.contains("c") && P.contains("d")){
            System.out.println("add test success");
            System.out.println("\n");
        }
    }

    //contain testing
    public void testContain(){
        if(P.contains("a") && !P.contains("h")){
            System.out.println("contain test success");
            System.out.println("\n");
        }
    }

    //get smallest testing
    public void testGet(){
        if(P.getSmallest().equals("c")){
            System.out.println("get smallest success");
            System.out.println("\n");
        }
    }

    //test remove smallest
    public void testRemove(){
        P.removeSmallest();
        if(P.getSmallest().equals("a")) {
            System.out.println("remove smallest success");
            System.out.println("\n");
        }
    }

    //test size
    public void testSize(){
        if(P.size() == 2){
            System.out.println("size test success");
            System.out.println("\n");
        }
    }

    //test ChangePriority
    public void testChangePriority(){
        P.changePriority("d", 8);
        if(P.getSmallest().equals("d")){
            System.out.println("change priority success");
            System.out.println("\n");
        }
    }

    public void addtimingTest(){
        long start = System.currentTimeMillis();
        for(int i = 0; i< 10000000; i++){
            int item =  i;
            double priority = StdRandom.uniform(1.0, 10000000000.0);
            P.add(item,priority);
        }
        long end = System.currentTimeMillis();
        System.out.println("my MinPQ add operation in 100000 times total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for(int i = 0; i< 10000000; i++){
            int item = i;
            double priority = StdRandom.uniform(1.0, 10000000000.0);
            NP.add(item,priority);
        }
        end = System.currentTimeMillis();
        System.out.println("Naive MinPQ add operation in 100000 times total time elapsed: " + (end - start)/1000.0 +  " seconds.");


    }

    public void containtimingtest(){
        long start = System.currentTimeMillis();
        for(int i = 0; i< 10000; i++){
            int item =  i;
            P.contains(item);
        }
        long end = System.currentTimeMillis();
        System.out.println("my MinPQ contain operation in 100000 times total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for(int i = 0; i< 10000; i++){
            int item = i;
            NP.contains(item);
        }
        end = System.currentTimeMillis();
        System.out.println("Naive MinPQ contain operation in 100000 times total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }

    public void gettimingtest(){
        long start = System.currentTimeMillis();
        P.getSmallest();
        long end = System.currentTimeMillis();
        System.out.println("my MinPQ get operation total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        NP.getSmallest();
        end = System.currentTimeMillis();
        System.out.println("my MinPQ get operation times total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }

    public void removetimingtest(){
        long start = System.currentTimeMillis();
        P.removeSmallest();
        long end = System.currentTimeMillis();
        System.out.println("my MinPQ remove operation total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        NP.removeSmallest();
        end = System.currentTimeMillis();
        System.out.println("my MinPQ remove operation times total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }

    public void sizetimetesting(){
        long start = System.currentTimeMillis();
        P.size();
        long end = System.currentTimeMillis();
        System.out.println("my MinPQ size operation total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        NP.size();
        end = System.currentTimeMillis();
        System.out.println("my MinPQ size operation times total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }

    public void changetimetesting(){
        long start = System.currentTimeMillis();
        for(int i = 0; i< 10000; i++){
            int item =  i;
            double priority = StdRandom.uniform(10000000000.0, 1000000000000.0);
            P.changePriority(item, priority);
        }
        long end = System.currentTimeMillis();
        System.out.println("my MinPQ change operation in 100000 times total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for(int i = 0; i< 10000; i++){
            int item = i;
            double priority = StdRandom.uniform(10000000000.0, 1000000000000.0);
            NP.changePriority(item, priority);
        }
        end = System.currentTimeMillis();
        System.out.println("Naive MinPQ change operation in 100000 times total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }



    public static void main(String[] args){
        ArrayHeapMinPQTest test = new ArrayHeapMinPQTest();
        test.testAdd();
        test.testContain();
        test.testGet();
        test.testRemove();
        test.testSize();
        test.testChangePriority();
        test.addtimingTest();

        System.out.println("\n");
        test.containtimingtest();

        System.out.println("\n");
        test.gettimingtest();

        System.out.println("\n");
        test.removetimingtest();

        System.out.println("\n");
        test.sizetimetesting();

        System.out.println("\n");
        test.changetimetesting();








    }
}
