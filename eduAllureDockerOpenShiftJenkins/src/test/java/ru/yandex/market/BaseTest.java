package ru.yandex.market;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static shusainov.helpers.DriverHelper.getDriver;

public abstract class BaseTest {

    @BeforeAll
    public static void initDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    public void closeDriver() {
        getDriver().quit();
    }
}
