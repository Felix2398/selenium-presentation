import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class FindElementsTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @AfterEach
    public void tearDown() {
        // driver.quit();
    }

    @Test
    public void findElementById() {
        // Text input
        WebElement element = driver.findElement(By.id("my-text-id"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findElementByName() {
        // Text input
        WebElement element = driver.findElement(By.name("my-text"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findElementByClassName() {
        // all form control elements
        List<WebElement> element = driver.findElements(By.className("form-control"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findElementByXPath() {
        // Submit button
        WebElement element = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/button"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findElementByCssSelector() {
        // Submit button
        WebElement element = driver.findElement(By.cssSelector(".btn"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findElementByLinkText() {
        // "Return to index" link
        WebElement element = driver.findElement(By.linkText("Return to index"));
        Assertions.assertNotNull(element);
    }
}
