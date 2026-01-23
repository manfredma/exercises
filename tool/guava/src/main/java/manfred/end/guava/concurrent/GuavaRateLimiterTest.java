package manfred.end.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.time.Duration;
import java.time.LocalDateTime;

public class GuavaRateLimiterTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 0; i < 10000; i++) {
            while (!rateLimiter.tryAcquire(1)) {

            }
            System.out.println(LocalDateTime.now() + ": acquire successful");
            if (System.currentTimeMillis() - start > 10000) {
                start = System.currentTimeMillis();
                rateLimiter.setRate(rateLimiter.getRate() * 2);
            }
        }
    }
}
