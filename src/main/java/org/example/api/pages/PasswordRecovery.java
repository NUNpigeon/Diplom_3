package org.example.api.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecovery {
    private final WebDriver driver;

    @FindBy(xpath = ".//a[text()='Войти']")
    private WebElement loginLink; // WebElement для ссылки "Войти"

    public PasswordRecovery(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Инициализация веб-элементов
    }

    @Step("Нажатие на ссылку 'Войти'")
    public void clickLoginLink() {
        waitForElement(loginLink);
        loginLink.click();
    }

    // Метод для ожидания пока элемент станет видимым
    private void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }
}