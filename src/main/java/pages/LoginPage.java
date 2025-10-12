package pages;

import com.codeborne.selenide.SelenideElement;

public class LoginPage {

    private SelenideElement inputEmail;
    private SelenideElement inputPassword;
    private SelenideElement signButton;


    public LoginPage enterEmail(String value) {
        inputEmail.setValue(value);
        return this;
    }
    public LoginPage enterPassword(String value) {
        inputPassword.setValue(value);
        return this;
    }
    public DashboardPage clickSignIn() {
        signButton.click();
        return new DashboardPage();
    }
}
