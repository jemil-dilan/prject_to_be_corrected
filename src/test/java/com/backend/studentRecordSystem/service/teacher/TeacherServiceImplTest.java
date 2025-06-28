package com.backend.studentRecordSystem.service.teacher;

import com.backend.studentRecordSystem.domain.teacher.*;
import com.backend.studentRecordSystem.mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {
    @Mock
    private TeacherFactory teacherFactory;

    @Mock
    private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherServiceImpl objectUnderTest;

    @Test
    void createTeacherTest() {
        TeacherData teacherData = mock(TeacherData.class);
        CreateTeacherRequest createTeacherRequest = mock(CreateTeacherRequest.class);
        Teacher teacher = mock(Teacher.class);
        TeacherResponse teacherResponse = mock(TeacherResponse.class);

        when(teacherMapper.toTeacherData(createTeacherRequest)).thenReturn(teacherData);
        when(teacherFactory.createTeacher(teacherData)).thenReturn(teacher);
        when(teacherMapper.toTeacherDTO(teacher)).thenReturn(teacherResponse);

        TeacherResponse resultUnderTest = objectUnderTest.createTeacher(createTeacherRequest);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(teacherResponse);
    }

}