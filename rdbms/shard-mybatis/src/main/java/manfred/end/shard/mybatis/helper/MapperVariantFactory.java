package manfred.end.shard.mybatis.helper;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import static org.slf4j.LoggerFactory.getLogger;

public class MapperVariantFactory {

    private static final Logger LOGGER = getLogger(MapperVariantFactory.class);

    public static final Map<Object, TargetHolder> cache = new ConcurrentHashMap<>();

    private MapperVariantFactory() {
    }

    private static class TargetHolder {
        final AtomicReference<Object> master = new AtomicReference<>(null);
        final AtomicReference<Object> archive = new AtomicReference<>(null);
    }

    public static enum ExecutorType {
        NORMAL, MASTER, ARCHIVE
    }

    public static abstract class PostCondition<R> {
        protected final List<R> result = new ArrayList<>();

        protected abstract boolean accept(List<R> list);
    }
}
