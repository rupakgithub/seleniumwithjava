package org.example.stepdefinations;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.utils.LocatorSingleton;
import org.example.utils.SingletonBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.Properties;


public class LoginPageDefinations {

    private static SingletonBrowserClass sbc;
    private static Properties prop;


    @BeforeAll
    public static void setup(){
         sbc = SingletonBrowserClass.getInstanceOfSingletonBrowserClass();
         prop =LocatorSingleton.readPropertiesFile("src/test/resources/locators/login-locator.properties");
    }


    @Given("User is in Suacedemo homepage {string}")
    public void navigate_to_url(String url) {
        sbc.getDriver().get(url);

    }

    @When("User enters correct username {string}")
    public void user_enters_correct_username(String username) {
        sbc.getDriver().findElement(By.cssSelector(prop.getProperty("username"))).sendKeys(username);

    }
    @Then("User enters correct password {string}")
    public void user_enters_correct_password(String password) {
        sbc.getDriver().findElement(By.cssSelector(prop.getProperty("password"))).sendKeys(password);
    }

    @Then("User should be able to login")
    public void user_should_be_able_to_login() {
        sbc.getDriver().findElement(By.cssSelector("input[id='login-button']")).click();
        WebDriverWait wait = new WebDriverWait(sbc.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='app_logo' and text()='Swag Labs']")));
        String errortext = sbc.getDriver().findElement(By.xpath("//div[@class='app_logo' and text()='Swag Labs']")).getText();
        if (errortext.contains("Swag Labs")){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Logo didn't appeared after waiting for 10 seconds");
        }
    }

    @When("User enters username as {string} and password as {string}")
    public void user_enters_username_as_and_password_as(String username, String password) {
        sbc.getDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys(username);
        sbc.getDriver().findElement(By.cssSelector("input[id='password']")).sendKeys(password);
    }
    @Then("User should be able to see error message {string}")
    public void user_should_be_able_to_see_error_message(String errormessage) {
        sbc.getDriver().findElement(By.cssSelector("input[id='login-button']")).click();
        String errortext = sbc.getDriver().findElement(By.xpath("//div[contains(@class,'error-message-container')]/h3[contains(text(),'"+errormessage+"')]")).getText();
        if (errortext.contains(errormessage)){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Text doesn't contains locked out user");
        }
    }

    @When("User enters correct credentials")
    public void user_enters_correct_credentials(DataTable credentials) {
        List<List<String>> data = credentials.asLists(String.class);
        for(List<String> columns: data){
            sbc.getDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys(columns.get(0));
            sbc.getDriver().findElement(By.cssSelector("input[id='password']")).sendKeys(columns.get(1));
        }
    }

    @Then("find recrument")
    public void find_recrument() {
        sbc.getDriver().findElement(By.xpath("//span[text()='Recruitment']")).click();
    }

    @Then("Select {string} as {string}")
    public void select_job_title( String data, String title)throws InterruptedException {
        String DD = prop.getProperty("HRM_recruitment_DD").replace("<<Var1>>",title);
        String DText = prop.getProperty("HRM_recruiment_text").replace("<<Var1>>",title);

        String title1 = "";

        Actions actions = new Actions(sbc.getDriver());
        WebElement jobTitle = sbc.getDriver().findElement(By.xpath(DD));
        WebDriverWait wait = new WebDriverWait(sbc.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
        actions.click(jobTitle).build().perform();
        Thread.sleep(1000);


        for (int i = 0; i < 200; i++) {
            title1 = sbc.getDriver().findElement(By.xpath(DText)).getText();
            System.out.println(title1);
            Thread.sleep(500);
            if (title1.equals(data)) {
                sbc.getDriver().findElement(By.xpath(DText)).click();
                break;
            }
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();

        }

    }

    @Then("User can login")
    public void user_can_login() throws InterruptedException{
        sbc.getDriver().findElement(By.xpath("//button[text()=' Login ']")).click();
        Thread.sleep(1000);

    }


    @AfterAll
    public static void teardown(){
        sbc.getDriver().quit();
    }
}
