Feature: Business Rules

  Background:
    Given delete all Transactions
    And delete all Accounts

  Scenario: Test F - for INTERNAL
    Given create account user
      | iban                     | amount |
      | ES9820385778983000760236 | 0      |

    And A transaction that is stored in our system:
      """
       {
        "reference": "12345A",
        "iban": "ES9820385778983000760236",
        "date": "2040-01-01T16:55:42.000",
        "amount": 193.38,
        "fee": 3.18,
        "description": "Restaurant payment"
        }
    """
    When I check the status from any channel:
       """
         {
         "reference":"12345A",
         "channel":"INTERNAL"
        }
       """
    Then The system returns the status:
       """
         {
         "reference":"12345A",
         "status":"FUTURE",
         "amount": 193.38,
         "fee":3.18
        }
       """
