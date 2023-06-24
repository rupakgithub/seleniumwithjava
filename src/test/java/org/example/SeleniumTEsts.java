package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTEsts {

    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);

    }

    @Test(priority = 1)
    public void firsttest() {
        driver.get("https://www.saucedemo.com/");
        String title = driver.getTitle();
        if (title.equals("Swag Labs")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @AfterTest
    public void aftertest() {
        driver.quit();
    }

}
