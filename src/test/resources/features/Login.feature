@login
Feature: All test cases for positive & negative scenarios

  Background:
    Given User is in "Suacedemo" homepage "https://www.saucedemo.com/"


  @Form
  Scenario Outline: Check username
    Then find recrument
    Then Select "<Job Title>" as "Job Title"
    Then Select "<Vacancy>" as "Vacancy"


    Examples:
      |Job Title| Vacancy|
      |Database Administrator|Senior QA Lead|
      |Database Administrator|Senior QA Lead|

 @validLogin @Regress @Login
  Scenario: Login with positive/correct credentials
    When User enters correct username "standard_user"
    And User enters correct password "secret_sauce"
    Then User should be able to login

  @datatabletest
  Scenario: Login with positive/correct credentials
    When User enters correct credentials
    |standard_user|secret_sauce|
    Then User should be able to login

  @InvalidLogin @Login
  Scenario Outline: Login with invalid credentials
    When User enters username as "<username>" and password as "<password>"
    Then User should be able to see error message "<errorMessage>"

    Examples:
    |username        |password    |errorMessage                          |
    |locked_out_user |secret_sauce| Sorry, this user has been locked out.|
    |Invalid_user    |secret_sauce|Username and password do not match any user in this service|