Feature: Student Creation
  Scenario: Create a new student
    Given an empty student database
    When I create a new student with the following details:
      | firstName     | John        |
      | lastName      | Doe         |
      | dateOfBirth   | 2000-01-01  |
      | placeOfBirth  | Douala  |
      | gender        | MALE |
      | status        | SCHOOLING |
    Then the student should be created successfully
    And the student database should contain 1 student