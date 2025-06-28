package com.backend.studentRecordSystem.service.teacher;

import com.backend.studentRecordSystem.domain.teacher.CreateTeacherRequest;
import com.backend.studentRecordSystem.domain.teacher.TeacherFactory;
import com.backend.studentRecordSystem.domain.teacher.TeacherResponse;
import com.backend.studentRecordSystem.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherFactory teacherFactory;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherResponse createTeacher(CreateTeacherRequest createTeacherRequest) {
        return teacherMapper.toTeacherDTO(
                teacherFactory.createTeacher(
                        teacherMapper.toTeacherData(createTeacherRequest)
                )
        );
    }
}
