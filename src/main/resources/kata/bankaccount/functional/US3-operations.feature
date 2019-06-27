Feature: US 3 : In order to check my operations
  As a bank client
  I want to see the history (operation, date, amount, balance) of my operations

  Background:
    Given Client 001 exists
    Given Client 002 exists

  Scenario: 3.1 - As a bank client, i want to check my operations
    Given I'm the client 001
    And Bank account has a positiv balance of 2560.0 €
    And I make a withdrawal of 100.0 € the 2019-05-22 at 12h54
    And I make a deposit of 5000.0 € the 2019-05-29 at 17h12
    And I make a deposit of 400.33 € the 2019-06-12 at 13h10
    When I consult my operations
    Then I should get the result
  """
  WITHDRAWAL      100,00 € the 2019-05-22 at 12h54 (new balance:    2460,00 €)
  DEPOSIT        5000,00 € the 2019-05-29 at 17h12 (new balance:    7460,00 €)
  DEPOSIT         400,33 € the 2019-06-12 at 13h10 (new balance:    7860,33 €)

  """

  Scenario: 3.2 - As a bank client, i want to make a deposit of x € on a bank account that doesn't exist
    Given I'm the client 666
    When I consult my operations
    Then I should get the error message : Client 666 unknown!

