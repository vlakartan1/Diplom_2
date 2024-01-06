package org.example;

import org.junit.Before;
import io.restassured.RestAssured;
import org.junit.Test;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class AuthNonExistMailAndPasswordTest {
    User user = new User();

    private static final User nonExistentMail = new User("karoline888", "888");
    private static final String message = "email or password are incorrect";

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Description("Проверка на авторизацию пользователя с несуществующими данными")
    @DisplayName("Тесты на авторизацию с невалидными данными")
    public void authUserAPITest() {
        authNonExistMailAndPassword();
    }

    @Step("Тест на авторизацию пользователя с несуществующими данными")
    private void authNonExistMailAndPassword() {
        user.authNonExistMailAndPassword(nonExistentMail, 401, message);
    }

}
