package com.backend.studentRecordSystem.stepDefinitions;

import com.backend.studentRecordSystem.dto.student.StudentDTO;
import com.backend.studentRecordSystem.service.student.StudentService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

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
