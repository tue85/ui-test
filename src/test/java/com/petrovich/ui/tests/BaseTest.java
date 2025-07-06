package com.petrovich.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.open;

/**
 * Абстрактный базовый класс для всех UI-тестов.
 * Содержит общую настройку Selenide и логику очистки состояния между тестами.
 */
public abstract class BaseTest {

    /**
     * Логгер для вывода информации о ходе запуска тестов и их завершении.
     */
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    /**
     * Глобальная настройка Selenide перед запуском всех тестов.
     * Выполняется один раз перед всеми методами тестовых классов.
     */
    @BeforeAll
    public static void setup() {
        // Размер окна браузера, в котором будут запускаться тесты
        Configuration.browserSize      = "1920x1080";

        // Базовый URL приложения, к которому методы open() будут добавлять относительные пути
        Configuration.baseUrl          = "https://petrovich.ru";

        // Стратегия ожидания загрузки страницы:
        // "eager" — считать страницу загруженной после того, как DOMContentLoaded завершён
        Configuration.pageLoadStrategy = "eager";

        // Тайм-аут (в миллисекундах) ожидания условий и элементов на странице
        Configuration.timeout           = 10_000;

        // Логируем стартовую информацию о запуске UI-тестов
        logger.info("Starting UI tests");
    }

    /**
     * Очистка состояния браузера перед каждым тестом.
     * Проверяет, запущен ли WebDriver, и если нет — открывает главную страницу,
     * чтобы привязать драйвер к текущему потоку выполнения.
     */
    @BeforeEach
    public void cleanState() {
        // Если WebDriver ещё не стартовал в текущем потоке,
        // то вызываем open("/") — это автоматически запустит браузер
        if (!WebDriverRunner.hasWebDriverStarted()) {
            open("/");  // открывает базовый URL, заданный в Configuration.baseUrl
        }
        // Здесь можно добавить сброс куки, localStorage и т.п. при необходимости
    }

    /**
     * Действия после выполнения всех тестов.
     * Используется для выводов итоговой информации или очистки глобальных ресурсов.
     */
    @AfterAll
    public static void tearDown() {
        // Логируем завершение выполнения UI-тестов
        logger.info("UI tests completed");
        // Здесь можно закрыть браузер или выполнить дополнительные действия по завершению
    }
}
