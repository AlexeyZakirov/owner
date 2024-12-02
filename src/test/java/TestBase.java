import api.bookStore.BookStoreApi;
import api.models.login.ResponseLoginModel;
import api.profile.ProfileApi;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import config.ProjectConfiguration;
import config.web.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.ProfilePage;

import static api.authorization.AuthorizationApi.getAuthorization;

public class TestBase {

    private static final WebDriverConfig webConfig = ConfigReader.INSTANCE.read();

    private static ResponseLoginModel loginModel;
    protected String token = loginModel.getToken();
    protected String userId = loginModel.getUserId();
    protected String expires = loginModel.getExpires();

    protected final ProfileApi profileApi = new ProfileApi();
    protected final BookStoreApi bookStoreApi = new BookStoreApi();
    protected final ProfilePage profilePage = new ProfilePage();

    @BeforeAll
    public static void setUp() {

        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
        projectConfiguration.apiConfig();
        projectConfiguration.webConfig();

        Configuration.pageLoadStrategy = "eager";
        loginModel = getAuthorization();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void tearDown() {
        if (Configuration.browser.equals("firefox")) {
            byte[] lastScreenshots = Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.addVideo();
        } else {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }
        Selenide.closeWebDriver();
    }
}
