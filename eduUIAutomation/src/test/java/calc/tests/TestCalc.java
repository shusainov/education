package calc.tests;

import calc.App;
import calc.pages.BasePage;
import mmarquee.automation.AutomationException;
import mmarquee.automation.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static calc.App.getWindow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCalc extends BaseTest{

    @Test
    public void runAppTest() throws AutomationException {
        Assertions.assertEquals("Калькулятор",getWindow().getName(), "Не верное приложение");
        App.quitApp();
        Assertions.assertEquals("Калькулятор",getWindow().getName(), "Не верное приложение");
        App.quitApp();
        Assertions.assertEquals("Калькулятор",getWindow().getName(), "Не верное приложение");
    }

    @Test
    public void checkAdd(){
        BasePage bp = new BasePage();
        bp.clickButton("7");
        bp.clickButton("Сложение");
        bp.clickButton("8");
        bp.clickButton("Равно");
        Assertions.assertEquals("15 ",bp.getResultText(), "Сложение 7+8 должно быть 15");
    }

    @Test
    public void getAllElementsTest(){
        BasePage bp = new BasePage();
        Assertions.assertNotNull(bp.getAllChild(),"Должны быть потомки");
    }

    @Test
    public void checkScreenCapture(){
        try {
            Utils.capture(getWindow().getNativeWindowHandle(), "test.png");
        } catch (Throwable e) {
        }
        File f = new File("test.png");
        assertTrue(f.exists());
    }

}
