package com.petrovich.ui.tests;

import com.petrovich.ui.pages.CartPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест изменения количества товара в корзине и проверки корректности суммы.
 */
public class CartUpdateQuantityTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testChangeProductQuantity() {
        CartPage cart = new CartPage().open();

        // Изменяем количество товара и проверяем корректность значения и суммы
        cart.updateProductQuantity(code, 5);
        assertEquals(5, cart.getProductQuantity(code));
        assertEquals(5 * 615, cart.getTotalPrice());

        // Удаляем товар, установив количество в 0
        cart.updateProductQuantity(code, 0);
        assertFalse(cart.isProductInCart(code));
    }
}