Feature: US 2 : In order to retrieve some or all of my savings
  As a bank client
  I want to make a withdrawal from my account

  Background:
    Given Client 001 exists
    Given Client 002 exists

  Scenario: 2.1 - As a bank client, i want to make a withdrawal of x € on a bank account
    Given I'm the client 001
    And Bank account has a positiv balance of 2560.0 €
    When I make a withdrawal of 100.0 €
    Then I should get the new balance 2460.0 €

  Scenario: 2.2 - As a bank client, i want to make a deposit of x € on a bank account
    Given I'm the client 001
    And Bank account has a negativ balance of -666.0 €
    When I make a withdrawal of 100.0 €
    Then I should get the new balance -766.0 €

  Scenario: 2.3 - As a bank client, i want to make a deposit of x € on a bank account
    Given I'm the client 001
    And Bank account has a balance of 0.0 €
    When I make a withdrawal of 100.0 €
    Then I should get the new balance -100.0 €

  Scenario: 2.4 - As a bank client, i want to make a deposit of x € on a bank account that doesn't exist
    Given I'm the client 666
    When I make a withdrawal of 100.0 €
    Then I should get the error message : Client 666 unknown!

