package tests.yandexAndWikipedia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.support.PageFactory;
import pages.Wikipedia;
import pages.Yandex;
import tests.BaseTest;

import java.util.List;

public class FirstExTest extends BaseTest {
    @ParameterizedTest
    @CsvSource({"таблица,Таблица — Википедия,Преподаватели кафедры программирования,Сергей Владимирович,Сергей Адамович"})
    public void firstExTest(String findText, String resultContainsText, String tableCaption, String firstIO, String lastIO) {
        Yandex yandex = PageFactory.initElements(chromeDriver, Yandex.class);
        chromeDriver.get("https://yandex.ru/");
        yandex.find(findText);
        Assertions.assertTrue(yandex.getResultTexts().stream().anyMatch(x -> x.contains(resultContainsText)), "Find results does not contain:" + resultContainsText);
        yandex.clickToTextContainSearchResultAndSwitchToLast(resultContainsText);

        Wikipedia wikipedia = new Wikipedia(chromeDriver);
        List<String> nameList = wikipedia.getColumn(tableCaption, "Имя");
        List<String> patronymicList = wikipedia.getColumn(tableCaption, "Отчество");
        int lastNumber = nameList.size() - 1;
        Assertions.assertEquals(nameList.get(0) + " " + patronymicList.get(0), firstIO, "The first row is not:" + firstIO);
        Assertions.assertEquals(nameList.get(lastNumber) + " " + patronymicList.get(lastNumber), lastIO, "The last row is not:" + firstIO);
    }
}
