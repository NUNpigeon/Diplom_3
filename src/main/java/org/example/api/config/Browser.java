package org.example.api.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

    public static WebDriver getDriver(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return getChromeDriver();
            case "yandex":
                return getYandexDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getYandexDriver() {
        System.setProperty("webdriver.chrome.driver", PageUrl.YANDEX_DRIVER_PATH); // Используем PageUrl.YANDEX_DRIVER_PATH
        return new ChromeDriver();
    }
}
