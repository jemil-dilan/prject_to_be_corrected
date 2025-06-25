package com.backend.studentRecordSystem.service;

import com.backend.studentRecordSystem.domain.student.Student;
import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;
import com.backend.studentRecordSystem.dto.StudentData;
import com.backend.studentRecordSystem.domain.student.StudentFactory;
import com.backend.studentRecordSystem.mapper.StudentMapper;
import com.backend.studentRecordSystem.service.student.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    StudentFactory studentFactory;
    @Mock
    StudentMapper studentMapper;
    @InjectMocks
    StudentServiceImpl objectUnderTest;

    @Test
    void getAllStudentsTest() {

        Student student = mock();
        Student student1 = mock();
        Student student2 = mock();

        StudentDTO studentDTO = mock();
        StudentDTO studentDTO2 = mock();
        StudentDTO studentDTO3 = mock();


        when(studentFactory.getAllStudents()).thenReturn(List.of(student, student1, student2));
        when(studentMapper.toStudentDTO(student)).thenReturn(studentDTO);
        when(studentMapper.toStudentDTO(student1)).thenReturn(studentDTO2);
        when(studentMapper.toStudentDTO(student2)).thenReturn(studentDTO3);

        List<StudentDTO> resultUnderTest = objectUnderTest.getAllStudents();

        assertThat(resultUnderTest)
                .hasSize(3)
                .contains(studentDTO2, studentDTO3, studentDTO)
                .startsWith(studentDTO)
                .endsWith(studentDTO3);
    }
    
    @Test
    void getStudentByIdTest() {
        Student student = mock();
        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");
        StudentDTO studentDTO = mock();

        when(studentFactory.getStudentById(studentId)).thenReturn(student);
        when(studentMapper.toStudentDTO(student)).thenReturn(studentDTO);

        StudentDTO resultUnderTest = objectUnderTest.getStudentById(studentId);

        assertThat(resultUnderTest).usingRecursiveComparison().isEqualTo(studentDTO);
    }

    @Test
    void deleteStudentByIdTest() {
        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");

        doNothing().when(studentFactory).deleteStudent(studentId);

        objectUnderTest.deleteStudent(studentId);

        verify(studentFactory, times(1)).deleteStudent(studentId);
    }

    @Test
    void createStudentTest() {
        CreateStudentDTO createStudentDTO = mock();
        Student student = mock();
        StudentDTO studentDTO = mock();
        StudentData studentData = mock(StudentData.class);

        when(studentMapper.toStudentData(createStudentDTO)).thenReturn(studentData);
        when(studentFactory.createStudent(studentData)).thenReturn(student);
        when(studentMapper.toStudentDTO(student)).thenReturn(studentDTO);

        StudentDTO resultUnderTest = objectUnderTest.createStudent(createStudentDTO);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(studentDTO);
    }

    @Test
    void updateStudentTest() {

        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");
        CreateStudentDTO createStudentDTO = mock();
        StudentData studentData = mock(StudentData.class);


        when(studentMapper.toStudentData(createStudentDTO)).thenReturn(studentData);
        doNothing().when(studentFactory).updateStudent(studentId, studentData);

        objectUnderTest.updateStudent(studentId, createStudentDTO);

        verify(studentFactory, times(1)).updateStudent(studentId, studentData);
        verify(studentMapper, times(1)).toStudentData(createStudentDTO);
        verify(studentFactory, times(1)).getStudentById(studentId);
    }
}