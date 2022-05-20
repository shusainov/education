package calc.pages;

import FlaNium.WinAPI.elements.MenuItem;
import FlaNium.WinAPI.elements.Window;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static calc.utils.DriverSingleton.getDriver;

public class BasePage {

    private String menuItemLocator = "//MenuItem[@Name='%s']";
    private String buttonLocator = "//Button[@Name='%s']";
    private String resultFieldLocator = "//Text[@Name='Результат']";

    public String getResult() {
        return getDriver().findElement(By.xpath(resultFieldLocator)).getText();
    }

    public void clickButton(String name) {
        getDriver().findElement(By.xpath(String.format(buttonLocator, name))).click();
    }

    public void clickMenuItem(String menuName) {
        getDriver().findElement(By.xpath(String.format(menuItemLocator, menuName))).click();
//        WebElement menu = new WebDriverWait(getDriver(), 10).until(driver -> getDriver().findElement(By.xpath(String.format(menuItemLocator, menuName))));
//        menu.click();
    }

    public boolean isSimpleMode() {
        WebElement menu = new WebDriverWait(getDriver(), 10).until(driver -> getDriver().findElement(By.xpath(String.format(menuItemLocator, "Вид"))));
        menu.click();
        WebElement simpleModeMenu = new WebDriverWait(getDriver(), 10).until(driver -> getDriver().findElement(By.xpath(String.format(menuItemLocator, "Обычный"))));
        MenuItem flMenu = new MenuItem(simpleModeMenu);
        return flMenu.isSelected();
    }

    public String getTitle() {
        Window window = new Window(getDriver().getActiveWindow());
        return window.title();
    }

    public void printAllElements() {
        List<WebElement> elements = getDriver().findElements(By.xpath("//*"));
        System.out.println("Elements count:" + elements.size());
        elements.forEach(e -> {
            System.out.print("AutomationId:" + e.getAttribute("AutomationId"));
            System.out.print(" Name:" + e.getAttribute("Name"));
            System.out.print(" ClassName:" + e.getAttribute("ClassName"));
            System.out.println(" ControlType:" + e.getAttribute("ControlType"));
            System.out.println(" BoundingRectangle:" + e.getAttribute("BoundingRectangle"));
        });
    }

    public void getLog() {
        getDriver().getActiveWindow().getId();
    }
}
