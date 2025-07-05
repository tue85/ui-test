package com.petrovich.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    public static void setup() {
        Configuration.browserSize      = "1920x1080";
        Configuration.baseUrl          = "https://petrovich.ru";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout           = 10_000;
        logger.info("Starting UI tests");
    }

    @BeforeEach
    public void cleanState() {
        // Гарантируем привязку WebDriver к текущему потоку
        if (!WebDriverRunner.hasWebDriverStarted()) {
            open("/");  
        }
    }

    @AfterAll
    public static void tearDown() {
        logger.info("UI tests completed");
    }
}
