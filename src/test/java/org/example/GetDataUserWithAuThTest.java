package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetDataUserWithAuThTest {

    User user = new User();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Description("Получение Информации о пользователе с авторизацией")
    @DisplayName("Получение Информации о пользователе с авторизацией")
    public void getDataWithAuThTest() {
        getUserInfo();
    }

    @Step("Получение Информации о пользователе с авторизацией")
    private void getUserInfo() {
        user.getUserInfo(Constants.EMAIL, Constants.NAME);
    }

    @After
    @Step("Удалить пользователя по полученному токену")
    public void removeUserByToken() {
        user.removeUserByToken(Constants.AUTH_USER);
    }


}
