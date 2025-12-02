package org.example.api.models;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApi {

    private final String pageURL;

    public UserApi(String pageURL) {
        this.pageURL = pageURL;
    }
    @Step("Создание уникального пользователя")
    public ValidatableResponse createUser(CreateUserRequest user) {
        return given()
                .baseUri(pageURL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register")
                .then();
    }

    @Step("Вход под существующим пользователем")
    public ValidatableResponse loginUser(User user) {
        return given()
                .baseUri(pageURL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login")
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .baseUri(pageURL)
                .auth().oauth2(accessToken)
                .delete("/api/auth/user")
                .then();
    }
}
