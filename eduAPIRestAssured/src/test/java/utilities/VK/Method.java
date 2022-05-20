package utilities.VK;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Method {
    LIKES_GET_LIST("/likes.getList"),
    WALL_CREATE_COMMENT("/wall.createComment"),
    WALL_EDIT("/wall.edit"),
    WALL_POST("/wall.post"),
    WALL_DELETE("/wall.delete"),
    USERS_GET("/users.get"),
    PHOTOS_GET_WALL_UPLOAD_SERVER("/photos.getWallUploadServer"),
    PHOTOS_SAVE_WALL_PHOTO("/photos.saveWallPhoto");
    @Getter
    private final String value;
}
