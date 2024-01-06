package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String email) {
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Step("Cоздать уникального пользователя")
    public void setNewUser(User newUser) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(newUser)
                        .when()
                        .post(Constants.USER_API);

        response
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }


    @Step("Создать одинаковых пользователей")
    public void youCannotCreateIdenticalUser(User newUser, int code, String message) {
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(newUser)
                        .when()
                        .post(Constants.USER_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(false), "message", equalTo(message));
    }


    @Step("Создание пользователя без значения в обязательном поле")
    public void creatingUserWithAnEmptyRequiredField(User noRequiredField, int code, String message) {
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(noRequiredField)
                        .when()
                        .post(Constants.USER_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(false), "message", equalTo(message));
    }


    @Step("Авторизоваться от созданного пользователя и получить его токен")
    public String authUserAndReceiveToken(User authUser) {
        String userToken;
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(authUser)
                        .when()
                        .post(Constants.USER_LOGIN_API);

        userToken = response
                .then()
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken").toString();

        return userToken;

    }


    @Step("Авторизоваться с неверными почтой и паролем")
    public void authNonExistMailAndPassword(User nonExistentMail, int code, String message) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(nonExistentMail)
                        .when()
                        .post(Constants.USER_LOGIN_API);

        response
                .then()
                .statusCode(code)
                .body("success", equalTo(false), "message", equalTo(message));
    }


    @Step("Удалить пользователя")
    public void removeUserByToken(User authUser) {
        String token = authUserAndReceiveToken(authUser);
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .header("Authorization", token)
                        .when()
                        .delete(Constants.REMOVE_USER_API);
        response
                .then()
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));

    }


    @Step("Получении информации о пользователе с авторизацией")
    public void getUserInfo(String email, String name) {
        setNewUser(Constants.NEW_USER);
        String token = authUserAndReceiveToken(Constants.AUTH_USER);
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .header("Authorization", token)
                        .when()
                        .get(Constants.GET_INFO_USER_API);
        response
                .then()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name));

    }


    @Step("Получении информации о пользователе без авторизации")
    public void getUserInfoWithoutAuth(int code, String message) {
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .when()
                        .get(Constants.GET_INFO_USER_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(false))
                .body("message", equalTo(message));
    }


    @Step("Изменение информации о пользователе с авторизацией")
    public void changeUserInfo(File newInfoUser, int code, String email, String name) {
        setNewUser(Constants.NEW_USER);
        String token = authUserAndReceiveToken(Constants.AUTH_USER);

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .header("Authorization", token)
                        .body(newInfoUser)
                        .when()
                        .patch(Constants.CHANGE_IFNO_USER_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name));
    }


    @Step("Изменение информации о пользователе без авторизации")
    public void changeUserInfoWithoutAuth(File newInfoUser, int code, String message) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(newInfoUser)
                        .when()
                        .patch(Constants.CHANGE_IFNO_USER_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(false))
                .body("message", equalTo(message));
    }
}
