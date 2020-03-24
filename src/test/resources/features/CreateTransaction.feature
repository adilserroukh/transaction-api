Feature: Create transaction

  Background:
    Given delete Account 'ES9820385778983000760236'

  Scenario: Create Transaction And verify in Data Base
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 0      |
    When receive the transaction
      | reference | account_iban             | date                     | amount | description        |
      | 1         | ES9820385778983000760236 | 2019-07-16T16:55:42.000Z | 193.38 | Restaurant payment |
    Then The system returns the status 'INVALID'
    And account user
      | iban                     | amount |
      | ES9820385778983000760236 | 0      |









