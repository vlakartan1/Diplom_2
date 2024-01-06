package org.example;

import org.junit.Before;
import io.restassured.RestAssured;
import org.junit.Test;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class AuthWithoutPasswordTest {
    User user = new User();
    private static final User withoutPassword = new User("karoline@yandex.ru", null);
    private static final String message = "email or password are incorrect";

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Description("Проверка на авторизацию пользователя без пароля")
    @DisplayName("Тест на авторизацию без пароля")
    public void authUserAPITest() {
        authWithoutPassword();
    }

    @Step("Тест на авторизацию пользователя без пароля")
    private void authWithoutPassword() {
        user.authNonExistMailAndPassword(withoutPassword, 401, message);
    }

}