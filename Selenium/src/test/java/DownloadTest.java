import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloadTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeEach
    public void setUp() throws IOException {
        folder.create();
    }

    @AfterEach
    public void tearDown() {
        folder.delete();
    }

    @Test
    public void downloadFile() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", folder.getRoot().getAbsolutePath());
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        options.setProfile(profile);

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/downloads/download.html");
        driver.findElement(By.id("file-1")).click();

        String path = folder.getRoot().getAbsolutePath() + "/file_1.txt";
        Assertions.assertTrue(Files.exists(Path.of(path)));
    }
}
