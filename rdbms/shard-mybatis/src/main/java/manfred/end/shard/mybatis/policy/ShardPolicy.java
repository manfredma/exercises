package manfred.end.shard.mybatis.policy;

public interface ShardPolicy {

    String shard(Object key, int count, String tableName);
}
