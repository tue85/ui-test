package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import com.petrovich.ui.pages.CartPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest extends BaseTest {
    private final String code = "104843";
 
    @Test
    public void testAddProductToCart() {
        ProductPage p = new ProductPage().open(code);
        int before = p.getCartCount();
        p.addToCart();
        assertEquals(before+1, p.getCartCount());
        CartPage cart = new CartPage().open();
        assertTrue(cart.isProductInCart(code));
        assertEquals(1, cart.getProductQuantity(code));
    }

    @Test
    public void testChangeProductQuantity() {
        
        CartPage c = new CartPage().open();
        c.updateProductQuantity(code, 5);
        assertEquals(5, c.getProductQuantity(code));
        assertEquals(5*615, c.getTotalPrice());
        c.updateProductQuantity(code, 0);
        assertFalse(c.isProductInCart(code));
    }

    @Test
    public void testRemoveProductFromCart() {
        ProductPage p = new ProductPage().open(code);
        p.addToCart();
        CartPage c = new CartPage().open();
        int before = c.getCartCount();
        c.removeProduct(code);
        assertFalse(c.isProductInCart(code));
        assertEquals(before-1, c.getCartCount());
    }
}
