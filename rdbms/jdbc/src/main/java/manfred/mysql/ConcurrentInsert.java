package manfred.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ConcurrentInsert {
    public static void main(String[] args) throws SQLException, InterruptedException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/demo",
                "coupon", "coupon");
        ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> {
            try {
                Statement statement = connection.createStatement();
                statement.execute("insert ignore into t(u) value (500), (600), (20), (700), (800), (801), (802), (803)");
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
            System.out.println("执行结束 - 1");

        };

        Connection connection2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/demo",
                "coupon", "coupon");
        ExecutorService threadPoolExecutor2 = Executors.newSingleThreadExecutor();
        Runnable runnable2 = () -> {
            try {
                Statement statement = connection2.createStatement();
                statement.execute("insert ignore into t(u) value (5000), (6000), (25), (7000), (8001), (8002), (8003)");
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
            System.out.println("执行结束 - 2");

        };

        Connection connection3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/demo",
                "coupon", "coupon");
        ExecutorService threadPoolExecutor3 = Executors.newSingleThreadExecutor();
        Runnable runnable3 = () -> {
            try {
                Statement statement = connection3.createStatement();
                statement.execute("insert ignore into t(u) value (50000), (60000), (15), (70000), (80001), (80002), (80003)");
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
            System.out.println("执行结束 - 3");

        };

        Connection connection4 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/demo",
                "coupon", "coupon");
        for (int i = 0; i < 100; i++) {
            Statement statement = connection4.createStatement();
            statement.execute("delete from t where u in (801, 8001, 80001, 802, 8002, 80002, 803, 8003, 80003)");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");

            ResultSet rs = statement.executeQuery("select * from t");
            while (rs.next()) {
                System.out.print(rs.getInt("u") + " | ");
            }
            System.out.println();
            threadPoolExecutor.execute(runnable);
            threadPoolExecutor2.execute(runnable2);
            // threadPoolExecutor3.execute(runnable3);

            TimeUnit.SECONDS.sleep(10);

            // 清理数据

        }

        threadPoolExecutor.shutdown();
        threadPoolExecutor2.shutdown();
        threadPoolExecutor3.shutdown();

    }
}
