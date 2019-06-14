package com.mastugan.test;

import com.mastugan.rule.DriverRule;
import com.mastugan.rule.DriverSetupRule;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;

public class FistTest {

    private static final String EXPECTED_URI = "https://otus.ru/";
    private static final String EXPECTED_SITE_NAME = "Otus";

    @ClassRule
    public static DriverSetupRule driverSetupRule = DriverSetupRule.getInstance();
    @Rule
    public DriverRule driverRule = DriverRule.getInstance();

    protected WebDriver driver;

    @Before
    public void setUp(){
        driver = driverRule.getDriver();
    }

    @Test
    public void openOtus() {
        driver.get(EXPECTED_URI);
        final String actualUri = driver.getCurrentUrl();
        final String actualSiteName = driver.findElement(By.cssSelector("meta[property='og:site_name']")).getAttribute("content");
        assertThat("Открыт не тот сайт!", actualUri, CoreMatchers.equalTo(EXPECTED_URI));
        assertThat("Открыт не тот сайт!", actualSiteName, CoreMatchers.equalTo(EXPECTED_SITE_NAME));

    }
}
