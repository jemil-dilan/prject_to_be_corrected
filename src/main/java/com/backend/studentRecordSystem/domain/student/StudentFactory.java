package com.backend.studentRecordSystem.domain.student;

import com.backend.studentRecordSystem.dto.StudentData;

import java.util.List;
import java.util.UUID;

public interface StudentFactory {
    Student createStudent(StudentData studentData);
    List<Student> getAllStudents();
    Student getStudentById(UUID id);
    void updateStudent(UUID id, StudentData studentData);
    void deleteStudent(UUID id);

    void clearDatabase();
}
