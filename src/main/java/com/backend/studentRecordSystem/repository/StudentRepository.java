package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.student.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    Student save(Student user);
    List<Student> findAll();
    Optional<Student> findById(UUID id);
    void deleteById( UUID id);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    boolean existsById(UUID id);
    void deleteAll();
}



