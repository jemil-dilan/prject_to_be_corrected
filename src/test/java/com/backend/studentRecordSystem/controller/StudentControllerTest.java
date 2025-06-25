package com.backend.studentRecordSystem.controller;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;
import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;
import com.backend.studentRecordSystem.service.student.StudentService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setup(){
        RestAssuredMockMvc.standaloneSetup(studentController);
    }

    @Test
    void getAllStudentsTest(){
        StudentDTO studentDTO = mock(StudentDTO.class);
        StudentDTO studentDTO1 = mock(StudentDTO.class);
        StudentDTO studentDTO2 = mock(StudentDTO.class);

        when(studentService.getAllStudents())
                .thenReturn(List.of(studentDTO, studentDTO1, studentDTO2));

        RestAssuredMockMvc.when()
                .get("/students")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(3));

        verify(studentService,times(1)).getAllStudents();
    }

    @Test
    void getStudentByIdTest(){
        StudentDTO studentDTO = new StudentDTO(
                UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"),
                "john",
                "cole",
                LocalDate.of(2000,10,5),
                "Douala",
                Gender.MALE,
                StudentStatus.SCHOOLING,
                LocalDateTime.of(2023,12,11,13,2),
                1L,
                Set.of(1L,2L)
        );

        when(studentService.getStudentById(studentDTO.id()))
                .thenReturn(studentDTO);


                when()
                .get("/students/{id}", studentDTO.id())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(studentDTO.id().toString()))
                .body("firstName", equalTo(studentDTO.firstName()))
                .body("lastName", equalTo(studentDTO.lastName()))
                .body("placeOfBirth", equalTo(studentDTO.placeOfBirth()))
                .body("gender", equalTo(studentDTO.gender().toString()))
                .body("status", equalTo(studentDTO.status().toString()))
                .body("classId", equalTo(studentDTO.classId().intValue()));

        verify(studentService, times(1)).getStudentById(studentDTO.id());

    }

    @Test
    void createStudentTest(){
        CreateStudentDTO createStudentDTO = new CreateStudentDTO(
                "john",
                "cole",
                LocalDate.of(2000,10,5),
                "Douala",
                Gender.MALE,
                StudentStatus.SCHOOLING
        );

        StudentDTO studentDTO = StudentDTO.builder()
                .id(UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"))
                .firstName(createStudentDTO.firstName())
                .lastName(createStudentDTO.lastName())
                .dateOfBirth(createStudentDTO.dateOfBirth())
                .placeOfBirth(createStudentDTO.placeOfBirth())
                .gender(createStudentDTO.gender())
                .status(createStudentDTO.status())
                .admissionDate(LocalDateTime.of(2011,10,12,10,53))
                .classId(1L)
                .parentIds(Set.of(1L,2L))
                .build();

        when(studentService.createStudent(createStudentDTO)).thenReturn(studentDTO);

        given()
                .contentType(ContentType.JSON)
                .body(createStudentDTO)
                .when()
                .post("/students")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", equalTo(studentDTO.id().toString()))
                .body("firstName", equalTo(studentDTO.firstName()))
                .body("lastName", equalTo(studentDTO.lastName()))
                .body("placeOfBirth", equalTo(studentDTO.placeOfBirth()))
                .body("gender", equalTo(studentDTO.gender().toString()))
                .body("status", equalTo(studentDTO.status().toString()))
                .body("classId", equalTo(studentDTO.classId().intValue()));

        verify(studentService, times(1)).createStudent(createStudentDTO);

    }

    @Test
    void updateStudentTest(){
        CreateStudentDTO createStudentDTO = new CreateStudentDTO(
                "john",
                "cole",
                LocalDate.of(2000,10,5),
                "Douala",
                Gender.MALE,
                StudentStatus.SCHOOLING
        );

        StudentDTO studentDTO = StudentDTO.builder()
                .id(UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"))
                .firstName(createStudentDTO.firstName())
                .lastName(createStudentDTO.lastName())
                .dateOfBirth(createStudentDTO.dateOfBirth())
                .placeOfBirth(createStudentDTO.placeOfBirth())
                .gender(createStudentDTO.gender())
                .status(createStudentDTO.status())
                .admissionDate(LocalDateTime.of(2011,10,12,10,53))
                .classId(1L)
                .parentIds(Set.of(1L,2L))
                .build();

        doNothing().when(studentService).updateStudent(studentDTO.id(), createStudentDTO);

        given()
                .contentType(ContentType.JSON)
                .body(createStudentDTO)
                .when()
                .put("/students/{id}", studentDTO.id())
                .then()
                .statusCode(204);

        verify(studentService, times(1)).updateStudent(studentDTO.id(),createStudentDTO);

    }

    @Test
    void deleteStudentTest(){

        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");

        doNothing().when(studentService).deleteStudent(studentId);

        when()
                .delete("/students/{id}", studentId)
                .then()
                .statusCode(204);

        verify(studentService, times(1)).deleteStudent(studentId);

    }
}