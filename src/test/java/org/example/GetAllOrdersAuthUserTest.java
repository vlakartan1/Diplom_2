package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetAllOrdersAuthUserTest {
    User user = new User();
    Order order = new Order();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        createNewUser();
        getIngredientAndCreateOrders();
    }

    @Test
    @Description("Получение заказов пользователя с авторизацией")
    @DisplayName("Тест на получение заказов авторизованного пользователя")
    public void getAllOrdersAuthUserTest() {
        getAllOrdersAuthUser();
    }

    @Step("Получение заказов пользователя с авторизацией")
    private void getAllOrdersAuthUser() {
        order.getAllOrdersAuthUser(200);
    }

    @Step("Получение ингредиентов и Создание заказа от авторизованного пользователя")
    private void getIngredientAndCreateOrders() {
        order.createOrderWithAuth(3, 4, 200, "Spicy био-марсианский бургер");
        order.createOrderWithAuth(2, 3, 200, "Био-марсианский метеоритный бургер");
    }

    @Step("Создание пользователя")
    private void createNewUser() {
        user.setNewUser(Constants.NEW_USER);
    }


    @After
    @Step("Удалить пользователя после получения списка его заказов")
    @Description("Удалить пользователя после получения списка его заказов")
    public void removeUserByToken() {
        user.removeUserByToken(Constants.AUTH_USER);
    }
}
