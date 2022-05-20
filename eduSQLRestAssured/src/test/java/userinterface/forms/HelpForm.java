package userinterface.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {
    private final IButton sendToBottomButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "Send to bottom button");

    public HelpForm() {
        super(By.xpath("//h2[@class='help-form__title']"), "Help form");
    }

    public void clickSendToBottomButton() {
        sendToBottomButton.click();
    }
}
