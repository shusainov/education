package calc.pages;

import FlaNium.WinAPI.elements.Window;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static calc.utils.DriverSingleton.getDriver;

public class DeveloperModePage extends BasePage {
    protected String bitPane = "//Pane/Image[5]";

    public void clickSomeBit() {
        WebElement image = new WebDriverWait(getDriver(), 10).until(driver -> getDriver().findElement(By.xpath(bitPane)));
        printAllElements();
        System.out.println(image.getSize());

//        Actions не работает, ошибок нету
        Actions actions = new Actions(getDriver());
//        actions.moveToElement(image);
//        actions.moveByOffset(10, 20);
//        actions.click();
        actions.moveToElement(getDriver().findElement(By.xpath("//Button[@Name='7']")));
        actions.moveByOffset(0, 0);
        actions.click();

        // Своя реализация mouseAction  кликает по абсолютным кординатам
        Window window = new Window(image);
        System.out.println(window.getCoordinates());
        System.out.println(window.getSize());
        System.out.println(window.getAttribute("BoundingRectangle"));

        // window.mouseClick(BasePoint.ZERO_POINT,10,10);

        // тест TouchActions, тапает по абсолютным кординатам
//        TouchActions touchActions = new TouchActions(getDriver());
//        Point point = new Point(100, 10);
//        Point points[] = new Point[1];
//        points[0] = point;
//        touchActions.tap(points);
    }


}
