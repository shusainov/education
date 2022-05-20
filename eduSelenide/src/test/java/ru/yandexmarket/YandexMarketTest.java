package ru.yandexmarket;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shusainov.pages.Yandex;
import shusainov.pages.YandexMarket;

import static com.codeborne.selenide.Selenide.open;

public class YandexMarketTest {

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью Selenide")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"Apple,iPhone", "Google,G", "HONOR,H","HUAWEI,H","Nokia,N","OnePlus,O","OPPO,O","realme,r","Samsung,S","vivo,v","Xiaomi,X","ZTE,Z"})

    public void yandexMarketCheckOnlySelenide(String filterName, String shouldContain) {
        open("https://yandex.ru", Yandex.class)
                .openMarket("Маркет", YandexMarket.class)
                .clickFirstLevelCatalog("Электроника")
                .clickSecondLevelCatalogNameLocator("Смартфоны")
                .setCheckBoxFilters(filterName)
                .waitFilterApply()
                .show12Product()
                .waitFilterApply()
                .forEach(page -> Assertions.assertTrue(page.getCurrentItems().stream().allMatch(x -> x.get("Name").contains(shouldContain)), "Не все товары содержат в названии:" + shouldContain));
    }


}
