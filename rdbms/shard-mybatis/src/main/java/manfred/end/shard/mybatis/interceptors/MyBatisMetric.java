package manfred.end.shard.mybatis.interceptors;

public interface MyBatisMetric {

    void reportMetric(long costTime, String code, MetricDbInfo metricDbInfo);
}
