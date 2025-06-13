Feature: Classroom Creation
  Scenario: create a new classroom
    Given An empty classroom database
    When I create a new classroom with the following details:
      | className        | Class 1      |
      | academicYear | 2023/2024 |
      | teacherId    | 1              |
      | studentIds   | 1,2           |

    Then The classroom should be created successfully
    And The classroom database should contain 1 classroom