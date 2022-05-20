package calc.tests;

import org.junit.jupiter.api.AfterEach;

import static calc.utils.DriverSingleton.closeDriver;

public class BaseTest {
    @AfterEach
    public void tearDown(){
    //    closeDriver();
    }
}
