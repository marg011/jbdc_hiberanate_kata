package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jbdc_hibernate";
    private static final String USER = "root";
    private static final String PASSWORD = "Root171190!";

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e1) {
            System.out.println("Driver class is not found");
        }


        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

        return connection;
    }
}
