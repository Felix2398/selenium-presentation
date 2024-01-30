import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AlertsTest {
    WebDriver driver;
    String url = "https://www.selenium.dev/selenium/web/alerts.html#";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(url);
    }

    @AfterEach
    public void tearDown() {
        // driver.quit();
    }

    @Test
    public void textAlert() {
        // This tests alerts: click me
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("cheese", alert.getText());

        alert.accept();
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }

    @Test
    public void promptAlert() {
        // Let's make the prompt happen
        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("test");
        alert.accept();

        WebElement result = driver.findElement(By.xpath("/html/body/div[1]/p"));
        assertEquals("test", result.getText());
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }

    @Test
    public void slowAlert() {
        // A SLOW alert
        driver.findElement(By.id("slow-alert")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        assertEquals("Slow", alert.getText());

        alert.accept();
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }

    @Test
    public void openNewPageAlert() {
        driver.findElement(By.id("open-page-with-onload-alert")).click();
        driver.switchTo().alert().accept();
        assertEquals("https://www.selenium.dev/selenium/web/pageWithOnLoad.html", driver.getCurrentUrl());
    }
}
