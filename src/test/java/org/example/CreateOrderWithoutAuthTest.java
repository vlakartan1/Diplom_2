package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class CreateOrderWithoutAuthTest {
    Order order = new Order();

    @Test
    @Description("Создание заказа без авторизации")
    @DisplayName("Создание заказа без авторизации")
    public void createOrderWithAuth() {
        getIngredientAndCreateOrder();
    }

    @Step("Получение ингредиентов и Создание заказа без авторизации")
    private void getIngredientAndCreateOrder() {
        order.createOrderWithoutAuth(2, 3, 200, "Био-марсианский метеоритный бургер");
    }

}
