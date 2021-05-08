package manfred.caffeine.loader;

import com.github.benmanes.caffeine.cache.CacheLoader;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MyCacheLoader implements CacheLoader<Object, Object> {


    //单个 key 的值加载
    @Nullable
    @Override
    public Object load(@NonNull Object key) throws Exception {
        System.out.println("---exec load---");
        return "load_one_" + System.currentTimeMillis();
    }

    //如果没有重写 loadAll 方法则默认的 loadAll 回循环调用 load 方法，一般重写优化性能
    @Override
    public @NonNull Map<Object, Object> loadAll(@NonNull Iterable<?> keys) throws Exception {
        System.out.println("---exec loadAll---");
        Map<Object, Object> data = new HashMap<>();
        for (Object key : keys) {
            data.put(key, "load_all_" + System.currentTimeMillis());
        }
        return data;
    }
}