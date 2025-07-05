package com.petrovich.ui.tests;

import com.petrovich.ui.pages.CartPage;
import com.petrovich.ui.pages.EstimatesPage;
import com.petrovich.ui.pages.EstimatePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест для проверки создания и проверки новой сметы.
 */
public class EstimateTest extends BaseTest {
    /** Код товара для добавления в смету. */
    private final String code = "104843";

    @Test
    public void testCreateEstimate() {
        // Название новой сметы
        String estimateName = "Тестовая смета";

        // 1. Устанавливаем количество товара в корзине
        CartPage cart = new CartPage().open();
        cart.updateProductQuantity(code, 2);

        // 2. Открываем список смет и переходим к созданию новой
        EstimatesPage list = new EstimatesPage().open();
        EstimatePage estimate = list.clickCreateNewEstimate();

        // 3. Заполняем форму: имя сметы и добавляем товар
        estimate.setName(estimateName);
        estimate.addProduct(code, 2);

        // 4. Сохраняем смету и возвращаемся к списку
        estimate.save();
        list.open();

        // 5. Проверяем, что в созданной смете есть нужный товар с правильным количеством
        assertTrue(estimate.containsProduct(code, 2), "Смете не добавлен ожидаемый товар");

        // 6. Проверяем корректность общей суммы
        assertEquals(2 * 615, estimate.getTotal(), "Неправильная общая сумма в смете");

        // 7. Убеждаемся, что смета отображается в списке
        assertTrue(list.isEstimatePresent(estimateName), "Новая смета не найдена в списке");
    }
}
