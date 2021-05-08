package manfred.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import manfred.caffeine.loader.MyCacheLoader;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @author cuishilei
 * @date 2019/8/23
 */
public class CaffeineLoadingCacheTest {
    public static void main(String[] args) throws InterruptedException {
        Caffeine<Object, Object> objectObjectCaffeine = Caffeine.newBuilder();
        objectObjectCaffeine.expireAfterWrite(100, TimeUnit.MILLISECONDS);//基于时间失效->写入之后开始计时失效
        LoadingCache<Object, Object> cache = objectObjectCaffeine
                //同步加载和手动加载的区别就是在构建缓存时提供一个同步的加载方法
                .build(new MyCacheLoader());

        String key = "key";

        //获取 key1 对应的值
        Object value = cache.get(key);
        System.out.println("{k = " + key + ", v = " + value + "}");

        sleep(101);

        //批量获取
        Map<Object, Object> all = cache.getAll(Arrays.asList("key1", "key2", "key3"));
        System.out.println("all: " + all);
    }
}