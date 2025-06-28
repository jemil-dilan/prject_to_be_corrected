package com.backend.studentRecordSystem.factory;

import com.backend.studentRecordSystem.domain.student.Student;
import com.backend.studentRecordSystem.dto.StudentData;
import com.backend.studentRecordSystem.domain.student.StudentFactoryImpl;
import com.backend.studentRecordSystem.domain.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class   StudentFactoryImplTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentFactoryImpl objectUnderTest;

    @Test
    void createStudentTest() {
        StudentData studentData = mock();
        Student student = Student.builder()
                .firstName(studentData.firstName())
                .lastName(studentData.lastName())
                .dateOfBirth(studentData.dateOfBirth())
                .placeOfBirth(studentData.placeOfBirth())
                .gender(studentData.gender())
                .status(studentData.status())
//                .schoolClass(studentData.classId())
//                .parents(studentData.parentIds())
                .build();

        when(studentRepository
                .existsByFirstNameAndLastName(
                        studentData.firstName(),
                        studentData.lastName()
                )).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student resultUnderTest = objectUnderTest
                .createStudent(studentData);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(student);
    }

    @Test
    void getAllStudentsTest() {
        Student student = mock();
        Student student2 = mock();
        Student student3 = mock();

        when(studentRepository.findAll())
                .thenReturn(List.of(student, student2, student3));

        List<Student> resultUnderTest = objectUnderTest.getAllStudents();

        assertThat(resultUnderTest)
                .hasSize(3)
                .contains(student2, student3, student)
                .startsWith(student)
                .endsWith(student3);
    }

    @Test
    void getStudentByIdTest() {
        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");
        Student student = mock();

        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(student));

        Student resultUnderTest = objectUnderTest
                .getStudentById(studentId);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(student);
    }

    @Test
    void updateStudentTest() {
        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");
        Student student = mock();
        StudentData studentData = mock();

        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        objectUnderTest.updateStudent(studentId, studentData);
    }

    @Test
    void deleteStudentTest() {
        UUID studentId = UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff");

        doNothing().when(studentRepository).deleteById(studentId);

        objectUnderTest.deleteStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }
}