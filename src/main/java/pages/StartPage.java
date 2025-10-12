package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StartPage {
    private String baseUrl = "https://cinescope.t-qa.ru";
    private SelenideElement loginButton = $("[data-qa-id=\"login_page_button\"]");
    private SelenideElement infoButton = $x("//main//button[1]");
    private SelenideElement profileButton = $("[data-qa-id=\"profile_page_button\"]");


    public void openStartPage() {
        open(baseUrl);
    }

    public void verifiLogin() {
        profileButton.shouldBe(visible);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void clickInfo() {
        infoButton.click();
    }
}
