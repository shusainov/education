package forms.VK;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class Wall extends Form {
    private final String xpathPostIdLocator = "post%d_%d";//example post627657327_17276 : postUserId_postId
    private final String xpathPostIdLocatorName = "Post user:%d post:%d)";
    private final String postPhotoLocator = "//a[@data-photo-id='%s_%s']";


    public Wall() {
        super(By.id("page_wall_posts"), "VK Wall");
    }

    private IElement getPostElement(int userId, int postId) {
        return getFormLabel().findChildElement(By.id(String.format(xpathPostIdLocator, userId, postId)), String.format(xpathPostIdLocatorName, userId, postId), ILabel.class);
    }

    private IElement getPostTextElement(int userId, int postId) {
        return getPostElement(userId, postId).findChildElement(By.xpath("//div[contains(@class,'wall_post_text')]"), "post text", ITextBox.class);
    }

    private ILink getShowNextCommentLink(int userId, int postId) {
        return getPostElement(userId, postId).findChildElement(By.xpath("//a[contains(@class,'replies_next')]/span"), "Show next comment", ILink.class);
    }

    private IElement getCommentTextElement(int userId, int commentId) {
        //ID коммента строится одинаково как и у поста
        return getPostElement(userId, commentId).findChildElement(By.xpath("//div[contains(@class,'wall_reply_text')]"), "Comment text", ILabel.class);
    }

    private IButton getLikeButton(int userId, int postId) {
        return getPostElement(userId, postId).findChildElement(By.xpath("//span[contains(@class,'like_button')]"), "Like button", IButton.class);
    }

    private IElement getPostPhotoElement(int userId, int postId, int photoId) {
        return getPostElement(userId, postId).findChildElement(By.xpath(String.format(postPhotoLocator, userId, photoId)), "Post photo element", ILink.class);
    }


    public boolean isPostVisible(int userId, int postId) {
        IElement post = getPostElement(userId, postId);
        post.state().waitForDisplayed();
        return post.state().isDisplayed();
    }

    public boolean isPostInvisible(int userId, int postId) {
        IElement post = getPostElement(userId, postId);
        post.state().waitForNotDisplayed();
        return !post.state().isDisplayed();
    }

    public String getPostText(int userId, int postId) {
        return getPostTextElement(userId, postId).getText();
    }

    public void showNextComment(int userId, int postId) {
        ILink showNextCommentLabel = getShowNextCommentLink(userId, postId);
        showNextCommentLabel.state().waitForClickable();
        showNextCommentLabel.click();
    }

    public String getCommentText(int userId, int commentId) {
        return getCommentTextElement(userId, commentId).getText();
    }

    public void clickLike(int userId, int postId) {
        getLikeButton(userId, postId).click();
    }

    public String getPostPhotoURL(int userId, int postId, int photoId) {
        return getPostPhotoElement(userId, postId, photoId).getCssValue("background-image");
    }
}

