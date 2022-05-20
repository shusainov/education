package SQL;

import Util.Config;
import org.testng.Reporter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Util.Config.getSQL;
import static Util.SQLConnection.closeConnection;
import static Util.SQLConnection.getStatement;

public class ProjectTable {
    private final static String INSERT_PROJECT_SQL = getSQL("INSERT_PROJECT_SQL");
    private final static String SELECT_FROM_PROJECT_WHERE_NAME = getSQL("SELECT_FROM_PROJECT_WHERE_NAME");

    public static void insertNewProject(String name) {
        try {
            getStatement().execute(String.format(INSERT_PROJECT_SQL, name));
        } catch (SQLException e) {
            Reporter.log("Cannot insert to project table " + e, true);
        } finally {
            closeConnection();
            Reporter.log("Insert to project table", true);
        }
    }

    public static int getProjectId(String name) {
        int result = -1;
        try {
            ResultSet resultSet = getStatement().executeQuery(String.format(SELECT_FROM_PROJECT_WHERE_NAME, name));
            if (resultSet.next()) result = resultSet.getInt("id");
            resultSet.close();
        } catch (SQLException e) {
            Reporter.log("Cannot SELECT from project table " + e, true);
        } finally {
            closeConnection();
        }
        return result;
    }

    public static void addProjectFromConfig() {
        insertNewProject(Config.getConfig("/project/name"));
    }
}
