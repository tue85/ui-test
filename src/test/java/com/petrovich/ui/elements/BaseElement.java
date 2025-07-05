package com.petrovich.ui.elements;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseElement {
    protected final SelenideElement element;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected BaseElement(SelenideElement element) {
        this.element = element;
    }

    public boolean isDisplayed() {
        try {
            element.shouldBe(Condition.visible, Duration.ofSeconds(2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void click() {
        element.shouldBe(Condition.visible, Duration.ofSeconds(2)).click();
        logger.info("Clicked on element: {}", element);
    }

    public String getText() {
        element.shouldBe(Condition.visible, Duration.ofSeconds(2));
        return element.getText();
    }
}
