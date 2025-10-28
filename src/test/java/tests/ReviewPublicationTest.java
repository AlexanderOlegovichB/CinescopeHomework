package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import junit.UITest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MovieInfoPage;
import pages.StartPage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("Общий функционал")
@Feature("Отзывы")
@UITest
public class ReviewPublicationTest {

    private StartPage startPage = new StartPage();
    private MovieInfoPage movieInfoPage = new MovieInfoPage();

    @Test
    @Story("Пользователь публикует отзыв")
    @DisplayName("Публикация отзыва")
    @Description("Тест публикации отзыва с валидными данными")
    public void canBuyTicketTest() {
        Configuration.holdBrowserOpen = true; //отладочная конфигурация


        String reviewExample = "Один фильм офигительней другого";
        String ratingValue = "4";
        String reviewAuthor = "Тестовый Юзер Юзерович";


        Allure.step("Нажать \"Подробнее\" у фильма", () -> {
            startPage.clickMovieInfo();
        });

        Allure.step("Написать отзыв в поле ввода отзыва", () -> {
            movieInfoPage.reviewEdit(reviewExample);
        });

        Allure.step("Выбрать балл рейтинга", () -> {
            movieInfoPage.setRatingValue(ratingValue);
        });

        Allure.step("Нажимаем кнопку \"Отправить\"", () -> {
            movieInfoPage.sendReview();
        });

        Allure.step("Проверка публикации отзыва", () -> {

            String succesReviewPublicationTextAssert = movieInfoPage.getReviewText();
            assertThat(succesReviewPublicationTextAssert).isEqualTo(reviewExample);

            String succesReviewPublicationAuthorAssert = movieInfoPage.getReviewAuthor();
            assertThat(succesReviewPublicationAuthorAssert).isEqualTo(reviewAuthor);

            String succesReviewPublicationValueAssert = movieInfoPage.getReviewValue();
            assertThat(succesReviewPublicationValueAssert).contains(ratingValue);
        });

    }
        @AfterEach
        public void cleanReview() {
        Allure.step("Постусловие - удаление отзыва", () -> {
            movieInfoPage.deleteReview();
        });

        Allure.step("Проверка удаления отзыва", () -> {

            SelenideElement succesVisibleReviewInput = movieInfoPage.getReviewInput();
            assertThat(succesVisibleReviewInput.isDisplayed());
        });
    }
}
