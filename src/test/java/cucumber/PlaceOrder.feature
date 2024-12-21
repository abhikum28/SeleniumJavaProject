#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase order from eCommerce website.
  I want to use this template for my feature file
  
  Background:
	Given I landed on eCommerce page

  @Regression
  Scenario Outline: Positive test of submitting a order
  
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  								| 		password				| productName  	|
      | abhikumar@outlook.com |     Abhiacademy$15	| Zara Coat 3 	|
      
      
  @Regression
  Scenario Outline: Positive test of validating a order
  
    Given Logged in with username <name> and password <password>
    When access MyOrder page
    Then <productName> is displayed for Submitted order and delete

    Examples: 
      | name  								| 		password				| productName  	|
      | abhikumar@outlook.com |     Abhiacademy$15	| Zara Coat 3 	|
