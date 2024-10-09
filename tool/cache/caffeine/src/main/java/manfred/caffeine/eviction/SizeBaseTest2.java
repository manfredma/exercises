package manfred.caffeine.eviction;

import com.github.benmanes.caffeine.cache.*;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class SizeBaseTest2 {

    public static void main(String[] args) {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //缓存最大权重值
                .maximumWeight(150)
                //自定义计算权重
                .weigher(new Weigher<Object, Object>() {
                    @Override
                    public @NonNegative int weigh(@NonNull Object k, @NonNull Object v) {
                        //这里为了简单，直接以 value 为权重
                        return (int) v;
                    }
                })
                .removalListener((k, v, removalCause) -> System.out.println("removed " + k + " cause " + removalCause))
                .build();

        for (int i = 0; i < 200; i++) {
            cache.put(i, 10);
        }
        cache.cleanUp();
    }
}