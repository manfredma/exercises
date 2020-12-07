import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author manfred
 * @since 2019-12-27 下午7:00
 */
public class Main {
    @Test
    public void mergeMapValuesTest() {
        List<List<String>> xx = new ArrayList<>();
        List<String> xx1 = new ArrayList<>();
        xx1.add("xxx");
        xx.add(xx1);

        List<String> xxS = xx.stream().flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println(xxS);
    }

    @Test
    public void testException() {
        A a1 = new A();
        A a2 = new A();
        List<A> aList = new ArrayList<>();
        aList.add(a1);
        aList.add(a2);
        aList = aList.stream()
                .sorted(
                        Comparator
                                .comparing(A::getX1)
                                .thenComparing(A::getX2))
                .collect(Collectors.toList());
    }

    @Test
    public void testException2() {
        A a1 = new A();
        a1.x1 = 3;
        A a2 = new A();
        a2.x2 = 4;
        List<A> aList = new ArrayList<>();
        aList.add(a1);
        aList.add(a2);
        aList = aList.stream()
                .sorted(
                        Comparator
                                .comparing(A::getX1)
                                .thenComparing(A::getX2))
                .collect(Collectors.toList());
    }

    class A {
        Integer x1;
        Integer x2;

        public Integer getX1() {
            System.out.println("return x1");
            return x1;
        }

        public Integer getX2() {
            System.out.println("return x2");
            return x2;
        }
    }
}
