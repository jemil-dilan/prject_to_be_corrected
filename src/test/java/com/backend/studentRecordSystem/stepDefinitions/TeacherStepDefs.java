package com.backend.studentRecordSystem.stepDefinitions;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StaffStatus;
import com.backend.studentRecordSystem.domain.teacher.CreateTeacherRequest;
import com.backend.studentRecordSystem.domain.teacher.TeacherRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.liquibase.enabled=false")
public class TeacherStepDefs implements En {
    @LocalServerPort
    private int port;
    private final TeacherRepository teacherRepository;

    private CreateTeacherRequest createTeacherRequest;
    private Response response;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    public TeacherStepDefs(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;

        And("the client is connected to the API", () -> {
            RestAssured.baseURI = "http://localhost:8080";
        });
        And("the client provide valid teacher details:", (DataTable dataTable) -> {
            Map<String, String> dataSet = dataTable.entries().getFirst();

            createTeacherRequest = CreateTeacherRequest.builder()
                    .firstName(dataSet.get("firstName"))
                    .lastName(dataSet.get("lastName"))
                    .phoneNumber(dataSet.get("phoneNumber"))
                    .email(dataSet.get("email"))
                    .address(dataSet.get("address"))
                    .dateOfBirth(LocalDate.parse(dataSet.get("dateOfBirth")))
                    .idNumber(dataSet.get("idNumber"))
                    .gender(Gender.valueOf(dataSet.get("gender")))
                    .salary(BigDecimal.valueOf(Long.parseLong(dataSet.get("salary"))))
                    .status(StaffStatus.valueOf(dataSet.get("status")))
                    .yearsOfExperience(Integer.parseInt(dataSet.get("yearsOfExperience")))
                    .build();
        });
        And("sends a request to {string} to create a new teacher", (String endpoint) -> {
            response = given()
                    .contentType("application/json")
                    .body(createTeacherRequest)
                    .when()
                    .post(endpoint);
        });
        And("the response status should return {int}", (Integer code) -> {
            response.then().statusCode(code);
        });
        And("the database should contain this teacher", () -> {
            boolean exists = teacherRepository.existsByEmail(createTeacherRequest.email());
            assertTrue(exists);
        });

        And("the client calls the endpoint {string} with the email {string}", (String endpoint, String email) -> {
            String modifiedEndpoint = endpoint.replace("{email}", email);
            response = given()
                    .contentType("application/json")
                    .when()
                    .get(modifiedEndpoint);
        });
        And("the response should contain the teacher name {string}", (String names) -> {
            String[] nameParts = names.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            response.then().body("firstName", equalTo(firstName));
            response.then().body("lastName", equalTo(lastName));
        });

        When("the client calls the endpoint {string} with the phone number {int}", (String endpoint, Integer phoneNumber) -> {
            String modifiedEndpoint = endpoint.replace("{phoneNumber}", phoneNumber.toString());
            response = given()
                    .contentType("application/json")
                    .when()
                    .get(modifiedEndpoint);
        });
        And("the client calls the endpoint {string} with the id card number {string}", (String endpoint, String idNumber) -> {
            String modifiedEndpoint = endpoint.replace("{idNumber}", idNumber);
            response = given()
                    .contentType("application/json")
                    .when()
                    .get(modifiedEndpoint);
        });
        And("a teacher exists with email {string}", (String email) -> {
            boolean exists = teacherRepository.existsByEmail(email);
            assertTrue(exists);
        });
        And("sends a request to {string} to update the teacher", (String endpoint) -> {
            response = given()
                    .when()
                    .get("/teachers/email/" + createTeacherRequest.email());

            UUID teacherId = response.jsonPath().get("id");
            String modifiedEndpoint = endpoint.replace("{id}", teacherId.toString());

            response = given()
                    .contentType("application/json")
                    .body(createTeacherRequest)
                    .when()
                    .put(modifiedEndpoint);
        });
        And("the response should contain the modified email {string}", (String email) -> {
            response = given()
                    .when()
                    .get("/teachers/idNumber/" + createTeacherRequest.idNumber());
            response.then().body("email", equalTo(email));
        });
        And("the client calls the endpoint {string} to delete teacher with id card number {string}", (String endpoint, String idNumber) -> {
            response = given()
                    .when()
                    .get("/teachers/idNumber/" + idNumber);

            UUID teacherId = response.jsonPath().get("id");
            String modifiedEndpoint = endpoint.replace("{id}", teacherId.toString());

            response = given()
                    .contentType("application/json")
                    .when()
                    .delete(modifiedEndpoint);
        });

    }

}
