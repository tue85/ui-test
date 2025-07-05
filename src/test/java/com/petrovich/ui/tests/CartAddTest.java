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
        int before = product.getCartCount();

        // Добавляем товар и проверяем счётчик в шапке
        product.addToCart();
        assertEquals(before + 1, product.getCartCount());

        // Переходим в корзину и проверяем наличие и количество товара
        CartPage cart = new CartPage().open();
        assertTrue(cart.isProductInCart(code));
        assertEquals(1, cart.getProductQuantity(code));
    }
}