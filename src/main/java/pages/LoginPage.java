package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement inputEmail = $("[data-qa-id='login_email_input']");
    private SelenideElement inputPassword = $("[data-qa-id='login_password_input']");
    private SelenideElement signButton = $("[data-qa-id='login_submit_button']");


    public LoginPage setEmail(String value) {
        inputEmail.shouldBe(visible);
        inputEmail.clear();
        inputEmail.setValue(value);
        return this;
    }

    public LoginPage setPassword(String value) {
        inputPassword.shouldBe(visible);
        inputPassword.clear();
        inputPassword.setValue(value);
        return this;
    }

    public StartPage clickSignIn() {
        signButton.shouldBe(visible);
        signButton.click();
        return new StartPage();
    }
}
