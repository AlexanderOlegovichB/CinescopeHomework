package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {

    private SelenideElement ticketCount = $("[data-qa-id='payment_amount_input']");
    private SelenideElement cardNumber = $("[data-qa-id='payment_card_number_input']");
    private SelenideElement cardHolder = $("[data-qa-id='payment_card_holder_input']");
    private SelenideElement cardExpMonth = $("[data-qa-id='payment_card_month_select']");
    private ElementsCollection cardExpMonthSelector = $$("[role='option']");
    private SelenideElement cardExpYear = $("[data-qa-id='payment_card_year_select']");
    private ElementsCollection cardExpYearSelector = $$("[role='option']");
    private SelenideElement cardCvv = $("[data-qa-id='payment_card_cvc_input']");
    private SelenideElement paymentButton = $("[data-qa-id='payment_submit_button']");
    private SelenideElement successIcon = $("svg.lucide-circle-check-big.text-green-500");
    private SelenideElement successText = $("p.text-xl");
    private SelenideElement returnToMainButton = $("svg.lucide lucide-circle-check-big.text-green-500");


    @Step("Выставить кол-во билетов")
    public PaymentPage setTicketCount(String value) {
        ticketCount.setValue(value);
        return this;
    }

    @Step("Выставить номер карты")
    public PaymentPage setCardNumber(String value) {
        cardNumber.setValue(value);
        return this;
    }

    @Step("Выставить владельца карты")
    public PaymentPage setCardHolder(String value) {
        cardHolder.setValue(value);
        return this;
    }

    @Step("Выставить месяц карты")
    public PaymentPage setExpMonth(String month) {
        cardExpMonth.click();
        cardExpMonthSelector.findBy(text(month)).click();
        return this;
    }

    @Step("Выставить год карты")
    public PaymentPage setExpYear(String year) {
        cardExpYear.click();
        cardExpYearSelector.findBy(text(year)).click();
        return this;
    }

    @Step("Выставить cvv")
    public PaymentPage setCvv(String value) {
        cardCvv.setValue(value);
        return this;
    }

    @Step("Подтвердить оплату")
    public PaymentPage submitPayment() {
        paymentButton.click();
        return this;
    }

    public String getSuccessText() {
        return successText.getText();
    }

    public boolean isSuccessIconVisible() {return successIcon.is(visible, Duration.ofSeconds(5));}
}
