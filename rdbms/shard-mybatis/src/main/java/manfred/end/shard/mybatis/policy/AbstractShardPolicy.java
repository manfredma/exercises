package manfred.end.shard.mybatis.policy;

public abstract class AbstractShardPolicy implements ShardPolicy {
    @Override
    public String shard(Object key, int count, String tableName) {
        String idx = generateIndex(key, count);
        return tableName + getSeparator() + idx;
    }

    protected abstract String generateIndex(Object key, int count);

    protected String getSeparator() {
        return "_";
    }
}
