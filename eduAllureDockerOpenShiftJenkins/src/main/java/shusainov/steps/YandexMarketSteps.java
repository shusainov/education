package shusainov.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import shusainov.pages.Yandex;
import shusainov.pages.YandexMarket;

import java.util.List;
import java.util.Map;

import static shusainov.helpers.DriverHelper.getDriver;

public class YandexMarketSteps {
    private static Yandex yandex = new Yandex(getDriver());
    private static YandexMarket yandexMarket = new YandexMarket(getDriver());

    @Step("Открытие сайта yandex.ru")
    public static void openYandex() {
        getDriver().get("https://yandex.ru");
    }

    @Step("Переход на Яндекс Маркет")
    public static void goToYandexMarket() {
        yandex.clickOnServiceLink("Маркет");
        getDriver().getWindowHandles().forEach(tab -> getDriver().switchTo().window(tab));
    }

    @Step("Выбор раздела 1го уровня каталога: {name}")
    public static void selectFirstlevelMenu(String name) {
        yandexMarket.clickFirstLevelCatalog(name);
    }

    @Step("Выбор раздела 2го уровня: {name}")
    public static void selectSecondLevelMenu(String name) {
        yandexMarket.setSecondLevelCatalogNameLocator(name);
    }

    @Step("Задать параметр «Цена, Р» от {from} до {to} рублей.")
    public static void setPriceFilter(String from, String to) {
        yandexMarket.setFromToFilter("Цена, ", from, to);
    }

    @Step("Выбор производителей: {names}")
    public static void setCheckBoxFilters(String... names) {
        yandexMarket.setCheckBoxFilters(names);
    }

    @Step("Ожидание применение фильтра")
    public static void waitFilterResult() {
        yandexMarket.waitSearchUpdate();
    }

    @Step("Установка количества показываемых элементов на 12")
    public static void show12Product() {
        yandexMarket.show12Product();
    }

    @Step("Ожидание обновления результатов")
    public static void waitShowResult() {
        yandexMarket.waitSearchUpdate();
    }

    @Step("Проверка, что на странице отображается {count} элементов")
    public static void checkElementsCount(int count) {
        List<Map<String, String>> products = yandexMarket.getProductList();
        Assertions.assertEquals(count, products.size(), "Результат должен выдавать " + count + " элементов");
    }

    @Step("Поиск первого элемента в списке и проверка его наличие в результате")
    public static void checkFindFirstElement() {
        List<Map<String, String>> products = yandexMarket.getProductList();
        String firstName = products.get(0).get("Name");
        yandexMarket.search(firstName);
        Assertions.assertTrue(yandexMarket.getProductList().stream().anyMatch(x -> x.get("Name").equals(firstName)), "Поиск не выдал самым первым:" + firstName);
    }
}