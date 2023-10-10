package org.example.uitests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
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
import java.util.List;
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
        ops.setExperimentalOption("prefs", chromePrefs);
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
    public void test_file_upload() {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.xpath("//input[@id='uploadFile']")).sendKeys("C:\\Users\\ASUS\\Desktop\\git example\\seleniumwithjava\\downloads\\sampleFile.jpeg");
        String uploadtext = driver.findElement(By.xpath("//p[@id='uploadedFilePath']")).getText();

        if (uploadtext.contains("sampleFile.jpeg")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("File not uploaded!!");
        }
    }

    @Test
    public void test_autocomplete() throws InterruptedException {
        driver.get("https://jqueryui.com/autocomplete/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@id='tags']")).sendKeys("C");

        String texttobeclicked = "Clojure";
        WebElement autooptions  = driver.findElement(By.id("ui-id-1"));

        try {
            wait.until(ExpectedConditions.visibilityOf(autooptions));
        } catch (TimeoutException e) {
            System.out.println("No element present with the character");
        }

        List<WebElement> alloptions = autooptions.findElements(By.tagName("li"));


        for (WebElement option : alloptions) {
            if (option.getText().equals(texttobeclicked)) {
                option.click();
                break;
            }
        }


        Thread.sleep(3000);

    }

    @Test
    public void openHRM_add_user() throws InterruptedException {
        String user_role_to_be_select = "ESS";
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//label[contains(text(),'User Role')]/../following-sibling::div")).click();
//        Thread.sleep(1000);
//        WebElement e = driver.findElement(By.xpath("//label[contains(text(),'User Role')]/../following-sibling::div//div[@role='listbox']"));
//        String d = e.getAttribute("innerHTML");
//        System.out.println(d);

        /*
         * Method 1
         */

//        Thread.sleep(1000);
//
//        WebElement elem = driver.findElement(By.xpath("//label[contains(text(),'User Role')]/../following-sibling::div//div[@role='option']/span[text()='ESS']"));
//
//        elem.click();

        //String d = elem.getAttribute("innerHTML");

        /*
         * Method 2
         */

        Actions act = new Actions(driver);
        while (true){
            act.sendKeys(Keys.DOWN).perform();
            Thread.sleep(1000);
            WebElement elem = driver.findElement(By.xpath("//label[contains(text(),'User Role')]/../following-sibling::div//div[@role='option' and contains(@class, 'select-option --focused')]"));
            String userroleselected = elem.getText();
            if (userroleselected.equals(user_role_to_be_select)){
                act.sendKeys(Keys.ENTER).perform();
                break;
            }
        }

        //System.out.println(d);

    }


    @AfterTest
    public void aftertest() {

        driver.quit();
    }
}
