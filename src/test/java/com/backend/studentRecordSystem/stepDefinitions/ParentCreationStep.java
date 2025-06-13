package com.backend.studentRecordSystem.stepDefinitions;

import com.backend.studentRecordSystem.domain.enums.Relationship;
import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;
import com.backend.studentRecordSystem.service.parent.ParentService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParentCreationStep {
    private final ParentService parentService;

    private ParentDTO parentDTO;

    public ParentCreationStep(ParentService parentService) {
        this.parentService = parentService;
    }

    @Given("an empty parent database")
    public void anEmptyParentDatabase() {
        parentService.clearDatabase();
        assertThat(parentService.getAllParents()).isEmpty();
    }

    @When("I create a new parent with the following details:")
    public void iCreateANewParentWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> details = dataTable.asMap(String.class, String.class);
        CreateParentDTO createParentDTO = CreateParentDTO.builder().
                firstName(details.get("firstName")).
                lastName(details.get("lastName")).
                phoneNumber(details.get("phoneNumber")).
                address(details.get("address")).
                relationship(Relationship.valueOf(details.get("relationship"))).
                alternativeContact(details.get("alternativeContact")).
                email(details.get("email")).
                occupation(details.get("occupation")).
                build();
        parentDTO = parentService.createParent(createParentDTO);
    }

    @Then("the parent should be created successfully")
    public void theParentShouldBeCreatedSuccessfully() {
        assertThat(parentDTO.id()).isNotNull();
        assertThat(parentService.getParentById(parentDTO.id())).isNotNull();
    }

    @And("the parent database should contain {int} student")
    public void theParentDatabaseShouldContainStudent(int count) {
        assertThat(parentService.getAllParents()).hasSize(count);
    }
}
