package tests.userinterface;

import org.testng.Assert;
import org.testng.annotations.Test;
import userinterface.forms.HelpForm;

public class HideHelpTest extends UserInterface {
    @Test
    public void hideHelp() {
        checkMainPageAndClickHere();

        HelpForm helpForm = new HelpForm();
        helpForm.clickSendToBottomButton();
        helpForm.state().waitForNotDisplayed();
        Assert.assertFalse(helpForm.state().isDisplayed(), "Help form must be hidden");
    }
}
