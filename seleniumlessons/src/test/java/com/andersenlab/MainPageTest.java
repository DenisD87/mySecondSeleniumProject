package com.andersenlab;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.time.Duration;
import java.util.List;

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
        mainPage.clickCookiesButton();
    }

    @DisplayName("Подчеркивание при наведении на элемент 'Java'")
    @Epic(value = "Lesson 15")
    @Test
    public void underlineOnHoverToJavaElement() {
        assertBorderBottom(mainPage.moveToTechStackElement()
                .moveToJavaElement()
                .getCssValue());
    }

    @Step("Проверка полученного css-свойства")
    public void assertBorderBottom(String borderBottom) {
        Assertions.assertEquals("1px solid rgb(255, 219, 0)", borderBottom, "Подчеркивание не соответсвует ожидаемому результату");
    }

    @DisplayName("Переход на страницу 'Quality Assurance' по ссылке в footer")
    @Epic(value = "Lesson 15")
    @Test
    public void openQaPage() {
        assertMoveQaPage(mainPage.moveToQaLink()
                .clickQALink());
    }

    @Step("Проверка перехода на страницу")
    public void assertMoveQaPage(QaPage qaPage) {
        Assertions.assertEquals("QA in full-cycle of software development", qaPage.getTitle(), "Переход на страницу не выполнен");
    }

    @AfterEach
    public void tearDown() {
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
        if (allLogRows.isEmpty()) {
            System.out.println("Logs are empty");
        } else {
            allLogRows.forEach(System.out::println);
        }
        driver.quit();
    }
}
