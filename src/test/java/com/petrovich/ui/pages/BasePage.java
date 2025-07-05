package com.petrovich.ui.pages;

import com.codeborne.selenide.Selenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected void openPage(String relativeUrl) {
        logger.info("Opening page: {}", relativeUrl);
        String url = relativeUrl.startsWith("/") ? relativeUrl : "/" + relativeUrl;
        Selenide.open(url);
    }

    public int getCartCount() {
        String text = Selenide.$("span.cart-count").getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text.trim());
    }

    public int getFavoritesCount() {
        String text = Selenide.$("span.favorites-count").getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text.trim());
    }
}
