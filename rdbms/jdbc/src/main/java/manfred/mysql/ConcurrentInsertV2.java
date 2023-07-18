package manfred.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class ConcurrentInsertV2 {
    public static void main(String[] args) throws SQLException, InterruptedException {
        AtomicBoolean isDeadLock = new AtomicBoolean(true);
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/demo",
                "coupon", "coupon");
        ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> {
            try {
                Statement statement = connection.createStatement();
                statement.execute("start transaction ");
                statement.execute("insert ignore into t(u) value (500)");
                statement.execute("insert ignore into t(u) value (600)");
                statement.execute("insert ignore into t(u) value  (20)");
                statement.execute("insert ignore into t(u) value (700)");
                statement.execute("insert ignore into t(u) value  (800)");
                statement.execute("insert ignore into t(u) value  (801)");
                statement.execute("insert ignore into t(u) value  (802)");
                statement.execute("insert ignore into t(u) value  (803)");
                statement.execute("insert ignore into t(u) value  (804)");
                statement.execute("insert ignore into t(u) value  (805)");
                statement.execute("insert ignore into t(u) value  (806)");
                statement.execute("commit ");
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
            System.out.println("执行结束 - 1");
            isDeadLock.set(false);

        };

        Connection connection2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/demo",
                "coupon", "coupon");
        ExecutorService threadPoolExecutor2 = Executors.newSingleThreadExecutor();
        Runnable runnable2 = () -> {
            try {
                Statement statement = connection2.createStatement();
                statement.execute("start transaction ");
                statement.execute("insert ignore into t(u) value (5000)");
                statement.execute("insert ignore into t(u) value (6000)");
                statement.execute("insert ignore into t(u) value (25)");
                statement.execute("insert ignore into t(u) value (7000)");
                statement.execute("insert ignore into t(u) value (8001)");
                statement.execute("insert ignore into t(u) value  (8002)");
                statement.execute("insert ignore into t(u) value  (8003)");
                statement.execute("insert ignore into t(u) value  (8004)");
                statement.execute("insert ignore into t(u) value  (8005)");
                statement.execute("insert ignore into t(u) value  (8006)");
                statement.execute("commit ");
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
            System.out.println("执行结束 - 2");
            isDeadLock.set(false);
        };


        Connection connection4 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/demo",
                "coupon", "coupon");
        do {
            Statement statement = connection4.createStatement();
            // 清理数据
            statement.execute("delete from t where u in (801, 8001, 80001, 802, 8002, 80002, 803, 8003, 80003, 804, 8004, 805, 8005)");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");

            ResultSet rs = statement.executeQuery("select * from t");
            while (rs.next()) {
                System.out.print(rs.getInt("u") + " | ");
            }
            System.out.println();
            threadPoolExecutor.execute(runnable);
            threadPoolExecutor2.execute(runnable2);

            TimeUnit.SECONDS.sleep(1);

        } while (!isDeadLock.get());

        threadPoolExecutor.shutdown();
        threadPoolExecutor2.shutdown();

    }
}
