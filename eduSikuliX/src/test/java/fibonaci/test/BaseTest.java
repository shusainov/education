package fibonaci.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.sikuli.basics.Debug;

import java.io.IOException;

public class BaseTest {
    Runtime runtime = Runtime.getRuntime();

    @BeforeEach
    public void tearUp() throws IOException {
        //Debug.on(3);
        runtime.exec("delphiProgram/Fibonaci.exe");

    }

    @AfterEach
    public void tearDown() throws IOException, InterruptedException {
       runtime.exec("taskkill /im fibonaci.exe");
        Thread.sleep(1000);
    }

}
