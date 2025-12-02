package org.example.api.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainConstructor {
    private final WebDriver driver;

    @FindBy(xpath = ".//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = ".//a[@href='/account']")
    private WebElement profileButton;

    @FindBy(xpath = ".//button[text()='Оформить заказ']")
    private WebElement createOrderButton;

    @FindBy(xpath = ".//span[text()='Булки']/parent::div")
    private WebElement sectionBun;

    @FindBy(xpath = ".//span[text()='Соусы']/parent::div")
    private WebElement sectionSauce;

    @FindBy(xpath = ".//span[text()='Начинки']/parent::div")
    private WebElement sectionTopping;

    @FindBy(xpath = ".//div[contains(@class, 'current')]/span")
    private WebElement currentSection;

    public MainConstructor(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие на раздел 'Булки'")
    public void clickBunSection() {
        waitForElementToBeClickable(sectionBun);
        sectionBun.click();
    }

    @Step("Нажатие на раздел 'Соусы'")
    public void clickSauceSection() {
        waitForElementToBeClickable(sectionSauce);
        sectionSauce.click();
    }

    @Step("Нажатие на раздел 'Начинки'")
    public void clickToppingSection() {
        waitForElementToBeClickable(sectionTopping);
        sectionTopping.click();
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        waitForElementToBeClickable(profileButton);
        profileButton.click();
    }

    @Step("Проверка отображения кнопки 'Оформить заказ'")
    public boolean isCreateOrderButtonDisplayed() {
        waitForElement(createOrderButton);
        return createOrderButton.isDisplayed();
    }

    @Step("Получение текста текущего раздела")
    public String getCurrentSectionText() {
        waitForElement(currentSection);
        return currentSection.getText();
    }

    private void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
