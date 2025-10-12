package junit;

import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.DashboardPage;
import pages.LoginPage;
import pages.StartPage;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String email = "email";
        String password = "password";


        StartPage startPage = new StartPage();
        startPage.clickLogin();


        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new LoginPage().enterEmail(email).enterPassword(password).clickSignIn();
    }
}
