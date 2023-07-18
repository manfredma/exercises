package manfred.end.shard.mybatis.interceptors;

import manfred.end.shard.mybatis.annotation.ShardConfig;
import manfred.end.shard.mybatis.policy.ArchiveNamePolicy;
import manfred.end.shard.mybatis.policy.ShardPolicy;
import org.slf4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class ShardTableSupport {

    private static final Logger LOGGER = getLogger(ShardTableSupport.class);

    public static final String MASTER_HINT = "/*{\"router\":\"m\"}*/";
    public static final String PRESS_HINT = "/*{\"mode\":\"shadow\"}*/";
    public static final String MASTER_PRESS_HINT = "/*{\"router\":\"m\", \"mode\":\"shadow\"}*/";

    public static final ThreadLocal<Boolean> forceMaster = ThreadLocal.withInitial(() -> Boolean.FALSE);
    public static final ThreadLocal<Boolean> userArchive = ThreadLocal.withInitial(() -> Boolean.FALSE);
    public static final ThreadLocal<Boolean> shardKey = ThreadLocal.withInitial(() -> null);

    private ShardTableSupport() {

    }

    private static final Map<String, ShardConf> policyMap = new ConcurrentHashMap<>();

    static class ShardConf {
        static final ShardConf EMPTY = new ShardConf(0, false, null, null);

        public final int shardCount;
        public final boolean hasShadow;
        public final ShardPolicy shardPolicy;
        public final ArchiveNamePolicy archiveNamePolicy;


        public ShardConf(int shardCount, boolean hasShadow, ShardPolicy shardPolicy, ArchiveNamePolicy archiveNamePolicy) {
            this.shardCount = shardCount;
            this.hasShadow = hasShadow;
            this.shardPolicy = shardPolicy;
            this.archiveNamePolicy = archiveNamePolicy;
        }

        boolean isEmpty() {
            return this == EMPTY;
        }
    }

    public static ShardConf getConf(String id) {
        int pos = id.lastIndexOf('.');
        String className = id.substring(0, pos);
        ShardConf shardConf = policyMap.get(className);
        if (shardConf != null) {
            return shardConf;
        }
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ShardConfig shardConfig = (ShardConfig) clazz.getAnnotation(ShardConfig.class);

        if (shardConfig == null) {
            policyMap.putIfAbsent(className, ShardConf.EMPTY);
            return ShardConf.EMPTY;
        }

        try {
            ShardPolicy shardPolicy = shardConfig.shardPolicy().newInstance();
            ArchiveNamePolicy archiveNamePolicy = shardConfig.archiveNamePolicy().newInstance();
            shardConf = new ShardConf(shardConfig.count(), shardConfig.hasShadow(), shardPolicy, archiveNamePolicy);
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("", e);
            throw new RuntimeException(e);
        }

        policyMap.putIfAbsent(className, shardConf);
        return shardConf;
    }
}
