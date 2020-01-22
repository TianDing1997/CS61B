package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class KDTreeTest {

    //test Naive Point Set
    public void testNaive(){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        if(ret.getX() == 3.3 && ret.getY() == 4.4){
            System.out.println("test naive pass");
        }

    }

    //random test
    public void randomTest(){
        boolean Rightness = true;
        List<Point> list = new ArrayList<Point>();
        List<Point> nearestPoint = new ArrayList<>();
        for(int i = 0; i<10000; i++){
            double x = StdRandom.uniform(0.0, 10000.0);
            double y = StdRandom.uniform(0.0, 10000.0);
            Point newpoint = new Point(x,y);
            list.add(newpoint);
        }

        for(int i =0; i<1000; i++){
            double x = StdRandom.uniform(0.0, 10000.0);
            double y = StdRandom.uniform(0.0, 10000.0);
            Point newpoint = new Point(x,y);
            nearestPoint.add(newpoint);
        }

        KDTree kd = new KDTree(list);
        NaivePointSet np = new NaivePointSet(list);
        for(int i = 0; i<nearestPoint.size(); i++){
            Point nearest1 = kd.nearest(nearestPoint.get(i).getX(),nearestPoint.get(i).getY());
            Point nearest2 = np.nearest(nearestPoint.get(i).getX(),nearestPoint.get(i).getY());
            if(!nearest1.equals(nearest2)){
                System.out.println("KD tree nearest function test fail");
                Rightness = false;
                break;
            }
        }
        if(Rightness == true){
            System.out.println("random test pass");
        }
    }

    public void singleTest(){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        KDTree kd = new KDTree(List.of(p1, p2, p3));
        if(kd.nearest(3.0,4.0).equals(nn.nearest(3.0,4.0))){
            System.out.println("single Test pass");
        }
        else{
            System.out.println("single test fail");
        }
    }

    public void timeTest(){
        List<Point> list = new ArrayList<Point>();
        List<Point> nearestPoint = new ArrayList<>();
        for(int i = 0; i<100000; i++){
            double x = StdRandom.uniform(0.0, 100000.0);
            double y = StdRandom.uniform(0.0, 100000.0);
            Point newpoint = new Point(x,y);
            list.add(newpoint);
        }

        for(int i =0; i<10000; i++){
            double x = StdRandom.uniform(0.0, 10000.0);
            double y = StdRandom.uniform(0.0, 10000.0);
            Point point = new Point(x,y);
            nearestPoint.add(point);
        }

        KDTree kd = new KDTree(list);
        NaivePointSet np = new NaivePointSet(list);

        long start = System.currentTimeMillis();
        for(int i = 0; i<nearestPoint.size(); i++){
            kd.nearest(nearestPoint.get(i).getX(),nearestPoint.get(i).getY());
        }
        long end = System.currentTimeMillis();
        System.out.println("my kd tree nearest function time: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for(int i = 0; i<nearestPoint.size(); i++){
            np.nearest(nearestPoint.get(i).getX(),nearestPoint.get(i).getY());
        }
        end = System.currentTimeMillis();
        System.out.println("Naive point set nearest function time: " + (end - start)/1000.0 +  " seconds.");

    }

    public static void main(String[] args){
        KDTreeTest test = new KDTreeTest();
        test.testNaive();
        test.randomTest();
        test.singleTest();
        test.timeTest();
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0,0);
        cache.put(0,1);
        cache.con


    }
}


