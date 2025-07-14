package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.User;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String CREATE_USER = "/api/auth/register";
    private static final String LOGIN_USER = "/api/auth/login";
    private static final String DELETE_USER = "/api/auth/user";

    @Step("Создание пользователя через API")
    public void createUser(User user) {
        given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(CREATE_USER)
                .then().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(User user) {
        String token = getAccessToken(user);
        given()
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .when()
                .delete(DELETE_USER)
                .then().statusCode(HttpURLConnection.HTTP_ACCEPTED);
    }

    @Step("Получение accessToken через API")
    private String getAccessToken(User user) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_USER)
                .then().statusCode(HttpURLConnection.HTTP_OK)
                .extract().response();
        return response.path("accessToken");
    }
}
