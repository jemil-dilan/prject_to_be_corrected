package com.backend.studentRecordSystem.stepDefinitions;

import com.backend.studentRecordSystem.domain.enums.Relationship;
import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ParentStepDefs implements En{

    private Response response;
    private String baseURL ="/parents";

    public ParentStepDefs() {
        And(
                "the api endpoint is {string}",
                this::theApiEndpointIs
        );
        And(
                "I create a new parent with the following details:",
                this::iCreateANewParentWithTheFollowingDetails
        );
        And(
                "then the response status should be {int}",
                this::thenTheResponseStatusShouldBe
        );
        And(
                "the response body should contain the parent Id",
                this::theResponseBodyShouldContainTheParentId
        );
        And(
                "a parent exists with phone Number {int}",
                this::aParentExistsWithPhoneNumber
        );And(
                "I send a GET request to {string}",
                this::iSendAGETRequestTo
        );And(
                "the response status should be {int}",
                this::theResponseStatusShouldBe
        );And(
                "the response should contain the parent name {string}",
                this::theResponseShouldContainTheParentName
        );And(
                "I send a PUT request to {string} with name {string}",
                this::iSendAPUTRequestToWithName
        );And(
                "I send a DELETE request to {string}",
                this::iSendADELETERequestTo
        );And(
                "the response should contain the parent last name {string}",
                this::theResponseShouldContainTheParentLastName
        );
    }

    public void theApiEndpointIs(String endpoint) {
        this.baseURL = endpoint;
    }

    public void iCreateANewParentWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> dataTableMap = dataTable.entries().getFirst();
        CreateParentDTO createParentDTO = CreateParentDTO.builder().
                    firstName(dataTableMap.get("firstName")).
                    lastName(dataTableMap.get("lastName")).
                    phoneNumber(dataTableMap.get("phoneNumber")).
                    address(dataTableMap.get("address")).
                    relationship(Relationship.valueOf(dataTableMap.get("relationship"))).
                    alternativeContact(dataTableMap.get("alternativeContact")).
                    email(dataTableMap.get("email")).
                    occupation(dataTableMap.get("occupation")).
                    build();

        response = given()
                .contentType(ContentType.JSON)
                .body(createParentDTO)
                .when()
                .post(baseURL);
    }

    public void thenTheResponseStatusShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public void theResponseBodyShouldContainTheParentId() {
        response.then().statusCode(201).body("id", notNullValue());
    }


    public void aParentExistsWithPhoneNumber(int phoneNumber) {
        response = given()
                .when()
                .get(baseURL);

        response.then()
                .statusCode(200)
                .body("phoneNumber[1]", equalTo(String.valueOf(phoneNumber)));
    }


    public void iSendAGETRequestTo(String getEndpoint) {
        response = given()
                .when()
                .get(baseURL);
        Long id = response.jsonPath().getLong("id[1]");
        String updatedEndpoint = getEndpoint.replace("{id}", String.valueOf(id));
        response = given()
                .when()
                .get(updatedEndpoint);
    }

    public void theResponseStatusShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public void theResponseShouldContainTheParentName(String firstName) {
        response.then().body("firstName", equalTo(firstName));
    }

    public void iSendAPUTRequestToWithName(String putEndpoint, String lastName) {
        response = given()
                .when()
                .get(baseURL);
        Long id = response.jsonPath().getLong("id[1]");
        String updatedEndpoint = putEndpoint.replace("{id}", String.valueOf(id));

        CreateParentDTO createParentDTO = CreateParentDTO.builder().
                firstName("John").
                lastName("Updated").
                phoneNumber("128526189").
                address("Douala-Bonaberi").
                relationship(Relationship.FATHER).
                alternativeContact("9876774321").
                email("exam@glo.com").
                occupation("teacher").
                build();

        response = given()
                .contentType(ContentType.JSON)
                .body(createParentDTO)
                .when()
                .put(updatedEndpoint);
    }

    public void theResponseShouldContainTheParentLastName(String lastName) {
        response = given()
                .when()
                .get(baseURL);
        Long id = response.jsonPath().getLong("id[1]");
        String getEndpoint = "/parents/{id}";
        String updatedEndpoint = getEndpoint.replace("{id}", String.valueOf(id));
        response = given()
                .when()
                .get(updatedEndpoint);
        response.then().body("lastName", equalTo(lastName));
    }

    public void iSendADELETERequestTo(String deleteEndpoint) {
        response = given()
                .when()
                .get(baseURL);
        Long id = response.jsonPath().getLong("id[1]");
        String updatedEndpoint = deleteEndpoint.replace("{id}", String.valueOf(id));
        response = given()
                .when()
                .delete(updatedEndpoint);
    }
}
