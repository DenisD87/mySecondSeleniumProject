package com.andersenlab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;
    private By teckStackMenu = By.xpath("//div[contains(text(),'Tech Stack')]");
    private By elementJavaFromTeckStackMenu = By.xpath("./following-sibling::div//a[text()='Java']");
    private By qaLink = By.xpath("//footer//p[contains(text(),'Services')]/following-sibling::ul//a[contains(text(),'Quality Assurance')]");
    private By cookiesButton = By.xpath("//button[contains(text(),'Accept cookies')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String moveToJavaElement() {
        WebElement menu = driver.findElement(teckStackMenu);
        WebElement elementJava = menu.findElement(elementJavaFromTeckStackMenu);
        builder.moveToElement(menu)
                .moveToElement(elementJava)
                .build()
                .perform();
        return elementJava.getCssValue("border-bottom");
    }

    public QaPage clickQALink() {
        builder.moveToElement(driver.findElement(qaLink))
                .click()
                .build()
                .perform();
        wait.until(ExpectedConditions.urlContains("quality-assurance-services"));
        return new QaPage(driver);
    }

    public void clickCookiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton));
        driver.findElement(cookiesButton).click();
    }
}
