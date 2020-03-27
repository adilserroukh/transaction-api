Feature: Create transaction

  Background:
    Given delete all Accounts

  Scenario: Users send credit
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 200    |
    When receive the transaction
      | iban                     | amount | fee |
      | ES9820385778983000760236 | 50     | 0   |
    Then account user
      | IBAN_NUMER               | AMOUNT |
      | ES9820385778983000760236 | 250.00 |


  Scenario: Users send debit
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 200    |
    When receive the transaction
      | iban                     | amount | fee |
      | ES9820385778983000760236 | -80    | 0   |
    Then account user
      | IBAN_NUMER               | AMOUNT |
      | ES9820385778983000760236 | 120.00 |


  Scenario: Users send 3 transactions
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 200    |
    When receive the transaction
      | iban                     | amount | fee |
      | ES9820385778983000760236 | 50     | 1   |
      | ES9820385778983000760236 | 20     | 1   |
      | ES9820385778983000760236 | -30    | 0   |
    Then account user
      | IBAN_NUMER               | AMOUNT |
      | ES9820385778983000760236 | 238.00 |





