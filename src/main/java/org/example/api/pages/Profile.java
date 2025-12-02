package org.example.api.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Profile {
    private final WebDriver driver;

    @FindBy(xpath = ".//p[text()='Конструктор']")
    private WebElement constructorButton; // Кнопка "Конструктор"

    @FindBy(xpath = ".//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']")
    private WebElement logo; // Логотип

    @FindBy(xpath = ".//button[text()='Выход']")
    private WebElement logoutButton; // Кнопка "Выход"

    public Profile(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Инициализация элементов страницы
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        waitForElement(constructorButton);
        constructorButton.click();
    }

    @Step("Нажатие на логотип")
    public void clickLogo() {
        waitForElement(logo);
        logo.click();
    }

    @Step("Нажатие на кнопку 'Выход'")
    public void clickLogoutButton() {
        waitForElement(logoutButton);
        logoutButton.click();
    }

    @Step("Проверка отображения кнопки 'Выход'")
    public boolean isLogoutButtonDisplayed() {
        waitForElement(logoutButton);
        return logoutButton.isDisplayed();
    }

    private void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }
}