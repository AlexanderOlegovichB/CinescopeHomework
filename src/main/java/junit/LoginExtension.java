package junit;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.StartPage;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String email = "ip.boroday@gmail.com";
        String password = "Test2025";


        new StartPage()
                .open()
                .loginAs(email, password);
    }
}
