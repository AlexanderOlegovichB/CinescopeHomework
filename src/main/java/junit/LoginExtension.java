package junit;

import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.StartPage;
import utils.RoleCreds;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {

//        Берем класс теста
        Class<?> testClass = context.getRequiredTestClass();
//        Ищем аннотацию юайтест на классе
        UITest uiTest = testClass.getAnnotation(UITest.class);
        if (uiTest == null) {
            throw new IllegalStateException("UItest не найдена на классе" + testClass.getName());
        }

        RoleCreds role = uiTest.loginRole();

        loginWithRole(role);
    }

    @Step("UI-логин пользователя {role.email}")
    private void loginWithRole(RoleCreds role) {
        new StartPage()
                .open()
                .loginAs(role.getEmail(), role.getPassword());
    }
}
