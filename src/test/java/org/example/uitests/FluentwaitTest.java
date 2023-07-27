package org.example.uitests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class FluentwaitTest {
    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().setup();
        String downloadFilepath = "C:\\Users\\ASUS\\Desktop\\git example\\seleniumwithjava\\downloads";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.setExperimentalOption("prefs", chromePrefs);
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test
    public void fluentwaittest() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Wait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        WebElement myinfo = driver.findElement(By.xpath("//span[text()='My Info']"));
        wait.until(ExpectedConditions.elementToBeClickable(myinfo));

        myinfo.click();

        WebElement saveButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(saveButton));
        Thread.sleep(3000);
        saveButton.click();

        //By message = By.xpath("//*[contains(text(),'Successfully')]");

        //fluentwait.until(ExpectedConditions.presenceOfElementLocated(message));
        System.out.println("Element located");
        Thread.sleep(1000);
        if (driver.getPageSource().contains("Successfully")){
            Assert.assertTrue(true);
        } else {
            Assert.fail("Success message not present");
        }




    }


    @AfterTest
    public void aftertest() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }
}
