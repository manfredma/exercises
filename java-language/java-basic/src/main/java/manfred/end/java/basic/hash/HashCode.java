package manfred.end.java.basic.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashCode {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(12);
        integerList.add(11);
        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(11);
        integerList2.add(12);
        System.out.println(Objects.hash(integerList));
        System.out.println(Objects.hash(integerList2));
    }
}
