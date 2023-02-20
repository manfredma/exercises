package manfred.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WaitTimeout {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3300/manfred", "coupon", "coupon");
        System.out.println("Database Connection Established.\n");
        System.out.println(connection.createStatement().execute("set session wait_timeout=2"));

       //  Thread.sleep(3000);
        ResultSet rs = connection.createStatement().executeQuery("select now() as date_str ");
        while (rs.next()) {
            System.out.println(rs.getTimestamp("date_str"));
        }

    }
}
