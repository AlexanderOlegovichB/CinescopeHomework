package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import junit.UITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MovieInfoPage;
import pages.PaymentPage;
import pages.StartPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@Epic("Общий функционал")
@Feature("Покупка билета")
@UITest
@Tag("regress")
@DisplayName("Покупка билета")
public class BuyTicketTest {

    private StartPage startPage = new StartPage();
    private MovieInfoPage movieInfoPage = new MovieInfoPage();
    private PaymentPage paymentPage = new PaymentPage();

    @Test
    @Story("Пользователь покупает билет")
    @DisplayName("Покупка билета")
    @Description("Тест покупки билета с валидными данными")
    public void canBuyTicketTest() {


        String ticketCount = "2";
        String cardHolder = "John Doe";
        String cardNumber = "4242424242424242";
        String expMonth = "Декабрь";
        String expYear = "2026";
        String cardCvv = "123";
        String successPayment = "Спасибо за покупку";

        startPage.clickMovieInfo();

        movieInfoPage.buyTicket();

        paymentPage
                .setTicketCount(ticketCount)
                .setCardNumber(cardNumber)
                .setCardHolder(cardHolder)
                .setExpMonth(expMonth)
                .setExpYear(expYear)
                .setCvv(cardCvv)
                .submitPayment();

        String successTextAssert = paymentPage.getSuccessText();
        assertThat(successTextAssert).isEqualTo(successPayment);

        assertThat(paymentPage.isSuccessIconVisible())
                .as("Иконка успеха должна быть видна")
                .isTrue();
    }
}
