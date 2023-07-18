package manfred.end.shard.mybatis.trans;

public class TransactionDelayHolder {

    private static final ThreadLocal<TransContext> delayHolder = ThreadLocal.withInitial(() -> new TransContext());

    private TransactionDelayHolder() {
    }

    private static class TransContext {
        private DelayedTransBeginCallback delayedTransBeginCallback;
        private boolean isBegun;
        private String currentDataSource;

        void setUp(DelayedTransBeginCallback delayedTransBeginCallback) {
            this.delayedTransBeginCallback = delayedTransBeginCallback;
        }

        boolean needBegin() {
            return delayedTransBeginCallback != null && !isBegun;
        }

        void doBegin(String dataSource) {
            try {
                delayedTransBeginCallback.doBeginCall();
                this.currentDataSource = dataSource;
                isBegun = true;
            } finally {
                delayedTransBeginCallback = null;
            }
        }

        void clear() {
            this.delayedTransBeginCallback = null;
            this.isBegun = false;
            this.currentDataSource = null;
        }
    }

    public static void setup(DelayedTransBeginCallback delayedTransBeginCallback) {
        delayHolder.get().setUp(delayedTransBeginCallback);
    }

    public static void doBegin(String dataSource) {
        if (delayHolder.get().needBegin()) {
            delayHolder.get().doBegin(dataSource);
        }
    }

    public static boolean checkDataSourceInTrans(String dataSource) {
        if (!delayHolder.get().isBegun) {
            return true;
        }
        return dataSource.equals(delayHolder.get().currentDataSource);
    }

    public static String currentTransDataSource() {
        return delayHolder.get().currentDataSource;
    }

    public static void clear() {
        delayHolder.get().clear();
    }
}
