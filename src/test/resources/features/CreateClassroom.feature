Feature: Classroom Creation
  Scenario: create a new classroom successfully
    Given the client is connected to the API
    When the client provide the following valid classroom details:
      | className    | academicYear |  teacherId    |studentIds   |
      | Class 1      |2023/2024 |1              | 1,2           |
    And sends a post request to "/classrooms"
    Then the response status should return 201
    And the database should contain this classroom

  Scenario: Get the created classroom by ID
    Given the client is connected to the API
    And a classroom exists with class name "Class_1"
    When the client calls the endpoint "/classrooms/{id}"
    Then the response status should return 201
    And the response should contain the classroom name "Class_1"

  Scenario: Update an existing classroom
    Given the client is connected to the API
    And a classroom exists with class name "Class_1"
    When the client provide the following valid classroom details:
      | className    | academicYear |  teacherId    |studentIds   |
      | Class 1      |2023/2024 |2            | 1,2           |
    And sends a post request to "/parents/{id}"
    Then the response status should return 204
    And the response should contain the assigned teacher names "Updated"

  Scenario: Delete a classroom
    Given the client is connected to the API
    And a classroom exists with class name "Class_1"
    When the client calls the endpoint "/parents/{id}"
    Then the response status should return 204