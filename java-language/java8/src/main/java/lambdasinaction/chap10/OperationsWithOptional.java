package lambdasinaction.chap10;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;

public class OperationsWithOptional {

    public static void main(String... args) {
        System.out.println(max(of(3), of(5)));
        System.out.println(max(empty(), of(5)));

        // 下面的逻辑中用的API是 Java9 中提供的，在 Java8 中无法编译
//        Optional<Integer> opt1 = of(5);
//        Optional<Integer> opt2 = opt1.or(() -> of(4));
//
//        System.out.println(of(5).or(() -> of(4)));
    }

    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
        return i.flatMap(a -> j.map(b -> Math.max(a, b)));
    }
}