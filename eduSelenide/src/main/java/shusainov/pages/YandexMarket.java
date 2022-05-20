package shusainov.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarket extends Yandex implements Iterable<YandexMarket> {

    private String catalogButtonLocator = "//*[@id='catalogPopupButton']";
    private String firstLevelCatalogNameLocator = "//li[@data-zone-name='category-link']/a[contains(.,'%s')]";
    private String secondLevelCatalogNameLocator = "//ul[@data-autotest-id]//a[contains(.,'%s')]";
    private String checkBoxFilterLocator ="//label[contains(.,'%s')]";
    private String showCountButtonLocator = "//button[@aria-haspopup and contains(.,'Показывать')]";
    private String show12ButtonLocator = "//button[contains(.,'Показывать по 12')]";

    private String productElementLocator = "//article[@data-autotest-id='product-snippet']";
    private String productNameSubLocator = ".//h3[@data-zone-name='title']//a";
    private String productPriceSubLocator = ".//div[@data-zone-name='price']//a";

    private String searchResultHider = "//div[@role='main']/div[2]";


    private void clickCatalogButton() {
        $x(catalogButtonLocator).click();
    }

    @Step("Выбор раздела 1го уровня каталога: {name}")
    public YandexMarket clickFirstLevelCatalog(String name) {
        clickCatalogButton();
        $x(String.format(firstLevelCatalogNameLocator, name)).click();
        return page(YandexMarket.class);
    }

    @Step("Выбор раздела 2го уровня каталога: {name}")
    public YandexMarket clickSecondLevelCatalogNameLocator(String name) {
        $x(String.format(secondLevelCatalogNameLocator, name)).shouldBe(exist).click();
        return page(YandexMarket.class);
    }

    private void setCheckBoxFilter(String name) {
        $x(String.format(checkBoxFilterLocator, name)).shouldBe(visible).click();
    }

    @Step("Установка фильтров: {names}")
    public YandexMarket setCheckBoxFilters(String... names) {
        $$x("//button[.='Показать всё']").forEach(SelenideElement::click);

        Arrays.stream(names).forEach(this::setCheckBoxFilter);
        return page(YandexMarket.class);

    }

    @Step("Ожидание применения фильтра")
    public YandexMarket waitFilterApply() {
        $x(searchResultHider).shouldBe(hidden);
        return page(YandexMarket.class);
    }

    @Step("Установка просмотра 12 элементов")
    public YandexMarket show12Product() {
        $x(showCountButtonLocator).click();
        $x(show12ButtonLocator).click();
        return page(YandexMarket.class);
    }

    @Step("Получение текущего списка товаров")
    public List<Map<String, String>> getCurrentItems() {
        List<Map<String, String>> result = new ArrayList<>();
        $$x(productElementLocator).forEach(selenideElement -> {
            Map<String, String> item = new HashMap<>();
            item.put("Name", selenideElement.find(By.xpath(productNameSubLocator)).getText());
            result.add(item);
        });
        return result;
    }

    @Override
    public Iterator<YandexMarket> iterator() {
        return new PageIterator();
    }

    private class PageIterator implements Iterator<YandexMarket> {
        //Has Next Page?
        @Override
        public boolean hasNext() {
            return $x("//a[@aria-label='Следующая страница']").exists();
        }

        //Click next page
        @Override
        public YandexMarket next() {
            $x("//a[@aria-label='Следующая страница']").click();
            waitFilterApply();
            return page(YandexMarket.class);
        }
    }
}
