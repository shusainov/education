package userinterface.forms;

import Util.TestRandom;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class LoginForm extends Form {
    private final ILabel pageIndicatorLabel = getElementFactory().getLabel(By.xpath("//div[@class='page-indicator']"), "Page indicator");

    private final ITextBox passwordTextBox = getTextBox("Password");
    private final ITextBox emailTextBox = getTextBox("email");
    private final ITextBox domainTextBox = getTextBox("Domain");
    private final ICheckBox termsConditionCheckBox = getElementFactory().getCheckBox(By.xpath("//span[@class='checkbox']"), "Terms and Condition checkBox");
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//a[text()='Next']"), "Next Button");
    private final IButton dropDownOpenerButton = getElementFactory().getButton(By.xpath("//div[contains(@class,'dropdown__opener')]"), "Dropdown opener");

    private ITextBox getTextBox(String name) {
        String boxLocator = "//input[contains(@placeholder,'%s')]";
        return getElementFactory().getTextBox(By.xpath(String.format(boxLocator, name)), name + " box");
    }

    private List<IElement> getDropDownItems() {
        return getElementFactory().findElements(By.xpath("//div[contains(@class,'dropdown__list-item')]"), ElementType.BUTTON);
    }

    public LoginForm() {
        super(By.xpath("//div[@class='login-form-with-pw-check']"), "LoginForm");
    }

    public String getPageNumber() {
        return pageIndicatorLabel.getText();
    }

    public void setPassword(String password) {
        passwordTextBox.clearAndType(password);
    }

    public void setEmail(String email) {
        emailTextBox.clearAndType(email);
    }

    public void setDomain(String domain) {
        domainTextBox.clearAndType(domain);
    }

    public void uncheckTermsAndCondition() {
        termsConditionCheckBox.click();
    }

    public void clickNext() {
        nextButton.click();
    }

    public void clickMenuDropDown() {
        dropDownOpenerButton.click();
    }

    public void selectRandomDomain() {
        getDropDownItems().get(TestRandom.getRandomInt(1,getDropDownItems().size()-1)).click();
    }
}
