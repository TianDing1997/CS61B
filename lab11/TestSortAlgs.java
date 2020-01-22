import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas.enqueue("fuck");
        tas.enqueue("shit");
        tas.enqueue("damn");
        tas.enqueue("damn");
        tas.enqueue("damn");
        tas.enqueue("fuck");
        Queue<String> sorted = QuickSort.quickSort(tas);
        if(isSorted(sorted)){
            System.out.println("quick Sort Success");
        }
        else{
            System.out.println("quick Sort Fail");
        }
    }

    @Test
    public void testMergeSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        Queue<String> sorted = MergeSort.mergeSort(tas);
        if(isSorted(sorted)){
            System.out.println("merge Sort Success");
        }
        else{
            System.out.println("mergeSort Fail");
        }
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args){
        TestSortAlgs test = new TestSortAlgs();
        test.testMergeSort();
        test.testQuickSort();
    }

}

