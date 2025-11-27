package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class StartPage {
    private String baseUrl = "https://cinescope.t-qa.ru";
    private SelenideElement loginButton = $("a[data-qa-id='login_page_button'] > button");
    private SelenideElement movieInfoButton = $x("(//button[text()='Подробнее'])[1]");
    private SelenideElement profileButton = $("[data-qa-id='profile_page_button']");
    private SelenideElement moreButton = $("ul.flex");
    private SelenideElement locationFilter = $("[data-qa-id='movies_filter_location_select']");
    private SelenideElement genreFilter = $x("(//main//button[@value='all'])");
    private SelenideElement freshFilter = $("[data-qa-id='movies_filter_created_at_select']");
    private ElementsCollection optionalSelect = $$("[role='option']");

    @Step("Нажимаем 'Все фильмы'")
    public StartPage clickMore() {
        moreButton.click();
        return this;
    }

    @Step("Выставляем локацию")
    public StartPage setLocationFilter(String location) {
        locationFilter.parent().click();
        optionalSelect.findBy(text(location)).click();
        return this;
    }

    @Step("Выставляем гендер")
    public StartPage setGenreFilter(String genre) {
        genreFilter.click();
        optionalSelect.findBy(text(genre)).click();
        return this;
    }

    public StartPage open() {
        Selenide.open(baseUrl);
        return this;
    }

    public void clickLogin() {
        loginButton.shouldBe(clickable);
        loginButton.click();
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

    public SelenideElement getLoginButton() {
        return loginButton;
    }
}
