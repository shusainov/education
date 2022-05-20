package fibonaci.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sikuli.script.*;


public class TestDelphi extends BaseTest {
    @Test
    public void testClick()  {
        ImagePath.setBundlePath("src/test/resources/images/");
        Screen screen = new Screen();
        Match iterationButton = screen.exists("iterationButton.PNG");
        Assertions.assertNotNull(iterationButton,"Нет кнопки Итерация");
        iterationButton.click();
        iterationButton.click();
        iterationButton.click();
        Match expectedResult = screen.exists(new Pattern("ExpectedResultAfter3Click.png").similar(0.99),1);
        Assertions.assertNotNull(expectedResult,"Не получили 3 в поле");
    }

    @Test
    public void testFindText(){
        Screen screen = new Screen();
        ImagePath.setBundlePath("src/test/resources/images/");
        OCR.globalOptions().language("rus");
        Match mainForm = screen.exists("mainForm.png");
        Assertions.assertNotNull(mainForm,"Нет основной формы");
        System.out.println(mainForm.text());
        Assertions.assertTrue(mainForm.text().contains("Фибоначи"), "Нет текста Фибоначи");
    }

    @Test
    public void testEditText(){
        Screen screen = new Screen();
        ImagePath.setBundlePath("src/test/resources/images/");
        Match firstEdit = screen.exists(new Pattern("firstElement.png").similar(0.99),1);
        Match secondEdit = screen.exists(new Pattern("secondEdit.png").similar(0.99),1);
        secondEdit.click();
        secondEdit.type("123");
        Match iterationButton = screen.exists("iterationButton.PNG");
        iterationButton.click();
        iterationButton.click();
        iterationButton.click();
        Assertions.assertTrue(firstEdit.text().contains("2462"),"Первое поле не равно 2462");
    }
}
