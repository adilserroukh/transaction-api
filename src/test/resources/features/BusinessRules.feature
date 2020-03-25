Feature: Business Rules

  Scenario: A
    Given A transaction that is not stored in our system
    When I check the status from any channel
    Then The system returns the status 'INVALID'


