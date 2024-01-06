package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class ChangInfoUserWithoutAuthTest {
    User user = new User();
    private static final String message = "You should be authorised";
    File newInfoUser = new File("src/test/resources/newInfoUser.json");


    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Description("Изменение информации о пользователе без авторизации")
    @DisplayName("Изменение информации о пользователе без авторизации и получение ошибки 401")
    public void changeInfoUser() {
        changeInfoUserWithAuth();
    }

    @Step("Изменение информации о пользователе без авторизации и получение ошибки 401")
    public void changeInfoUserWithAuth() {
        user.changeUserInfoWithoutAuth(newInfoUser, 401, message);
    }
}
