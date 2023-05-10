package manfred.end.stream.lib;

import com.clearspring.analytics.stream.quantile.TDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HelloTDigest {
    public static void main(String[] args) {
        Random rand = new Random();
        List<Double> data = new ArrayList<>();
        TDigest digest = new TDigest(100);
        for (int i = 0; i < 1000000; ++i) {
            double d = rand.nextDouble();
            data.add(d);
            digest.add(d);
        }
        Collections.sort(data);
        for (double q : new double[]{0.1, 0.5, 0.9}) {
            System.out.println(
                    String.format("quantile=%.1f digest=%.4f exact=%.4f",
                            q, digest.quantile(q), data.get((int) (data.size() * q))));
        }
// quantile=0.1 digest=0.0998 exact=0.1003
// quantile=0.5 digest=0.5009 exact=0.5000
// quantile=0.9 digest=0.8994 exact=0.8998
    }
}
