Feature: All test cases for positive & negative scenarios

  Background:
    Given User is in Suacedemo homepage "https://www.saucedemo.com/"

  Scenario: Login with positve/correct credentials
    When Logins to the system
    Then User enters correct "standard_user"
    And User enters correct "secret_sauce"
    Then User should be able to login