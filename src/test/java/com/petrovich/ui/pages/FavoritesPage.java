package com.petrovich.ui.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

/**
 * Страница избранных товаров.
 */
public class FavoritesPage extends BasePage {
    // TODO: Проверьте в адресной строке относительный путь к странице избранного (например, "/favorites")
    public FavoritesPage open() {
        openPage("/favorites");
        return this;
    }

    /**
     * Проверяет, что на странице избранного присутствует товар с указанным кодом.
     * @param code точный код товара, скопированный из UI (например, из карточки товара или списка)
     */
    public boolean containsProduct(String code) {
        // TODO: Вызовите this метод с тем же текстом, что отображается на странице
        return $(byText(code)).exists();
    }
}
