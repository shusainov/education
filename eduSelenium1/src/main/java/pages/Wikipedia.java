package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class Wikipedia {
    private WebDriver driver;
    private long timeout = 30;

    private String columnLocator = "//table[contains(caption,'%s')]//td[count(//th[contains(.,'%s')]/preceding-sibling::th)+1]";

    public Wikipedia(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getColumn(String tableCaption, String columnName) {

        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(String.format(columnLocator, tableCaption, columnName))));

        return driver.findElements(By.xpath(String.format(columnLocator, tableCaption, columnName))).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }
}
