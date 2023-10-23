package manfred.end.diff;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.ListCompareAlgorithm;

public class JaversDemo {
    public static void main(String[] args) {
        Javers javers = JaversBuilder.javers()
                .withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
                .build();
        System.out.println(javers.compare(new char[]{'a','b','c','d','e'},
               new char[]{'a','c','d','e'}));

        System.out.println("========================");
        javers = JaversBuilder.javers()
                .withListCompareAlgorithm(ListCompareAlgorithm.SIMPLE)
                .build();

        System.out.println(javers.compare(new char[]{'a','b','c','d','e'},
                new char[]{'a','c','d','e'}));

        System.out.println("========================");

        javers = JaversBuilder.javers()
                .withListCompareAlgorithm(ListCompareAlgorithm.AS_SET)
                .build();

        System.out.println(javers.compare(new char[]{'a','b','c','d','e'},
                new char[]{'a','c','d','e'}));

    }
}
