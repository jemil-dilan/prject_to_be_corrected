package com.backend.studentRecordSystem.factory.parent;

import com.backend.studentRecordSystem.domain.Parent;
import com.backend.studentRecordSystem.dto.ParentData;

import java.util.List;

public interface ParentFactory {
    Parent createStudent(ParentData parentData);
    List<Parent> getAllStudents();
    Parent getStudentById(Long id);
    void updateStudent(Long id, ParentData parentData);
    void deleteStudent(Long id);

    void clearDatabase();
}
