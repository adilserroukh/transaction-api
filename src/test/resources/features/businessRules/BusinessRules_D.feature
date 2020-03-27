Feature: Business Rules

  Background:
    Given delete all Transactions
    And delete all Accounts

  Scenario: Test D - for CLIENT
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 0      |

    And A transaction that is stored in our system:
      """
       {
        "reference": "12345A",
        "iban": "ES9820385778983000760236",
        "amount": 193.38,
        "fee": 3.18,
        "description": "Restaurant payment"
        }
    """
    When I check the status from any channel:
       """
         {
         "reference":"12345A",
         "channel":"CLIENT"
        }
       """
    Then The system returns the status:
       """
         {
         "reference":"12345A",
         "status":"PENDING",
         "amount": 190.2
        }
       """


  Scenario: Test D - for ATM
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 0      |

    And A transaction that is stored in our system:
      """
       {
        "reference": "12345A",
        "iban": "ES9820385778983000760236",
        "amount": 193.38,
        "fee": 3.18,
        "description": "Restaurant payment"
        }
    """
    When I check the status from any channel:
       """
         {
         "reference":"12345A",
         "channel":"ATM"
        }
       """
    Then The system returns the status:
       """
         {
         "reference":"12345A",
         "status":"PENDING",
         "amount": 190.2
        }
       """
