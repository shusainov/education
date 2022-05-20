package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static utils.APIUtils.isItNotFoundUser;

public class Post150Test extends BaseTest {
    @Test
    public void checkPost150() {
        assertTrue(isItNotFoundUser(150), "get wrong JSON");
    }
}
