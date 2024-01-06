package org.example;


import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class Order {
    User user = new User();
    private String[] ingredients;

    public Order(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public Order() {
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    //Получение Ингредиентов
    public String getIngredient(int id) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .get(Constants.GET_INGREDIENT_API);

        response
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("data._id", notNullValue());


        ArrayList<String> allIdsIngr = response.jsonPath().get("data._id");
        String idIngr = allIdsIngr.get(id);

        System.out.println(idIngr);
        return idIngr;

    }


    @Step("Создание заказа с авторизацией")
    public void createOrderWithAuth(int firstIngredient, int twoIngredient, int code, String name) {
        String token = user.authUserAndReceiveToken(Constants.AUTH_USER);

        String firstIngr = getIngredient(firstIngredient);
        String twoIngr = getIngredient(twoIngredient);
        String bodyOrder = "{ \"ingredients\": [\"" + firstIngr + "\",\"" + twoIngr + "\"] }";


        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .header("Authorization", token)
                        .body(bodyOrder)
                        .when()
                        .post(Constants.CREATE_ORDER_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(true), "name", equalTo(name), "order.number", notNullValue());


    }


    @Step("Создание заказа без авторизации")
    public void createOrderWithoutAuth(int firstIngredient, int twoIngredient, int code, String name) {

        String firstIngr = getIngredient(firstIngredient);
        String twoIngr = getIngredient(twoIngredient);
        String bodyOrder = "{ \"ingredients\": [\"" + firstIngr + "\",\"" + twoIngr + "\"] }";


        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(bodyOrder)
                        .when()
                        .post(Constants.CREATE_ORDER_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(true), "name", equalTo(name), "order.number", notNullValue());


    }


    @Step("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredient(int code, String message) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .when()
                        .post(Constants.CREATE_ORDER_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(false), "message", equalTo(message));


    }


    @Step("Создание заказа с неверным хешем ингредиентов")
    public void createOrderWithInvalidHash(String firstIngr, String twoIngr, int code) {

        String bodyOrder = "{ \"ingredients\": [\"" + firstIngr + "\",\"" + twoIngr + "\"] }";


        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(bodyOrder)
                        .when()
                        .post(Constants.CREATE_ORDER_API);
        response
                .then()
                .statusCode(code);

    }


    @Step("Получение заказов авторизованного пользователя")
    public void getAllOrdersAuthUser(int code) {
        String token = user.authUserAndReceiveToken(Constants.AUTH_USER);

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .header("Authorization", token)
                        .when()
                        .get(Constants.GET_ALL_ORDERS_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(true), "orders", notNullValue(), "total", notNullValue());

    }


    @Step("Получение заказов пользователя без авторизации")
    public void getAllOrdersNotAuthUser(int code, String message) {
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .when()
                        .get(Constants.GET_ALL_ORDERS_API);
        response
                .then()
                .statusCode(code)
                .body("success", equalTo(false), "message", equalTo(message));

    }

}


