package userinterface;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LogonPage extends Form {
    private final ILabel timerLabel = getElementFactory().getLabel(By.xpath("//div[contains(@class,'timer')]"), "Timer");

    public LogonPage() {
        super(By.xpath("//div[contains(@class,'game')]"), "Logon page");
    }
    public String getTimerValue(){
        return timerLabel.getText();
    }
}
