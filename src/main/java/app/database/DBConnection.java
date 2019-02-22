package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://localhost:3306/newShop?useLegacyDatetimeCode=false&serverTimezone=UTC";

        return DriverManager.getConnection(connectionURL, userName, password);
    }
}
