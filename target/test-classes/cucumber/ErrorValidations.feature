
@tag
Feature: Error validation on eCommerce website
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error validation in login
    Given I landed on eCommerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." login error message is displayed

    Examples: 
      | name  									| 		password				|
      | abhikumar@outlook.com 	|     Abhiacademy$155	|
      | abhikumar1@outlook.com 	|     Abhiacademy$15	|
