package SQL;

import Util.Config;
import org.testng.Reporter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static SQL.AuthorTable.addAuthorFromConfig;
import static SQL.AuthorTable.getAuthorId;
import static SQL.ProjectTable.addProjectFromConfig;
import static SQL.ProjectTable.getProjectId;
import static Util.Config.getSQL;
import static Util.SQLConnection.*;

public class TestTable {
    private static String INSERT_INTO_TEST = getSQL("INSERT_INTO_TEST");
    private static String SELECT_FROM_TEST = getSQL("SELECT_FROM_TEST");
    private static String UPDATE_TEST = getSQL("UPDATE_TEST");
    private static String UPDATE_TEST_PREPARED_STATEMENT = getSQL("UPDATE_TEST_PREPARED_STATEMENT");

    public static void updateTest(int id, String name, int testResult, String methodName, long startTime, long endTime, String browser) {
        try {
            int projectId = getProjectId(Config.getConfig("/project/name"));
            if (projectId == -1) {
                addProjectFromConfig();
                projectId = getAuthorId(Config.getConfig("/project/name"));
            }
            int authorId = getAuthorId(Config.getConfig("/author/login"));
            if (authorId == -1) {
                addAuthorFromConfig();
                authorId = getAuthorId(Config.getConfig("/author/login"));
            }
            String resultString = String.format(UPDATE_TEST,
                    name,
                    testResult,
                    methodName,
                    projectId,
                    "1",//session_id(int) - это время, когда выполнялся тест - не совсем понятно откуда брать данные(поскольку Foreign key нужна автоматизация добавления)//пусть будет всегда 1
                    new Timestamp(startTime),
                    new Timestamp(endTime),
                    Config.getConfig("/env"),
                    browser,
                    authorId,
                    id);
            getStatement().execute(resultString);
        } catch (SQLException e) {
            Reporter.log("Cannot update on Test table " + e, true);
        } finally {
            closeConnection();
        }
    }

    public static void insertNewTest(String name, int testResult, String methodName, long startTime, long endTime, String browser) {
        try {
            int projectId = getProjectId(Config.getConfig("/project/name"));
            if (projectId == -1) {
                addProjectFromConfig();
                projectId = getAuthorId(Config.getConfig("/project/name"));
            }
            int authorId = getAuthorId(Config.getConfig("/author/login"));
            if (authorId == -1) {
                addAuthorFromConfig();
                authorId = getAuthorId(Config.getConfig("/author/login"));
            }

            getStatement().execute(String.format(INSERT_INTO_TEST,
                    name,
                    testResult,
                    methodName,
                    projectId,
                    "1",//    session_id(int) - это время, когда выполнялся тест - не совсем понятно откуда брать данные(поскольку Foreign key нужна автоматизация добавления)//пусть будет всегда 1
                    new Timestamp(startTime),
                    new Timestamp(endTime),
                    Config.getConfig("/env"),
                    browser,
                    authorId
            ));
        } catch (SQLException e) {
            Reporter.log("Cannot insert to Test table " + e, true);
        } finally {
            closeConnection();
        }
    }

    public static ArrayList<ArrayList<String>> selectIdLikeFromTable(String likeId) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            String resultString = String.format(SELECT_FROM_TEST, likeId);
            ResultSet resultSet = getStatement().executeQuery(resultString);
            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                result.add(row);
            }
        } catch (SQLException e) {
            Reporter.log("Cannot SELECT from test table " + e, true);
        } finally {
            closeConnection();
        }
        return result;
    }

    public static void updateTest(ArrayList<String> row) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(UPDATE_TEST_PREPARED_STATEMENT);
            for (int i = 1; i < row.size(); i++) {
                preparedStatement.setString(i, row.get(i));
            }
            preparedStatement.setString(11, row.get(0));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Reporter.log("Cannot update on Test table method with prepared statement " + e, true);
        } finally {
            closeConnection();
        }
    }
}

