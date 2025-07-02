package com.backend.studentRecordSystem.controller;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StaffRole;
import com.backend.studentRecordSystem.domain.enums.StaffStatus;
import com.backend.studentRecordSystem.domain.teacher.CreateTeacherRequest;
import com.backend.studentRecordSystem.domain.teacher.TeacherResponse;
import com.backend.studentRecordSystem.service.teacher.TeacherService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.liquibase.enabled=false")
@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {
    @MockitoBean
    private TeacherService teacherService;

    String base_URI = "/teachers";

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void createTeacherTest() {
        CreateTeacherRequest createTeacherRequest = CreateTeacherRequest.builder()
                .firstName("fistname")
                .lastName("lastName")
                .phoneNumber("phone")
                .email("mail")
                .address("address")
                .dateOfBirth(LocalDate.of(2002,1,30))
                .idNumber("idNum")
                .gender(Gender.MALE)
                .salary(BigDecimal.valueOf(10000L))
                .status(StaffStatus.ACTIVE)
                .yearsOfExperience(1)
                .assignedClassId(2L)
                .build();

        TeacherResponse teacherResponse = TeacherResponse.builder()
                .id(UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"))
                .firstName(createTeacherRequest.firstName())
                .lastName(createTeacherRequest.lastName())
                .phoneNumber(createTeacherRequest.phoneNumber())
                .email(createTeacherRequest.email())
                .gender(createTeacherRequest.gender())
                .staffRole(StaffRole.TEACHER)
                .assignedClassClassName(ClassNames.CLASS_1)
                .build();

        when(teacherService.createTeacher(createTeacherRequest)).thenReturn(teacherResponse);

        given()
                .contentType("application/json")
                .body(createTeacherRequest)
                .when()
                .post(base_URI)
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("id", equalTo(teacherResponse.id().toString()))
                .body("firstName", equalTo(teacherResponse.firstName()))
                .body("lastName", equalTo(teacherResponse.lastName()))
                .body("phoneNumber", equalTo(teacherResponse.phoneNumber()))
                .body("email", equalTo(teacherResponse.email()))
                .body("gender", equalTo(teacherResponse.gender().toString()))
                .body("staffRole", equalTo(teacherResponse.staffRole().toString()))
                .body("assignedClassClassName", equalTo(teacherResponse.assignedClassClassName().toString()));
    }

    @Test
    void getTeacherByEmailTest(){
        String teacherEmail = "example.com";
        TeacherResponse teacherResponse = TeacherResponse.builder()
                .id(UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"))
                .firstName("fistname")
                .lastName("lastName")
                .phoneNumber("phone")
                .email("mail")
                .gender(Gender.MALE)
                .staffRole(StaffRole.TEACHER)
                .assignedClassClassName(ClassNames.CLASS_1)
                .build();

        when(teacherService.getTeacherByEmail(teacherEmail)).thenReturn(teacherResponse);

        given()
                .when()
                .get("/teachers/email/example.com")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(teacherResponse.id().toString()))
                .body("firstName", equalTo(teacherResponse.firstName()))
                .body("lastName", equalTo(teacherResponse.lastName()))
                .body("phoneNumber", equalTo(teacherResponse.phoneNumber()))
                .body("email", equalTo(teacherResponse.email()))
                .body("gender", equalTo(teacherResponse.gender().toString()))
                .body("staffRole", equalTo(teacherResponse.staffRole().toString()))
                .body("assignedClassClassName", equalTo(teacherResponse.assignedClassClassName().toString()));
    }
}