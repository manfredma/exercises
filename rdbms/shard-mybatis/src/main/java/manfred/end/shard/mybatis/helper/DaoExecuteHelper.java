package manfred.end.shard.mybatis.helper;

import manfred.end.shard.mybatis.interceptors.ShardTableSupport;

public class DaoExecuteHelper {

    private DaoExecuteHelper() {
    }

    public static <T> T executeUsingMaster(DaoExecutor<T> daoExecutor) {
        Boolean old = ShardTableSupport.forceMaster.get();
        try {
            ShardTableSupport.forceMaster.set(true);
            return daoExecutor.execute();
        } finally {
            ShardTableSupport.forceMaster.set(old);
        }
    }
}
