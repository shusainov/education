package tests;

import model.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static utils.APIUtils.getUser;
import static utils.Config.getProperty;
import static utils.FileUtils.read;

public class User5Test extends BaseTest {
    @Test
    public void checkUser5() {
        User user5 = getUser(5);
        User user5FromFile = read(getProperty("user5FilePath"), User.class);
        assertEquals(user5FromFile, user5, "Wrong JSON");
    }
}
