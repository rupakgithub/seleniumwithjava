package org.example.uitests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Javascriptexecutortests {

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
    public void test_scroll() throws InterruptedException {
        driver.get("https://www.tutorialspoint.com/index.htm");
        WebElement element = driver.findElement(By.xpath("//*[text()='ABOUT US']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void test_entertext() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.getElementById('user-name').setAttribute('value','new user')");
        WebElement element = driver.findElement(By.xpath("//input[@id='user-name']"));
        js.executeScript("arguments[0].setAttribute('value','test_user_1')", element);
        Thread.sleep(5000);
    }

    @AfterTest
    public void aftertest() {

        driver.quit();
    }
}
