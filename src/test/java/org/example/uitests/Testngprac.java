package org.example.uitests;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import org.testng.annotations.*;

public class Testngprac {

    @BeforeTest
    public void beforetest(){
        System.out.println("beforetest");
    }

    @BeforeMethod
    public void beforemethod(){
        System.out.println("beforetest");
    }

    @BeforeStep
    public void beforestep(){
        System.out.println("beforestep");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @AfterTest
    public void aftertest(){
        System.out.println("aftertest");
    }

    @AfterStep
    public void afterstep(){
        System.out.println("afterstep");
    }

    @AfterMethod
    public void aftermethod(){
        System.out.println("aftermethod");
    }

}
