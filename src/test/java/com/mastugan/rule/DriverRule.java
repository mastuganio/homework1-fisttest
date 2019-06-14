package com.mastugan.rule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverRule extends ExternalResource {

    private static final Logger LOGGER = LogManager.getLogger(DriverRule.class);

    private WebDriver driver;

    private DriverRule() {
    }

    public static DriverRule getInstance() {
        return new DriverRule();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() {
        driver = new ChromeDriver();
        LOGGER.info("Init ChromeDriver");
    }

    @Override
    protected void after() {
        if (driver != null) {
            driver.quit();
            LOGGER.info("Quits driver, closing every associated window ");
        }
    }
}
