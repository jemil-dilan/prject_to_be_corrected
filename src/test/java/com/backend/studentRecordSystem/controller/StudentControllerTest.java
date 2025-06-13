package com.backend.studentRecordSystem.controller;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;
import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;
import com.backend.studentRecordSystem.service.student.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class)
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    StudentService studentService;

    private final String BASE_URL = "/students";


    @Test
    void getAllStudentsTest() throws Exception{

        StudentDTO studentDTO = mock();
        StudentDTO studentDTO2 = mock();
        StudentDTO studentDTO3 = mock();

        when(studentService.getAllStudents())
                .thenReturn(List.of(studentDTO, studentDTO2, studentDTO3));

        mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(3)));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void getStudentByIDTest() throws Exception {

        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");
        StudentDTO studentDTO = new StudentDTO(
                studentId,
                "john",
                "cole",
                LocalDate.of(2000,10,5),
                "Douala",
                Gender.MALE,
                StudentStatus.SCHOOLING,
                LocalDateTime.of(2024,11,5,10,56),
                2L,
                Set.of(2L,5L,6L)
        );
        when(studentService.getStudentById(studentId)).thenReturn(studentDTO);

        mockMvc.perform(get(BASE_URL + "/605057e5-dfe3-4c4e-aebe-cde8209604ff").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(studentDTO.id().toString()))
                .andExpect(jsonPath("$.firstName").value(studentDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(studentDTO.lastName()))
                .andExpect(jsonPath("$.date_Of_Birth").value(studentDTO.dateOfBirth().toString()))
                .andExpect(jsonPath("$.place_Of_Birth").value(studentDTO.placeOfBirth()))
                .andExpect(jsonPath("$.status").value(studentDTO.status().toString()))
                .andExpect(jsonPath("$.gender").value(studentDTO.gender().toString()))
                .andExpect(jsonPath("$.classId").value(studentDTO.classId()));

        verify(studentService).getStudentById(studentId);
    }

    @Test
    void deleteStudentTest() throws Exception {
        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");

        doNothing().when(studentService).deleteStudent(studentId);

        mockMvc.perform(delete(BASE_URL + "/605057e5-dfe3-4c4e-aebe-cde8209604ff").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(studentId);
    }

    @Test
    void createStudentTest() throws Exception {
        CreateStudentDTO createStudentDTO = new CreateStudentDTO(
                "john",
                "cole",
                LocalDate.of(2000,10,5),
                "Douala",
                Gender.MALE,
                StudentStatus.SCHOOLING
        );
        StudentDTO studentDTO = new StudentDTO(
                UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"),
                "john",
                "cole",
                LocalDate.of(2000,10,5),
                "Douala",
                Gender.MALE,
                StudentStatus.SCHOOLING,
                LocalDateTime.of(2024,11,5,10,56),
                2L,
                Set.of(2L,5L,6L)
        );

        when(studentService.createStudent(createStudentDTO)).thenReturn(studentDTO);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createStudentDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(studentDTO.id().toString()))
                .andExpect(jsonPath("$.firstName").value(studentDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(studentDTO.lastName()))
                .andExpect(jsonPath("$.date_Of_Birth").value(studentDTO.dateOfBirth().toString()))
                .andExpect(jsonPath("$.place_Of_Birth").value(studentDTO.placeOfBirth()))
                .andExpect(jsonPath("$.status").value(studentDTO.status().toString()))
                .andExpect(jsonPath("$.gender").value(studentDTO.gender().toString()))
                .andExpect(jsonPath("$.classId").value(studentDTO.classId()));
//                .andExpect(jsonPath("$.parentIds").value(studentDTO.parentIds()));

        verify(studentService).createStudent(createStudentDTO);
    }

    @Test
    void updateStudentTest() throws Exception {
        CreateStudentDTO createStudentDTO = new CreateStudentDTO(
                "john",
                "cole",
                LocalDate.of(2000,10,5),
                "Douala",
                Gender.MALE,
                StudentStatus.SCHOOLING
        );
        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");


        doNothing().when(studentService).updateStudent(studentId,createStudentDTO);

        mockMvc.perform(put(BASE_URL + "/605057e5-dfe3-4c4e-aebe-cde8209604ff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createStudentDTO)))
                .andExpect(status().isNoContent());

        verify(studentService).updateStudent(studentId,createStudentDTO);
    }
}