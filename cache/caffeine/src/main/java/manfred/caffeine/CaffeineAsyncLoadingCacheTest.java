package manfred.caffeine;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import manfred.caffeine.loader.MyCacheLoader;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class CaffeineAsyncLoadingCacheTest {
    public static void main(String[] args) throws InterruptedException {
        AsyncLoadingCache<Object, Object> asyncLoadingCache = Caffeine.newBuilder()
                //基于时间失效->写入之后开始计时失效
                .expireAfterWrite(100, TimeUnit.MILLISECONDS)
                .buildAsync(new MyCacheLoader());

        String key1 = "key1";

        //获取值，为空则执行异步加载方法
        CompletableFuture<Object> future = asyncLoadingCache.get(key1);
        //异步获取值
        future.thenAccept(o -> System.out.println(key1 + "->" + o));
        //批量异步获取
        CompletableFuture<Map<Object, Object>> all = asyncLoadingCache.getAll(Arrays.asList("key1", "key2", "key3"));
        all.thenAccept(System.out::println);
        sleep(50);
        examineCache(asyncLoadingCache);
        // 待数据超时
        sleep(51);
        examineCache(asyncLoadingCache);
    }

    private static void examineCache(AsyncLoadingCache<Object, Object> asyncLoadingCache) {
        ConcurrentMap<Object, CompletableFuture<Object>> asMap;
        asMap = asyncLoadingCache.asMap();
        if (!asMap.isEmpty()) {
            asMap.forEach((k, v) -> System.out.println("k=" + k + ", v=" + v.getNow("not completed")));
        } else {
            System.out.println("缓存中没有数据");
        }
    }
}