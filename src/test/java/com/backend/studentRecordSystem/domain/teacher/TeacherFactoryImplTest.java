package com.backend.studentRecordSystem.domain.teacher;

import com.backend.studentRecordSystem.domain.enums.StaffRole;
import com.backend.studentRecordSystem.domain.staff.StaffRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherFactoryImplTest {
    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private TeacherFactoryImpl objectUnderTest;

    @Test
    void createTeacherTest() {
        TeacherData teacherData = mock();
        Teacher teacher = Teacher.builder()
                .firstName(teacherData.firstName())
                .lastName(teacherData.lastName())
                .phoneNumber(teacherData.phoneNumber())
                .email(teacherData.email())
                .address(teacherData.address())
                .dateOfBirth(teacherData.dateOfBirth())
                .idNumber(teacherData.idNumber())
                .gender(teacherData.gender())
                .staffRole(StaffRole.TEACHER)
                .salary(teacherData.salary())
                .status(teacherData.status())
                .yearsOfExperience(teacherData.yearsOfExperience())
                .assignedClass(teacherData.assignedClass())
                .build();

        when(staffRepository.existsByEmail(teacherData.email())).thenReturn(false);
        when(staffRepository.existsByIdNumber(teacherData.idNumber())).thenReturn(false);
        when(staffRepository.existsByPhoneNumber(teacherData.phoneNumber())).thenReturn(false);
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        Teacher resultUnderTest = objectUnderTest.createTeacher(teacherData);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(teacher);
    }

    @Test
    void getAllTeacherTest() {
    }

    @Test
    void getTeacherByIdTest() {
    }

    @Test
    void getTeacherByEmailTest() {
        Teacher teacher = mock(Teacher.class);
        String teacherEmail = "example@go.com";

        when(teacherRepository.findByEmail(teacherEmail)).thenReturn(Optional.of(teacher));

        Teacher resultUnderTest = objectUnderTest.getTeacherByEmail(teacherEmail);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(teacher);
    }

    @Test
    void getTeacherByPhoneNumberTest() {
    }

    @Test
    void updateTeacherTest() {
    }

    @Test
    void deleteTeacherTest() {
    }
}