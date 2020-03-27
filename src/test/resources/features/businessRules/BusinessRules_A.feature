Feature: Business Rules

  Background:
    Given delete all Transactions
    And delete all Accounts

  Scenario: Test A - Status 'INVALID' from any channel
    Given A transaction that is not stored in our system
    When I check the status from any channel:
       """
         {
         "reference":"XXXXXX",
         "channel":"CLIENT"
        }
       """
    Then The system returns the status:
       """
         {
         "reference":"XXXXXX",
         "status":"INVALID"
        }
       """

