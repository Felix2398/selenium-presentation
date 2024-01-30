import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

public class InteractionsTest {
    WebDriver driver;
    String url = "https://www.selenium.dev/selenium/web/web-form.html";
    String submitUrl = "https://www.selenium.dev/selenium/web/submitted-form.html";
    String indexUrl = "https://www.selenium.dev/selenium/web/index.html";

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
    public void clickButton() {
        // Submit button
        WebElement button = driver.findElement(By.cssSelector(".btn"));
        button.click();
        assertTrue(driver.getCurrentUrl().contains(submitUrl));
    }

    @Test
    public void clickLink() {
        // "Return to index" link
        WebElement link = driver.findElement(By.linkText("Return to index"));
        link.click();
        assertEquals(indexUrl, driver.getCurrentUrl());
    }

    @Test
    public void checkValue() {
        // Readonly input
        WebElement text = driver.findElement(By.name("my-readonly"));
        String value = text.getAttribute("value");
        assertEquals("Readonly input", value);
    }

    @Test
    public void enterPassword() {
        // Password
        WebElement passwordField = driver.findElement(By.name("my-password"));
        passwordField.sendKeys("password");
        String value = passwordField.getAttribute("value");
        assertEquals("password", value);
    }

    @Test
    public void enableCheckbox() {
        // Default checkbox
        WebElement checkbox = driver.findElement(By.id("my-check-2"));
        assertFalse(checkbox.isSelected());
        checkbox.click();
        assertTrue(checkbox.isSelected());
    }

    @Test
    public void clearText() {
        // Text input
        WebElement text = driver.findElement(By.name("my-text"));
        text.sendKeys("Text");
        assertEquals("Text", text.getAttribute("value"));
        text.clear();
        assertEquals("", text.getAttribute("value"));
    }

    @Test
    public void selectFromDropdown() {
        // Dropdown (select)
        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);
        select.selectByIndex(1);
        assertEquals("One", select.getFirstSelectedOption().getText());
    }

    @Test
    public void setColor() {
        // Color picker
        WebElement color = driver.findElement(By.name("my-colors"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '#ff0000')", color);
        assertEquals("#ff0000", color.getAttribute("value"));
    }
}
