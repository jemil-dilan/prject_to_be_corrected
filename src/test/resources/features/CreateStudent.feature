Feature: Student Operations
  Scenario: Create a new student
    Given the api endpoint is "/students"
    When I create a new student with the following details:
      | firstName     |lastName      |dateOfBirth   |placeOfBirth  |gender        |status        |
      | Pierre        |Ngassa        |2000-01-01    | Douala       | MALE         | SCHOOLING    |
    Then response status should be 201
    And the response body should contain the student Id

  Scenario: Get the created Student by ID
    Given a student exists with first name "Pierre" and last name "Ngassa"
    When I send a GET request to api "/students/{id}"
    Then response status should be 200
    And the response should contain the student first name "Pierre" and last name "Ngassa"

  Scenario: Update an existing Student
    Given a student exists with first name "Pierre" and last name "Ngassa"
    When I send a PUT request to "/students/{id}" with last name "Jean"
    Then response status should be 204
    And the response should contain the student last name "Jean"

  Scenario: Delete a student
    Given a student exists with first name "Pierre" and last name "Jean"
    When I send a DELETE request to api "/students/{id}"
    Then response status should be 204