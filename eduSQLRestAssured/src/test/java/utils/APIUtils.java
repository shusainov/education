package utils;

import io.restassured.response.Response;
import model.Post;
import model.User;
import org.apache.http.HttpStatus;

import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class APIUtils {

    public static Post[] getPosts() {
        Response response = get(URLPaths.POSTS.getValue()).then().assertThat()
                .statusCode(HttpStatus.SC_OK).extract().response();
        return response.as(Post[].class);
    }

    public static Post getPost(int postId) {
        Response response = get(URLPaths.POSTS + "/" + postId).then().assertThat()
                .statusCode(HttpStatus.SC_OK).extract().response();
        return response.as(Post.class);
    }

    public static User[] getUsers() {
        Response response = get(URLPaths.USERS.getValue()).then().assertThat()
                .statusCode(HttpStatus.SC_OK).extract().response();
        return response.as(User[].class);
    }

    public static User getUser(int userId) {
        Response response = get(URLPaths.USERS.getValue() + "/" + userId).then()
                .assertThat().statusCode(HttpStatus.SC_OK).extract().response();
        return response.as(User.class);
    }

    public static Post postPost(Map<String, ?> parameters) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(parameters)
                .post(URLPaths.POSTS.getValue()).then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();
        return response.as(Post.class);
    }

    public static boolean isItNotFoundUser(int userId) {
        Response response = get(URLPaths.POSTS.getValue() + "/" + userId).then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND).extract().response();
        return "{}".equals(response.asString());
    }
}
