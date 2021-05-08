import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Longs {
    public static void main(String[] args) {
        List<String> x = new ArrayList<>();
        Map<String, Long> cache = new HashMap<>();

        for (int i = 0; i < 10_000_000; i++) {
            Long value = (long) (Math.random() * 100_000_000L);
            x.add(String.valueOf(value));
            cache.put(String.valueOf(value), value);

        }

        x.forEach(a -> Long.parseLong(a));
        x.forEach(a -> com.google.common.primitives.Longs.tryParse(a));

        long begin = System.currentTimeMillis();
        x.forEach(a -> Long.parseLong(a));
        System.out.println("Cost:=" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        x.forEach(a -> com.google.common.primitives.Longs.tryParse(a));
        System.out.println("Cost:=" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        x.forEach(a -> cache.get(a));
        System.out.println("Cost:=" + (System.currentTimeMillis() - begin));
    }
}
