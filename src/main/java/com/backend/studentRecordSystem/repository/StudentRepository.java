package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.Student;

import java.util.UUID;

public interface StudentRepository extends SpringRepository<Student, UUID> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}



