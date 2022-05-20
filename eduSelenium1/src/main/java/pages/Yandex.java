package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class Yandex {
    @FindBy(xpath = "//li[contains(@class,'desktop-card')]//span[contains(@role,'text') and contains(@class,'title')]")
    List<WebElement> results;

    @FindBy(xpath = "//input[contains(@class,'input')]")
    private WebElement searchField;

    @FindBy(xpath = "//button[contains(@class,'search')]")
    private WebElement searchButton;

    private long timeout = 30;
    private WebDriver driver;

    public Yandex(WebDriver driver) {
        this.driver = driver;
    }

    public void find(String text) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(searchField));
        searchField.click();
        searchField.sendKeys(text);
        searchButton.click();
    }

    public List<String> getResultTexts() {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElements(results));
        return results.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickToTextContainSearchResultAndSwitchToLast(String text) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElements(results));
        results.stream().filter(x -> x.getText().contains(text)).findFirst().get().click();
        driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
        ;
    }
}
