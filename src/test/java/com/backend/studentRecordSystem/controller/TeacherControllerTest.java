package com.backend.studentRecordSystem.controller;

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

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        CreateTeacherRequest createTeacherRequest = mock(CreateTeacherRequest.class);
        TeacherResponse teacherResponse = TeacherResponse.builder()
                .id(UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"))
                .firstName(createTeacherRequest.firstName())
                .lastName(createTeacherRequest.lastName())
                .phoneNumber(createTeacherRequest.phoneNumber())
                .email(createTeacherRequest.email())
                .gender(createTeacherRequest.gender())
                .build();

        when(teacherService.createTeacher(createTeacherRequest)).thenReturn(teacherResponse);

        given()
                .contentType(ContentType.JSON)
                .body(createTeacherRequest)
                .when()
                .post("/teachers")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("firstName", equalTo(teacherResponse.firstName()))
                .body("lastName", equalTo(teacherResponse.lastName()))
                .body("phoneNumber", equalTo(teacherResponse.phoneNumber()))
                .body("email", equalTo(teacherResponse.email()))
                .body("address", equalTo(teacherResponse.gender().toString()))
                .body("staffRole", equalTo(teacherResponse.staffRole().toString()));
    }
}