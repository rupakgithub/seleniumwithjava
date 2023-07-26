package org.example.stepdefinations;

import io.cucumber.java.*;
import org.example.staticfields.StaticInstances;
import org.example.utils.LocatorSingleton;
import org.example.utils.SingletonBrowserClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class commonStepDefinitions {

    @BeforeAll
    public static void setup(){
        StaticInstances.sbc = SingletonBrowserClass.getInstanceOfSingletonBrowserClass();
        StaticInstances.prop = LocatorSingleton.readPropertiesFile("src/test/resources/locators/login-locator.properties");
    }

    @AfterAll
    public static void teardown(){
        StaticInstances.sbc.getDriver().quit();
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            System.out.println("Scenario "+scenario.getName()+" is failed");
            final byte[] screenshot = ((TakesScreenshot) StaticInstances.sbc).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

}
