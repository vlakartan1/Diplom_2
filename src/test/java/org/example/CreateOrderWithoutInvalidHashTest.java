package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class CreateOrderWithoutInvalidHashTest {
    Order order = new Order();

    @Test
    @Description("Создание заказа с неверным хешем ингредиентов")
    @DisplayName("Создание заказа с неверным хешем ингредиентов")
    public void createOrderWithAuth() {
        createOrderInvalidHashIngredient();
    }

    @Step("Создание заказа с неверным хешем ингредиентов")
    private void createOrderInvalidHashIngredient() {
        order.createOrderWithInvalidHash("1515151515", "5465656565", 500);
    }

}
