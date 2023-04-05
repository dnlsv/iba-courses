package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String URL = "jdbc:mysql://localhost:3306/bank";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private Connection connection;

    public DatabaseConnection(){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getDatabaseConnection(){
        return connection;
    }
}


