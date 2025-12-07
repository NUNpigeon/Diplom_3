import static org.junit.Assert.assertEquals;

import org.example.api.config.PageUrl;
import org.example.api.pages.MainConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MainConstructorTest extends BaseTest {
    private final String sectionName;

    public MainConstructorTest(String sectionName) {
        this.sectionName = sectionName;
    }

    @Parameterized.Parameters(name = "Section: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Булки"},
                {"Соусы"},
                {"Начинки"}
        });
    }

    @Test
    public void testConstructorSectionSwitching() {
        initDriver(getBrowser());
        driver.get(PageUrl.MAIN_PAGE_URL);

        MainConstructor mainConstructor = new MainConstructor(driver);

        switch (sectionName) {
            case "Булки":
                mainConstructor.clickBunSection();
                break;
            case "Соусы":
                mainConstructor.clickSauceSection();
                break;
            case "Начинки":
                mainConstructor.clickToppingSection();
                break;
        }

        String actualSection = mainConstructor.getCurrentSectionText();
        assertEquals("Не произошел переход к разделу " + sectionName, sectionName, actualSection);
    }
}