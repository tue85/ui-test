// src/main/java/com/petrovich/ui/pages/CartPage.java
package com.petrovich.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;       // для относительных XPath вызовов
import static com.codeborne.selenide.Selenide.$;        // для $("span.cart-total")

public class CartPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

     private static final String QTY_INPUT_XPATH = 
        "/html/body/div[2]/main/div/div/div/div/div[2]/div/div/div/div[3]/div[4]/div[2]/div[1]/div/div/div/input";
    /** Открыть корзину */
    public CartPage open() {
        openPage("/cart");
        return this;
    }

    /** Есть ли товар в корзине */
    public boolean isProductInCart(String code) {
        return findCartItem(code).exists();
    }

    
    public CartPage updateProductQuantity(String code, int qty) {
        SelenideElement item = findCartItem(code);

        // 1) Находим поле количества через XPath внутри карточки
        SelenideElement qtyInput = item.$x(QTY_INPUT_XPATH);

        // 2) Кликаем, очищаем и вводим новое значение
        qtyInput.shouldBe(Condition.visible, Duration.ofSeconds(2)).click();
        qtyInput.clear();
        qtyInput.setValue(String.valueOf(qty));

        
        logger.info("Updated {} qty to {}", code, qty);
        return this;
    }

    /** Получить текущее количество товара */
    public int getProductQuantity(String code) {
        String value = findCartItem(code)
                     .$("input.quantity")
                     .getValue();
        return Integer.parseInt(value);
    }

    /** Удалить товар из корзины */
    public CartPage removeProduct(String code) {
        findCartItem(code)
          .$x(".//button[contains(@class,'remove-item')]")
          .click();
        logger.info("Removed {} from cart", code);
        return this;
    }

    /** Общая сумма корзины */
    public int getTotalPrice() {
        String total = $("span.cart-total")
                      .getText()
                      .replaceAll("\\D", "");
        return total.isEmpty() ? 0 : Integer.parseInt(total);
    }
    
    /** Найти карточку товара по коду */
    private SelenideElement findCartItem(String code) {
        return $x(
          "//*[contains(@class,'cart-item') " +
          " and .//*[contains(text(),'" + code + "')]]"
        );
    }

}
