package org.example.uitests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Selectclasstest {

    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().setup();

        /*Map<String, Object> preferences = new Hashtable<String, Object>();

        preferences.put("download.default_directory", "C://");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);*/
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS).implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test(priority = 2)
    public void test_select_class() throws Exception {
        driver.get("https://demoqa.com/select-menu");

        WebElement selectcolor = driver.findElement(By.xpath("//select[@id='oldSelectMenu']"));

        Select se = new Select(selectcolor);

        //se.selectByIndex(1);
        se.selectByVisibleText("Purple");
        //se.selectByValue("Red");

        Thread.sleep(3000);

        //se.deselectAll();
    }

    @Test(priority = -11)
    public void test_alerts() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");

        driver.findElement(By.xpath("//button[@id='alertButton']")).click();

        Thread.sleep(2000);
        System.out.println(driver.switchTo().alert().getText());

        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().accept();


        Thread.sleep(2000);
    }



    @AfterTest
    public void aftertest() {

        driver.quit();
    }

}
