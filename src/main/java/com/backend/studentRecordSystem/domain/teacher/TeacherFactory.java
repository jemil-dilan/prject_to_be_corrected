package com.backend.studentRecordSystem.domain.teacher;

import java.util.List;
import java.util.UUID;

public interface TeacherFactory {
    Teacher createTeacher(TeacherData teacherData);
    List<Teacher> getAllTeacher();
    Teacher getTeacherById(UUID id);
    Teacher getTeacherByEmail(String email);
    Teacher getTeacherByPhoneNumber(String phoneNumber);
    void updateTeacher(UUID id, TeacherData teacherData);
    void deleteTeacher(UUID id);
}
