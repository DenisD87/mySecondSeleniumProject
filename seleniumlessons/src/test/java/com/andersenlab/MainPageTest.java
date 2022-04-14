package com.andersenlab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://andersenlab.com/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void underlineOnHoverToJavaElement() {
        mainPage.clickCookiesButton();
        String borderBottom = mainPage.moveToJavaElement();
        Assertions.assertEquals("1px solid rgb(255, 219, 0)", borderBottom);
    }

    @Test
    public void openQAPage() {
        mainPage.clickCookiesButton();
        QAPage qaPage = mainPage.clickQALink();
        Assertions.assertEquals("QA in full-cycle of software development", qaPage.getTitle());
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
