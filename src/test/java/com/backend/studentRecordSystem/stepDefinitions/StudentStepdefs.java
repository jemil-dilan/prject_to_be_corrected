package com.backend.studentRecordSystem.stepDefinitions;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;
import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StudentStepdefs implements En {
    private String baseURL = "/students";
    private Response response;

    public StudentStepdefs() {
        And("the response body should contain the student Id",
                this::theResponseBodyShouldContainTheStudentId);
        And("a student exists with first name {string} and last name {string}",
                this::aStudentExistsWithFirstNameAndLastName);
        And("the response should contain the student first name {string} and last name {string}",
                this::theResponseShouldContainTheStudentFirstNameAndLastName);
        And("I send a PUT request to {string} with last name {string}",
                this::iSendAPUTRequestToWithLastName);
        And("the response should contain the student last name {string}",
                this::theResponseShouldContainTheStudentLastName);
        And("I create a new student with the following details:",
                this::iCreateANewStudentWithTheFollowingDetails);
        And("I send a GET request to api {string}",
                this::iSendAGETRequestToApi);
        And("I send a DELETE request to api {string}",
                this::iSendADELETERequestToApi);
        And(
                "response status should be {int}",
                this::ResponseStatusShouldBe
        );
    }

    public void iCreateANewStudentWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> tableMaps = dataTable.entries().getFirst();

        CreateStudentDTO createStudentDTO = CreateStudentDTO.builder().
                firstName(tableMaps.get("firstName")).
                lastName(tableMaps.get("lastName")).
                dateOfBirth(LocalDate.parse(tableMaps.get("dateOfBirth"))).
                placeOfBirth(tableMaps.get("placeOfBirth")).
                gender(Gender.valueOf(tableMaps.get("gender"))).
                status(StudentStatus.valueOf(tableMaps.get("status"))).
                build();

        response = given()
                .contentType(ContentType.JSON)
                .body(createStudentDTO)
                .when()
                .post(baseURL);
    }
    public void theResponseBodyShouldContainTheStudentId() {
        response.then().statusCode(201).body("id", notNullValue());
    }

    public void aStudentExistsWithFirstNameAndLastName(String firstName, String lastName) {
        response = given()
                .when()
                .get(baseURL);
        response.then()
                .statusCode(200)
                .body("firstName[1]", equalTo(firstName))
                .body("lastName[1]", equalTo(lastName));
    }

    public void theResponseShouldContainTheStudentFirstNameAndLastName(String firstName, String lastName) {
        response.then()
                .statusCode(200)
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName));
    }

    public void iSendAPUTRequestToWithLastName(String putEndpoint, String lastName) {
        response = given()
                .when()
                .get(baseURL);
        UUID id = response.jsonPath().getUUID("id[1]");
        String updatedEndpoint = putEndpoint.replace("{id}", id.toString());

        CreateStudentDTO createStudentDTO = CreateStudentDTO.builder().
                firstName("Pierre").
                lastName(lastName).
                dateOfBirth(LocalDate.of(2000,1,1)).
                placeOfBirth("Douala").
                gender(Gender.MALE).
                status(StudentStatus.SCHOOLING).
                build();

        response = given()
                .contentType(ContentType.JSON)
                .body(createStudentDTO)
                .when()
                .put(updatedEndpoint);

    }

    public void theResponseShouldContainTheStudentLastName(String lastName) {
        response = given()
                .when()
                .get(baseURL);
        UUID id = response.jsonPath().getUUID("id[1]");
        this.baseURL = "/students/{id}";
        String updatedEndpoint = baseURL.replace("{id}", id.toString());
        response = given()
                .when()
                .get(updatedEndpoint);
        response.then()
                .body("lastName", equalTo(lastName));
    }

    public void iSendAGETRequestToApi (String getEndpoint) {
        response = given()
                .when()
                .get(baseURL);
        UUID id = response.jsonPath().getUUID("id[1]");
        String updatedEndpoint = getEndpoint.replace("{id}", id.toString());
        response = given()
                .when()
                .get(updatedEndpoint);
    }

    public void iSendADELETERequestToApi (String deleteEndpoint){
        response = given()
                .when()
                .get(baseURL);
        UUID id = response.jsonPath().getUUID("id[1]");
        String updatedEndpoint = deleteEndpoint.replace("{id}", String.valueOf(id));
        response = given()
                .when()
                .delete(updatedEndpoint);
    }

    public void ResponseStatusShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }
}
