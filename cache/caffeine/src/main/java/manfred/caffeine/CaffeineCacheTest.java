package manfred.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.lang.Thread.sleep;


public class CaffeineCacheTest {
    public static void main(String[] args) throws InterruptedException {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //基于时间失效->写入之后开始计时失效
                .expireAfterWrite(100, TimeUnit.MILLISECONDS)
                .build();

        //返回 key + 当前时间戳作为 value
        Function<Object, Object> getFuc = key -> "auto_" + System.currentTimeMillis();

        String key = "key";

        //获取 key1 对应的值，如果获取不到则执行 getFuc
        Object value = cache.get(key, getFuc);
        System.out.println("{k = " + key + ", v = " + value + "}");
        sleep(101);
        System.out.println("wake up after key timeout!");

        //获取 key1 对应的值，如果获取不到则返回 null
        value = cache.getIfPresent(key);
        System.out.println("{k = " + key + ", v = " + value + "}");

        //设置 key1 的值
        cache.put(key, "put_" + System.currentTimeMillis());
        value = cache.get(key, getFuc);
        System.out.println("{k = " + key + ", v = " + value + "}");

        ConcurrentMap<Object, Object> asMap = cache.asMap();
        System.out.println("all before invalidate: " + asMap);

        //删除 key1
        cache.invalidate(key);
        asMap = cache.asMap();
        System.out.println("all after invalidate: " + asMap);
    }
}