package com.backend.studentRecordSystem.stepDefinitions;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;
import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;
import com.backend.studentRecordSystem.service.student.StudentService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateStudentTestSteps {
    private final StudentService studentService;

    private StudentDTO studentDTO;

    public CreateStudentTestSteps(StudentService studentService) {
        this.studentService = studentService;
    }

    @Given("an empty student database")
    public void anEmptyStudentDatabase() {
        // Assuming the studentService has a method to clear the database
        studentService.clearDatabase();
        assertThat(studentService.getAllStudents()).isEmpty();
    }

    @When("I create a new student with the following details:")
    public void iCreateANewStudentWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> tableMaps = dataTable.asMap(String.class, String.class);

        CreateStudentDTO createStudentDTO = CreateStudentDTO.builder().
                firstName(tableMaps.get("firstName")).
                lastName(tableMaps.get("lastName")).
                dateOfBirth(LocalDate.parse(tableMaps.get("dateOfBirth"))).
                placeOfBirth(tableMaps.get("placeOfBirth")).
                gender(Gender.valueOf(tableMaps.get("gender"))).
                status(StudentStatus.valueOf(tableMaps.get("status"))).
                build();
        studentDTO = studentService.createStudent(createStudentDTO);
    }

    @Then("the student should be created successfully")
    public void theStudentShouldBeCreatedSuccessfully() {
        assertThat(studentDTO.id()).isNotNull();
        assertThat(studentService.getStudentById(studentDTO.id())).isNotNull();
    }

    @And("the student database should contain {int} student")
    public void theStudentDatabaseShouldContainStudent(int count) {
        assertThat(studentService.getAllStudents()).hasSize(count);
    }
}
