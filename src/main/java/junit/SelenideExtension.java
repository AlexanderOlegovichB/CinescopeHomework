package junit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class SelenideExtension implements BeforeAllCallback, AfterEachCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        // Определяем, запускать локально или через Selenoid
        configureSelenoid();

        // Selenide
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.baseUrl = "https://cinescope.t-qa.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
    }

    private void configureSelenoid() {
        // Адрес Selenoid
        Configuration.remote = "http://selenoid:4444/wd/hub";
        Configuration.browserVersion = "128.0";

        // Capabilities для Selenoid
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", false,
                "enableLog", true
        ));

        // ChromeOptions
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080"
        );
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        Configuration.browserCapabilities = capabilities;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.closeWebDriver();
        }
    }
}

