package shusainov.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Yandex extends BasePage {
    @Step("Открытие YandexMarket, с сайта yandex.ru")
    public <T extends BasePage> T openMarket(String service, Class<T> typeNextPage) {
        $x("//nav[contains(@class,'services')]//li//a[contains(.,'Маркет')]").click();
        getWebDriver().getWindowHandles().forEach(tab -> getWebDriver().switchTo().window(tab));
        return typeNextPage.cast(page(YandexMarket.class));
    }
}
