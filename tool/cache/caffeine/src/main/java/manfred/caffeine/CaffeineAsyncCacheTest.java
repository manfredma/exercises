package manfred.caffeine;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.lang.Thread.sleep;

public class CaffeineAsyncCacheTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AsyncCache<Object, Object> asyncCache = Caffeine.newBuilder()
                .expireAfterWrite(100, TimeUnit.MILLISECONDS)
                .buildAsync();

        //返回 key + 当前时间戳作为 value
        Function<Object, Object> getFuc = key -> key + "_" + System.currentTimeMillis();

        String key1 = "key1";

        //异步手动加载返回的不是值，而是 CompletableFuture
        CompletableFuture<Object> future = asyncCache.get(key1, getFuc);
        //异步获取结果,对高性能场景有帮助
        future.thenAccept(o -> {
            //输出结果可以看到打印时间比生成时间晚
            System.out.println(System.currentTimeMillis() + "->" + o);
        });

        sleep(200);

        //如果 cache 中 key 为空，直接返回 null，不为空则异步取值
        CompletableFuture<Object> ifPresent = asyncCache.getIfPresent(key1);
        if (ifPresent == null) {
            System.out.println("null");
        } else {
            //异步取值
            ifPresent.thenAccept(o -> System.out.println(System.currentTimeMillis() + "->" + o));
        }
        //批量异步取值，取不到则加载值
        CompletableFuture<Map<Object, Object>> all = asyncCache.getAll(Arrays.asList("key1", "key2", "key3"), new Function<Iterable<?>, Map<Object, Object>>() {
            @Override
            public Map<Object, Object> apply(Iterable<?> keys) {
                Map<Object, Object> data = new HashMap<>();
                for (Object key : keys) {
                    data.put(key, key + "_all_" + System.currentTimeMillis());
                }
                return data;
            }
        });
        //批量异步获取
        all.thenAccept(System.out::println);

        sleep(200);

        ConcurrentMap<Object, CompletableFuture<Object>> asMap = asyncCache.asMap();
        System.out.println("all  after timeout: " + asMap);
    }
}