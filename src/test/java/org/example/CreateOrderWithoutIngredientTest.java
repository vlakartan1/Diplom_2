package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class CreateOrderWithoutIngredientTest {
    Order order = new Order();

    @Test
    @Description("Создание заказа без ингредиентов")
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithAuth() {
        getIngredientAndCreateOrder();
    }

    @Step("Создание заказа без ингредиентов")
    private void getIngredientAndCreateOrder() {
        String message = "Ingredient ids must be provided";
        order.createOrderWithoutIngredient(400, message);
    }

}
