package junit;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SelenideExtension implements BeforeAllCallback, AfterEachCallback {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.baseUrl = "https://cinescope.t-qa.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.closeWebDriver();
        }
    }
}