package forms.VK;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LogonForm extends Form {

    private final ITextBox emailTextBox = getElementFactory().getTextBox(By.xpath("//input[contains(@name,'email')]"), "login form email");
    private final ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[contains(@name,'pass')]"), "login form password");
    private final IButton signInButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'login_button')]"), "logon form sign in button");

    public LogonForm() {
        super(By.id("index_login"), "VK login form");
    }

    public void setPhoneOrEmail(String email) {
        emailTextBox.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordTextBox.sendKeys(password);
    }

    public void pressSignIn() {
        signInButton.clickAndWait();
    }
}
