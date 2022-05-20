package tests.userinterface;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import userinterface.forms.AvatarForm;
import userinterface.forms.LoginForm;

public class RegistrationTest extends UserInterface {
    private final String firstPage = "1 / 4";
    private final String secondPage = "2 / 4";
    private final String thirdPage = "3 / 4";

    @Test
    public void registrationTest() {
        checkMainPageAndClickHere();

        LoginForm loginForm = new LoginForm();
        Assert.assertEquals(loginForm.getPageNumber(), firstPage, "First page doesn't opened");

        String randomEmailLetter = RandomStringUtils.randomAlphabetic(1);
        loginForm.setPassword(randomEmailLetter + RandomStringUtils.randomAlphabetic(7) + "N6");
        loginForm.setEmail(randomEmailLetter + RandomStringUtils.randomAlphabetic(5));
        loginForm.setDomain(RandomStringUtils.randomAlphabetic(5));
        loginForm.clickMenuDropDown();
        loginForm.selectRandomDomain();
        loginForm.uncheckTermsAndCondition();
        loginForm.clickNext();

        AvatarForm avatarForm = new AvatarForm();
        avatarForm.state().waitForDisplayed();
        Assert.assertEquals(loginForm.getPageNumber(), secondPage, "Second page doesn't opened");
        avatarForm.clickUploadPhoto();
        avatarForm.uploadPhoto();

        avatarForm.uncheckUnselectAll();
        avatarForm.set3RandomInterest();

        avatarForm.clickNext();
        Assert.assertEquals(loginForm.getPageNumber(), thirdPage, "Third page doesn't opened");
    }
}
