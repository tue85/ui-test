package com.petrovich.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;       // для относительных XPath вызовов
import static com.codeborne.selenide.Selenide.$;        // для $("span.cart-total")

/**
 * Страница корзины покупок.
 * Предоставляет методы для работы с товарами в корзине:
 * открытия страницы, проверки наличия товара,
 * изменения количества, удаления и получения общей суммы.
 */
public class CartPage extends BasePage {
    // Логгер для вывода информации о действиях на странице
    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    // XPath шаблон кнопки "Удалить товар" внутри карточки товара
    private static final String REMOVE_PRODUCT = ".//button[contains(@class,'remove-item')]";

    // Полный XPath до поля ввода количества товара внутри карточки
    private static final String QTY_INPUT_XPATH =
        "/html/body/div[2]/main/div/div/div/div/div[2]/div/div/div/div[3]/div[4]/div[2]/div[1]/div/div/div/input";

    /**
     * Открывает страницу корзины по пути "/cart".
     * @return this для поддержки цепочки вызовов
     */
    public CartPage open() {
        openPage("/cart"); // наследуемый метод BasePage, который выполняет навигацию
        return this;
    }

    /**
     * Проверяет, есть ли в корзине товар с указанным артикулом.
     * @param code артикул товара
     * @return true, если товар найден, иначе false
     */
    public boolean isProductInCart(String code) {
        return findCartItem(code).exists(); // проверяем существование элемента в DOM
    }

    /**
     * Изменяет количество указанного товара в корзине.
     * @param code артикул товара
     * @param qty  новое количество
     * @return this для цепочки вызовов
     */
    public CartPage updateProductQuantity(String code, int qty) {
        // Находим карточку товара по артикулу
        SelenideElement item = findCartItem(code);

        // Находим поле ввода количества внутри этой карточки
        SelenideElement qtyInput = item.$x(QTY_INPUT_XPATH);

        // Дожидаемся, пока поле станет видимым, кликаем, очищаем и вводим новое значение
        qtyInput.shouldBe(Condition.visible, Duration.ofSeconds(2))
                .click();                       // активируем фокус
        qtyInput.clear();                     // очищаем текущее значение
        qtyInput.setValue(String.valueOf(qty)); // вводим новое количество

        logger.info("Updated {} qty to {}", code, qty); // логируем успешное изменение
        return this;
    }

    /**
     * Получает текущее количество указанного товара в корзине.
     * @param code артикул товара
     * @return количество единиц товара
     */
    public int getProductQuantity(String code) {
        // Получаем значение из текстового поля и конвертируем его в число
        String value = findCartItem(code)
                           .$("input.quantity")
                           .getValue();
        return Integer.parseInt(value);
    }

    /**
     * Удаляет товар с указанным артикулом из корзины.
     * @param code артикул товара
     * @return this для цепочки вызовов
     */
    public CartPage removeProduct(String code) {
        // Находим кнопку удаления внутри карточки и кликаем по ней
        findCartItem(code)
            .$x(REMOVE_PRODUCT)
            .click();
        logger.info("Removed {} from cart", code); // логируем удаление товара
        return this;
    }

    /**
     * Получает общую сумму корзины.
     * Считывает текст из элемента span.cart-total,
     * удаляет все нецифровые символы и парсит в int.
     * @return общая сумма, или 0 если элемент пустой
     */
    public int getTotalPrice() {
        String total = $("span.cart-total")
                           .getText()
                           .replaceAll("\\D", ""); // удаляем всё, кроме цифр
        return total.isEmpty() ? 0 : Integer.parseInt(total);
    }

    /*** Вспомогательный метод для поиска карточки товара по артикулу.
     * Ищет элемент с классом 'cart-item', внутри которого есть текст артикула.
     * @param code артикул товара
     * @return найденный элемент карточки
     */
    private SelenideElement findCartItem(String code) {
        return $x(
            "//*[contains(@class,'cart-item') " +
            " and .//*[contains(text(),'" + code + "')]]"
        );
    }
}

