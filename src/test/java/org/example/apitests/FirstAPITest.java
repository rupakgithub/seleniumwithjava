package org.example.apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class FirstAPITest {

    @Test
    public void validate_get_request(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=1");
        System.out.println(response.statusCode());
        System.out.println(response.asString());

        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode, 200);
    }
}
