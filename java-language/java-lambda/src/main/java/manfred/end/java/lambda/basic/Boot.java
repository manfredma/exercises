package manfred.end.java.lambda.basic;

@FunctionalInterface
interface Print<T> {
    void print(T x);
}

public class Boot {
    public static void PrintString(String s, Print<String> print) {
        print.print(s);
    }

    public static void main(String[] args) {
        PrintString("test", (x) -> System.out.println(x));
    }
}