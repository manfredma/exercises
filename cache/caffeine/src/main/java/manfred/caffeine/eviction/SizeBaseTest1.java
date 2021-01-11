package manfred.caffeine.eviction;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class SizeBaseTest1 {

    public static void main(String[] args) {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //缓存最大条数,超过这个条数就是驱逐缓存
                .maximumSize(20)
                .removalListener((k, v, removalCause) -> System.out.println("removed " + k + " cause " + removalCause.toString()))
                .build();

        for (int i = 0; i < 25; i++) {
            cache.put(i, i + "_value");
        }
        cache.cleanUp();
    }
}