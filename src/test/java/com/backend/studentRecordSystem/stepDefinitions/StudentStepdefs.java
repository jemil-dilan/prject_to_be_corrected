package com.backend.studentRecordSystem.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class StudentStepdefs {
    @And("the response body should contain the student Id")
    public void theResponseBodyShouldContainTheStudentId() {
    }

    @Given("a student exists with first name {string} and last name {string}")
    public void aStudentExistsWithFirstNameAndLastName(String arg0, String arg1) {
    }

    @And("the response should contain the student first name {string} and last name {string}")
    public void theResponseShouldContainTheStudentFirstNameAndLastName(String arg0, String arg1) {
    }

    @When("I send a PUT request to {string} with last name {string}")
    public void iSendAPUTRequestToWithLastName(String arg0, String arg1) {
    }

    @And("the response should contain the student last name {string}")
    public void theResponseShouldContainTheStudentLastName(String arg0) {
    }
}
