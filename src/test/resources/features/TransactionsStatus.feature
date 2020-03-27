Feature: Seach transactions

  Background:
    Given delete all Transactions
    And delete all Accounts


  Scenario: Users send credit
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 200    |
    When receive the transaction
      | reference | iban                     | amount | fee |
      | 1         | ES9820385778983000760236 | 50     | 0   |
      | 1         | ES9820385778983000760236 | 200    | 0   |
      | 3         | ES9820385778983000760236 | 5      | 0   |
      | 1         | ES9820385778983000760236 | 100    | 0   |
    Then account user
      | IBAN_NUMER               | AMOUNT |
      | ES9820385778983000760236 | 555.00 |

    And the JSON response should have:
    """
   {"dateTook":"2019-07-16T16:55:42.000","fee":0,"iban":"ES9820385778983000760236","reference":"","amount":100,"description":""}
    """

