package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import junit.UITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MovieInfoPage;
import pages.StartPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("Общий функционал")
@Feature("Фильтры")
@UITest
public class AddFiltersTest {

    private MovieInfoPage movieInfoPage = new MovieInfoPage();
    private StartPage startPage = new StartPage();

    @Test
    @Story("Пользователь применяет фильтры")
    @DisplayName("Применение фильтров")
    @Description("Валидный тест применения фильтров поиска фильмов на главной странице")

    public void correctAddFiltersTest() {
        String genre = "Триллер";
        startPage.clickMore().setGenreFilter(genre).clickMovieInfo();

        String succesGenreFilterMatch = movieInfoPage.getGenreInfo();
        assertThat(succesGenreFilterMatch).contains(genre);
    }
}
