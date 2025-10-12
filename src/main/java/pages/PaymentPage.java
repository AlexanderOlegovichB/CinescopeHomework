package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {

    private SelenideElement ticketCount = $("data-qa-id=\"payment_amount_input\"");
    private SelenideElement cardNumber = $("data-qa-id=\"payment_card_number_input\"");
    private SelenideElement cardHolder = $("John Doe");
    private SelenideElement cardExpMonth = $("data-qa-id=\"payment_card_month_select\"");
    private SelenideElement cardExpYear = $("data-qa-id=\"payment_card_year_select\"");
    private SelenideElement cardCvv = $("data-qa-id=\"payment_card_cvc_input\"");
    private SelenideElement paymentButton = $("data-qa-id=\"payment_submit_button\"");


    public void setTicketCount(String value) {
        ticketCount.setValue(value);
    }

    public void setCardNumber(String value) {
        cardNumber.setValue(value);
    }

    public void setCardHolder(String value) {
        cardHolder.setValue(value);
    }

    public void setExpDate(String month, String year) {
        cardExpMonth.click();
        cardExpMonth.selectOption(month);
        cardExpYear.click();
        cardExpYear.selectOption(year);
    }

    public void setCvv(String value) {
        cardCvv.setValue(value);
    }

    public void buyTicket() {
        paymentButton.click();
    }
}
