package org.example.stepdefinations;

import io.cucumber.java.en.Given;

public class HRMStepDefinations {

    @Given("User is in {string} homepage")
    public void user_is_in_homepage(String string) {

    }

    @Given("Take {string} name as {string}")
    public void take_name_as(String a, String b) {
        System.out.println(a);
        System.out.println(b);
    }

}
