package tests;

import model.Post;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static utils.APIUtils.postPost;
import static utils.RandomUtils.getAlphabetic;

public class POSTPostTest extends BaseTest {
    @Test
    public void checkPostPost() {
        String randomTitle = getAlphabetic(10);
        String randomBody = getAlphabetic(10);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("body", randomBody);
        parameters.put("title", randomTitle);
        parameters.put("userId", "1");

        Post post = postPost(parameters);

        assertEquals(post.getBody(), randomBody, "Wrong body");
        assertEquals(post.getTitle(), randomTitle, "Wrong title");
        assertNotNull(post.getUserId(), "Have not userId");
    }
}
