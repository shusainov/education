package SQL;

import Util.Config;
import org.testng.Reporter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Util.Config.getSQL;
import static Util.SQLConnection.closeConnection;
import static Util.SQLConnection.getStatement;

public class AuthorTable {
    private final static String INSERT_AUTHOR_SQL = getSQL("INSERT_AUTHOR_SQL");
    private final static String SELECT_FROM_AUTHOR_WHERE_LOGIN = getSQL("SELECT_FROM_AUTHOR_WHERE_LOGIN");

    public static void insertNewAuthor(String name, String login, String email) {
        try {
            getStatement().execute(String.format(INSERT_AUTHOR_SQL, name, login, email));
        } catch (SQLException e) {
            Reporter.log("Cannot insert to Author table " + e, true);
        } finally {
            closeConnection();
            Reporter.log("Insert to author table", true);
        }

    }

    public static int getAuthorId(String login) {
        int result = -1;
        try {
            ResultSet resultSet = getStatement().executeQuery(String.format(SELECT_FROM_AUTHOR_WHERE_LOGIN, login));
            if (resultSet.next()) result = resultSet.getInt("id");
            resultSet.close();
        } catch (SQLException e) {
            Reporter.log("Cannot SELECT from Author table " + e, true);
        } finally {
            closeConnection();
        }
        return result;
    }

    public static void addAuthorFromConfig() {
        insertNewAuthor(Config.getConfig("/author/name"), Config.getConfig("/author/login"), Config.getConfig("/author/email"));
    }
}
