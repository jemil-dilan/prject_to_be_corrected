Feature: Teacher Operations

  Scenario: create a new Teacher successfully
    Given the client is connected to the API
    When the client provide valid teacher details:
      | firstName | lastName | phoneNumber | email         | address        | dateOfBirth | idNumber     | gender | salary | status | yearsOfExperience | assignedClassId |
      | Job       | Ngassa   | 12345678    | zoo@email.com | bonaberi_ndobo | 1990-01-03  | lt21a1104e52 | MALE   | 500000 | ACTIVE | 5                 | 1               |
    And sends a request to "/teachers" to create a new teacher
    Then the response status should return 201
    And the database should contain this teacher

  Scenario: Get the created teacher by email
    Given the client is connected to the API
    When the client calls the endpoint "/teachers/email/{email}" with the email "zoo@email.com"
    Then the response status should return 200
    And the response should contain the teacher name "Job Ngassa"

  Scenario: Get the created teacher by phone number
    Given the client is connected to the API
    When the client calls the endpoint "/teachers/phoneNumber/{phoneNumber}" with the phone number 12345678
    Then the response status should return 200
    And the response should contain the teacher name "Job Ngassa"

  Scenario: Get the created teacher by ID card number
    Given the client is connected to the API
    When the client calls the endpoint "/teachers/idNumber/{idNumber}" with the id card number "lt21a1104e52"
    Then the response status should return 200
    And the response should contain the teacher name "Job Ngassa"

  Scenario: Update an existing teacher
    Given the client is connected to the API
    And a teacher exists with email "zoom@example.com"
    When the client provide valid teacher details:
      | firstname | lastname | phoneNumber | email         | address        | dateOfBirth | idNumber     | gender | salary | status | yearsOfExperience | assignedClassId |
      | Job       | Ngassa   | 12345678    | ngassa@po.com | bonaberi_ndobo | 1990-01-03  | lt21a1104e52 | MALE   | 500000 | ACTIVE | 5                 | 1               |
    And sends a request to "/teachers/{id}" to update the teacher
    Then the response status should return 204
    And the response should contain the modified email "ngassa@po.com"

  Scenario: Delete a classroom
    Given the client is connected to the API
    And a teacher exists with email "ngassa@po.com"
    When the client calls the endpoint "/teachers/{id}" to delete teacher with id card number "lt21a1104e52"
    Then the response status should return 204