package com.andersenlab;

import org.openqa.selenium.WebDriver;

public class QAPage {
    private WebDriver driver;

    public QAPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
