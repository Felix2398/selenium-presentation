import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowserOptionsTest {

    @Test
    public void runInHeadlessMode() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        FirefoxDriver driver = new FirefoxDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement text = driver.findElement(By.name("my-readonly"));
        String value = text.getAttribute("value");
        assertEquals("Readonly input", value);
    }

    @Test
    public void runWithLightAndDarkMode() {
        // dark mode
        FirefoxOptions darkModeOptions = new FirefoxOptions();
        darkModeOptions.addPreference("ui.systemUsesDarkTheme", 1);
        WebDriver darkModeDriver = new FirefoxDriver(darkModeOptions);
        darkModeDriver.get("https://www.youtube.com/");

        WebElement element = darkModeDriver.findElement(By.tagName("ytd-app"));
        String backgroundColor = element.getCssValue("background-color");
        assertEquals("rgb(15, 15, 15)", backgroundColor);

        // light mode
        FirefoxOptions lightModeOptions = new FirefoxOptions();
        lightModeOptions.addPreference("ui.systemUsesDarkTheme", 0);
        WebDriver lightModeDriver = new FirefoxDriver(lightModeOptions);
        lightModeDriver.get("https://www.youtube.com/");

        element = lightModeDriver.findElement(By.tagName("ytd-app"));
        backgroundColor = element.getCssValue("background-color");
        assertEquals("rgb(255, 255, 255)", backgroundColor);
    }

    @Test
    public void installExtension() throws URISyntaxException {
        // without ad blocker extension
        FirefoxDriver normalDriver = new FirefoxDriver();
        normalDriver.get("https://canyoublockit.com/testing/");

        normalDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement element = normalDriver.findElement(By.id("interads-tit"));
        Assertions.assertTrue(element.isDisplayed());

        // with ad blocker extension
        URL resource = getClass().getClassLoader().getResource("addons/ublock.xpi");
        Path path = Paths.get(Objects.requireNonNull(resource).toURI());
        FirefoxDriver extensionDriver = new FirefoxDriver();
        extensionDriver.installExtension(path);
        extensionDriver.get("https://canyoublockit.com/testing/");

        extensionDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        element = extensionDriver.findElement(By.id("interads-tit"));
        Assertions.assertFalse(element.isDisplayed());
    }
}
