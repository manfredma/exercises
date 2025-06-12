package manfred.end.java.lambda.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@FunctionalInterface
interface Print<T> {
    void print(T x);
}

public class Boot {
    public static void PrintString(String s, Print<String> print) {
        print.print(s);
    }

    public static void main(String[] args) {
        //PrintString("test", (x) -> System.out.println(x));

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("1");
        list.stream().collect(Collectors.toMap(a -> "k" + a, a -> "v" + a + Math.random()));
    }
}