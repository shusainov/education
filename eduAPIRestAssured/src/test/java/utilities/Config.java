package utilities;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class Config {

    public static String getProperty(String parameter) {
        ISettingsFile environment = new JsonSettingsFile("testSettings.json");
        return environment.getValue(parameter).toString();
    }

    public static String getSecuretyProperty(String parameter) {
        ISettingsFile environment = new JsonSettingsFile("securityCredential.json");
        return environment.getValue(parameter).toString();
    }
}
