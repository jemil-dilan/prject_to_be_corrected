package com.backend.studentRecordSystem.mapper;

import com.backend.studentRecordSystem.domain.Student;
import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;
import com.backend.studentRecordSystem.dto.StudentData;
import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentMapperTest {

    private final StudentMapper objectUnderTest = Mappers.getMapper(StudentMapper.class);

    @Test
    void toStudentDTOTest() {
        var student = Student.builder()
                .id(UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"))
                .firstName("firstName")
                .lastName("lastName")
                .dateOfBirth(LocalDate.of(2002,12,3))
                .placeOfBirth("douala")
                .gender(Gender.MALE)
                .status(StudentStatus.SCHOOLING)
                .build();
        StudentDTO resultUnderTest = objectUnderTest.toStudentDTO(student);

        assertThat(resultUnderTest)
                .isNotNull()
                .isInstanceOf(StudentDTO.class)
                .hasFieldOrPropertyWithValue("id", UUID.fromString("605057e5-dfe3-4c4e-aebe-cde8209604ff"))
                .hasFieldOrPropertyWithValue("firstName","firstName")
                .hasFieldOrPropertyWithValue("lastName", "lastName")
                .hasFieldOrPropertyWithValue("dateOfBirth",LocalDate.of(2002,12,3))
                .hasFieldOrPropertyWithValue("placeOfBirth", "douala")
                .hasFieldOrPropertyWithValue("gender", Gender.MALE)
                .hasFieldOrPropertyWithValue("status", StudentStatus.SCHOOLING);
    }

    @Test
    void toStudentDataTest() {
        CreateStudentDTO createStudentDTO = CreateStudentDTO.builder()
                .firstName("firstName")
                .lastName("lastName")
                .dateOfBirth(LocalDate.of(2002,12,3))
                .placeOfBirth("douala")
                .status(StudentStatus.SCHOOLING)
                .gender(Gender.MALE)
                .build();

        StudentData resultUnderTest = objectUnderTest.toStudentData(createStudentDTO);

        assertThat(resultUnderTest)
                .isNotNull()
                .isInstanceOf(StudentData.class)
                .hasFieldOrPropertyWithValue("firstName","firstName")
                .hasFieldOrPropertyWithValue("lastName", "lastName")
                .hasFieldOrPropertyWithValue("dateOfBirth",LocalDate.of(2002,12,3))
                .hasFieldOrPropertyWithValue("placeOfBirth", "douala")
                .hasFieldOrPropertyWithValue("gender", Gender.MALE)
                .hasFieldOrPropertyWithValue("status", StudentStatus.SCHOOLING);
    }
}