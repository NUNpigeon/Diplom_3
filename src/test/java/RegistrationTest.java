import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.api.config.PageUrl;
import org.example.api.config.TestDataModel;
import org.example.api.models.User;
import org.example.api.pages.LoginPage;
import org.example.api.pages.RegistrationUser;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка регистрации с валидными данными")
    public void testSuccessfulRegistration() {
        initDriver(getBrowser());
        driver.get(PageUrl.REGISTER_PAGE_URL);
        RegistrationUser registrationUser = new RegistrationUser(driver);

        User newUser = TestDataModel.generateUser();
        registrationUser.registrationUser(
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword()
        );

        testUser = newUser;


        LoginPage login = new LoginPage(driver);
        assertTrue(
                "Редирект на страницу логина не произошёл",
                login.isLoginButtonDisplayed()
        );
    }

    @Test
    @DisplayName("Ошибка при регистрации с коротким паролем")
    @Description("Проверка отображения ошибки при вводе пароля менее 6 символов")
    public void testRegistrationWithShortPassword() {
        initDriver(getBrowser());
        driver.get(PageUrl.REGISTER_PAGE_URL);
        RegistrationUser registrationUser = new RegistrationUser(driver);

        User userWithShortPassword = TestDataModel.generateUser();
        String shortPassword = "short";

        registrationUser.registrationUser(
                userWithShortPassword.getName(),
                userWithShortPassword.getEmail(),
                shortPassword
        );

        assertTrue(
                "Ошибка 'Некорректный пароль' не отображается",
                registrationUser.isWrongPasswordErrorDisplayed()
        );

        testUser = null;
    }
}