package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Epic("Общий функционал")
@Feature("Покупка билета")
@UITest
public class BuyTicketTest {

    @Test
    @Story("Пользователь покупает билет")
    @DisplayName("Покупка билета")
    @Description("Тест покупки билета с валидными данными")
    public void canBuyTicketTest() {
        //Configuration.holdBrowserOpen = true; //отладочная конфигурация

        String ticketCount = "2";
        String cardHolder = "John Doe";
        String cardNumber = "4242424242424242";
        String expMonth = "Декабрь";
        String expYear = "2025";
        String cardCvv = "123";
        String succesPayment = "Спасибо за покупку";

        Allure.step("Нажать \"Подробнее\" у фильма", () -> {
            StartPage startPage = new StartPage();
            startPage.clickInfo();
        });

        Allure.step("Нажать \"Купить билет\" в карточке фильма", () -> {
            MovieInfo movieInfo = new MovieInfo();
            movieInfo.buyTicket();
        });

        Allure.step("Заполнение формы оплаты", () -> {
            PaymentPage paymentPage = new PaymentPage();
            paymentPage.setTicketCount(ticketCount);
            paymentPage.setCardNumber(cardNumber);
            paymentPage.setCardHolder(cardHolder);
            paymentPage.setExpMonth(expMonth);
            paymentPage.setExpYear(expYear);
            paymentPage.setCvv(cardCvv);
            paymentPage.buyTicket();
        });

        Allure.step("Проверка успешной оплаты", () -> {
            PaymentPage paymentPage = new PaymentPage();

            String succesTextAssert = paymentPage.getSuccesText();
            assertThat(succesTextAssert).isEqualTo(succesPayment);
            SelenideElement succesIconAssert = paymentPage.getSuccesIcon();
            assertThat(succesIconAssert.isDisplayed());
        });
    }
}
