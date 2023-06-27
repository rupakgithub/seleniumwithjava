package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
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

    @Test(enabled = false)
    public void firsttest() {
        driver.get("https://www.saucedemo.com/");

        String title = driver.getTitle();

        if (title.equals("Swag Labs")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Title didn't match");
        }
    }

    @Test
    public void positive_login_scenario() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[id='login-button']")).click();
        Thread.sleep(5);
    }

    @AfterTest
    public void aftertest() {
        driver.quit();
    }

}
