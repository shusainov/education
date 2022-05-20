package calc.tests;

import calc.App;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {

    @AfterEach
    public void quitApp(){
          App.quitApp();
    }
}
