import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserTest {

    String url = "https://www.selenium.dev/";

    @Test
    public void firefoxDriver() {
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
    }

    @Test
    public void chromeDriver() {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void edgeDriver() {
        WebDriver driver = new EdgeDriver();
        driver.get(url);
    }

    @Test
    public void safariDriver() {
        WebDriver driver = new SafariDriver();
        driver.get(url);
    }
}
