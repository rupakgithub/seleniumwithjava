package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class MouseHoverTests {

    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test
    public void hover_test()  {
        driver.get("https://www.spicejet.com/");
        Actions actions = new Actions(driver);
        WebElement spicejet = driver.findElement(By.xpath("(//div[text()='SpiceClub'])[1]"));
        actions.moveToElement(spicejet).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'/home#program')]")));

        WebElement homeprogram = driver.findElement(By.xpath("//a[contains(@href,'/home#program')]"));

        String targetattr = homeprogram.getAttribute("target");

        if (targetattr.equals("_blank")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Link Our Program has no attribute _blank");
        }

       // homeprogram.click();


    }


    @AfterTest
    public void aftertest() {
        driver.quit();
    }
}
