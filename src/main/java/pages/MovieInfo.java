package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MovieInfo {

    private SelenideElement reviewInput = $("[data-qa-id=\"movie_review_input\"]");
    private SelenideElement buyButton = $x("//button[.//p[text()='Купить билет']]");
    private SelenideElement sendReviewButton = $("[data-qa-id=\"movie_review_submit_button\"]");
    private SelenideElement ratingSelect = $("button[role='combobox']");
    private ElementsCollection ratingOption = $$("[role='option']");
    private SelenideElement reviewBlock = $x("//div[contains(@class, 'rounded-xl')][.//h4[text()='Тестовый Юзер Юзерович']]");
    private SelenideElement reviewText = $("p.overflow-hidden.text-ellipsis");
    private SelenideElement reviewValue = $("span.underline");
    private SelenideElement reviewAuthor = $("h4.text-xl.w-fit");
    private SelenideElement reviewActionsButton = $("[data-qa-id=\"movie_review_actions_button\"]");
    private SelenideElement reviewActionsDeleteButton = $("[data-qa-id=\"movie_review_action_delete_button\"]");

    public void buyTicket() {
        buyButton.click();
    }

    public void reviewEdit(String value) {
        reviewInput.setValue(value);
    }

    public void setRatingValue(String value) {
        ratingSelect.click();
        ratingOption.findBy(text(value)).click();
    }

    public void sendReview() {
        sendReviewButton.click();
    }

    public String getReviewText() {
        return reviewText.getText();
    }

    public String getReviewValue() {
        return reviewValue.getText();
    }

    public String getReviewAuthor() {
        return reviewAuthor.getText();
    }

    public void deleteReview() {
        reviewActionsButton.click();
        reviewActionsDeleteButton.click();
    }

    public SelenideElement getReviewInput() {
        return reviewInput;
    }
}
