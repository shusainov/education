package utils;

import model.Post;
import model.User;

public class TestHelpers {
    public static boolean isItAscendingPosts(Post[] posts) {
        boolean isItAscending = true;
        if (posts.length > 0) {
            for (int i = 1; i < posts.length - 1; i++) {
                isItAscending &= (Integer.parseInt(posts[i - 1].getId()) < Integer.parseInt(posts[i].getId()));
            }
        }
        return isItAscending;
    }

    public static User getUserFromArray(User[] users, int id) {
        User result = null;
        for (User user : users) {
            if (id == user.getId()) {
                result = user;
                break;
            }
        }
        return result;
    }
}
