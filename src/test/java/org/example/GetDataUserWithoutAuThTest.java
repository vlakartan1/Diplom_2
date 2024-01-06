package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.Before;
import org.junit.Test;

public class GetDataUserWithoutAuThTest {
    private static final String message = "You should be authorised";

    User user = new User();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Description("Получение Информации о пользователе без авторизации")
    @DisplayName("Получение Информации о пользователе без авторизации")
    public void getDataWithoutAuThTest() {
        getUserInfoWithoutAuth();
    }

    @Step("Получение Информации о пользователе без авторизации")
    private void getUserInfoWithoutAuth() {
        user.getUserInfoWithoutAuth(401, message);
    }
}
