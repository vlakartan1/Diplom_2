package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderWithAuthTest {
    User user = new User();
    Order order = new Order();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        createNewUser();
    }

    @Test
    @Description("Создание заказа с авторизацией")
    @DisplayName("Создание заказа с авторизацией")
    public void createOrderWithAuth(){
        getIngredientAndCreateOrder();
    }

    @Step("Получение ингредиентов и Создание заказа с авторизацией")
    private void getIngredientAndCreateOrder(){
        order.createOrderWithAuth(3, 4, 200, "Spicy био-марсианский бургер");
    }

    @Step("Создание пользователя")
    private void createNewUser(){
        user.setNewUser(Constants.NEW_USER);
    }

    @After
    @Step("Удалить пользователя по полученному токену")
    @Description("Удалить пользователя по полученному токену")
    public void removeUserByToken() {
        user.removeUserByToken(Constants.AUTH_USER);
    }
}
