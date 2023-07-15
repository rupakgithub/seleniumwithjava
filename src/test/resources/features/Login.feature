@login
Feature: All test cases for positive & negative scenarios

  Background:
    Given User is in Suacedemo homepage "https://www.saucedemo.com/"

  Scenario: Login with positive/correct credentials
    When Logins to the system
    Then User enters correct username "standard_user"
    And User enters correct password "secret_sauce"
    Then User should be able to login