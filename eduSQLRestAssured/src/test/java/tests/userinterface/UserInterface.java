package tests.userinterface;

import org.testng.Assert;
import userinterface.MainPage;

public abstract class UserInterface extends BaseTest {

    protected void checkMainPageAndClickHere() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Welcome page is not opened");
        mainPage.clickHere();
    }
}
