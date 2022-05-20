package Util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String configPath = "src/test/resources/SQL.properties";
    private static Properties SQL;


    public static String getConfig(String parameter) {
        ISettingsFile environment = new JsonSettingsFile("TestSettings.json");
        return environment.getValue(parameter).toString();
    }

    public static String getDBConfig(String parameter) {
        ISettingsFile environment = new JsonSettingsFile("DBSettings.json");
        return environment.getValue(parameter).toString();
    }

    public static String getSQL(String parameter) {
        if (SQL == null) {
            try {
                SQL = new Properties();
                SQL.load(new FileInputStream(configPath));
            } catch (IOException e) {
                Reporter.log("Can not load SQL.properties file" + e,true);
            }
        }
        return SQL.getProperty(parameter);
    }
}
