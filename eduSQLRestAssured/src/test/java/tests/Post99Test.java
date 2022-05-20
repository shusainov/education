package tests;

import model.Post;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static utils.APIUtils.getPost;

public class Post99Test extends BaseTest {
    @Test
    public void checkPost99() {
        Post post99 = getPost(99);

        assertEquals(post99.getUserId(), "10", "get wrong userId");
        assertEquals(post99.getId(), "99", "get wrong Id");
        assertNotEquals(post99.getBody(), StringUtils.EMPTY, "Empty body");
        assertNotEquals(post99.getTitle(), StringUtils.EMPTY, "Empty title");
    }
}
