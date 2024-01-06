package org.example;

import io.qameta.allure.Step;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class GetAllOrdersNotAuthUserTest {

    Order order = new Order();

    private static final String message = "You should be authorised";

    @Test
    @Description("Получение заказов пользователя без авторизации")
    @DisplayName("Тест на получение заказов не авторизованного пользователя")
    public void getAllOrdersNotAuthUserTest() {
        getAllOrdersNotAuthUser();
    }

    @Step("Получение заказов пользователя без авторизации")
    private void getAllOrdersNotAuthUser() {
        order.getAllOrdersNotAuthUser(401, message);
    }
}
