package org.example.api.config;


public class PageUrl {
    public static final String PAGE_URL = "https://stellarburgers.education-services.ru/";


    public static final String YANDEX_DRIVER_PATH = "C:\\Program Files (x86)\\chromedriver-win64\\yandexdriver.exe";

    // API endpoints
    public static final String REGISTER_ENDPOINT = "api/auth/register";
    public static final String LOGIN_ENDPOINT = "api/auth/login";
    public static final String USER_ENDPOINT = "api/auth/user";

    // URLs
    public static final String MAIN_PAGE_URL = PAGE_URL;
    public static final String LOGIN_PAGE_URL = PAGE_URL + "login";
    public static final String REGISTER_PAGE_URL = PAGE_URL + "register";
    public static final String FORGOT_PASSWORD_URL = PAGE_URL + "forgot-password";
}