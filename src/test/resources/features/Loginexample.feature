Feature: This is a example feature file

  @saucedemo
  Scenario: This is scenario to test suacedemo login with positive credentials
    Given User navigated to url "https://www.saucedemo.com/"
    When User enters username "standard_user"
    And user enters password "secret_sauce"
    And User clicks on login button
    Then User should be able to see the homepage

