package com.andersenlab;

import org.openqa.selenium.WebDriver;

public class QaPage {
    private WebDriver driver;

    public QaPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
