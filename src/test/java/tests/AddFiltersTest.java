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
       Configuration.holdBrowserOpen = true; //отладочная конфигурация
        String genre = "Триллер";


        Allure.step("Нажимаем 'Все фильмы'", () -> {
            startPage.clickMore();
        });

        Allure.step("Выбираем жанр", () -> {
            startPage.setGenreFilter(genre);
        });

        Allure.step("Нажимаем 'Подробнее' у первого фильма в выдаче", () -> {
            startPage.clickMovieInfo();
        });

        Allure.step("Проверяем жанр на соответствие фильтру", () -> {

            String succesGenreFilterMatch = movieInfoPage.getGenreInfo();
            assertThat(succesGenreFilterMatch).contains(genre);
        });
    }
}
