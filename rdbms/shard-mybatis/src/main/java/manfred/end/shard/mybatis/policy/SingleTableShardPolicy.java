package manfred.end.shard.mybatis.policy;

public class SingleTableShardPolicy implements ShardPolicy {
    @Override
    public String shard(Object key, int count, String tableName) {
        return tableName;
    }
}
