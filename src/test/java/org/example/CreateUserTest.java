package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest {
    User user = new User();
    private static final String userExists = "User already exists";
    private static final String areRequiredFields = "Email, password and name are required fields";
    private static final User noRequiredField = new User("karoline@yandex.ru", "121212", null);

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        setNewUser();
    }


    @Test
    @DisplayName("Проверка на создаение пользователя с дублирующими данными и без обязательного поля")
    @Description("Тест: на невозможность создания одинаковых пользователей и без заполнения обязательного поля")
    public void UserTest() {
        youCannotCreateIdenticalUser();
        creatingUserWithAnEmptyRequiredField();
    }

    @Step("Создание нового пользователя с уникальными данными")
    @Description("Создание нового пользователя")
    private void setNewUser() {
        user.setNewUser(Constants.NEW_USER);
    }

    @Step("Создание пользователя без обязательного поля")
    @Description("Создание пользователя без обязательного поля")
    public void creatingUserWithAnEmptyRequiredField() {
        user.creatingUserWithAnEmptyRequiredField(noRequiredField, 403, areRequiredFields);
    }

    @Step("Создание пользователя с одинаковыми (теми же) данными")
    @Description("Создание пользователя с одинаковыми данными")
    private void youCannotCreateIdenticalUser() {
        user.youCannotCreateIdenticalUser(Constants.NEW_USER, 403, userExists);
    }


    @After
    @Step("Удалить пользователя по полученному токену")
    @Description("Удалить пользователя по полученному токену")
    public void removeUserByToken() {
        user.removeUserByToken(Constants.AUTH_USER);
    }
}


