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

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Epic("Общий функционал")
@Feature("Отзывы")
@UITest
public class ReviewPublicationTest {

    @Test
    @Story("Пользователь публикует отзыв")
    @DisplayName("Публикация отзыва")
    @Description("Тест публикации отзыва с валидными данными")
    public void canBuyTicketTest() {
        Configuration.holdBrowserOpen = true; //отладочная конфигурация

        String reviewExample = "Один фильм офигительней другого";
        String ratingValue = "4";
        String expectRatingValue = "4/5";
        String reviewAuthor = "Тестовый Юзер Юзерович";


        Allure.step("Нажать \"Подробнее\" у фильма", () -> {
            StartPage startPage = new StartPage();
            startPage.clickInfo();
        });

        Allure.step("Написать отзыв в поле ввода отзыва", () -> {
            MovieInfo movieInfo = new MovieInfo();
            movieInfo.reviewEdit(reviewExample);
        });

        Allure.step("Выбрать балл рейтинга", () -> {
            MovieInfo movieInfo = new MovieInfo();
            movieInfo.setRatingValue(ratingValue);
        });

        Allure.step("Нажимаем кнопку \"Отправить\"", () -> {
            MovieInfo movieInfo = new MovieInfo();
            movieInfo.sendReview();
        });

        Allure.step("Проверка публикации отзыва", () -> {
            MovieInfo movieInfo = new MovieInfo();

            String succesReviewPublicationTextAssert = movieInfo.getReviewText();
            assertThat(succesReviewPublicationTextAssert).isEqualTo(reviewExample);

            String succesReviewPublicationAuthorAssert = movieInfo.getReviewAuthor();
            assertThat(succesReviewPublicationAuthorAssert).isEqualTo(reviewAuthor);

            String succesReviewPublicationValueAssert = movieInfo.getReviewValue();
            assertThat(succesReviewPublicationValueAssert).contains(ratingValue);
        });

        Allure.step("Постусловие - удаление отзыва", () -> {
            MovieInfo movieInfo = new MovieInfo();
            movieInfo.deleteReview();
        });

        Allure.step("Проверка удаления отзыва", () -> {
            MovieInfo movieInfo = new MovieInfo();

            SelenideElement succesVisibleReviewInput = movieInfo.getReviewInput();
            assertThat(succesVisibleReviewInput.isDisplayed());
        });
    }
}
