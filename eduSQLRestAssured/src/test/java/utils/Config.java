package utils;

import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String configPath = "src/test/resources/config.properties";
    public static Properties PROPERTIES = new Properties();

    private static void load() {
        try {
            PROPERTIES.load(new FileInputStream(configPath));
        } catch (IOException e) {
            Reporter.log("Cannot load config file " + e, true);
        }
    }

    public static String getProperty(String val) {
        if (PROPERTIES.getProperty(val) == null) load();
        return PROPERTIES.getProperty(val);
    }
}