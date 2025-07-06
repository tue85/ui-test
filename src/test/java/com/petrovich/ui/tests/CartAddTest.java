package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import com.petrovich.ui.pages.CartPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест добавления товара в корзину и проверка счётчика.
 */
public class CartAddTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testAddProductToCart() {
        
        // Открываем страницу товара и фиксируем текущее число товаров в корзине
        ProductPage product = new ProductPage().open(code);
        // Считываем текущее количество товаров в шапке сайта
        int before = product.getCartCount();

        // Добавляем товар и проверяем счётчик в шапке
        product.addToCart();
        // Проверяем, что счётчик в шапке увеличился на 1
        assertEquals(before + 1, product.getCartCount());

        // Переходим в корзину и проверяем наличие и количество товара
        CartPage cart = new CartPage().open();
        // Убеждаемся, что добавленный товар присутствует в корзине
        assertTrue(cart.isProductInCart(code));
        // Проверяем, что количество товара в корзине равно 1
        assertEquals(1, cart.getProductQuantity(code));
    }
}