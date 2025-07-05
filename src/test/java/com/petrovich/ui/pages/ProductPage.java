package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Button;
import com.petrovich.ui.elements.Table;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.open;

/**
 * Страница товара.
 */
public class ProductPage extends BasePage {
    private final Button addToCartButton = Button.byText("В корзину");

    // TODO: Найдите в DevTools селектор кнопки добавления в избранное (CSS-класс, data-атрибут и т.п.)
    private final Button favoriteButton  = Button.byText("Избранное");

    // TODO: В DevTools найдите таблицу технических характеристик и скопируйте её уникальный CSS-селектор
    private final Table techSpecsTable   = Table.byCss("table.tech-specs");

    /**
     * Открыть страницу товара по артикулу.
     * TODO: Проверьте, что относительный путь "/product/{code}" соответствует маршруту в приложении.
     */
    public ProductPage open(String productCode) {
        openPage("/product/" + productCode);//нужно здесь прописать что должен делать класс open а не ссылаться на openPage.
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
     * TODO: Убедитесь, что заголовок расположен в теге h1 через DevTools.
     */
    public String getTitle() {
        SelenideElement title = $("h1").shouldBe(Condition.visible);
        return title.getText();
    }

    /**
     * Текст цены, например "615 ₽".
     * TODO: Уточните точный селектор элемента цены (например .price__value) через DevTools.
     */
    public String getPrice() {
        SelenideElement price = $("[class*=price]").shouldBe(Condition.visible);
        return price.getText();
    }

    /**
     * Описание товара.
     * TODO: Найдите контейнер описания товара (например div.product-description) и скопируйте селектор.
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
     * TODO: Уточните селектор секции отзывов (например id="reviews" или класс reviews-section).
     */
    public boolean hasReviewsSection() {
        return $("*[class*=reviews], *:contains('Отзывы')").exists();
    }

    /**
     * Есть ли секция "Доставка".
     * TODO: Уточните селектор секции доставки (например div.delivery-info) через DevTools.
     */
    public boolean hasDeliveryInfoSection() {
        return $("*[class*=delivery], *:contains('Доставка')").exists();
    }

    /**
     * Видна ли картинка с подстрокой imageName в пути.
     * TODO: Передавайте сюда имя файла или часть пути картинки, как указано в атрибуте src.
     */
    public boolean isImageDisplayed(String imageName) {
        return $("img[src*='" + imageName + "']").exists();
    }
}
