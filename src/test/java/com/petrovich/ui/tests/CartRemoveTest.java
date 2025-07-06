package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import com.petrovich.ui.pages.CartPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест удаления товара из корзины и проверки обновления счётчика.
 */
public class CartRemoveTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testRemoveProductFromCart() {
        // Добавляем товар, чтобы можно было его удалить
        ProductPage product = new ProductPage().open(code);
        product.addToCart();
        CartPage cart = new CartPage().open();
        int before = cart.getCartCount();

        // Удаляем товар и проверяем, что он исчез и счётчик уменьшился
        cart.removeProduct(code);
        assertFalse(cart.isProductInCart(code));
        assertEquals(before - 1, cart.getCartCount());
    }
}