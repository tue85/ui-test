package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;

public class ProductPageTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testProductDetailsPageElements() {
        String expectedTitle = "Штукатурка гипсовая Knauf МП-75 машинная 30 кг";

        String expectedDescriptionSnippet = "Гипсовая машинная штукатурка Knauf МП-75";

        // Открываем страницу товара
        ProductPage p = new ProductPage().open(code);

        // Ждём появления заголовка и проверяем его
        $("h1").shouldBe(Condition.visible, Duration.ofSeconds(2));
        assertEquals(expectedTitle, p.getTitle());

        // Ждём отображения цены и проверяем значение без валюты
        $("[class*=price]").shouldBe(Condition.visible, Duration.ofSeconds(2));
        assertEquals("615", p.getPrice().replaceAll("\\D", ""));

        // Ждём описания и проверяем, что содержится нужный фрагмент
        $("div.product-description").shouldBe(Condition.visible, Duration.ofSeconds(2));
        assertTrue(p.getDescription().contains(expectedDescriptionSnippet));

        // Ждём появления изображения товара по фрагменту src и проверяем
        $("img[src*='mp75']").shouldBe(Condition.visible, Duration.ofSeconds(2));
        assertTrue(p.isImageDisplayed("mp75"));

        // Проверяем таблицу технических характеристик
        $("table.tech-specs").shouldBe(Condition.visible, Duration.ofSeconds(2)); // TODO: уточнить селектор
        assertTrue(p.hasTechnicalSpecifications());

        // Проверяем секцию "Отзывы"
        $("*[class*=reviews], *:contains('Отзывы')").shouldBe(Condition.visible, Duration.ofSeconds(2)); // TODO: уточнить селектор
        assertTrue(p.hasReviewsSection());

        // Проверяем секцию "Доставка"
        $("*[class*=delivery], *:contains('Доставка')").shouldBe(Condition.visible, Duration.ofSeconds(2)); // TODO: уточнить селектор
        assertTrue(p.hasDeliveryInfoSection());
    }
}
