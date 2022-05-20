package tests;

import model.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.APIUtils.getUsers;
import static utils.Config.getProperty;
import static utils.FileUtils.read;
import static utils.TestHelpers.getUserFromArray;

public class UsersTest extends BaseTest {
    @Test
    public void checkUsers() {
        User[] users = getUsers();
        User user5FromFile = read(getProperty("user5FilePath"), User.class);
        boolean isJSON = !(users == null);
        assertTrue(isJSON, "Not JSON format");
        assertEquals(user5FromFile, getUserFromArray(users, 5), "Wrong JSON");
    }
}
