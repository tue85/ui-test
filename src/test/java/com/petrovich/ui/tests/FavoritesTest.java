package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import com.petrovich.ui.pages.FavoritesPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class FavoritesTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testAddAndRemoveFavorite() {

        
        // Открываем страницу товара и получаем начальное значение счётчика избранного
        ProductPage product = new ProductPage().open(code);
        int before = product.getFavoritesCount();

        // Добавляем в избранное и ждём обновления счётчика
        product.addToFavorites();
        $("span.favorites-count")
            .shouldHave(Condition.text(String.valueOf(before + 1)), Duration.ofSeconds(2));
        assertEquals(before + 1, product.getFavoritesCount());

        // Переходим в избранное и ждём появления товара
        FavoritesPage fav = new FavoritesPage().open();
        $(byText(code))
            .shouldBe(Condition.visible, Duration.ofSeconds(2));
        assertTrue(fav.containsProduct(code));

        // Удаляем из избранного и ждём обновления счётчика
        product.open(code).removeFromFavorites();
        $("span.favorites-count")
            .shouldHave(Condition.text(String.valueOf(before)), Duration.ofSeconds(1));
        assertEquals(before, product.getFavoritesCount());

        // Ждём, пока товар исчезнет из списка избранного и проверяем отсутствие
        FavoritesPage favAfter = fav.open();
        $(byText(code))
            .shouldNot(Condition.exist, Duration.ofSeconds(2));
        assertFalse(favAfter.containsProduct(code));
    }
}
