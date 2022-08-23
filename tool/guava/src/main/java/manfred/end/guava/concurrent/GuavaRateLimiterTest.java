package manfred.end.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;

public class GuavaRateLimiterTest {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 0; i < 10; i++) {
            while (!rateLimiter.tryAcquire(1)) {

            }
            System.out.println(LocalDateTime.now() + ": acquire successful");
        }
    }
}
