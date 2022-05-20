package ru.yandex.market;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shusainov.steps.YandexMarketSteps;

public class SecondExTest extends BaseTest {

    @Feature("Задание 2 по Selenium")
    @DisplayName("Задание 2 по Selenium")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @Tag("Отладочный тест")
    @CsvSource(value = {"10000,900000,HP,Lenovo"})
    public void yandexMarketWithSteps(String from, String to, String name1, String name2) {
        YandexMarketSteps.openYandex();
        YandexMarketSteps.goToYandexMarket();
        YandexMarketSteps.selectFirstlevelMenu("Компьютеры");
        YandexMarketSteps.selectSecondLevelMenu("Ноутбуки");
        YandexMarketSteps.setPriceFilter(from, to);
        YandexMarketSteps.setCheckBoxFilters(name1, name2);
        YandexMarketSteps.waitFilterResult();
        YandexMarketSteps.show12Product();
        YandexMarketSteps.waitShowResult();
        YandexMarketSteps.checkElementsCount(12);
        YandexMarketSteps.checkFindFirstElement();
    }


}
