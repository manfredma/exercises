package manfred.end.shard.mybatis.interceptors;

public final class MetricDbInfo {

    private String dbId;

    private String host;

    void accept(String dbId, String host) {
        this.dbId = dbId;
        this.host = host;
    }

    public String getDbId() {
        return dbId;
    }

    public String getHost() {
        return host;
    }

}
