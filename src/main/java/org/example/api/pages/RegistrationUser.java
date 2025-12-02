package org.example.api.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationUser {
    private final WebDriver driver;

    @FindBy(xpath = ".//label[text()='Имя']/following-sibling::input")
    private WebElement nameField;

    @FindBy(xpath = ".//label[text()='Email']/following-sibling::input")
    private WebElement emailField;

    @FindBy(xpath = ".//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = ".//p[text()='Некорректный пароль']")
    private WebElement wrongPasswordError;

    @FindBy(xpath = ".//a[@href='/login']")
    private WebElement loginLink;

    public RegistrationUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Регистрация пользователя с именем: {name}, email: {email}, паролем: {password}")
    public void registrationUser(String name, String email, String password) {
        waitForElement(registerButton);
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        registerButton.click();
    }

    @Step("Проверка отображения ошибки 'Некорректный пароль'")
    public boolean isWrongPasswordErrorDisplayed() {
        waitForElement(wrongPasswordError); //ждем, пока появится сообщение об ошибке
        return wrongPasswordError.isDisplayed();
    }

    @Step("Нажатие на ссылку 'Войти'")
    public void clickLoginLink() {
        waitForElement(loginLink); //ждем, пока ссылка "Войти" станет видимой
        loginLink.click();
    }

    private void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element)); //ждем, пока элемент не станет видимым
    }
}
