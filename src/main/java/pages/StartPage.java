package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class StartPage {
    private SelenideElement loginButton = $("a[data-qa-id='login_page_button'] > button");
    private SelenideElement movieInfoButton = $x("(//button[text()='Подробнее'])[1]");
    private SelenideElement profileButton = $("[data-qa-id='profile_page_button']");
    private SelenideElement moreButton = $("ul.flex");
    private SelenideElement genreFilter = $x("(//main//button[@value='all'])");
    private ElementsCollection optionalSelect = $$("[role='option']");


    @Step("Логинимся пользователем {email}")
    public StartPage loginAs(String email, String password) {
        clickLogin();
        new LoginPage()
                .setEmail(email)
                .setPassword(password)
                .clickSignIn()
                .verifyLogin();
        return this;
    }

    @Step("Нажимаем 'Все фильмы'")
    public StartPage clickAllMovies() {
        moreButton.click();
        return this;
    }

    @Step("Выставляем жанр")
    public StartPage setGenreFilter(String genre) {
        genreFilter.click();
        optionalSelect.findBy(text(genre)).click();
        return this;
    }

    @Step("Открываем стартовую страницу")
    public StartPage open() {
        Selenide.open("/");
        return this;
    }

    @Step("Кликаем кнопку 'Войти'")
    public LoginPage clickLogin() {
        loginButton.shouldBe(clickable);
        loginButton.click();
        return new LoginPage();
    }


    public StartPage verifyLogin() {
        profileButton.shouldBe(visible);
        return this;
    }

    @Step("Нажимаем 'Подробнее' у первого фильма в выдаче")
    public MovieInfoPage clickMovieInfo() {
        movieInfoButton.click();
        return new MovieInfoPage();
    }
}
