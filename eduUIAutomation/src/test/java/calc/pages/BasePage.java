package calc.pages;

import mmarquee.automation.AutomationException;
import mmarquee.automation.controls.AutomationBase;

import java.util.List;

import static calc.App.getWindow;

public class BasePage {


    public void clickButton(String name){
        try {
            getWindow().getButton(name).click();
        } catch (AutomationException e) {
            throw new RuntimeException(e);
        }
    }

    public String getResultText(){
        try {
            return getWindow().getTextBox("Результат").getValue();
        } catch (AutomationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AutomationBase> getAllChild(){
        List<AutomationBase> allChilds = null;
        try {
            allChilds = getWindow().getChildren(true);
        } catch (AutomationException e) {
            throw new RuntimeException(e);
        }
        return allChilds;
    }

    public void setResultText(String text){
        try {
            System.out.println(getWindow().getTextBox("Результат").isReadOnly());
            System.out.println(getWindow().getTextBox("Результат").getValue());
            getWindow().getTextBox("Результат").setValueFromIAccessible("1");
            System.out.println(getWindow().getTextBox("Результат").getValue());
        } catch (AutomationException e) {
            throw new RuntimeException(e);
        }
    }


}
