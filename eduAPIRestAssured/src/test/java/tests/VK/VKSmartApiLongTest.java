package tests.VK;

import forms.VK.LogonForm;
import forms.VK.Menu;
import forms.VK.Wall;
import models.VkParams.CommentParams;
import models.VkParams.LikeParams;
import models.VkParams.PostParams;
import org.testng.annotations.Test;
import utilities.RandomUtils;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static utilities.Config.getProperty;
import static utilities.Config.getSecuretyProperty;
import static utilities.TestHelper.*;
import static utilities.VK.VkApiUtils.*;

public class VKSmartApiLongTest extends BaseTest {

    String randomPostText1 = RandomUtils.getAlphabetic(10);
    String randomPostText2 = RandomUtils.getAlphabetic(13);
    String randomComment = RandomUtils.getAlphabetic(20);
    LogonForm logonForm = new LogonForm();
    Menu menu = new Menu();
    Wall wall = new Wall();

    PostParams postParams = new PostParams();
    CommentParams commentParams = new CommentParams();
    LikeParams likeParams = new LikeParams();

    int postId;
    int userId;
    int photoId;
    int commentId;

    @Test
    public void runLongTest() {
        logonForm.setPhoneOrEmail(getSecuretyProperty("/login"));
        logonForm.setPassword(getSecuretyProperty("/password"));
        logonForm.pressSignIn();
        menu.clickMyProfile();

        userId = getUserId();
        postParams.setMessage(randomPostText1);
        postId = postPost(postParams);

        assertTrue("Don't see created post", wall.isPostVisible(userId, postId));
        assertEquals("Wrong post text", randomPostText1, wall.getPostText(userId, postId));

        photoId = savePhoto(getProperty("/AttachPhoto"));
        postParams = new PostParams();
        postParams.setPost_id(postId);
        postParams.setMessage(randomPostText2);
        postParams.setAttachment(String.format("photo%d_%d", userId, photoId)); //photo" + {owner_id} + "_" + {photo_id}
        editPost(postParams);
        String photoURL = extractUrls(wall.getPostPhotoURL(userId, postId, photoId)).get(0);
        downloadFileFromURL(photoURL, getProperty("/TempFile"));

        assertEquals("Wrong post text", randomPostText2, wall.getPostText(userId, postId));
        assertTrue("Different post photo", compareFilesByHash(getProperty("/AttachPhoto"), getProperty("/TempFile")));
        deleteTempFile();

        commentParams.setPost_id(postId);
        commentParams.setMessage(randomComment);
        commentId = createComment(commentParams);
        wall.showNextComment(userId, postId);
        assertEquals("Wrong comment text", randomComment, wall.getCommentText(userId, commentId));

        wall.clickLike(userId, postId);
        likeParams.setType("post");
        likeParams.setOwner_id(userId);
        likeParams.setItem_id(postId);

        List<Integer> likes = getLikes(likeParams);
        assertTrue("Wrong liker", likes.contains(userId));

        postParams = new PostParams();
        postParams.setPost_id(postId);
        deletePost(postParams);
        assertTrue("Post does not deleted", wall.isPostInvisible(userId, postId));
    }
}
