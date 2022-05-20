package Util;

import org.testng.Reporter;

import java.sql.*;

public class SQLConnection {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    private static Connection getDBConnection() throws SQLException {
        if ((connection == null)||(connection.isClosed())) {
            try {
                String url = Config.getDBConfig("/URL");
                String username = Config.getDBConfig("/user");
                String password = Config.getDBConfig("/password");
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                System.out.println("Connection failed..." + e);
            }
        }
        return connection;
    }

    public static Statement getStatement() throws SQLException {
        if ((statement == null)||(statement.isClosed())) {
                statement =  getDBConnection().createStatement();
        }
        return statement;
    }

    public static PreparedStatement getPreparedStatement(String SQL) throws SQLException {
        if ((preparedStatement == null)||(preparedStatement.isClosed())) {
            preparedStatement =  getDBConnection().prepareStatement(SQL);
        }
        return preparedStatement;
    }

    public static void closeConnection() {
        try {
            if (statement!=null) {statement.close();}
            if (preparedStatement!=null) {preparedStatement.close();}
            if (connection!=null) {connection.close();}
        } catch (SQLException e) {
            System.out.println("Cannot close DB connection.. " + e);
        }
    }
}
