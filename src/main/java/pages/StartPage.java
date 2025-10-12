package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class StartPage {
    private String baseUrl = "https://cinescope.t-qa.ru";
    private SelenideElement loginButton = $("[data-qa-id=\"login_page_button\"]");



    public void clickLogin() {
        loginButton.click();
    }
}
