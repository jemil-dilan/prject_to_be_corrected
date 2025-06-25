Feature: Parent Operations

  Scenario: Create a new parent
    Given the api endpoint is "/parents"
    When I create a new parent with the following details:
      | firstName          |lastName           |phoneNumber        |address            | relationship       |alternativeContact |email              |occupation         |
      | John           |Amos            | 128526189       |Douala-Bonaberi |FATHER          |9876774321       |  exam@glo.com |teacher          |
    Then then the response status should be 201
    And the response body should contain the parent Id

  Scenario: Get the created Parent by ID
    Given a parent exists with phone Number 128526189
    When I send a GET request to "/parents/{id}"
    Then the response status should be 200
    And the response should contain the parent name "John"

  Scenario: Update an existing Parent
    Given a parent exists with phone Number 128526189
    When I send a PUT request to "/parents/{id}" with name "Updated"
    Then the response status should be 204
    And the response should contain the parent last name "Updated"

  Scenario: Delete a parent
    Given a parent exists with phone Number 128526189
    When I send a DELETE request to "/parents/{id}"
    Then the response status should be 204