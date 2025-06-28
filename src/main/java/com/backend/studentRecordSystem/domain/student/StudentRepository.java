package com.backend.studentRecordSystem.domain.student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    Student save(Student student);
    List<Student> findAll();
    Optional<Student> findById(UUID id);
    void deleteById( UUID id);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    boolean existsById(UUID id);
    void deleteAll();
}



