// ProductPageTest.java
package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductPageTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testProductDetailsPageElements() {
        // Открываем страницу товара
        ProductPage product = new ProductPage().open(code);

        // Проверяем заголовок страницы
        String expectedTitle = "Штукатурка гипсовая Knauf МП-75 машинная 30 кг";
        assertEquals(expectedTitle, product.getTitle(), "Заголовок страницы не соответствует ожидаемому");

        // Проверяем отображение цены
        String price = product.getPrice();
        assertNotNull(price, "Цена не должна быть null");
        assertFalse(price.isEmpty(), "Цена не должна быть пустой строкой");

        // Проверяем описание товара
        String description = product.getDescription();
        assertNotNull(description, "Описание не должно быть null");
        assertFalse(description.isEmpty(), "Описание не должно быть пустым");

        // Проверяем работу кнопки "В корзину"
        int initialCount = product.getCartCount();
        product.addToCart();
        assertEquals(initialCount + 1, product.getCartCount(), "Счётчик в корзине должен увеличиться на 1 после добавления товара");

        // Проверяем, что изображение товара отображается
        assertTrue(product.isImageDisplayed("mp75"), "Изображение товара с фрагментом 'mp75' в src должно быть видно");

        // Проверяем таблицу технических характеристик
        assertTrue(product.hasTechnicalSpecifications(), "Таблица технических характеристик должна быть видна");

        // Проверяем секцию "Отзывы"
        assertTrue(product.hasReviewsSection(), "Секция отзывов должна быть видна");

        // Проверяем секцию "Доставка"
        assertTrue(product.hasDeliveryInfoSection(), "Секция информации о доставке должна быть видна");
    }
}
