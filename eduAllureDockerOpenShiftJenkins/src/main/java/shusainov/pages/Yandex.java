package shusainov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Реализация PageObject сайта https://yandex.ru/
 */
public class Yandex {
    private String serviceLinkLocator = "//nav[contains(@class,'services')]//li//a[contains(.,'%s')]";
    private WebDriver driver;
    private long timeout = 30;

    public Yandex(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Нажатие на сервис яндекса
     *
     * @param name наименование сервиса, например Карты
     */
    public void clickOnServiceLink(String name) {
        WebElement serviceLink = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(serviceLinkLocator, name))));
        serviceLink.click();
    }
}
