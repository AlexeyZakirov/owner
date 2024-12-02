package config.web;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})

public interface WebDriverConfig extends Config {
    @Key("browser")
    @DefaultValue("chrome")
    Browser browser();

    @Key("browserVersion")
    @DefaultValue("124.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("baseUrl")
    String baseUrl();

    @Key("isRemote")
    boolean isRemote();

    @Key("remoteUrl")
    String remoteUrl();
}
