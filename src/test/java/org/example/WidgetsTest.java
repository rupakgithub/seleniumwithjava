package org.example;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WidgetsTest {

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
        ops.setExperimentalOption("prefs",chromePrefs);
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test
    public void test_sliders() throws InterruptedException {
        driver.get("https://demoqa.com/slider");
        Actions actions = new Actions(driver);
        WebElement slider = driver.findElement(By.xpath("//input[contains(@class,'range-slider')]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(slider));

        actions.click(slider).build().perform();

        for (int i = 0; i < 5; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
            Thread.sleep(500);
        }

        Thread.sleep(3000);
    }

    @Test
    public void test_drag_and_drop() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        Actions actions = new Actions(driver);

        WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']"));

        WebElement drop = driver.findElement(By.xpath("//div[@id='draggable']/following-sibling::div"));

        //actions.clickAndHold(drag).moveToElement(drop).release(drop).build().perform();

        actions.dragAndDrop(drag, drop).perform();

        Thread.sleep(5000);
    }

    @Test
    public void test_button_color() throws InterruptedException {
        driver.get("https://demoqa.com/dynamic-properties");
        WebElement button = driver.findElement(By.xpath("//button[@id='colorChange']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(button));

        String colorbefore = button.getCssValue("color");
        System.out.println(colorbefore);

        button.click();

        Thread.sleep(3000);

        String colorafter = button.getCssValue("color");
        System.out.println(colorafter);

        assert colorbefore != colorafter;
    }

    @Test
    public void test_download() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");

        WebElement button = driver.findElement(By.xpath("//a[@id='downloadButton']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(button));

        button.click();

        Thread.sleep(5000);

    }

    @Test
    public void test_dynamic_elements() throws InterruptedException {
        driver.get("https://demoqa.com/dynamic-properties");
        WebElement button = driver.findElement(By.xpath("//button[@id='enableAfter']"));
        WebElement button2 = driver.findElement(By.xpath("//button[@id='visibleAfter']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(button));

        wait.until(ExpectedConditions.visibilityOf(button2));

        Thread.sleep(5000);

    }

    @Test
    public void test_file_upload(){
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.xpath("//input[@id='uploadFile']")).sendKeys("C:\\Users\\ASUS\\Desktop\\git example\\seleniumwithjava\\downloads\\sampleFile.jpeg");
        String uploadtext = driver.findElement(By.xpath("//p[@id='uploadedFilePath']")).getText();

        if (uploadtext.contains("sampleFile.jpeg")){
            Assert.assertTrue(true);
        }else {
            Assert.fail("File not uploaded!!");
        }
    }


    @AfterTest
    public void aftertest() {

        driver.quit();
    }
}
