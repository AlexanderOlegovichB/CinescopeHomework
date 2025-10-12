package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MovieInfo {

    private SelenideElement buyButton = $x("//button[.//p[text()='Купить билет']]");

    public void buyTicket() {
        buyButton.click();
    }
}
