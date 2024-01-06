package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class ChangInfoUserWithAuthTest {
    User user = new User();
    private static final String newEmail = "karoline2002@yandex.ru";
    private static final String newPassword = "56565656";
    File newInfoUser = new File("src/test/resources/newInfoUser.json");
    public static final User newAuthUser = new User(newEmail, newPassword);


    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Description("Изменение информации о пользователе")
    @DisplayName("Изменение информации о пользователе")
    public void changeInfoUser() {
        changeInfoUserWithAuth();
    }

    @Step("Изменение информации о пользователе")
    public void changeInfoUserWithAuth() {
        user.changeUserInfo(newInfoUser, 200, newEmail, "Karolisha");
    }


    @After
    @Step("Удалить пользователя по полученному токену")
    public void removeUserByToken() {
        user.removeUserByToken(newAuthUser);
    }
}
