import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.api.config.PageUrl;
import org.example.api.models.CreateUserRequest;
import org.example.api.pages.Login;
import org.example.api.pages.MainConstructor;
import org.example.api.pages.Profile;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода из аккаунта через личный кабинет")
    public void testLogout() {
        initDriver(getBrowser());

        // Создаем и логиним пользователя
        userApiClient.createUser(
                new CreateUserRequest(testUser.getEmail(), testUser.getPassword(), testUser.getName())
        );

        // Логин через UI
        driver.get(PageUrl.LOGIN_PAGE_URL);
        Login login = new Login(driver);
        login.login(testUser.getEmail(), testUser.getPassword());

        // Переход в профиль и выход
        MainConstructor mainConstructor = new MainConstructor(driver);
        mainConstructor.clickProfileButton();

        Profile profile = new Profile(driver);
        profile.clickLogoutButton();

        // Проверяем, что произошел выход (редирект на страницу логина)
        assertTrue("Выход из аккаунта не произошел",
                login.isLoginButtonDisplayed());
    }
}
