package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer(3);
        //arb.dequeue();
        arb.enqueue("a");
        arb.enqueue("b");
        arb.enqueue("c");
        arb.dequeue();
        arb.enqueue("a");

        ArrayRingBuffer<String> arb2 = new ArrayRingBuffer(10);
        arb2.enqueue("a");
        arb2.enqueue("b");
        arb2.enqueue("c");

        arb.dequeue();
        arb2.dequeue();
        arb.enqueue("d");
        arb2.enqueue("d");




        if(arb.equals(arb2)){
            System.out.println("equal");
        }
        else{
            System.out.println("not equal");
        }



        String a = arb.dequeue();
        if(a.equals("a")) {
            System.out.println("delete1 success");
        }else{
            System.out.println("delete1 fail");
        }

        String b = arb.dequeue();
        if(b.equals("b")){
            System.out.println("delete2 success");
        }else{
            System.out.println("delete2 fail");
        }

        String c = arb.peek();
        if(c.equals("c")){
            System.out.println("peek success");
        }else{
            System.out.println("peek fail");
        }

        ArrayRingBuffer<Double> arb1 = new ArrayRingBuffer(10);
        System.out.println(arb1.peek());

        System.out.println(arb1.capacity());
    }
}
