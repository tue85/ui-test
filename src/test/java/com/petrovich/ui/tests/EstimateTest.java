package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import com.petrovich.ui.pages.CartPage;
import com.petrovich.ui.pages.EstimatesPage;
import com.petrovich.ui.pages.EstimatePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstimateTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testCreateEstimate() {
        String estimateName = "Тестовая смета";

        
        CartPage c = new CartPage().open();
        c.updateProductQuantity(code, 2);

        EstimatesPage list = new EstimatesPage().open();
        EstimatePage e = list.clickCreateNewEstimate();
        e.setName(estimateName);
        e.addProduct(code, 2);
        e.save();

        list.open();

        assertTrue(e.containsProduct(code, 2));
        assertEquals(2*615, e.getTotal());
        assertTrue(list.isEstimatePresent(estimateName));
    }
}
