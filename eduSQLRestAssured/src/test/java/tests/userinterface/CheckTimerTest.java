package tests.userinterface;

import Util.Config;
import org.testng.Assert;
import org.testng.annotations.Test;
import userinterface.LogonPage;

public class CheckTimerTest extends UserInterface {
    private final String zeroTime = Config.getConfig("/zeroTime");

    @Test
    public void checkTimer() {
        checkMainPageAndClickHere();

        LogonPage logonPage = new LogonPage();
        Assert.assertEquals(logonPage.getTimerValue(),zeroTime, "Time is wrong");
    }
}
