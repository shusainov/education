package shusainov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * Реализация PageObject сайта https://market.yandex.ru/
 */
public class YandexMarket {
    private WebDriver driver;
    private long timeout = 30;

    private String catalogButtonLocator = "//*[@id='catalogPopupButton']";
    private String firstLevelCatalogNameLocator = "//li[@data-zone-name='category-link']/a[contains(.,'%s')]";
    private String secondLevelCatalogNameLocator = "//ul[@data-autotest-id]//a[contains(.,'%s')]";
    private String fromToFilterLocator = "//legend[contains(.,'%s')]/following-sibling::div//input[@type='text']";
    private String checkBoxFilterLocator = "//label[contains(.,'%s')]";
    private String showCountButtonLocator = "//button[@aria-haspopup and contains(.,'Показывать')]";
    private String show12ButtonLocator = "//button[contains(.,'Показывать по 12')]";

    private String productElementLocator = "//article[@data-autotest-id='product-snippet']";
    private String productNameSubLocator = ".//h3[@data-zone-name='title']//a";
    private String productPriceSubLocator = ".//div[@data-zone-name='price']//a";

    private String searchResultHider = "//div[@role='main']/div[2]";

    private String searchFieldLocator = "//input[@id='header-search']";
    private String searchButtonLocator = "//button[@data-r='search-button']";

    public YandexMarket(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Нажатие на кнопку "Каталог"
     */
    public void clickCatalogButton() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement catalogButton = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(catalogButtonLocator)));
        catalogButton.click();
    }

    /**
     * Выбор меню первого уровня каталога
     *
     * @param name наименование пункта меню каталога первого уровня, например "Компьютер"
     */
    public void clickFirstLevelCatalog(String name) {
        clickCatalogButton();
        WebElement firstLevelCatalogLink = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(firstLevelCatalogNameLocator, name))));
        firstLevelCatalogLink.click();
    }

    /**
     * Выбор меню второго уровня каталога
     * вызывать необходимо после выбора первого уровня
     *
     * @param name наименование пункта меню каталога второго уровня, например "Ноутбуки"
     */
    public void setSecondLevelCatalogNameLocator(String name) {
        WebElement secondLevelCatalogLink = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(secondLevelCatalogNameLocator, name))));
        secondLevelCatalogLink.click();
    }

    /**
     * Задать фильтр "от и до"
     *
     * @param name название как написано на сайте, например "Цена"
     * @param from от скольки
     * @param to   до скольки
     */
    public void setFromToFilter(String name, String from, String to) {
        List<WebElement> fromToFilters = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(String.format(fromToFilterLocator, name))));
        fromToFilters.get(0).sendKeys(from);
        fromToFilters.get(1).sendKeys(to);
    }


    private void setCheckBoxFilter(String name) {
        WebElement checkBoxFilter = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(checkBoxFilterLocator, name))));
        checkBoxFilter.click();
    }

    /**
     * Задать или убрать, фильтры типа checkBox
     *
     * @param names названия
     */
    public void setCheckBoxFilters(String... names) {
        Arrays.stream(names).forEach(name -> setCheckBoxFilter(name));
    }

    /**
     * Задать количество отображаемых элементов в выборке 12шт
     */
    public void show12Product() {
        WebElement сountButton = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.xpath(showCountButtonLocator)));

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", сountButton);

        WebElement c12Button = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.xpath(show12ButtonLocator)));
        executor.executeScript("arguments[0].click();", c12Button);
    }

    /**
     * Ждать пока применится фильтр
     */
    public void waitSearchUpdate() {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(searchResultHider)));
    }

    /**
     * Получить список свойств продуктов,
     * Реализованно возврат полей:
     * Name - Название продукта
     * Price - Цена продукта
     *
     * @return Возвращает список элементов
     */
    public List<Map<String, String>> getProductList() {
        List<WebElement> productList = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(productElementLocator)));
        if (productList.size() == 0) return null;
        List<Map<String, String>> result = new ArrayList<>();
        productList.stream().forEach(element -> {
            Map<String, String> product = new HashMap<>();
            product.put("Name", element.findElement(By.xpath(productNameSubLocator)).getText());
            product.put("Price", element.findElement(By.xpath(productPriceSubLocator)).getText());
            result.add(product);
        });
        return result;
    }

    /**
     * Поиск через поисковую строку
     *
     * @param text текст поиска
     */
    public void search(String text) {
        WebElement searchField = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchFieldLocator)));
        WebElement searchButton = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchButtonLocator)));
        searchField.sendKeys(text);
        WebElement htmlElement = driver.findElement(By.xpath("//html"));
        searchButton.click();
        new WebDriverWait(driver, timeout).until(ExpectedConditions.stalenessOf(htmlElement));
    }
}
