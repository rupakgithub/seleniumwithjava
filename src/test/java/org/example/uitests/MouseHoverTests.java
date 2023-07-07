package org.example.uitests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

   // @Test
    public void hover_test() throws InterruptedException {
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

       homeprogram.click();

        Thread.sleep(4000);

        actions.keyDown(Keys.CONTROL).keyDown(Keys.TAB).build().perform();



    }

    @Test(enabled = false)
    public void open_in_new_link_test() throws InterruptedException {
        driver.get("https://www.tutorialspoint.com/about/about_careers.htm");
        String term = Keys.chord(Keys.CONTROL, Keys.ENTER);
        driver.findElement(By.xpath("//a[text()='Terms of Use']")).sendKeys(term);
        Thread.sleep(1000);

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String title = driver.getTitle();

        if (title.equals("Terms of Use")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Landed in wrong tab");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search-strings']")));

        driver.findElement(By.xpath("//input[@id='search-strings']")).sendKeys("hhgj");
    }

    @Test
    public void test_new_window(){
        driver.get("https://demoqa.com/browser-windows");
        String currentwindowid = driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@id='windowButton']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String allwindow: driver.getWindowHandles()) {
            if (!currentwindowid.contentEquals(allwindow)){
                driver.switchTo().window(allwindow);
                break;
            }
        }

        String urlofnewwindow = driver.getCurrentUrl();
        if(urlofnewwindow.contains("https://demoqa.com/sample")){
            Assert.assertTrue(true);
        } else {
            Assert.fail("URL of child window is different");
        }

    }


    @AfterTest
    public void aftertest() {

       driver.quit();
    }
}
