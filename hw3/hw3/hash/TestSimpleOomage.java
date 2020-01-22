package hw3.hash;

import org.junit.ComparisonFailure;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        List<SimpleOomage> a = new ArrayList<SimpleOomage>();
        int N = 256;
        int red = 0;
        int green = 0;
        int blue = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int k = 0; k<N; k++){
                    if(i%5 ==0 && j%5 ==0 && k%5 ==0){
                        red = i;
                        green = j;
                        blue = k;
                        SimpleOomage oo = new SimpleOomage(red, green, blue);
                        a.add(oo);
                    }
                }
            }
        }
        for(int i = 0; i<a.size(); i++){
            for(int j = i+1; j<a.size(); j++){
             if(a.get(i).hashCode() == a.get(j).hashCode()){
                 assertEquals(a.get(i), a.get(j));
             }
            }
        }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
//        int num = 0;
//        for(int i = 0; i<256; i++){
//            if(i%5 == 0){
//                num++;
//            }
//        }
//        System.out.println(num);
    }

}
