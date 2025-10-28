package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class StartPage {
    private String baseUrl = "https://cinescope.t-qa.ru";
    private SelenideElement loginButton = $("a[data-qa-id='login_page_button'] > button");
    private SelenideElement movieInfoButton = $x("(//button[text()='Подробнее'])[1]");
    private SelenideElement profileButton = $("[data-qa-id=\"profile_page_button\"]");
    private SelenideElement moreButton = $("ul.flex");
    private SelenideElement locationFilter = $("[data-qa-id='movies_filter_location_select']");
    private SelenideElement genreFilter = $x("(//main//button[@value='all'])");
    private SelenideElement freshFilter = $("[data-qa-id=\"movies_filter_created_at_select\"]");
    private ElementsCollection optionalSelect = $$("[role='option']");


    public void clickMore() {
        moreButton.click();
    }

    public void setLocationFilter(String location) {
        locationFilter.parent().click();
        optionalSelect.findBy(text(location)).click();
    }

    public void setGenreFilter(String genre) {
        genreFilter.click();
        optionalSelect.findBy(text(genre)).click();
    }


    public void setFreshFilter(String fresh) {
        freshFilter.parent().click();
        optionalSelect.findBy(text(fresh)).click();
    }

    public void openStartPage() {
        open(baseUrl);
    }

    public void clickLogin() {
        loginButton.shouldBe(clickable);
        loginButton.click();
    }

    public void verifiLogin() {
        profileButton.shouldBe(visible);
    }

    public void clickMovieInfo() {
        movieInfoButton.click();
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }
}
