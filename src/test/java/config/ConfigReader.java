package config;

import config.web.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;

public enum ConfigReader {
    INSTANCE;

    private static final WebDriverConfig webConfig =
            ConfigFactory.create(
                    WebDriverConfig.class,
                    System.getProperties()
            );

    public WebDriverConfig read() {
        return webConfig;
    }
}
