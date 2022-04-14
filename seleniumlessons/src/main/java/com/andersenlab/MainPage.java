package com.andersenlab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage {
    private WebDriver driver;
    private Actions builder;
    private By teckStackMenu = By.xpath("//div[contains(text(),'Tech Stack')]");
    private By elementJavaFromTeckStackMenu = By.xpath("./following-sibling::div//a[text()='Java']");
    private By qaLink = By.xpath("//footer//p[contains(text(),'Services')]/following-sibling::ul//a[contains(text(),'Quality Assurance')]");
    private By cookiesButton = By.xpath("//button[contains(text(),'Accept cookies')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
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

    public QAPage clickQALink() {
        builder.moveToElement(driver.findElement(qaLink))
                .click()
                .build()
                .perform();
        return new QAPage(driver);
    }

    public void clickCookiesButton() {
        driver.findElement(cookiesButton).click();
    }
}
