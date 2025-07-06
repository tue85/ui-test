package com.petrovich.ui.elements;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Базовый абстрактный класс-обёртка над {@link SelenideElement}.
 * Содержит общие методы ожидания, проверки видимости, клика и получения текста,
 * а также логгирование действий с элементами.
 */
public abstract class BaseElement {

    /** Оборачиваемый элемент Selenide. */
    protected final SelenideElement element;

    /** Логгер для всех наследников. */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Проверяет, отображается ли элемент на странице.
     *
     * <p>Метод ждёт видимости элемента до 2 секунд. Если за это время
     * элемент не становится видимым, возвращается {@code false} без
     * выбрасывания исключения.</p>
     *
     * @return {@code true}, если элемент виден; {@code false} — в противном случае
     */
    public boolean isDisplayed() {
        try {
            element.shouldBe(Condition.visible, Duration.ofSeconds(2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Кликает по элементу.
     *
     * <p>Перед кликом метод ждёт, пока элемент станет видимым (до 2 секунд),
     * а затем логирует факт клика.</p>
     */
    public void click() {
        element.shouldBe(Condition.visible, Duration.ofSeconds(2)).click();
        logger.info("Clicked on element: {}", element);
    }

    /**
     * Возвращает текст элемента.
     *
     * <p>Сначала ожидает видимости элемента до 2 секунд, затем
     * считывает и возвращает текстовое содержимое.</p>
     *
     * @return текст, содержащийся в элементе
     */
    public String getText() {
        element.shouldBe(Condition.visible, Duration.ofSeconds(2));
        return element.getText();
    }

    /**
     * Конструктор базового элемента.
     *
     * @param element экземпляр {@link SelenideElement}, который будет обёрнут
     */
    protected BaseElement(SelenideElement element) {
        this.element = element;
    }
}
