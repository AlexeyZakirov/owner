package config;

import com.codeborne.selenide.Configuration;
import config.web.WebDriverConfig;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ProjectConfiguration {
    private final WebDriverConfig webConfig;

    public ProjectConfiguration(WebDriverConfig webConfig){
        this.webConfig = webConfig;
    }

    public void apiConfig(){
        RestAssured.baseURI = webConfig.baseUrl();
    }

    public void webConfig(){
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser().toString();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
        }
    }
}
