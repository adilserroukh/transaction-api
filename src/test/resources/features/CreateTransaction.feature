Feature: Create transaction

  Background:
    Given delete Account 'ES9820385778983000760236'

  Scenario: Create Transaction And verify in Data Base
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 200    |
    When receive the transaction
      | iban                     | amount |
      | ES9820385778983000760236 | 50     |
    Then account user
      | iban                     | AMOUNT |
      | ES9820385778983000760236 | 150    |









