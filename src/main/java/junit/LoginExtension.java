package junit;

import static com.codeborne.selenide.Condition.clickable;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.LoginPage;
import pages.StartPage;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String email = "ip.boroday@gmail.com";
        String password = "Test2025";


        StartPage startPage = new StartPage();
        startPage.open();
        startPage.getLoginButton().shouldBe(clickable);
        startPage.clickLogin();


        StartPage loggedStartPage = new LoginPage()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignIn();

        loggedStartPage.verifyLogin();
    }
}
