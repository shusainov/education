package utilities.VK;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.VkParams.CommentParams;
import models.VkParams.LikeParams;
import models.VkParams.PostParams;
import org.testng.Reporter;
import utilities.Config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static utilities.Config.getSecuretyProperty;
import static utilities.VK.Method.*;

public class VkApiUtils {

    private static RequestSpecification setStandardSpec() {
        return given().baseUri(Config.getProperty("/API_URL"))
                .config(
                        config().encoderConfig(
                                encoderConfig()
                                        .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)));
    }

    private static void addToken(Map<String, String> parameters) {
        parameters.put("access_token", getSecuretyProperty("/token"));
    }

    private static void addVersion(Map<String, String> parameters) {
        parameters.put("v", Config.getProperty("/VkApiVersion"));
    }

    public static JsonPath uploadFile(String URL, String filePath) {
        Map<String, String> params = new HashMap<>();
        addToken(params);
        addVersion(params);
        Response response = given().spec(setStandardSpec())
                .config(
                        config().encoderConfig(
                                encoderConfig()
                                        .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .multiPart("photo", new File(filePath))
                .params(params).post(URL);
        Reporter.log(String.format("Upload file :%s", response.jsonPath().prettify()), true);
        return response.jsonPath();
    }

    public static int saveWallPhoto(JsonPath jsonParam) {
        Map<String, String> params = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            params = objectMapper.readValue(jsonParam.prettify(), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        addVersion(params);
        addToken(params);
        Response response = given().spec(setStandardSpec())
                .params(params).when().post(PHOTOS_SAVE_WALL_PHOTO.getValue());
        Reporter.log(String.format("Save photo to the wall, get answer:%s", response.jsonPath().prettify()), true);
        return (int) response.jsonPath().getList("response.id").get(0);
    }

    public static JsonPath postAPI(@NotNull Object model, Method method) {
        Map<String, String> parameters;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        parameters = objectMapper.convertValue(model, new TypeReference<Map<String, String>>() {
        });
        addVersion(parameters);
        addToken(parameters);
        Response response = given().spec(setStandardSpec())
                .params(parameters).when().post(method.getValue());
        return response.jsonPath();
    }

    public static int postPost(PostParams postParams) {
        return postAPI(postParams, WALL_POST).getInt("response.post_id");
    }

    public static int getUserId() {
        return (int) postAPI(new Object(), USERS_GET).getList("response.id").get(0);
    }

    public static int savePhoto(String filePath) {
        JsonPath response = postAPI(new Object(), PHOTOS_GET_WALL_UPLOAD_SERVER);
        String uploadServer = response.getString("response.upload_url");
        JsonPath photoUploadJson = uploadFile(uploadServer, filePath);
        return saveWallPhoto(photoUploadJson);
    }

    public static void editPost(PostParams postParams) {
        postAPI(postParams, WALL_EDIT);
    }

    public static int createComment(CommentParams commentParams) {
        return postAPI(commentParams, WALL_CREATE_COMMENT).getInt("response.comment_id");
    }

    public static List<Integer> getLikes(LikeParams likeParams) {
        JsonPath likesAnswer = postAPI(likeParams, LIKES_GET_LIST);
        return likesAnswer.getList("response.items");
    }

    public static void deletePost(PostParams postParams) {
        postAPI(postParams, WALL_DELETE);
    }
}
