package manfred.end.shard.mybatis.policy;

public class DefaultShardPolicy extends AbstractShardPolicy implements ShardPolicy {

    @Override
    protected String generateIndex(Object key, int count) {
        long id = convertId(key);
        long mod = (id % count);

        if (mod < 0) {
            mod = -mod;
        }
        return mod + "";
    }

    private long convertId(Object key) {
        try {
            if (key instanceof Long) {
                return (long) key;
            }
            if (key instanceof Integer) {
                return (int) key;
            }
            if (key instanceof String) {
                long id = Long.parseLong((String) key);
                return id;
            }
        } catch (Exception ex) {

        }
        throw new RuntimeException("key must be int or long or String can be parsed to long");
    }
}
