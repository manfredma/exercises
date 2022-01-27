package manfred.end.invoke;

import java.util.stream.Stream;

public class LambdaTest {

    public static void main(String[] args) {
        long lengthyColors = Stream.of("Red", "Green", "Blue").filter(c -> c.length() > 3).count();

    }
}
