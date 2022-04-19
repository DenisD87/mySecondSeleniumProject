package com.andersenlab;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class QaPage {
    private WebDriver driver;

    public QaPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получить title")
    public String getTitle() {
        return driver.getTitle();
    }
}
