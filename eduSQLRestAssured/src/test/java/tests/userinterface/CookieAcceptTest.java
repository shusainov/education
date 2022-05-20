package tests.userinterface;

import org.testng.Assert;
import org.testng.annotations.Test;
import userinterface.forms.CookieForm;

public class CookieAcceptTest extends UserInterface {

    @Test
    public void hideCookieAccept() {
        checkMainPageAndClickHere();

        CookieForm cookieForm = new CookieForm();
        cookieForm.acceptCookie();
        Assert.assertFalse(cookieForm.state().isDisplayed(), "Help form must be hidden");
    }
}
