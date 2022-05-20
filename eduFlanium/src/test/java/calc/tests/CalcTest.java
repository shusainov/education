package calc.tests;

import FlaNium.WinAPI.enums.KeyCombination;
import FlaNium.WinAPI.webdriver.DesktopOptions;
import FlaNium.WinAPI.webdriver.FlaNiumDriver;
import FlaNium.WinAPI.webdriver.FlaNiumDriverService;
import calc.pages.BasePage;
import calc.pages.DeveloperModePage;
import calc.pages.SimpleModePage;
import calc.utils.Config;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static calc.utils.DriverSingleton.getDriver;

public class CalcTest extends BaseTest {
    @Test
    public void checkTitle() {
        BasePage basePage = new BasePage();
        Assertions.assertEquals("Калькулятор", basePage.getTitle(), "Название программы не калькулятор");
    }

    @Test
    public void changeModeTest() {
        //Тест не дописан. Не работает определение свойства State
        SimpleModePage simpleModePage = new SimpleModePage();
        Assertions.assertTrue(simpleModePage.isSimpleMode(), "Не в простом режиме");
        simpleModePage.clickMenuItem("Вид");
        simpleModePage.clickMenuItem("Инженерный");
        Assertions.assertTrue(false, "Не в инженерном режиме");
    }

    @Test
    public void checkAddFunction() {
        SimpleModePage simpleModePage = new SimpleModePage();
        simpleModePage.clickButton("3");
        System.out.println(simpleModePage.getResult());
        Assertions.assertEquals("3", simpleModePage.getResult().trim(), "Не равно 3");
        simpleModePage.clickButton("Сложение");
        simpleModePage.clickButton("4");
        simpleModePage.clickButton("Равно");
        Assertions.assertEquals("7", simpleModePage.getResult().trim(), "Не равно 7");
    }

    @Test
    public void checkPercentOperation() {
        SimpleModePage simpleModePage = new SimpleModePage();
        simpleModePage.clickButton("1");
        simpleModePage.clickButton("0");
        simpleModePage.clickButton("0");
        simpleModePage.clickButton("Вычитание");
        simpleModePage.clickButton("9");
        simpleModePage.clickButton("Равно");
        Assertions.assertEquals("91", simpleModePage.getResult().trim(), "Не равно 91");
        File scrFile = getDriver().getJpegScreenshotFile();
        try {
            FileUtils.copyFile(scrFile, new File("./image.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testClipBoard() {
        getDriver().setClipboardText("99");
        getDriver().performKeyCombination(KeyCombination.CTRL_V);
        SimpleModePage simpleModePage = new SimpleModePage();
        Assertions.assertEquals("99", simpleModePage.getResult().trim(), "Не равно 99");
    }

    @Test
    public void testKeyboard() {
        Actions actionProvider = new Actions(getDriver());
        actionProvider.sendKeys("1").sendKeys("2").sendKeys("3").build().perform();
        SimpleModePage simpleModePage = new SimpleModePage();
        Assertions.assertEquals("123", simpleModePage.getResult().trim(), "Не равно 123");
    }

    @Test
    public void testMouse() {
        DeveloperModePage devMode = new DeveloperModePage();
        devMode.clickMenuItem("Вид");
        devMode.clickMenuItem("Программист");

        devMode.clickSomeBit();

        devMode.clickMenuItem("Вид");
        devMode.clickMenuItem("Обычный");
    }

    @Test
    public void testResponse() {
//        Response response = getDriver().execute(DriverCommand.QUIT, Collections.emptyMap());
//        System.out.println(response);
    }

    @Test
    public void testDelphiProgram() {
        FlaNiumDriverService service = new FlaNiumDriverService.Builder()
                .usingDriverExecutable(new File(Config.get("DRIVER_PATH")).getAbsoluteFile())
                .usingPort(Integer.parseInt(Config.get("PORT")))
                .withVerbose(true)
                .withSilent(true)
                .buildDesktopService();
        DesktopOptions options = new DesktopOptions();
        options.setApplicationPath(new File("delphiProgram/Fibonaci.exe").getAbsolutePath());
        options.setLaunchDelay(5);
        options.setDebugConnectToRunningApp(false);
        FlaNiumDriver driver = new FlaNiumDriver(service, options);

        List<WebElement> elements = driver.findElements(By.xpath("//*"));
        System.out.println(elements.size());
        elements.forEach(e -> {
            System.out.print("AutomationId:" + e.getAttribute("AutomationId"));
            System.out.print(" Name:" + e.getAttribute("Name"));
            System.out.print(" ClassName:" + e.getAttribute("ClassName"));
            System.out.println(" ControlType:" + e.getAttribute("ControlType"));
            System.out.println(" BoundingRectangle:" + e.getAttribute("BoundingRectangle"));
        });
        // С Delphi не работает!
        // В итоге есть доступ только к элементам titleBar.

        driver.close();
        // Не понятно почему не работает driver.quit();
    }
}
