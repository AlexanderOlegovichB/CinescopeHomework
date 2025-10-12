package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import junit.LoginExtension;
import junit.UITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MovieInfo;
import pages.PaymentPage;
import pages.StartPage;

import static com.codeborne.selenide.Selenide.$x;
@Epic("Общий функционал")
@Feature("Покупка билета")
@UITest
public class BuyTicketTest {

    @Test
    @Story("Пользователь покупает билет")
    @DisplayName("Покупка билета")
    @Description("Тест покупки билета с валидными данными")
    public void canBuyTicketTest() {
        Configuration.holdBrowserOpen = true;

        String ticketCount = "2";
        String cardHolder = "John Doe";
        String cardNumber = "4242424242424242";
        String expMonth = "Декабрь";
        String expYear = "2025";
        String cardCvv = "123";

        StartPage startPage = new StartPage();
        startPage.clickInfo();
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.buyTicket();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.setTicketCount(ticketCount);
        paymentPage.setCardNumber(cardNumber);
        paymentPage.setCardHolder(cardHolder);
        paymentPage.setExpDate(expMonth, expYear);
        paymentPage.setCvv(cardCvv);
        paymentPage.buyTicket();
    }
}
