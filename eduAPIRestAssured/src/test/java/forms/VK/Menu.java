package forms.VK;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class Menu extends Form {

    private final ILink myProfileLink = getElementFactory().getLink(By.xpath("//li[@id='l_pr']/a"), "My Profile link");

    public Menu() {
        super(By.id("side_bar"), "VK menu");
    }

    public void clickMyProfile() {
        myProfileLink.getJsActions().clickAndWait();
    }
}
