package manfred.end.shard.mybatis.policy;

public class DefaultArchiveNamePolicy implements ArchiveNamePolicy {
    @Override
    public String archiveName(String tableName) {
        return tableName + "_dal";
    }
}
