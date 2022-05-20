package tests;

import model.Post;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static utils.APIUtils.getPosts;
import static utils.TestHelpers.isItAscendingPosts;

public class PostsAscendingTest extends BaseTest {
    @Test
    public void isPostsAscending() {
        Post[] posts = getPosts();
        assertTrue(isItAscendingPosts(posts), "id is not ascending");
    }
}
