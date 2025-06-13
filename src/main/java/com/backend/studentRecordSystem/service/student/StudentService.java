package com.backend.studentRecordSystem.service.student;

import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;

import java.util.List;
import java.util.UUID;


public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(UUID id);
    void deleteStudent(UUID id);
    StudentDTO createStudent(CreateStudentDTO createStudentDTO);
    void updateStudent(UUID id, CreateStudentDTO createStudentDTO);
    void clearDatabase();
}
