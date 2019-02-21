package app.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by OK on 22.02.2019.
 */
public class ConnectionUtils {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        return MySQLConnUtils.getMySQLConnection();
    }
}
