import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    public List<Bear> solvedBears;
    public List<Bed> solvedBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        solvedBears = quickSort(bears, beds).first();
        solvedBeds = quickSort(bears, beds).second();

    }



    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return solvedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return solvedBeds;
    }

    public static void partitionBed(List<Bed> unsorted, Bear pivot, List<Bed> less, List<Bed> equal, List<Bed> greater ){
        for(Bed bed : unsorted){
            int temp = pivot.compareTo(bed);
            if(temp < 0){
                greater.add(bed);
            }
            else if(temp == 0){
                equal.add(bed);
            }
            else{
                less.add(bed);
            }

        }
    }

    public static void partitionBear(List<Bear> unsorted, Bed pivot, List<Bear> less, List<Bear> equal, List<Bear> greater){
        for(Bear bear : unsorted){
            int temp = pivot.compareTo(bear);
            if(temp < 0){
                greater.add(bear);
            }
            else if(temp == 0){
                equal.add(bear);
            }
            else{
                less.add(bear);
            }

        }
    }

    public static <Item extends Comparable> List<Item> connect(List<Item> p1, List<Item > p2){
        List<Item> connect = new ArrayList<>();
        for(Item item : p1){
            connect.add(item);
        }
        for(Item item : p2){
            connect.add(item);
        }
        return connect;
    }

    public static Pair<List<Bear>, List<Bed>> quickSort(List<Bear> bears, List<Bed> beds){
        if(bears.size()<=1 || beds.size()<=1){
            return new Pair(bears, beds);
        }

        Bed pivot1 = beds.get(0);
        List<Bear> lessBear = new ArrayList<>();
        List<Bear> equalBear = new ArrayList<>();
        List<Bear> greaterBear = new ArrayList<>();

        List<Bed> lessBed = new ArrayList<>();
        List<Bed> equalBed = new ArrayList<>();
        List<Bed> greaterBed = new ArrayList<>();

        partitionBear(bears, pivot1, lessBear, equalBear, greaterBear);
        Bear pivot2 = equalBear.get(0);
        partitionBed(beds, pivot2, lessBed, equalBed, greaterBed);

        Pair<List<Bear>, List<Bed>> LessPair = quickSort(lessBear, lessBed);
        List<Bear> lessOfbear = LessPair.first();
        List<Bed>  lessOfbed = LessPair.second();

        Pair<List<Bear>, List<Bed>> GreaterPair = quickSort(greaterBear, greaterBed);
        List<Bear> greaterOfbear = GreaterPair.first();
        List<Bed> greaterOfbed = GreaterPair.second();

        List<Bear> temp1 = connect(connect(lessOfbear, equalBear), greaterOfbear);
        List<Bed> temp2 = connect(connect(lessOfbed, equalBed), greaterOfbed);

        return new Pair(temp1, temp2);


    }


}
