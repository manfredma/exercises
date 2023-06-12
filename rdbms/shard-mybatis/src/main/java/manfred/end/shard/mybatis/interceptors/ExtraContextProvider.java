package manfred.end.shard.mybatis.interceptors;

public interface ExtraContextProvider {

    String[] provider();

    boolean isPress();
}
