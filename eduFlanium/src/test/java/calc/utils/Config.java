package calc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static String configPath = "src/test/resources/config.properties";
    private static Properties PROPERTY;

    public static String get(String parameter) {
        if (PROPERTY == null) {
            try {
                PROPERTY = new Properties();
                PROPERTY.load(new FileInputStream(configPath));
            } catch (IOException e) {
                System.out.println("Cannot read config file:" + e);
            }
        }
        return PROPERTY.getProperty(parameter);
    }
}
