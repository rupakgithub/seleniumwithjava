package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumTEsts {

    WebDriver driver = null;
    String chromedriverpath = "C:\\Users\\ASUS\\Desktop\\git example\\seleniumwithjava\\src\\test\\drivers\\chromedriver.exe";

    @BeforeTest
    public void beforetest(){
        System.out.println("This is before test");
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver",chromedriverpath);
        driver.get("https://www.saucedemo.com/");

    }

    @Test(priority = 1)
    public void firsttest(){
      String title = driver.getTitle();
      if(title.equals("Swag Labs")){
          Assert.assertTrue(true);
      }else {
          Assert.fail();
      }
    }

    @AfterTest
    public void aftertest(){
        driver.quit();
    }

}
