package com.andersenlab;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;
    @FindBy(xpath = "//div[contains(text(),'Tech Stack')]")
    private WebElement teckStackMenu;
    @FindBy(xpath = "//div[contains(text(),'Tech Stack')]/following-sibling::div//a[text()='Java']")
    private WebElement elementJavaFromTeckStackMenu;
    @FindBy(xpath = "//footer//p[contains(text(),'Services')]/following-sibling::ul//a[contains(text(),'Quality Assurance')]")
    private WebElement qaLink;
    @FindBy(xpath = "//button[contains(text(),'Accept cookies')]")
    private WebElement cookiesButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Навести курсор на на элемент 'Tech Stack'")
    public MainPage moveToTechStackElement() {
        builder.moveToElement(teckStackMenu)
                .build()
                .perform();
        return this;
    }

    @Step("Навести курсор на на элемент 'Java'")
    public MainPage moveToJavaElement() {
        builder.moveToElement(elementJavaFromTeckStackMenu)
                .build()
                .perform();
        return this;
    }

    @Step("Получить css-свойство элемента 'Java'")
    public String getCssValue() {
        return elementJavaFromTeckStackMenu.getCssValue("border-bottom");
    }

    @Step("Перейти к ссылке 'Quality Assurance' в footer")
    public MainPage moveToQaLink() {
        builder.moveToElement(qaLink)
                .build()
                .perform();
        return this;
    }

    @Step("Кликнуть на ссылку 'Quality Assurance' в footer")
    public QaPage clickQaLink() {
        builder.click(qaLink)
                .build()
                .perform();
        wait.until(ExpectedConditions.urlContains("quality-assurance-services"));
        return new QaPage(driver);
    }

    public void clickCookiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton));
        cookiesButton.click();
    }
}
