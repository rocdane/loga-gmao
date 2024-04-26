package tech.loga.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class Database {

    private static Connection connection;

    @Autowired
    private Database(Environment env) {
        final String DRIVER = env.getProperty("spring.datasource.driverClassName");
        final String JDBC_URL = env.getProperty("spring.datasource.url");
        final String USERNAME = env.getProperty("spring.datasource.username");
        final String PASSWORD = env.getProperty("spring.datasource.password");

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}