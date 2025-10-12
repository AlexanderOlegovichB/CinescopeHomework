package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private String loginUrl = "/login";
    private SelenideElement inputEmail = $("[data-qa-id=\"login_email_input\"]");
    private SelenideElement inputPassword = $("[data-qa-id=\"login_password_input\"]");
    private SelenideElement signButton = $("[data-qa-id=\"login_submit_button\"]");


    public LoginPage enterEmail(String value) {
        inputEmail.clear();
        inputEmail.setValue(value);
        return this;
    }
    public LoginPage enterPassword(String value) {
        inputPassword.clear();
        inputPassword.setValue(value);
        return this;
    }
    public StartPage clickSignIn() {
        signButton.click();
        return new StartPage();
    }
}
