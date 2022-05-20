package tests;

import org.testng.annotations.BeforeTest;
import utils.Config;

import static io.restassured.RestAssured.baseURI;

public abstract class BaseTest {

    @BeforeTest
    public void setup() {
        baseURI = Config.getProperty("baseUri");
    }
}
