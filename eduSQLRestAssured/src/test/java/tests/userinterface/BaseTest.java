package tests.userinterface;

import Util.Config;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static SQL.TestTable.insertNewTest;
import static Util.SQLConnection.closeConnection;

public abstract class BaseTest {

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }

    @BeforeMethod
    public void beforeTest() {
        getBrowser().goTo(Config.getConfig("/URL"));
        getBrowser().maximize();
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        insertNewTest(result.getName(), result.getStatus(), result.getMethod().getQualifiedName(), result.getStartMillis(), result.getEndMillis(), AqualityServices.getBrowser().getBrowserName().toString());

        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
        closeConnection();
    }
}
