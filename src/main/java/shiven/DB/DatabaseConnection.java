package shiven.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@192.168.6.21:1521:dblabs";
    private static final String USERNAME = "iee2021129";
    private static final String PASSWORD = "BedRoss";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
