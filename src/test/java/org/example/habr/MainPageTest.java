package org.example.habr;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @DisplayName("Restoring a save")
    @Test
    public void test1() {
        WebElement settings = driver.findElement(By.cssSelector("button[class='btn btn_solid btn_small tm-header-user-menu__login']"));
        settings.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Забыли пароль?')]")).isDisplayed());

//          Эти тесты не прошли так как ругается на токен  при их запуске
//        WebElement forgotYourPassword = driver.findElement(By.xpath("//*[contains(text(), 'Забыли пароль?')]"));
//        forgotYourPassword.click();

//      assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Восстановление пароля')]")).isDisplayed());
    }

    @DisplayName("Best blogs")
    @Test
    public void test2() {
        WebElement Management = driver.findElement(By.cssSelector("a[href='/ru/flows/design/']"));
        Management.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Читают сейчас')]")).isDisplayed());
    }

    @DisplayName("expectation")
    @Test
    public void visible() {
        driver.get("https://demoqa.com/dynamic-properties");
        By button = By. cssSelector("#visibleAfter");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        wait.until(ExpectedConditions.elementToBeClickable(button));
        WebElement buttonn = driver.findElement(By.cssSelector("#visibleAfter"));

        assertTrue(buttonn.isEnabled());
    }
}