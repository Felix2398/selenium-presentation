import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WaitStrategiesTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();

    }

    @AfterEach
    public void tearDown() {
        // driver.quit();
    }

    @Test
    public void waitForPresence() {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("box1")));
        Assertions.assertNotNull(element);
    }

    @Test
    public void waitForSelection() {
        // Default Checkbox
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeSelected(By.id("my-check-2")));
        driver.findElement(By.name("my-password")).sendKeys("password");
    }

    @Test
    public void slowAlert() {
        // A SLOW alert
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        driver.findElement(By.id("slow-alert")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        assertEquals("Slow", alert.getText());

        alert.accept();
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }


}
