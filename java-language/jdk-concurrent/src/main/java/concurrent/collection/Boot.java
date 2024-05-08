package concurrent.collection;

import java.util.ArrayList;
import java.util.List;

public class Boot {

    static List<Integer> x = new ArrayList<>();

    public static void main(String[] args) {
        x.add(1);
        x.add(2);
        Integer[] b = new Integer[x.size()];
        // startDel();
        Integer[] a = x.toArray(b);
        for (Integer integer : a) {
            System.out.println(integer);
        }
    }

    private static void startDel() {
        x.remove(1);
    }


}
