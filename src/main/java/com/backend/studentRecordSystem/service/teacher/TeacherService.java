package com.backend.studentRecordSystem.service.teacher;

import com.backend.studentRecordSystem.domain.teacher.CreateTeacherRequest;
import com.backend.studentRecordSystem.domain.teacher.TeacherResponse;

public interface TeacherService {
    TeacherResponse createTeacher(CreateTeacherRequest createTeacherRequest);
}
