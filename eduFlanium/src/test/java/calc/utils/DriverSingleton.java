package calc.utils;

import FlaNium.WinAPI.webdriver.DesktopOptions;
import FlaNium.WinAPI.webdriver.FlaNiumDriver;
import FlaNium.WinAPI.webdriver.FlaNiumDriverService;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class DriverSingleton {

    private DriverSingleton() {
    }

    private static FlaNiumDriver driver = null;

    public static FlaNiumDriver getDriver() {
        if (driver == null) {
            FlaNiumDriverService service = new FlaNiumDriverService.Builder()
                    .usingDriverExecutable(new File(Config.get("DRIVER_PATH")).getAbsoluteFile())
                    .usingPort(Integer.parseInt(Config.get("PORT")))
                    .withVerbose(true)
                    .withSilent(true)
                    .buildDesktopService();
            DesktopOptions options = new DesktopOptions();
            options.setApplicationPath(new File(Config.get("APP_PATH")).getAbsolutePath());
            options.setLaunchDelay(5);
            options.setDebugConnectToRunningApp(false);
            driver = new FlaNiumDriver(service, options);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) driver.close();
        driver = null;
    }
}
