package userinterface;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private final ILink hereLink = getElementFactory().getLink(By.xpath("//a[contains(@Class, 'start__link')]"), "Start Link");

    public MainPage() {
        super(By.xpath("//div[contains(@class,'start')]"), "Start page");
    }

    public void clickHere() {
        hereLink.click();
    }

}
