import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class HeapSortTest {

    @Test public void sort(){

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(7);
        list.add(9);
        list.add(5);
        list.add(3);
        HeapSort.sort(list);

        ArrayList<Integer> resultat = new ArrayList<Integer>();

        resultat.add(1);
        resultat.add(3);
        resultat.add(5);
        resultat.add(7);
        resultat.add(9);

        assertArrayEquals(list.toArray(),resultat.toArray());
    }

    @org.junit.Test
    public void sort1() {
    }
}