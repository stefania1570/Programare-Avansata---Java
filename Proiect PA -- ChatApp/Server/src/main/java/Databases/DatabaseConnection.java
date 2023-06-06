package Databases;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class that will create a connection to the database.
 * Implements the Singleton DP.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/messenger";
    private static final String USER = "postgres";
    private static final String PASSWORD = "student";
    private static Connection connection = null;
    private static HikariDataSource dataSource;

    //private DatabaseConnection(){}
    private static void init() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

        dataSource = new HikariDataSource(config);
       // dataSource.setAutoCommit(false);
        dataSource.setMaximumPoolSize(30);
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            init();
        }
        return dataSource.getConnection();
    }
}
