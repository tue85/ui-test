package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import com.petrovich.ui.pages.FavoritesPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import com.codeborne.selenide.Condition;
import java.time.Duration;

/**
 * Тесты, связанные с удалением товара из избранного и проверкой счётчика.
 */
public class FavoritesRemoveTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testRemoveFavorite() {
        // Убедимся, что товар в избранном (или добавим его)
        ProductPage product = new ProductPage().open(code);
        if (product.getFavoritesCount() == 0) {
            product.addToFavorites();
            new FavoritesPage().open(); // обновляем страницу
        }
        FavoritesPage fav = new FavoritesPage().open();
        int before = product.getFavoritesCount();

        // Удаляем из избранного
        product.open(code).removeFromFavorites();
        $("span.favorites-count")
            .shouldHave(Condition.text(String.valueOf(before - 1)), Duration.ofSeconds(2));
        assertEquals(before - 1, product.getFavoritesCount());

        // Проверяем, что товар исчез из списка избранного
        FavoritesPage favAfter = fav.open();
        $(byText(code))
            .shouldNot(Condition.exist, Duration.ofSeconds(2));
        assertFalse(favAfter.containsProduct(code));
    }
}
