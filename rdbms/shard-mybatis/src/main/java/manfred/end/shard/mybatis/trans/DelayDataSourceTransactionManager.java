package manfred.end.shard.mybatis.trans;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DelayDataSourceTransactionManager extends DataSourceTransactionManager {

    private final boolean useProxy;

    public DelayDataSourceTransactionManager(DataSource dataSource) {
        this(dataSource, true);
    }

    public DelayDataSourceTransactionManager(DataSource dataSource, boolean useProxy) {
        super(dataSource);
        this.useProxy = useProxy;
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        TransactionDelayHolder.setup(() -> DelayDataSourceTransactionManager.this.callSuperDoBegin(transaction,
                definition));
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        try {
            super.doCommit(status);
        } finally {
            TransactionDelayHolder.clear();
        }
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) {
        try {
            super.doRollback(status);
        } finally {
            TransactionDelayHolder.clear();
        }
    }

    @Override
    protected void prepareTransactionalConnection(Connection connection, TransactionDefinition definition) throws SQLException {
        Statement stmt = connection.createStatement();
        if (isEnforceReadOnly() && definition.isReadOnly()) {
            try {
                stmt.executeUpdate("SET TRANSACTION READ ONLY");
            } finally {
                stmt.close();
            }
        } else if (useProxy) {
            try {
                stmt.executeUpdate("START TRANSACTION ");
            } finally {
                stmt.close();
            }
        }
    }

    private void callSuperDoBegin(Object transaction, TransactionDefinition definition) {
        super.doBegin(transaction, definition);
    }
}
