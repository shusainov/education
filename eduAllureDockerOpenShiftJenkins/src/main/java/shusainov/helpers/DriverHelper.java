package shusainov.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Webdriver singleton
 * Получать через метод getDriver()
 */
public class DriverHelper {

    private DriverHelper() {
    }

    public static WebDriver getDriver() {
        return LazyHolder.getDriver();
    }

    private static class LazyHolder {
        private static WebDriver driver = null;

        private static WebDriver getDriver() {
            if (driver == null) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless", "--no-sandbox","start-maximized","disable-infobars","--disable-extensions");
                driver = new ChromeDriver(options);
            }
            return driver;
        }

    }

}
