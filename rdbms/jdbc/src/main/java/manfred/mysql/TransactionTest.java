package manfred.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TransactionTest {
    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/manfred",
                "coupon", "coupon");
        Connection monitorConnection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/manfred", "coupon", "coupon");

        System.out.println("Database Connection Established.\n");
        // connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.executeQuery("start transaction ");
        printCurrentTransaction(monitorConnection);

        statement.execute("select * from employees where emp_no = 14");
        printCurrentTransaction(monitorConnection);

        Random random = new Random();
        statement.execute("update employees set first_name = '" + random.nextInt() +
                "' where emp_no = 100012");

        printCurrentTransaction(monitorConnection);

        statement.execute("commit ");
        printCurrentTransaction(monitorConnection);
    }

    private static void printCurrentTransaction(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        printCurrentTransaction(new Date(), statement);
    }
    private static void printCurrentTransaction(Date execDate, Statement statement) throws Exception {
        System.out.println("\n\n\n" + execDate + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
        TimeUnit.SECONDS.sleep(2);
        ResultSet resultSet = statement.executeQuery("select* from iNFORMATION_SCHEMA.INNODB_TRX");
        ResultSetPrinter.printResultSet(resultSet);
    }
}
