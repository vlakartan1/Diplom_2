package org.example;

public class Constants {
    public static final String USER_LOGIN_API = "https://stellarburgers.nomoreparties.site/api/auth/login";
    public static final String USER_API = "https://stellarburgers.nomoreparties.site/api/auth/register";
    public static final String REMOVE_USER_API = "https://stellarburgers.nomoreparties.site/api/auth/user/";
    public static final String CONTENT_TYPE = "Content-type";
    public static final String APPLICATION = "application/json";
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String GET_INFO_USER_API = "https://stellarburgers.nomoreparties.site/api/auth/user";
    public static final String CHANGE_IFNO_USER_API = "https://stellarburgers.nomoreparties.site/api/auth/user";
    public static final String GET_INGREDIENT_API = "https://stellarburgers.nomoreparties.site/api/ingredients";
    public static final String CREATE_ORDER_API = "https://stellarburgers.nomoreparties.site/api/orders";
    public static final String GET_ALL_ORDERS_API = "https://stellarburgers.nomoreparties.site/api/orders";
    public static final String EMAIL = "karoline@yandex.ru";
    public static final String NAME = "Karoline";

    public static final User NEW_USER = new User("karoline@yandex.ru", "121212", "Karoline");
    public static final User AUTH_USER = new User("karoline@yandex.ru", "121212");

}
