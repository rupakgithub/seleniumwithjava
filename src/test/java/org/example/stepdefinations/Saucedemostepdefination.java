package org.example.stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.staticfields.StaticInstances;
import org.example.utils.SingletonBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Saucedemostepdefination {

    @Given("User navigated to url {string}")
    public void user_navigated_to_url(String url) {
        //StaticInstances.sbc.getDriver().get(url);
        SingletonBrowserClass.getInstanceOfSingletonBrowserClass().getDriver().get(url);
    }
    @When("User enters username {string}")
    public void user_enters_username(String username) {
        SingletonBrowserClass.getInstanceOfSingletonBrowserClass().getDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys(username);
    }
    @When("user enters password {string}")
    public void user_enters_password(String password) {
        SingletonBrowserClass.getInstanceOfSingletonBrowserClass().getDriver().findElement(By.cssSelector("input[id='password']")).sendKeys(password);
    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
        SingletonBrowserClass.getInstanceOfSingletonBrowserClass().getDriver().findElement(By.cssSelector("input[id='login-button']")).click();
    }

    @Then("User should be able to see the homepage")
    public void user_should_be_able_to_see_the_homepage() {
        WebDriverWait wait = new WebDriverWait(SingletonBrowserClass.getInstanceOfSingletonBrowserClass().getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header_label']")));

        String homepagetext = StaticInstances.sbc.getDriver().findElement(By.xpath("//div[@class='header_label']")).getText();

        if(homepagetext.equals("Swag Labs")){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Logo didn't appeared after waiting for 10 seconds");
        }
    }
}
