import org.example.api.config.Browser;
import org.example.api.config.PageUrl;
import org.example.api.config.TestDataModel;
import org.example.api.models.User;
import org.example.api.models.UserApi;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected User testUser;
    protected UserApi userApiClient;

    @Before
    public void setUp() {
        userApiClient = new UserApi(PageUrl.PAGE_URL);
        testUser = TestDataModel.generateUser();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        deleteTestUserSafely();
    }

    protected void initDriver(String browser) {
        try {
            driver = Browser.getDriver(browser);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (Exception e) {
            System.err.println("Ошибка при инициализации драйвера: " + e.getMessage());
            throw e;
        }
    }

    protected String getBrowser() {
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = System.getenv("BROWSER");
        }
        return browser != null && !browser.isEmpty() ? browser : "chrome";
    }

    private void deleteTestUserSafely() {
        if (testUser == null || testUser.getEmail() == null) {
            return;
        }

        try {
            ValidatableResponse response = userApiClient.loginUser(testUser);
            String accessToken = response.extract().path("accessToken");

            if (accessToken != null && !accessToken.isEmpty()) {
                userApiClient.deleteUser(accessToken);
                System.out.println("Пользователь " + testUser.getEmail() + " удалён");
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя " + testUser.getEmail() + ": " + e.getMessage());
        }
    }
}