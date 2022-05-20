package tests.VK;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import utilities.Config;

import static aquality.selenium.browser.AqualityServices.getBrowser;

abstract public class BaseTest {
    @BeforeTest
    public void setup() {
        getBrowser().goTo(Config.getProperty("/URL"));
        getBrowser().maximize();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
