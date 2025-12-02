import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.api.config.PageUrl;
import org.example.api.pages.Login;
import org.example.api.pages.MainConstructor;
import org.example.api.pages.PasswordRecovery;
import org.example.api.pages.RegistrationUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.example.api.models.CreateUserRequest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    private final String loginMethod;

    public LoginTest(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    @Parameterized.Parameters(name = "Login method: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"main_page"},
                {"profile_button"},
                {"register_page"},
                {"forgot_password"}
        });
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();
        createTestUser();
    }

    private void createTestUser() {
        ValidatableResponse response = userApiClient.createUser(
                new CreateUserRequest(testUser.getEmail(), testUser.getPassword(), testUser.getName()));
        response.statusCode(201);
    }

    @Test
    @DisplayName("Проверка входа в аккаунт различными способами")
    @Description("Тестирование входа через разные точки входа в системе")
    public void testLoginVariousMethods() {
        initDriver(getBrowser());

        switch (loginMethod) {
            case "main_page":
                loginViaMainPage();
                break;
            case "profile_button":
                loginViaProfileButton();
                break;
            case "register_page":
                loginViaRegisterPage();
                break;
            case "forgot_password":
                loginViaForgotPasswordPage();
                break;
        }

        MainConstructor mainConstructor = new MainConstructor(driver);
        assertTrue("Ошибка входа: кнопка 'Оформить заказ' не отображается",
                mainConstructor.isCreateOrderButtonDisplayed());
    }

    private void loginViaMainPage() {
        driver.get(PageUrl.MAIN_PAGE_URL);
        MainConstructor mainPage = new MainConstructor(driver);
        mainPage.clickLoginButton();
        performLogin();
    }

    private void loginViaProfileButton() {
        driver.get(PageUrl.MAIN_PAGE_URL);
        MainConstructor mainConstructor = new MainConstructor(driver);
        mainConstructor.clickProfileButton();
        performLogin();
    }

    private void loginViaRegisterPage() {
        driver.get(PageUrl.REGISTER_PAGE_URL);
        RegistrationUser registrationUser = new RegistrationUser(driver);
        registrationUser.clickLoginLink();
        performLogin();
    }

    private void loginViaForgotPasswordPage() {
        driver.get(PageUrl.FORGOT_PASSWORD_URL);
        PasswordRecovery passwordRecovery = new PasswordRecovery(driver);
        passwordRecovery.clickLoginLink();
        performLogin();
    }

    private void performLogin() {
        Login login = new Login(driver);
        login.login(testUser.getEmail(), testUser.getPassword());
    }
}