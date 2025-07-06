package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Button;
import com.petrovich.ui.elements.Table;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница товара.
 */
public class ProductPage extends BasePage {

    private static final String ADD_TO_CART_TEXT    = "В корзину";
    private static final String FAVORITE_BUTTON_TEXT = "Избранное";
    private static final String TECH_SPECS_TABLE_CSS = "table.tech-specs";
    private static final String DELIVERY_CSS ="*[class*=delivery], *:contains('Доставка')";
    private static final String REVIEW_CSS = "*[class*=reviews], *:contains('Отзывы')";
    // Элементы страницы товара
    private final Button addToCartButton = Button.byText(ADD_TO_CART_TEXT);
    private final Button favoriteButton  = Button.byText(FAVORITE_BUTTON_TEXT);
    private final Table  techSpecsTable   = Table.byCss(TECH_SPECS_TABLE_CSS);

    /**
     * Открыть страницу товара по артикулу.
     */
    public ProductPage open(String productCode) {
        openPage("/product/" + productCode);
        return this;
    }

    /**
     * Добавить товар в корзину.
     */
    public void addToCart() {
        logger.info("Adding product to cart");
        addToCartButton.click();
    }

    /**
     * Получить текущее число в шапке корзины.
     */
    public int getCartCount() {
        return super.getCartCount();
    }

    /**
     * Добавить товар в избранное.
     */
    public void addToFavorites() {
        logger.info("Adding product to favorites");
        favoriteButton.click();
    }

    /**
     * Убрать товар из избранного.
     */
    public void removeFromFavorites() {
        logger.info("Removing product from favorites");
        favoriteButton.click();
    }

    /**
     * Заголовок товара (h1).
     */
    public String getTitle() {
        SelenideElement title = $("h1").shouldBe(Condition.visible);
        return title.getText();
    }

    /**
     * Текст цены.
     */
    public String getPrice() {
        SelenideElement price = $("[class*=price]").shouldBe(Condition.visible);
        return price.getText();
    }

    /**
     * Описание товара.
     */
    public String getDescription() {
        SelenideElement desc = $("div.product-description").shouldBe(Condition.visible);
        return desc.getText();
    }

    /**
     * Есть ли таблица технических характеристик.
     */
    public boolean hasTechnicalSpecifications() {
        return techSpecsTable.isDisplayed();
    }

    /**
     * Есть ли секция "Отзывы".
     */
    public boolean hasReviewsSection() {
        return $(REVIEW_CSS).exists();
    }

    /**
     * Есть ли секция "Доставка".
     */
    public boolean hasDeliveryInfoSection() {
        return $(DELIVERY_CSS).exists();
    }

    /**
     * Видна ли картинка с подстрокой imageName в пути.
     */
    public boolean isImageDisplayed(String imageName) {
        return $("img[src*='" + imageName + "']").exists();
    }
}
