
package manfred.caffeine.eviction;

import com.github.benmanes.caffeine.cache.*;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class TimeBase1 {
    public static void main(String[] args) throws InterruptedException {
        //单个 key 的值加载
        LoadingCache<Object, Object> cache = Caffeine.newBuilder()
                //基于时间失效->写入之后开始计时失效
                .expireAfterWrite(2, TimeUnit.SECONDS)
                //or 基于时间失效->访问之后开始计时失效
                //.expireAfterAccess(10, TimeUnit.SECONDS)
                //自定义线程池异步执行 remove 监听
                .executor(Executors.newSingleThreadExecutor())
                .removalListener((k, v, removalCause) -> System.out.println("缓存失效了 removed " + k + " cause " + removalCause))
                //同步加载和手动加载的区别就是在构建缓存时提供一个同步的加载方法
                .build(key -> {
                    System.out.println("---exec load---");
                    return key + "_" + System.currentTimeMillis();
                });
        //放入缓存
        cache.put("k1", "v1");
        //准备失效
        sleep(3000);
        System.out.println("sleep done");
        System.out.println("我要开始取失效的缓存了");
        Object v1 = cache.get("k1");
        System.out.println("新值 " + v1);
        System.exit(1);
    }
}