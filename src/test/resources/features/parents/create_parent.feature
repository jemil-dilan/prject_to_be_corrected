Feature: Parent Creation
  Scenario: Create a new parent
    Given an empty parent database
    When I create a new parent with the following details:
      | firstName     | Peter        |
      | lastName      | Gosh         |
      | phoneNumber   | 123456789  |
      | address  | Douala-Bonaberi  |
      | relationship        | FATHER |
      | alternativeContact        | 987654321 |
      | email        | example@glo.com |
      | occupation        | driver |
    Then the parent should be created successfully
    And the parent database should contain 1 student