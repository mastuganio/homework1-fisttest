package simple;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;

public class OpenOtusSiteTest {

    private static final Logger LOG = LogManager.getLogger(OpenOtusSiteTest.class);
    private static final String EXPECTED_URI = "https://otus.ru/";
    private static final String EXPECTED_SITE_NAME = "Otus";
    private WebDriver driver;

    @BeforeClass
    public static void setAll() {
        LOG.info("Setup ChromeDriver locate...");
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        LOG.info("Init ChromeDriver...");
        driver = new ChromeDriver();

    }

    @Test
    public void openSite() {
        driver.get(EXPECTED_URI);
        final String actualUri = driver.getCurrentUrl();
        final String actualSiteName = driver.findElement(By.cssSelector("meta[property='og:site_name']")).getAttribute("content");
        assertThat("Открыт не тот сайт!", actualUri, CoreMatchers.equalTo(EXPECTED_URI));
        assertThat("Открыт не тот сайт!", actualSiteName, CoreMatchers.equalTo(EXPECTED_SITE_NAME));

    }

    @After
    public void tearDown() {
        if (driver != null) {
            LOG.info("Close ChromeDriver..");
            driver.quit();
        }
    }
}
