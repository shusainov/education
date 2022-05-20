package userinterface.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookieForm extends Form {
    private final IButton cookieAcceptButton = getElementFactory().getButton(By.xpath("//button[text()='Not really, no']"), "Cookie accept button");

    public CookieForm() {
        super(By.xpath("//div[contains(@class,'cookies') ]"), "Cookie accept form");
    }

    public void acceptCookie() {
        cookieAcceptButton.click();
    }

}
